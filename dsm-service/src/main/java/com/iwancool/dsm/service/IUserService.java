package com.iwancool.dsm.service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.UserModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 
 * @ClassName IUserService
 * @Description TODO  用户service
 * @author huchanghuan
 * @Date 2016年8月29日 上午9:56:00
 * @version 1.0.0
 */
public interface IUserService extends IGenericService {

	/**
	 * 保存更新user
	 * @Description (TODO
	 * @param user
	 * @return
	 */
	public ResultResp saveOrUpdate(UserModel user);
	
	/**
	 * 删除user
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deleteUser(long id);
	
	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idsStr
	 * @return
	 */
	public ResultResp deleteBatchUser(String idsStr);
	
	/**
	 * id查找user
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public UserModel getUser(long id);
	
	/**
	 * 根据用户状态，关键词查找并分页
	 * @Description (TODO
	 * @param status
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<UserModel> findUserList(int status, String keyword, int offset, int limit);

	/**
	 * 更新状态
	 * @Description (TODO
	 * @param id
	 * @param status
	 * @return
	 */
	public ResultResp updateStatus(long id, int status);
	
}
