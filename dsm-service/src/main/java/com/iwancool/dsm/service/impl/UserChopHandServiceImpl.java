package com.iwancool.dsm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserChopHandDao;
import com.iwancool.dsm.service.IUserChopHandService;

/**
 * 剁手达人Serice实现类
 * @author hch
 *
 */
@Service(value = "userChopHandService")
public class UserChopHandServiceImpl extends AbstractBaseService implements IUserChopHandService{

	@Resource(name = "userChopHandDao")
	private IUserChopHandDao userChopHandDao;
	
	@Override
	public ResultResp saveBatchUserChopHand(List<Map<String, Object>> chopHandUserList, int date) {
		userChopHandDao.saveBatchUserChopHand(chopHandUserList, date);
		return ResultResp.SUCCESS;
	}

	
}
