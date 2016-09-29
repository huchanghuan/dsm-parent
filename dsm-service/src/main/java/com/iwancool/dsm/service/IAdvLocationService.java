package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.AdvLocationModel;

/**
 * 广告位service
 * @ClassName IAdvLocationService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午11:24:53
 * @version 1.0.0
 */
public interface IAdvLocationService extends IGenericService{

	/**
	 * 保存更新
	 * @Description (TODO
	 * @param advlocation
	 * @return
	 */
	public ResultResp saveOrUpdate(AdvLocationModel advlocation);
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deleteAdvlocation(long id);
	
	/**
	 * 获得
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public AdvLocationModel getAdvLocation(long id);
	
	/**
	 * 查所有
	 * @Description (TODO
	 * @return
	 */
	public List<AdvLocationModel> findAllAdvlocation();

	/**
	 * 更新状态
	 * @Description (TODO
	 * @param locationId
	 * @param status
	 * @return
	 */
	public ResultResp updateStatus(long locationId, int status);
}
