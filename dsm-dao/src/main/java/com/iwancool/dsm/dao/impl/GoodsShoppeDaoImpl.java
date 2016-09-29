package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IGoodsShoppeDao;
import com.iwancool.dsm.domain.GoodsShoppeModel;

/**
 * 专柜实现类
 * @author hch
 *
 */
@Repository(value = "goodsShoppeDao")
public class GoodsShoppeDaoImpl extends AbstractBaseGenericORMDaoImpl<GoodsShoppeModel, Integer> implements IGoodsShoppeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<GoodsShoppeModel> findAllGoodsShoppe() {
		String hql = "FROM GoodsShoppeModel ORDER BY ordinal DESC, id DESC";
		return find(hql);
	}

	@Override
	public void updateStatus(List<Integer> idList, int status) {
		String hql = "UPDATE GoodsShoppeModel SET status = :status WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public void updateSort(List<Integer> idList, List<Integer> sortNoList) {
		if (null != idList && !idList.isEmpty()
				&& null != sortNoList && !sortNoList.isEmpty()) {
			StringBuffer sql = new StringBuffer();
			sql.append("update goods_shoppe set ordinal = case id");
			for (int i = 0, len = idList.size(); i < len; i++) {
				sql.append(" when id = ").append(idList.get(i)).append(" then ").append(sortNoList.get(i));
			}
			sql.append(" end");
			sql.append(" where id in (:idList)");
			
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
			sqlQuery.setParameterList("idList", idList);
			sqlQuery.executeUpdate();
		}
			
	}

	@Override
	public int getMaxId() {
		String hql = "SELECT MAX(id) FROM GoodsShoppeModel";
		Query query = getCurrentSession().createQuery(hql);
		Integer num = (Integer) query.uniqueResult();
		return null == num ? 0 : num;
	}

}
