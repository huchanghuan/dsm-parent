package com.iwancool.dsm.service;

import java.util.List;
import java.util.Map;

import com.iwancool.dsm.common.ResultResp;

/**
 * 剁手达人Service
 * @author hch
 *
 */
public interface IUserChopHandService extends IGenericService {

	/**
	 * 批量保存
	 * @param chopHandUserList
	 * @param date 
	 * @return
	 */
	public ResultResp saveBatchUserChopHand(List<Map<String, Object>> chopHandUserList, int date);

}
