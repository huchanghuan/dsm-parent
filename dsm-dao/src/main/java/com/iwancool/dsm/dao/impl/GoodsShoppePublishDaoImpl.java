package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IGoodsShoppePublishDao;
import com.iwancool.dsm.domain.GoodsShoppePublishModel;

/**
 * 商品专柜Dao实现类
 * @author hch
 *
 */
@Repository(value = "goodsShoppePublishDao")
public class GoodsShoppePublishDaoImpl extends AbstractBaseGenericORMDaoImpl<GoodsShoppePublishModel, Integer> implements IGoodsShoppePublishDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteGoodsShoppePublishByShoppeId(int shoppeId) {
		String hql = "DELETE FROM GoodsShoppePublishModel WHERE shoppeId = :shoppeId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shoppeId", shoppeId);
		executeHql(hql, params);
		
	}

	@Override
	public void deleteBatchGoodsShoppePublish(List<Integer> idList) {
		String hql = "DELETE FROM GoodsShoppePublishModel WHERE idx IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public int findGoodsShoppePublishList(int shoppeId) {
		String hql = "SELECT COUNT(idx) FROM GoodsShoppePublishModel WHERE shoppeId = :shoppeId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shoppeId", shoppeId);
		return count(hql, params).intValue();
	}

	@Override
	public List<GoodsShoppePublishModel> findGoodsShoppePublishList(int shoppeId, int currPage, int limit) {
		String hql = "FROM GoodsShoppePublishModel WHERE shoppeId = :shoppeId ORDER BY idx DESC";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shoppeId", shoppeId);
		return find(hql, params, currPage, limit);
	}

}
