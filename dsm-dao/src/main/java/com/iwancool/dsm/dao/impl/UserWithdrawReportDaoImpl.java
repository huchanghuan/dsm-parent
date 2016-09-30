package com.iwancool.dsm.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IUserWithdrawReportDao;
import com.iwancool.dsm.domain.UserWithdrawReportModel;
import com.iwancool.dsm.utils.bean.MerchantsRecordBean;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;

/**
 * 用户提现报表Dao实现类
 * @author hch
 *
 */
@Repository(value = "userWithdrawReportDao")
public class UserWithdrawReportDaoImpl extends AbstractBaseGenericORMDaoImpl<UserWithdrawReportModel, Integer> implements IUserWithdrawReportDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<WithdrawReportBean> findWithdrawReportList(int offset, int limit) {
		String sql = "select uwr.date date, COUNT(uwr.idx) userNum, SUM(uwr.amount) withdrawAmount, MAX(uwr.status) statuc, uwr.batch_no batchNo from user_withdraw_report uwr group by uwr.date, uwr.batch_no order by uwr.idx limit " + offset+ "," + limit;
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(WithdrawReportBean.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<MerchantsRecordBean> findWithdrawExportList(int date, String batchNo) {
		String sql = "select uwr.user_id userId, ua.alipay_no payee, ua.realname payeeName, uwr.amount from user_withdraw_report uwr inner join user_account ua on uwr.user_id = ua.id and uwr.status = 0 and uwr.date = ? and uwr.batch_no = ?";
		
		return jdbcTemplate.query(sql, new Object[]{date,batchNo}, new BeanPropertyRowMapper(MerchantsRecordBean.class));
	}

	@Override
	public int findWithdrawReportListCount() {
		String sql = "select count(uwr.idx) from user_withdraw_report uwr group by uwr.date, uwr.batch_no";
		return countBySql(sql).intValue();
	}

	@Override
	public int findToatlAmount(int date, String batchNo) {
		String sql = "select SUM(uwr.amount) from user_withdraw_report uwr where uwr.date = :date and uwr.batch_no = :batchNo";
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		sqlQuery.setParameter("date", date);
		sqlQuery.setParameter("batchNo", batchNo);
		
		return ((BigDecimal) sqlQuery.uniqueResult()).intValue();
	}

	@Override
	public void updateBatchStatus(int date, String batchNo, int status) {
		String hql = "UPDATE UserWithdrawReportModel set status = :status WHERE date = :date AND batchNo = :batchNo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		params.put("batchNo", batchNo);
		params.put("status", status);
		executeHql(hql, params);
	}

	@Override
	public int findWithdrawReportListCount(int date, String batchNo) {
		String hql = "SELECT COUNT(idx) FROM UserWithdrawReportModel WHERE date = :date AND batchNo = :batchNo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		params.put("batchNo", batchNo);
		return count(hql, params).intValue();
	}

	@Override
	public List<UserWithdrawReportModel> findWithdrawReportList(int date, String batchNo, int currPage, int limit) {
		String hql = "FROM UserWithdrawReportModel WHERE date = :date AND batchNo = :batchNo ORDER BY idx";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		params.put("batchNo", batchNo);
		return find(hql, params, currPage, limit);
	}

	
}
