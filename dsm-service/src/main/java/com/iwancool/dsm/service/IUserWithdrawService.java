package com.iwancool.dsm.service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.UserWithDrawModel;

/**
 * 提现记录 service
 * @ClassName IUserWithdrawService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月5日 下午4:00:48
 * @version 1.0.0
 */
public interface IUserWithdrawService extends IGenericService{

	/**
	 * 保存提现记录
	 * @Description (TODO
	 * @param userWithdraw
	 * @return
	 */
	public ResultResp saveUserWithdraw(UserWithDrawModel userWithdraw);
}
