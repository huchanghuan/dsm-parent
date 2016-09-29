package com.iwancool.dsm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IRoleDao;
import com.iwancool.dsm.domain.RoleModel;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;

/**
 * 角色service实现类
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:24:24
 * @version 1.0.0
 */
@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService{
	
	@Resource(name = "roleDao")
	private IRoleDao roleDao;
	
	@Resource(name = "permissionService")
	private IPermissionService permissionService;

	@Override
	public RoleModel getRole(long id) {
		
		return roleDao.getByPrimaryKey(id);
	}

	@Override
	public ResultResp saveOrUpdate(RoleModel role) {
		roleDao.saveOrUpdate(role);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteRole(long id) {
		roleDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public List<RoleModel> findRoleAllList() {
		return roleDao.findRoleAllList();
	}

	@Override
	public ResultResp savePermission(long roleId, String permissionStr) {
		RoleModel role = roleDao.getByPrimaryKey(roleId);
		if (null == role) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "角色不存在");
			
		role.setPermissionStr(permissionStr);
		roleDao.save(role);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp updateRoleName(long roleId, String roleName) {
		RoleModel role = getRole(roleId);
		if (null == role) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "角色不存在");
			
		role.setRoleName(roleName);
		roleDao.update(role);
		return ResultResp.SUCCESS;
	}

}
