package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.ManagerModel;
import com.iwancool.dsm.domain.PermissionModel;
import com.iwancool.dsm.utils.bean.DataGrid;


public interface IManagerService extends IGenericService{

	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResultResp login(String username, String password);
	
	/**
	 * 根据用户名查找
	 * @param username
	 * @return
	 */
	public ManagerModel getManager(String username);
	
	/**
	 * 保存或更新
	 * @param manager
	 * @return
	 */
	public ResultResp saveOrUpdate(ManagerModel manager);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ResultResp deleteManager(long id);

	/**
	 * 根据管理员账号查找对应的权限
	 * @Description (TODO
	 * @param username
	 * @return
	 */
	public List<PermissionModel> findManagerPermissionList(String username);

	/**
	 * 查找管理员并分页
	 * @Description (TODO
	 * @param status
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<ManagerModel> findManagerList(int status, int offset, int limit);

	/**
	 * 更新状态
	 * @param id
	 * @param status
	 * @return
	 */
	public ResultResp updateStatus(long id, int status);

	/**
	 * manager
	 * @param id
	 * @return
	 */
	public ManagerModel getManager(long id);

	
}
