package com.iwancool.dsm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserAccountDao;
import com.iwancool.dsm.domain.OrderModel;
import com.iwancool.dsm.domain.UserAccountModel;
import com.iwancool.dsm.domain.UserWithDrawModel;
import com.iwancool.dsm.service.IOrderService;
import com.iwancool.dsm.service.IUserAccountService;
import com.iwancool.dsm.service.IUserWithdrawService;
import com.iwancool.dsm.utils.bean.AccountStatementBean;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.util.ExcelUtils;

/**
 * 用户账号service实现类
 * @author hch
 *
 */
@Service(value = "userAccountService")
public class UserAccountServiceImpl extends AbstractBaseService implements IUserAccountService{

	@Resource(name = "userAccountDao")
	private IUserAccountDao userAccountDao;
	
	@Resource(name = "orderService")
	private IOrderService orderService;
	
	@Resource(name = "userWithdrawService")
	private IUserWithdrawService userWithdrawService;
	
	@Override
	public UserAccountModel getUserAccount(long userId) {
		log.debug("getUserAccount -> " + userId);
		return userAccountDao.getByPrimaryKey(userId);
	}

	@Override
	public ResultResp saveOrUpdateUserAccount(UserAccountModel userAccount) {
		log.debug("saveOrUpdateUserAccount -> " + userAccount);
		userAccountDao.saveOrUpdate(userAccount);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBatchUserAccount(String userAccountStr) {
		if (StringUtils.isEmpty(userAccountStr)) 
			return ResultResp.ERROR;
		
		List<Long> idList = new ArrayList<Long>();
		for (String str : userAccountStr.split(",")) {
			idList.add(Long.valueOf(str));
		}
		userAccountDao.deleteBatchUserAccount(idList);
		return ResultResp.SUCCESS;
	}

	@Override
	public DataGrid<UserAccountModel> findUserAccountList(String keyword,
			int offset, int limit) {
		List<UserAccountModel> userAccountList = new ArrayList<UserAccountModel>();
		int totalSize = userAccountDao.findUserAccountListCount(keyword);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			userAccountList = userAccountDao.findUserAccountList(keyword,currPage, limit);
		}
		return new DataGrid<UserAccountModel>(totalSize, userAccountList);
	}

	@Override
	public ResultResp updateBatchBalance(List<Long> userIdList,
			List<Integer> balanceList) {
		if (null == userIdList || null == balanceList 
				|| balanceList.isEmpty() || userIdList.isEmpty())
			return ResultResp.ERROR;
		
		ResultResp resultResp = ResultResp.SUCCESS;
		for (int i=0,len = userIdList.size(); i < len; i++) {
			UserAccountModel userAccount = userAccountDao.getByPrimaryKey(userIdList.get(i));
			if (null == userAccount) {
				resultResp = ResultResp.ERROR;
				break;
			}
			userAccount.setBalance(userAccount.getBalance() + balanceList.get(i));
			userAccountDao.update(userAccount);
		}
		
		return resultResp;
	}

	@Override
	public ResultResp updateBatchPay(String orderIdsStr, String userIdsStr, String balancesStr) {
		log.debug("updateBatchPay -> userIdsStr:" + userIdsStr + ",balancesStr:" + balancesStr);
		if (StringUtils.isEmpty(userIdsStr) || StringUtils.isEmpty(balancesStr))
			return ResultResp.ERROR;
		
		String[] userArr = userIdsStr.split(",");
		String[] balanceArr = balancesStr.split(",");
		if (userArr.length != balanceArr.length)
			return ResultResp.ERROR;
		
		List<Long> userIdList = new ArrayList<Long>();
		List<Integer> balanceList = new ArrayList<Integer>();
		for (int i = 0,len =balanceArr.length;  i < len; i++) {
			userIdList.add(Long.valueOf(userArr[i]));
			balanceList.add(Integer.valueOf(balanceArr[i]));
		}
		ResultResp resultResp = updateBatchBalance(userIdList, balanceList);
		
		if (ResultResp.isSuccess(resultResp))
			resultResp = orderService.updateBatchOrder(orderIdsStr, OrderModel.CANCEL);
		return resultResp;
	}

	@Override
	public void exportWithdrawData(HttpServletRequest request, HttpServletResponse response, String idsStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String title = sdf.format(new Date()) + "DSM提现表";					//标题
		List<String> columnNames = getColumnNames();						//列标题
		List<List<Object>> contents = new ArrayList<List<Object>>();
		
		List<UserAccountModel> userAccountList = userAccountDao.findUserAccountList(null, 0, 0);
		if (null != userAccountList) {
			for (int i = 0, len = userAccountList.size(); i < len; i++) {
				List<Object> content = new ArrayList<Object>();
				UserAccountModel userAccount = userAccountList.get(i);
	
				content.add(i+1);										//序号
				content.add(userAccount.getAlipayNo());				//支付宝
				content.add((userAccount.getWithdraw() * 1.0)/100);				//提现金额
				
				contents.add(content);								//放入contents
			}
		}
		
		ResultResp resultResp = updateBatchUserAccount(userAccountList);
		if (ResultResp.isSuccess(resultResp));
			ExcelUtils.exportExcel(request, response, contents, columnNames, title);		
	}

	@Override
	public ResultResp updateBatchUserAccount(List<UserAccountModel> userAccountList) {
		if (null == userAccountList || userAccountList.isEmpty())
			return ResultResp.ERROR;
		
		ResultResp resultResp = ResultResp.SUCCESS;
		for (UserAccountModel userAccountModel : userAccountList) {
			UserWithDrawModel userWithDrawModel = new UserWithDrawModel();
			userWithDrawModel.setAmount(userAccountModel.getWithdraw());
			userWithDrawModel.setBalance(userAccountModel.getBalance());
			userWithDrawModel.setUserId(userAccountModel.getId());
			userWithDrawModel.setUtc(new Date().getTime());
			
			//保存提现记录
			resultResp = userWithdrawService.saveUserWithdraw(userWithDrawModel);
			//扣除用户账号的提现款项
			if (ResultResp.isSuccess(resultResp)) {
				userAccountModel.setWithdraw(0);
				userAccountDao.update(userAccountModel);
			}
				
		}
		
		return resultResp;
		
	}
	
	/**
	 * 获得列头
	 * @Description (TODO
	 * @return
	 */
	private List<String> getColumnNames() {
		List<String> columnNames = new ArrayList<String>();
		Collections.addAll(columnNames, "编号", "支付宝账号", "提现金额");
		return columnNames;
	}

	@Override
	public ResultResp updateBatchUserWithdraw(int day, List<AccountStatementBean> accountStatementList) {
		if (null != accountStatementList && !accountStatementList.isEmpty())
				return ResultResp.ERROR;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long startUtc = sdf.parse(String.valueOf(day)).getTime(); 
			long endUtc = startUtc + 24 * 60 * 60 * 1000;
			for (AccountStatementBean accountStatement : accountStatementList) {
				userAccountDao.updateUserWithdraw(startUtc, endUtc, accountStatement.getUserId(), accountStatement.getStatus());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResultResp.ERROR;
	}

}
