package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.UserWithdrawRecordModel;

/**
 * 用户提现申请dao
 * @author hch
 *
 */
public interface IUserWithdrawRecordDao  extends IGeneralORMDao<UserWithdrawRecordModel, Integer>{

	/**
	 * 查询申请提款记录
	 * @param startUtc
	 * @param endUtc
	 * @return
	 */
	public List<Object[]> findUserWithdrawRecordListByUtc(long startUtc, long endUtc);

	/**
	 * 批量更新状态
	 * @Description (TODO
	 * @param idList
	 * @param status
	 * @param startUtc
	 * @param endUtc
	 */
	public void updateBatchUserWithdrawRecord(List<Long> userIdList, int status, long startUtc, long endUtc);

	/**
	 * 提现记录
	 * @param startUtc
	 * @param endUtc
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<UserWithdrawRecordModel> findUserWithdrawRecordListByUtc(long startUtc, long endUtc, int offset,
			int limit);

}
