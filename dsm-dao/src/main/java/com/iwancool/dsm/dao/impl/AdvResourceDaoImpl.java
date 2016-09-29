package com.iwancool.dsm.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IAdvResourceDao;
import com.iwancool.dsm.domain.AdvResourceModel;

/**
 * 广告资源dao实现类
 * @ClassName AdvResourceDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午2:06:43
 * @version 1.0.0
 */
@Repository(value = "advResourceDao")
public class AdvResourceDaoImpl extends AbstractBaseGenericORMDaoImpl<AdvResourceModel, Long> implements IAdvResourceDao {

	private static final long serialVersionUID = 6100664892151261502L;

	@Override
	public List<AdvResourceModel> findAdvResource(long locationId) {
		String hql = "FROM AdvResourceModel where locationId = :locationId ORDER BY sortNo DESC, id DESC";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("locationId", locationId);
		return find(hql, params);
	}


}
