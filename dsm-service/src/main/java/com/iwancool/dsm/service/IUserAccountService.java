package com.iwancool.dsm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.UserAccountModel;
import com.iwancool.dsm.utils.bean.AccountStatementBean;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 用户账号
 * @author hch
 *
 */
public interface IUserAccountService extends IGenericService{

	/**
	 * 根据ID得到
	 * @param userId
	 * @return
	 */
	public UserAccountModel getUserAccount(long userId);
	
	/**
	 * 保存更新
	 * @param userAccount
	 * @return
	 */
	public ResultResp saveOrUpdateUserAccount(UserAccountModel userAccount);
	
	/**
	 * 批量删除
	 * @param idsStr
	 * @return
	 */
	public ResultResp deleteBatchUserAccount(String idsStr);
	
	/**
	 * 查找
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<UserAccountModel> findUserAccountList(String keyword, int offset, int limit);

	/**
	 * 批量更新
	 * @param userIdList
	 * @param balanceList
	 * @return
	 */
	public ResultResp updateBatchBalance(List<Long> userIdList,List<Integer> balanceList);

	/**
	 * 批量更新余额
	 * @param orderIdsStr
	 * @param userIdsStr
	 * @param balancesStr
	 * @return
	 */
	public ResultResp updateBatchPay(String orderIdsStr, String userIdsStr, String balancesStr);

	/**
	 * 导出提现报表
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @param idsStr
	 * @return 
	 * @throws Exception 
	 */
	public void exportWithdrawData(HttpServletRequest request, HttpServletResponse response, String idsStr) throws Exception;

	/**
	 * 更新用户账号当前提现金额
	 * @Description (TODO
	 * @param userAccountList
	 * @return
	 */
	public ResultResp updateBatchUserAccount(List<UserAccountModel> userAccountList);
	
	/**
	 * 根据支付宝返回的记录结果，批量更新用户余额信息
	 * @Description (TODO
	 * @param accountStatementList
	 * @return
	 */
	public ResultResp updateBatchUserWithdraw(int day, List<AccountStatementBean> accountStatementList);
}
