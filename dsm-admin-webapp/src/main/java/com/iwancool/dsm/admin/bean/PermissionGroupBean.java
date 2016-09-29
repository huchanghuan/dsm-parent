package com.iwancool.dsm.admin.bean;

import java.io.Serializable;
import java.util.List;

import com.iwancool.dsm.domain.PermissionModel;

/**
 * 权限组类
 * @ClassName PermissionGroup
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月25日 下午9:39:53
 * @version 1.0.0
 */
public class PermissionGroupBean implements Serializable{

	private static final long serialVersionUID = 2416408438869087962L;

	private long GroupId;
	
	private String groupName;
	
	private String className;
	
	private List<PermissionModel> permissionList;

	public long getGroupId() {
		return GroupId;
	}

	public void setGroupId(long groupId) {
		GroupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<PermissionModel> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionModel> permissionList) {
		this.permissionList = permissionList;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
