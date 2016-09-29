package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.dao.IOrderDao;
import com.iwancool.dsm.domain.OrderModel;

/**
 * 订单实现类
 * @ClassName OrderDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:39:37
 * @version 1.0.0
 */
@Repository(value = "orderDao")
public class OrderDaoImpl extends AbstractBaseGenericORMDaoImpl<OrderModel, Long> implements IOrderDao{

	private static final long serialVersionUID = 4978521445351229834L;

	@Override
	public int findOrderListCount(List<Integer> statusList, String keyword) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(id) FROM OrderModel WHERE 1 = 1");
		//Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != statusList && !statusList.isEmpty()) {
			hql.append(" AND status IN (:status)");
			//params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (cast(id as string) LIKE :id OR phone LIKE :phone)");
			//String kw = "%" + keyword + "%";
			//params.put("id", kw);
			//params.put("phone", kw);
		}
		
		Query query = getCurrentSession().createQuery(hql.toString());
		
		if (null != statusList && !statusList.isEmpty()) 
			query.setParameterList("status", statusList);
		
		if (!StringUtils.isEmpty(keyword)) {
			String kw = "%" + keyword + "%";
			query.setParameter("id", kw);
			query.setParameter("phone", kw);
		}
		return ((Long)query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderModel> findOrderList(List<Integer> statusList, String keyword, int currPage, int limit) {
		StringBuffer hql = new StringBuffer("FROM OrderModel WHERE 1 = 1");
		//Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != statusList && !statusList.isEmpty()) {
			hql.append(" AND status IN (:status)");
			//params.put("status", status);
		}
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (cast(id as string) LIKE :id OR phone LIKE :phone)");
			//String kw = "%" + keyword + "%";
			//params.put("id", kw);
			//params.put("phone", kw);
		}
		hql.append(" ORDER BY id DESC");
		
		Query query = getCurrentSession().createQuery(hql.toString());
		
		if (null != statusList && !statusList.isEmpty()) 
			query.setParameterList("status", statusList);
		
		if (!StringUtils.isEmpty(keyword)) {
			String kw = "%" + keyword + "%";
			query.setParameter("id", kw);
			query.setParameter("phone", kw);
		}
		
		return query.setFirstResult((currPage - 1) * limit).setMaxResults(limit).list();
	}

	@Override
	public void updateBatchOrder(List<Long> idList, int status) {
		String hql = "UPDATE OrderModel SET status = :status WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public void deleteBatchOrder(List<Long> idList) {
		String hql = "DELETE FROM OrderModel WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	
}
