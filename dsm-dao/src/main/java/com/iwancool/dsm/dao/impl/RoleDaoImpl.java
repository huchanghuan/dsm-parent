package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IRoleDao;
import com.iwancool.dsm.domain.RoleModel;

@Repository(value = "roleDao")
public class RoleDaoImpl extends AbstractBaseGenericORMDaoImpl<RoleModel, Long> implements IRoleDao{

	private static final long serialVersionUID = 5778067898747713411L;

	@Override
	public List<RoleModel> findRoleAllList() {
		String  hql = "FROM RoleModel";
		return find(hql);
	}

}
