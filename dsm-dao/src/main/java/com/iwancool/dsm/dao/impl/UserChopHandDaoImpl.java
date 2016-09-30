package com.iwancool.dsm.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IUserChopHandDao;
import com.iwancool.dsm.domain.UserChopHandModel;

/**
 * 剁手达人dao实现类
 * @author hch
 *
 */
@Repository(value = "userChopHandDao")
public class UserChopHandDaoImpl extends AbstractBaseGenericORMDaoImpl<UserChopHandModel, Integer> implements IUserChopHandDao{

	/*@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void saveBatchUserChopHand(List<Map<String, Object>> chopHandUserList, int date) {
		Session session = getCurrentSession();
		//Transaction tx = session.getTransaction();
		
		for (int i = 0, len = chopHandUserList.size(); i < len; i++) {
			Map<String, Object> map = chopHandUserList.get(i);
			long userId = ((BigInteger) map.get("userId")).longValue();
			int saleFrequency = ((Long)map.get("saleFrequency")).intValue();
			UserChopHandModel userChopHand = new UserChopHandModel();
			userChopHand.setDate(date);
			userChopHand.setSaleFrequency(saleFrequency);
			userChopHand.setUserId(userId);
			session.save(userChopHand);
			
			if (i != 0 && i % 50 == 0) {
				session.flush();
				session.clear();
				//tx.commit();
				//tx = session.beginTransaction();
			}
		}
		
		//tx.commit();
	}
	
	

}
