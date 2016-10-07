package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.dao.IUserAccountDao;
import com.iwancool.dsm.domain.UserAccountModel;
import com.iwancool.dsm.utils.bean.AccountStatementBean;

/**
 * 用户账号dao实现类
 * @author hch
 *
 */
@Repository(value = "userAccountDao")
public class UserAccountDaoImpl extends AbstractBaseGenericORMDaoImpl<UserAccountModel, Long> implements IUserAccountDao{

	private static final long serialVersionUID = -9181297383339537752L;

	@Override
	public void deleteBatchUserAccount(List<Long> idList) {
		String hql = "DELETE FROM UserAccountModel WHERE id IN (:idList)";
		Query query =getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public int findUserAccountListCount(String keyword) {
		StringBuffer hql = new StringBuffer("SELECT COUNT(id) FROM UserAccountModel WHERE withdraw != 0");
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (CAST(id as string) LIKE :id OR realname LIKE :realname)");
		}
		
		Query query = getCurrentSession().createQuery(hql.toString());
		
		if (!StringUtils.isEmpty(keyword)) {
			String kw = "%" + keyword + "%";
			query.setParameter("id", kw);
			query.setParameter("realname", kw);
		}
		return ((Long)query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAccountModel> findUserAccountList(String keyword,
			int currPage, int limit) {
		StringBuffer hql = new StringBuffer("FROM UserAccountModel WHERE withdraw != 0");
		
		if (!StringUtils.isEmpty(keyword)) {
			hql.append(" AND (CAST(id as string) LIKE :id OR realname LIKE :realname)");
		}
		
		Query query = getCurrentSession().createQuery(hql.toString());
		
		if (!StringUtils.isEmpty(keyword)) {
			String kw = "%" + keyword + "%";
			query.setParameter("id", kw);
			query.setParameter("realname", kw);
		}
		
		if (currPage == 0 && limit == 0) return query.list();
		return query.setFirstResult((currPage - 1) * limit).setMaxResults(limit).list();
	}

	@Override
	public void updateUserWithdraw(long startUtc, long endUtc, long userId, int status) {
		StringBuffer sql = new StringBuffer();
		if (status == AccountStatementBean.SUCCESS) {
			//支付宝转账成功-更新待提现金额
			sql.append("update user_account ua"); 
			sql.append(" set ua.withdraw = ua.withdraw - (select sum(uwr.request_amount) totalAmount from user_withdraw_record uwr where uwr.user_id = ua.id and utc >= :startUtc and utc < :endUtc)");
			sql.append(" where ua.id = :userId");
		} else if (status == AccountStatementBean.FAIL) {
			//支付宝转账失败-更新提现金额到剁手猫账号
			sql.append("update user_account ua"); 
			sql.append(" set ua.withdraw = ua.withdraw - (select sum(uwr.request_amount) totalAmount from user_withdraw_record uwr where uwr.user_id = ua.id and utc >= :startUtc and utc < :endUtc),");
			sql.append(" ua.balance = ua.balance + (select sum(uwr.request_amount) totalAmount from user_withdraw_record uwr where uwr.user_id = ua.id and utc >= :startUtc and utc < :endUtc)");
			sql.append(" where ua.id = :userId");
		}
		
		if (0 != sql.length()) {
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
			sqlQuery.setParameter("startUtc", startUtc);
			sqlQuery.setParameter("endUtc", endUtc);
			sqlQuery.setParameter("userId", userId);
			sqlQuery.executeUpdate();
		}
	}

	
}
