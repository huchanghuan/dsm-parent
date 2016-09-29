package com.iwancool.dsm.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserWithdrawReportDao;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;
import com.iwancool.dsm.domain.UserWithdrawReportModel;
import com.iwancool.dsm.service.IUserAccountService;
import com.iwancool.dsm.service.IUserWithdrawRecordService;
import com.iwancool.dsm.service.IUserWithdrawReportService;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.bean.MerchantsRecordBean;
import com.iwancool.dsm.utils.bean.WithdrawExportBean;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;
import com.iwancool.dsm.utils.bean.WithdrawResultBean;
import com.iwancool.dsm.utils.util.ExcelUtils;

/**
 * 提款报表Service实现类
 * @author hch
 *
 */
@Service(value = "userWithdrawReportService")
public class UserWithdrawReportServiceImpl extends AbstractBaseService implements IUserWithdrawReportService{

	private static final int MAX_WITHDRAW_NUM = 3000;      //支付宝一次最多处理的提款数
	private static final String ALIPAY_ACCOUNT = "alipay_account";
	private static final String ALIPAY_USERNAME = "alipay_username";
	
	private static final String DEFAULT_ALIPAY_ACCOUNT = "iwancool@126.com";
	private static final String DEFAULT_ALIPAY_USERNAME = "深圳恒兴方圆科技有限公司";
	
	@Resource(name = "config")
	private Properties config;
	
	@Resource(name = "userWithdrawReportDao")
	private IUserWithdrawReportDao userWithdrawReportDao;
	
	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;
	
	@Resource(name = "userWithdrawRecordService")
	private IUserWithdrawRecordService userWithdrawRecordService;

	@Override
	public ResultResp saveBatch(List<Object[]> objs) {
		if (null == objs) 
				return ResultResp.ERROR;
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long batchNo = Long.valueOf(sdf.format(new Date()) + "001");
			for (int i = 0, len = objs.size(); i < len; i++) {
				long batchNum = batchNo + len/MAX_WITHDRAW_NUM;
				UserWithdrawReportModel userWithdrawReport = new UserWithdrawReportModel();
				
				Object[] objArrays = objs.get(i);
				userWithdrawReport.setUserId(((BigInteger)objArrays[0]).longValue());
				userWithdrawReport.setBatchNo(String.valueOf(batchNum));
				userWithdrawReport.setStatus(UserWithdrawReportModel.NOT_EXPORT);
				userWithdrawReport.setDate(Integer.valueOf(sdf.format(new Date())));
				userWithdrawReport.setAmount(((BigDecimal)objArrays[1]).intValue());
				
				userWithdrawReportDao.save(userWithdrawReport);
			}
		
		return ResultResp.SUCCESS;
	}

	@Override
	public DataGrid<WithdrawReportBean> findWithdrawReportList(int offset, int limit) {
		List<WithdrawReportBean> list = new ArrayList<WithdrawReportBean>();
		int totalSize = userWithdrawReportDao.findWithdrawReportListCount();
		if (totalSize > 0) {
			list = userWithdrawReportDao.findWithdrawReportList(offset, limit);
		}
		return new DataGrid<WithdrawReportBean>(totalSize, list);
	}

	@Override
	public void exportAliPayExcel(HttpServletRequest request, HttpServletResponse response, int date, String batchNo) {
		List<MerchantsRecordBean> exportDataList = userWithdrawReportDao.findWithdrawExportList(date, batchNo);
		int totalAmount = userWithdrawReportDao.findToatlAmount(date, batchNo);
		
		if (null != exportDataList && !exportDataList.isEmpty()) {
			WithdrawExportBean withdrawExport = new WithdrawExportBean();
			withdrawExport.setBatchNo(batchNo);
			withdrawExport.setPayDay(String.valueOf(date));
			withdrawExport.setDrawee(config.getProperty(ALIPAY_ACCOUNT, DEFAULT_ALIPAY_ACCOUNT));
			withdrawExport.setAccountName(config.getProperty(ALIPAY_USERNAME, DEFAULT_ALIPAY_USERNAME));
			withdrawExport.setTotalAmount(totalAmount);
			withdrawExport.setTotalNum(exportDataList.size());
			
			withdrawExport.setMerchantsRecordList(exportDataList);
			
			boolean exportResult = ExcelUtils.exportAliPayExcel(request, response, withdrawExport, date + "-" + batchNo + "剁手猫提现excel");
			if (exportResult) {
				List<Long> userIdList = new ArrayList<Long>();
				for (MerchantsRecordBean merchants : exportDataList) {
					userIdList.add(Long.valueOf(merchants.getUserId()));
				}
				//更新提现记录表状态
				userWithdrawRecordService.updateBatchUserWithdrawRecord(userIdList, UserWithdrawRecordModel.WITHDRAW_SUCCESS, date);
				//更新提现报表状态
				userWithdrawReportDao.updateBatchStatus(date, batchNo, UserWithdrawReportModel.WITHDRAW);
			}
		}
	}

	@Override
	public ResultResp importAliPayExcel(InputStream inputStream) {
		//1、读取结果文件
		WithdrawResultBean withdrawResult = ExcelUtils.readAliPayExcel(inputStream);
		if (null == withdrawResult || null == withdrawResult.getStatementList() 
				|| withdrawResult.getStatementList().isEmpty()){
			return ResultResp.ERROR;
		}
		
		ResultResp resultResp = null;
		//2、更新账户余额
		int day = Integer.valueOf(withdrawResult.getBatchNo().substring(0, 7));
		List<Long> userIdList = new ArrayList<Long>();
		for (Long userId : userIdList) {
			userIdList.add(userId);
		}
		resultResp = userAccountService.updateBatchUserWithdraw(day, withdrawResult.getStatementList());
		if (!ResultResp.isSuccess(resultResp)) 
			return resultResp;
		//3、更新提款记录状态
		resultResp = userWithdrawRecordService.updateBatchUserWithdrawRecord(userIdList, UserWithdrawRecordModel.WITHDRAW_SUCCESS, day);
		if (!ResultResp.isSuccess(resultResp)) 
			return resultResp;
		//4、更新提款报表状态
		userWithdrawReportDao.updateBatchStatus(day, withdrawResult.getBatchNo(), UserWithdrawReportModel.COMPLETE);
		return resultResp;
	}

	@Override
	public DataGrid<UserWithdrawReportModel> findUserWithdrawReportList(int date, String batchNo, int offset,
			int limit) {
		List<UserWithdrawReportModel> userWithdrawReportList = new ArrayList<UserWithdrawReportModel>();
		int totalSize = userWithdrawReportDao.findWithdrawReportListCount(date, batchNo);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			userWithdrawReportList = userWithdrawReportDao.findWithdrawReportList(date, batchNo, currPage, limit);
		}
		return new DataGrid<UserWithdrawReportModel>(totalSize, userWithdrawReportList);
	}

}
