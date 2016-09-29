package com.iwancool.dsm.dao;

import java.util.List;


import com.iwancool.dsm.domain.UserWithdrawReportModel;
import com.iwancool.dsm.utils.bean.MerchantsRecordBean;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;

/**
 * 用户提现报表Dao
 * @author hch
 *
 */
public interface IUserWithdrawReportDao extends IGeneralORMDao<UserWithdrawReportModel, Integer>{

	/**
	 * 提款报表
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<WithdrawReportBean> findWithdrawReportList(int offset, int limit);

	/**
	 * 根据日期、批次号查找
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 * @return
	 */
	public List<MerchantsRecordBean> findWithdrawExportList(int date, String batchNo);

	/**
	 * 提现报表记录数
	 * @Description (TODO
	 * @return
	 */
	public int findWithdrawReportListCount();

	/**
	 * 指定日期、批次的总金额
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 * @return
	 */
	public int findToatlAmount(int date, String batchNo);

	/**
	 * 根据date、批次更细状态
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 */
	public void updateBatchStatus(int date, String batchNo, int status);

	/**
	 * 查找指定天、批次的记录数
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 * @return
	 */
	public int findWithdrawReportListCount(int date, String batchNo);

	/**
	 * 查找指定天、批次的记录
	 * @Description (TODO
	 * @param date
	 * @param batchNo
	 * @return
	 */
	public List<UserWithdrawReportModel> findWithdrawReportList(int date, String batchNo, int currPage, int limit);
}
