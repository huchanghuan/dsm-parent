package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IPermissionDao;
import com.iwancool.dsm.domain.PermissionModel;
import com.iwancool.dsm.domain.RoleModel;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;


/**
 * 
 * @ClassName PermissionServiceImpl
 * @Description TODO  权限service实现类
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:31:02
 * @version 1.0.0
 */
@Service(value = "permissionService")
public class PermissionServiceImpl implements IPermissionService{

	@Resource(name= "permissionDao")
	private IPermissionDao permissionDao;
	
	@Resource(name = "roleService")
	private IRoleService roleService;

	@Override
	public ResultResp saveOrUpdate(PermissionModel permission) {
		
		permissionDao.saveOrUpdate(permission);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deletePermission(long id) {
		permissionDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public List<PermissionModel> findPermissionList(String permissionStr) {
		List<PermissionModel> permissionList = new ArrayList<PermissionModel>();
		if (!StringUtils.isEmpty(permissionStr)){
			
			String[] permissionIds = permissionStr.split("/");
			for (String str : permissionIds) {
				PermissionModel permission = permissionDao.getByPrimaryKey(Long.valueOf(str));
				permissionList.add(permission);
			}
		}
		
		//排序
		Collections.sort(permissionList, new Comparator<PermissionModel>() {

			@Override
			public int compare(PermissionModel o1, PermissionModel o2) {
				
				return ((Long)(o1.getId() - o2.getId())).intValue();
			}
		
		});
		return permissionList;
	}

	@Override
	public List<JSONObject> findPermissionListByRoleId(long roleId) {
		RoleModel role = roleService.getRole(roleId);
		List<JSONObject> treeNodes = new ArrayList<JSONObject>();
		if (null != role) {
			String[] permissionArrays = role.getPermissionStr().split("/");
			List<PermissionModel> permissionList = findAllPermissionList();
			if (null != permissionList) {
				List<String> ownList = Arrays.asList(permissionArrays);
				//
				for (PermissionModel permissionModel : permissionList) {
					JSONObject json = new JSONObject();
					//选中
					
					if (ownList.contains(String.valueOf(permissionModel.getId()))) {
						json.put("checked", true);
					}
					//打开
					if (permissionModel.getPid() == 1) {
						json.put("open", true);
					}
					json.put("id", permissionModel.getId());
					json.put("pId", permissionModel.getPid());
					json.put("name", permissionModel.getName());
					
					//放入treeNodes
					treeNodes.add(json);
				}
			}
		}
		return treeNodes;
	}

	@Override
	public List<PermissionModel> findAllPermissionList() {
		
		return permissionDao.findAllPermissionList();
	}
	
}
