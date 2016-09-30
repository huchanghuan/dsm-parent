package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;

/**
 * 用户提现申请记录
 * @author hch
 *
 */
public interface IUserWithdrawRecordService extends IGenericService{

	/**
	 * 保存或者更新
	 * @param userWithdrawRecord
	 * @return
	 */
	public ResultResp saveOrUpdate(UserWithdrawRecordModel userWithdrawRecord);
	
	/**
	 * 批量更新
	 * @param userIdList
	 * @param status
	 * @return
	 */
	public ResultResp updateBatchUserWithdrawRecord(List<Long> userIdList, int status, int day);
	
	
	/**
	 * 查记录
	 * @param startUtc
	 * @param endUtc
	 * @return
	 */
	public List<Object[]> findUserWithdrawRecordListByUtc(long startUtc, long endUtc);

	/**
	 * 查提现记录
	 * @param startUtc
	 * @param endUtc
	 * @param batchNo
	 * @return
	 */
	public List<UserWithdrawRecordModel> findUserWithdrawRecordListByUtc(long startUtc, long endUtc,
			String batchNo);

}
