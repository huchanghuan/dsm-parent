package com.iwancool.dsm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

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
	
	public static final int MAX_WITHDRAW_NUM = 3000;      //支付宝一次最多处理的提款数

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
	 * @return 
	 */
	public Map<String, Object> exportAliPayExcel(HttpServletRequest request, HttpServletResponse response, int date, String batchNo);

	/**
	 * 导入支付宝提现Excel
	 * @Description (TODO
	 * @param file
	 * @return
	 */
	public ResultResp importAliPayExcel(MultipartFile file);

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
