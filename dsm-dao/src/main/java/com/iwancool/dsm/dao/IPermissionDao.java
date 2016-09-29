package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.PermissionModel;

/**
 * permissionDao 
 * @ClassName IPermissionDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:43:02
 * @version 1.0.0
 */
public interface IPermissionDao extends IGeneralORMDao<PermissionModel, Long>{

	/**
	 * 找所有
	 * @Description (TODO
	 * @return
	 */
	public List<PermissionModel> findAllPermissionList();

	
}
