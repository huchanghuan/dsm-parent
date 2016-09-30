package com.iwancool.dsm.dao;

import java.util.List;
import java.util.Map;

import com.iwancool.dsm.domain.UserChopHandModel;

/**
 * 剁手达人dao
 * @author hch
 *
 */
public interface IUserChopHandDao extends IGeneralORMDao<UserChopHandModel, Integer>{

	/**
	 * 批量保存
	 * @param chopHandUserList
	 * @param date
	 */
	public void saveBatchUserChopHand(List<Map<String, Object>> chopHandUserList, int date);

}
