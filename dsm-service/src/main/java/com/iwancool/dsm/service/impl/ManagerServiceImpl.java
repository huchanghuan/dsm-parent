package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IManagerDao;
import com.iwancool.dsm.domain.ManagerModel;
import com.iwancool.dsm.domain.PermissionModel;
import com.iwancool.dsm.domain.RoleModel;
import com.iwancool.dsm.service.IManagerService;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 管理员service
 * @ClassName ManagerServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月26日 下午2:17:18
 * @version 1.0.0
 */
@Service(value = "managerService")
public class ManagerServiceImpl implements IManagerService{
	
	private static final Logger log = Logger.getLogger(ManagerServiceImpl.class);

	@Resource(name = "managerDao")
	private IManagerDao managerDao;
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	@Resource(name = "permissionService")
	private IPermissionService permissionService;

	@Override
	public ResultResp login(String username, String password) {
		log.debug("login -> username:" + username + ",password:" + password);
		ManagerModel manager = managerDao.getManager(username);
		if (null == manager) {
			return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "账号不存在");
		} 
		
		if (!password.equals(manager.getPassword())) {
			return ResultResp.createResult(ResultCode.ERROR, "密码错误");
		}
		
		if (manager.getStatus() > ManagerModel.ENABLE_STATUS) {
			return ResultResp.createResult(ResultCode.ERROR, "状态异常");
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ManagerModel getManager(String username) {
		log.debug("getManager -> username:" + username);
		return managerDao.getManager(username);
	}

	@Override
	public ResultResp saveOrUpdate(ManagerModel manager) {
		log.debug("saveOrUpdate -> manager:" + manager);
		ManagerModel managerModel = getManager(manager.getUsername());
		if (null != managerModel && manager.getId() != managerModel.getId()) {
			return ResultResp.createResult(ResultCode.NAME_HAS_EXITS, "账号已存在");
		}
		
		if (manager.getId() > 0) {
			ManagerModel managerUpdate = managerDao.getByPrimaryKey(manager.getId());
			if (null == managerUpdate) {
				return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
			}
			managerUpdate.setEmail(manager.getEmail());
			managerUpdate.setPassword(manager.getPassword());
			managerUpdate.setPhoneNo(manager.getPhoneNo());
			managerUpdate.setRoleId(manager.getRoleId());
			managerUpdate.setUsername(manager.getUsername());
			managerDao.update(managerUpdate);
		} else {
			manager.setUtc(new Date().getTime());
			managerDao.save(manager);
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteManager(long id) {
		log.debug("deleteManager -> id:" + id);
		managerDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public List<PermissionModel> findManagerPermissionList(String username) {
		log.debug("findManagerPermissionList -> username:" + username);
		List<PermissionModel> permissionList = new ArrayList<PermissionModel>();
		ManagerModel manager = managerDao.getManager(username);
		if (null != manager && manager.getRoleId() > 0) {
			RoleModel role = roleService.getRole(manager.getRoleId());
			if (null != role && !StringUtils.isEmpty(role.getPermissionStr())) {
				permissionList = permissionService.findPermissionList(role.getPermissionStr());
 			}
		}
		return permissionList;
	}

	@Override
	public DataGrid<ManagerModel> findManagerList(int status, int offset, int limit) {
		log.debug("findManagerList -> status:" + status + ",offset:" + offset + ",limit:" + limit);
		List<ManagerModel> managerList = new ArrayList<ManagerModel>();
		int totalSize = managerDao.findManagerListCount(status);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			managerList = managerDao.findManagerList(status, currPage, limit);
		}
		return new DataGrid<ManagerModel>(totalSize, managerList);
	}

	@Override
	public ResultResp updateStatus(long id, int status) {
		ManagerModel manager = managerDao.getByPrimaryKey(id);
		if (null == manager) {
			return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "对象不存在");
		}
		
		manager.setStatus(status);
		managerDao.update(manager);
		return ResultResp.SUCCESS;
	}

	@Override
	public ManagerModel getManager(long id) {
		
		return managerDao.getByPrimaryKey(id);
	}

		
	
}
