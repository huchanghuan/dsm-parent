package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.ManagerModel;

public interface IManagerDao extends IGeneralORMDao<ManagerModel, Long>{

	/**
	 * 根据名称查找
	 * @param username
	 * @return
	 */
	public ManagerModel getManager(String username);

	/**
	 * 查找记录数
	 * @param status
	 * @return
	 */
	public int findManagerListCount(int status);

	/**
	 * 查找记录
	 * @param status
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<ManagerModel> findManagerList(int status, int currPage, int limit);

}
