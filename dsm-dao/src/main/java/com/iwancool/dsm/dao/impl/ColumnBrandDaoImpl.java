package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IColumnBrandDao;
import com.iwancool.dsm.domain.ColumnBrandModel;

/**
 * 栏目品牌dao实现类
 * @author hch
 *
 */
@Repository(value = "columnBrandDao")
public class ColumnBrandDaoImpl extends AbstractBaseGenericORMDaoImpl<ColumnBrandModel, Integer> implements IColumnBrandDao{

	private static final long serialVersionUID = -2049134109824482847L;

	@Override
	public List<ColumnBrandModel> findColumnBrandList(String brandName) {
		String hql = "FROM ColumnBrandModel WHERE brandName = :brandName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("brandName", brandName);
		return find(hql, params);
	}

}
