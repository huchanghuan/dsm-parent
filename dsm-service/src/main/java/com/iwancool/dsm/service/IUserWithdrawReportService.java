package com.iwancool.dsm.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.UserWithdrawReportModel;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;

/**
 * 用户提现报表Service
 * @author hch
 *
 */
public interface IUserWithdrawReportService extends IGenericService{

	/**
	 * 保存
	 * @param objs
	 * @return
	 */
	public ResultResp saveBatch(List<Object[]> objs);
	
	/**
	 * 查找显示
	 * @return
	 */
	public DataGrid<WithdrawReportBean> findWithdrawReportList(int offset, int limit); 

	/**
	 * 导出支付宝转账Excel
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 */
	public void exportAliPayExcel(HttpServletRequest request, HttpServletResponse response, int date, String batchNo);

	/**
	 * 导入支付宝提现Excel
	 * @Description (TODO
	 * @param inputStream
	 * @return
	 */
	public ResultResp importAliPayExcel(InputStream inputStream);

	/**
	 * 查找指定天、批次的提现记录
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<UserWithdrawReportModel> findUserWithdrawReportList(int date, String batchNo, int offset,
			int limit);

}
