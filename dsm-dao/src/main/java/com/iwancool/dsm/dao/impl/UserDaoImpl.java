package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.dao.IUserDao;
import com.iwancool.dsm.domain.UserModel;

/**
 * userDao 实现类
 * @ClassName UserDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 上午10:24:59
 * @version 1.0.0
 */
@Repository(value = "userDao")
public class UserDaoImpl extends AbstractBaseGenericORMDaoImpl<UserModel, Long> implements IUserDao{

	private static final long serialVersionUID = -6362373147327874816L;

	@Override
	public UserModel getUserByPhone(String phone) {
		String hql = "FROM UserModel WHERE phone = :phone";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		return get(hql, params);
	}

	@Override
	public UserModel getUserByEmail(String email) {
		String hql = "FROM UserModel WHERE email = :email";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		return get(hql, params);
	}

	@Override
	public void deleteBatchUser(List<Long> idList) {
		String hql = "DELETE FROM UserModel WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public int findUserListCount(int status, String keyword) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(id) FROM UserModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (status > -1) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (phone LIKE :phone OR nickname LIKE :nickname)");
			params.put("phone", "%"+keyword+"%");
			params.put("nickname", "%"+keyword+"%");
		}
		
		return count(hql.toString(), params).intValue();
	}

	@Override
	public List<UserModel> findUserList(int status, String keyword, int currPage, int limit) {
		StringBuffer hql = new StringBuffer("FROM UserModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (status > -1) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (phone LIKE :phone OR nickname LIKE :nickname)");
			params.put("phone", "%"+keyword+"%");
			params.put("nickname", "%"+keyword+"%");
		}
		hql.append(" ORDER BY id DESC");
		return find(hql.toString(), params, currPage, limit);
	}

	
}
