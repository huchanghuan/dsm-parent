package com.iwancool.dsm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserWithdrawDao;
import com.iwancool.dsm.domain.UserWithDrawModel;
import com.iwancool.dsm.service.IUserWithdrawService;

/**
 * 用户提现service实现类
 * @ClassName UserWithdrawServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月5日 下午7:08:52
 * @version 1.0.0
 */
@Service(value = "userWithdrawService")
public class UserWithdrawServiceImpl extends AbstractBaseService implements IUserWithdrawService{

	@Resource(name = "userWithdrawDao")
	private IUserWithdrawDao userWithdrawDao;
	
	@Override
	public ResultResp saveUserWithdraw(UserWithDrawModel userWithdraw) {
		log.debug("saveUserWithdraw -> " + userWithdraw);
		userWithdrawDao.save(userWithdraw);
		return ResultResp.SUCCESS;
	}

}
