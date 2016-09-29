package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.RoleModel;

/**
 * 角色dao
 * @ClassName IRoleDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:25:36
 * @version 1.0.0
 */
public interface IRoleDao extends IGeneralORMDao<RoleModel, Long>{

	/**
	 * 找所有
	 * @return
	 */
	public  List<RoleModel> findRoleAllList();

}
