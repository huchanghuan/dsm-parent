package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.dao.IBrandDao;
import com.iwancool.dsm.domain.BrandModel;

/**
 * brand dao实现类
 * @ClassName BrandDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午3:46:33
 * @version 1.0.0
 */
@Repository(value = "brandDao")
public class BrandDaoImpl extends AbstractBaseGenericORMDaoImpl<BrandModel, String> implements IBrandDao{

	private static final long serialVersionUID = 634323810370395104L;

	@Override
	public List<Object[]> findBrandList(int columnId, int status, String keyword, int currPage, int limit) {
		StringBuffer sql = new StringBuffer("select b.name name1,b.initial,b.pinyin,b.visible,c.id,group_concat(c.name) name2 from brand b,column_brand cb,`column` c where b.name = cb.brand_name and cb.column_id = c.id and c.visible = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (columnId > 0) {
			sql.append(" and cb.column_id = :columnId");
			params.put("columnId", columnId);
		}
		
		if (status > -1) {
			sql.append(" and b.visible = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			sql.append(" and b.name LIKE :name");
			params.put("name", "%" + keyword + "%");
		}
		sql.append(" group by b.name");
		return findBySql(sql.toString(), params, currPage, limit);
	}
	
	@Override
	public int findBrandListCount(int columnId, int status, String keyword) {
		//指定columnId的记录，并为可见
		StringBuffer sql = new StringBuffer("select COUNT(distinct b.name) from brand b,column_brand cb,`column` c where b.name = cb.brand_name and cb.column_id = c.id and c.visible = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (columnId > 0) {
			sql.append(" and cb.column_id = :columnId");
			params.put("columnId", columnId);
		}
		
		if (status > -1) {
			sql.append(" and b.visible = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			sql.append(" and b.name LIKE :name");
			params.put("name", "%" + keyword + "%");
		}
		return countBySql(sql.toString(), params).intValue();
	}

	@Override
	public void deleteBacthBrand(List<String> idList) {
		String hql = "DELETE FROM BrandModel WHERE name IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

}
