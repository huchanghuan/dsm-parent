package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.dao.IGoodsDao;
import com.iwancool.dsm.domain.GoodsModel;

/**
 * goods dao实现类
 * @ClassName GoodsDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:56:45
 * @version 1.0.0
 */
@Repository(value = "goodsDao")
public class GoodsDaoImpl extends AbstractBaseGenericORMDaoImpl<GoodsModel, Long> implements IGoodsDao{

	private static final long serialVersionUID = -4152238431600103016L;
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void deleteBatchGoods(List<Long> idList) {
		String hql = "DELETE FROM GoodsModel WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public int findGoodsListCount(int columnId, int categoryId, int status, String keyword) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(id) FROM GoodsModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (columnId > 0) {
			hql.append(" AND columnId = :columnId");
			params.put("columnId", columnId);
		}
		
		if (categoryId > 0) {
			hql.append(" AND categoryId = :categoryId");
			params.put("categoryId", categoryId);
		}
		
		if (categoryId > 0) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND name LIKE :name");
			params.put("name", "%" + keyword + "%");
		}
		return count(hql.toString(), params).intValue();
	}

	@Override
	public List<GoodsModel> findGoodsList(int columnId, int categoryId, int status, String keyword, int currPage,
			int limit) {
		StringBuffer hql = new StringBuffer("FROM GoodsModel WHERE 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (columnId > 0) {
			hql.append(" AND columnId = :columnId");
			params.put("columnId", columnId);
		}
		
		if (categoryId > 0) {
			hql.append(" AND categoryId = :categoryId");
			params.put("categoryId", categoryId);
		}
		
		if (status > -1) {
			hql.append(" AND status = :status");
			params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND name LIKE :name");
			params.put("name", "%" + keyword + "%");
		}
		hql.append(" ORDER BY rcmLevel DESC, id DESC");
		
		return find(hql.toString(), params, currPage, limit);
	}

	@Override
	public List<Map<String, Object>> findChopHandUser(long startUtc, long endUtc, int size) {
		String sql = "select user_id userId, count(id) saleFrequency from goods where pub_utc >= ? and pub_utc < ? group by user_id order by saleFrequency desc limit ?";
		return jdbcTemplate.queryForList(sql, startUtc, endUtc, size);
	}

	
}
