package com.iwancool.dsm.service;


import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.RoleModel;

/**
 * 
 * @ClassName IRoleService
 * @Description TODO  权限角色
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:19:51
 * @version 1.0.0
 */
public interface IRoleService extends IGenericService{

	/**
	 * 获得角色信息
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public RoleModel getRole(long id);
	
	/**
	 * 保存或者更新角色
	 * @Description (TODO
	 * @param role
	 * @return
	 */
	public ResultResp saveOrUpdate(RoleModel role);
	
	/**
	 * 删除角色
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deleteRole(long id);
	
	/**
	 * 找所有角色
	 * @return
	 */
	public  List<RoleModel> findRoleAllList();

	/**
	 * 保存权限字符串
	 * @Description (TODO
	 * @param roleId
	 * @param permissionStr
	 * @return
	 */
	public ResultResp savePermission(long roleId, String permissionStr);
	
	/**
	 * 更新角色名
	 * @Description (TODO
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	public ResultResp updateRoleName(long roleId, String roleName);
}
