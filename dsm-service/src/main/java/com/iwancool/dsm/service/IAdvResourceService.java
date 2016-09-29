package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.AdvResourceModel;

/**
 * 广告资源
 * @ClassName IAdvResourceService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午2:01:06
 * @version 1.0.0
 */
public interface IAdvResourceService extends IGenericService {

	/**
	 * 保存更新
	 * @Description (TODO
	 * @param advResource
	 * @return
	 */
	public ResultResp saveOrUpdate(AdvResourceModel advResource);
	
	/**
	 * 删除广告资源
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deleteAdvResource(long id);
	
	/**
	 * 得到广告资源
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public AdvResourceModel getAdvResource(long id);
	
	/**
	 * 查找广告位的资源
	 * @Description (TODO
	 * @param locationId
	 * @return
	 */
	public List<AdvResourceModel> findAdvResource(long locationId);
}
