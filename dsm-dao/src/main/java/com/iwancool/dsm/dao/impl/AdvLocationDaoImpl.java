package com.iwancool.dsm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IAdvLocationDao;
import com.iwancool.dsm.domain.AdvLocationModel;

/**
 * 广告位dao实现类
 * @ClassName AdvLocationDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午11:30:16
 * @version 1.0.0
 */
@Repository(value = "advLocationDao")
public class AdvLocationDaoImpl extends AbstractBaseGenericORMDaoImpl<AdvLocationModel, Long> implements IAdvLocationDao{

	private static final long serialVersionUID = 7721408511798210112L;

	@Override
	public List<AdvLocationModel> findAllAdvlocation() {
		String hql = "FROM AdvLocationModel ORDER BY id DESC";
		return find(hql);
	}

}
