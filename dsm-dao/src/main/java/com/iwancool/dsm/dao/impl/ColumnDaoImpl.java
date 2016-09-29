package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IColumnDao;
import com.iwancool.dsm.domain.ColumnModel;

/**
 * 类别dao实现类
 * @ClassName ColumnDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:08:29
 * @version 1.0.0
 */
@Repository(value = "columnDao")
public class ColumnDaoImpl extends AbstractBaseGenericORMDaoImpl<ColumnModel, Integer> implements IColumnDao{

	private static final long serialVersionUID = 1854864096461949621L;

	@Override
	public List<ColumnModel> findAll(int status) {
		StringBuffer hql = new StringBuffer("FROM ColumnModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (status > -1) {
			hql.append(" AND visible = :status");
			params.put("status", status);
		}
		return find(hql.toString(), params);
	}

	@Override
	public ColumnModel getColumn(String name) {
		String hql = "FROM ColumnModel WHERE name = :name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return get(hql, params);
	}

}
