package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IUserWithdrawRecordDao;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;

/**
 * 用户提现申请dao实现类
 * @author hch
 *
 */
@Repository(value = "userWithdrawRecordDao")
public class UserWithdrawRecordDaoImpl extends AbstractBaseGenericORMDaoImpl<UserWithdrawRecordModel, Integer> implements IUserWithdrawRecordDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Object[]> findUserWithdrawRecordListByUtc(long startUtc, long endUtc) {
		String sql = "select user_id, SUM(pay_amount) from user_withdraw_record where utc >= :startUtc and utc < :endUtc group by user_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startUtc", startUtc);
		params.put("endUtc", endUtc);
		return findBySql(sql, params);
	}

	@Override
	public void updateBatchUserWithdrawRecord(List<Long> idList, int status, long startUtc, long endUtc) {
		if (null != idList && !idList.isEmpty()) {
			String hql = "UPDATE UserWithdrawRecordModel SET status = :status WHERE userId IN (:idList) and utc >= :startUtc and utc < :endUtc";
			Query query = getCurrentSession().createQuery(hql);
			query.setParameter("status", status);
			query.setParameterList("idList", idList);
			query.setParameter("startUtc", startUtc);
			query.setParameter("endUtc", endUtc);
			
			query.executeUpdate();
		}
	}

}
