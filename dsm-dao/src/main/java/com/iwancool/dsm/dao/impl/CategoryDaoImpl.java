package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.ICategoryDao;
import com.iwancool.dsm.domain.CategoryModel;

/**
 * dao实现类
 * @ClassName CategoryDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:45:40
 * @version 1.0.0
 */
@Repository(value = "categoryDao")
public class CategoryDaoImpl extends AbstractBaseGenericORMDaoImpl<CategoryModel, Integer> implements ICategoryDao{

	private static final long serialVersionUID = 5686666410618179798L;

	@Override
	public List<CategoryModel> findCategoryListByPid(int columnId) {
		String hql = "FROM CategoryModel WHERE pid = :pid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", columnId);
		return find(hql, params);
	}

	@Override
	public CategoryModel getCategory(String name) {
		String hql = "FROM CategoryModel WHERE name = :name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return get(hql, params);
	}

	@Override
	public int getMaxId(int pid) {
		String hql = "SELECT MAX(id) FROM CategoryModel WHERE pid = :pid";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("pid", pid);
		Object obj = query.uniqueResult();
		return null == obj?0:(Integer)obj;
	}

}
