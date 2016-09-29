package com.iwancool.dsm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IAdvResourceDao;
import com.iwancool.dsm.domain.AdvResourceModel;
import com.iwancool.dsm.service.IAdvResourceService;

/**
 * 广告资源service实现类
 * @ClassName AdvResourceServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午2:02:31
 * @version 1.0.0
 */
@Service(value = "advResourceService")
public class AdvResourceServiceImpl extends AbstractBaseService implements IAdvResourceService {

	@Resource(name = "advResourceDao")
	private IAdvResourceDao advResourceDao;
	
	@Override
	public ResultResp saveOrUpdate(AdvResourceModel advResource) {
		if (advResource.getId() <= 0)
			advResource.setCreateUtc(new Date().getTime());
		advResourceDao.saveOrUpdate(advResource);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteAdvResource(long id) {
		advResourceDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public AdvResourceModel getAdvResource(long id) {
		
		return advResourceDao.getByPrimaryKey(id);
	}

	@Override
	public List<AdvResourceModel> findAdvResource(long locationId) {
		log.debug("findAdvResource -> locationId:" + locationId);
		
		return advResourceDao.findAdvResource(locationId);
	}

}
