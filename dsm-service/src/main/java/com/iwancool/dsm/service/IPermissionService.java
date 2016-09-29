package com.iwancool.dsm.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.PermissionModel;

/**
 * 
 * @ClassName IPermissionService
 * @Description TODO  权限service
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:29:37
 * @version 1.0.0
 */
public interface IPermissionService extends IGenericService{

	/**
	 * 保存更新
	 * @Description (TODO
	 * @param permission
	 * @return
	 */
	public ResultResp saveOrUpdate(PermissionModel permission);
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deletePermission(long id);
	
	/**
	 * 权限字符串查找
	 * @Description (TODO
	 * @param permissionStr
	 * @return
	 */
	public List<PermissionModel> findPermissionList(String permissionStr);

	/**
	 * 
	 * @Description (TODO
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findPermissionListByRoleId(long roleId);
	
	/**
	 * 查所有权限
	 * @Description (TODO
	 * @return
	 */
	public List<PermissionModel> findAllPermissionList();
}
