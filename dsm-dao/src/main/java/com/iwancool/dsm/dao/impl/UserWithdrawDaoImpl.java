package com.iwancool.dsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IUserWithdrawDao;
import com.iwancool.dsm.domain.UserWithDrawModel;

/**
 * 用户提现dao实现类
 * @ClassName UserWithdrawDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月5日 下午7:12:46
 * @version 1.0.0
 */
@Repository(value = "userWithdrawDao")
public class UserWithdrawDaoImpl extends AbstractBaseGenericORMDaoImpl<UserWithDrawModel, Integer> implements IUserWithdrawDao{

	private static final long serialVersionUID = -6087467418237752820L;

}
