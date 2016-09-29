package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.UserAccountModel;

/**
 * 用户账号dao
 * @author hch
 *
 */
public interface IUserAccountDao extends IGeneralORMDao<UserAccountModel, Long>{

	/**
	 * 批量删除
	 * @param idList
	 */
	public void deleteBatchUserAccount(List<Long> idList);

	/**
	 * 查记录数
	 * @param keyword
	 * @return
	 */
	public int findUserAccountListCount(String keyword);

	/**
	 * 查记录
	 * @param keyword
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<UserAccountModel> findUserAccountList(String keyword, int currPage,
			int limit);

	/**
	 * 更新账户金额
	 * @Description (TODO
	 * @param startUtc
	 * @param endUtc
	 * @param userId
	 * @param status
	 */
	public void updateUserWithdraw(long startUtc, long endUtc, long userId, int status);

}
