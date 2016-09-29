package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.AdvLocationModel;

/**
 * 广告位dao
 * @ClassName IAdvLocationDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午11:27:08
 * @version 1.0.0
 */
public interface IAdvLocationDao extends IGeneralORMDao<AdvLocationModel, Long>{

	public List<AdvLocationModel> findAllAdvlocation();

}
