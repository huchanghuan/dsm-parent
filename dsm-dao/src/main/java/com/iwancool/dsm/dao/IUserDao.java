package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.UserModel;

/**
 * userDao数据访问接口
 * @ClassName IUserDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 上午10:23:23
 * @version 1.0.0
 */
public interface IUserDao extends IGeneralORMDao<UserModel, Long>{

	/**
	 * 根据手机号查找
	 * @Description (TODO
	 * @param phone
	 * @return
	 */
	public UserModel getUserByPhone(String phone);

	/**
	 * 根据邮箱查找
	 * @Description (TODO
	 * @param email
	 * @return
	 */
	public UserModel getUserByEmail(String email);

	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idList
	 */
	public void deleteBatchUser(List<Long> idList);

	/**
	 * 查找记录数
	 * @Description (TODO
	 * @param status
	 * @param keyword
	 * @return
	 */
	public int findUserListCount(int status, String keyword);

	/**
	 * 查找记录
	 * @Description (TODO
	 * @param status
	 * @param keyword
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<UserModel> findUserList(int status, String keyword, int currPage, int limit);

}
