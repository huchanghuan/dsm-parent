package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.AdvResourceModel;

/**
 * 广告资源dao
 * @ClassName IAdvResourceDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午2:04:11
 * @version 1.0.0
 */
public interface IAdvResourceDao extends IGeneralORMDao<AdvResourceModel, Long>{

	/**
	 * 根据广告位查找
	 * @Description (TODO
	 * @param locationId
	 * @return
	 */
	public List<AdvResourceModel> findAdvResource(long locationId);

}
