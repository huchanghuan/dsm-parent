package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IPermissionDao;
import com.iwancool.dsm.domain.PermissionModel;

/**
 * 权限dao实现类
 * @ClassName PermissionDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月23日 下午9:44:44
 * @version 1.0.0
 */
@Repository(value = "permissionDao")
public class PermissionDaoImpl extends AbstractBaseGenericORMDaoImpl<PermissionModel, Long> implements IPermissionDao{

	private static final long serialVersionUID = 8947755217009131043L;

	@Override
	public List<PermissionModel> findAllPermissionList() {
		String hql = "FROM PermissionModel";
		return find(hql);
	}

}
