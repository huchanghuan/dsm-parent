package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IManagerDao;
import com.iwancool.dsm.domain.ManagerModel;

@Repository(value = "managerDao")
public class ManagerDaoImpl extends AbstractBaseGenericORMDaoImpl<ManagerModel, Long> implements IManagerDao{

	private static final long serialVersionUID = -7198746117538778725L;

	@Override
	public ManagerModel getManager(String username) {
		String hql = "FROM ManagerModel WHERE username = :username";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		return get(hql, params);
	}

	@Override
	public int findManagerListCount(int status) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(id) FROM ManagerModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (status > 0) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		return count(hql.toString(), params).intValue();
	}

	@Override
	public List<ManagerModel> findManagerList(int status, int currPage, int limit) {
		StringBuffer hql = new StringBuffer("SELECT new ManagerModel(id, username, phoneNo, email, roleId, status, utc, roleName) FROM ManagerModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		if (status > 0) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		return find(hql.toString(), params, currPage, limit);
	}

}
