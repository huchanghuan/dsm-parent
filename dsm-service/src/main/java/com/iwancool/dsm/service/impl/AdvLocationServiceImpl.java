package com.iwancool.dsm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IAdvLocationDao;
import com.iwancool.dsm.domain.AdvLocationModel;
import com.iwancool.dsm.service.IAdvLocationService;

/**
 * 广告位service实现类
 * @ClassName AdvLocationServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午11:25:41
 * @version 1.0.0
 */
@Service(value = "advLocationService")
public class AdvLocationServiceImpl extends AbstractBaseService implements IAdvLocationService{

	@Resource(name = "advLocationDao")
	private IAdvLocationDao advLocationDao;
	
	@Override
	public ResultResp saveOrUpdate(AdvLocationModel advLocation) {
		if (advLocation.getId() <= 0){
			advLocation.setCreateUtc(new Date().getTime());
			advLocationDao.save(advLocation);
		} else {
			AdvLocationModel advLocationModel = advLocationDao.getByPrimaryKey(advLocation.getId());
			if (null == advLocationModel) 
				return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
		
			advLocationModel.setName(advLocation.getName());
			advLocationDao.update(advLocationModel);
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteAdvlocation(long id) {
		advLocationDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public AdvLocationModel getAdvLocation(long id) {
		
		return advLocationDao.getByPrimaryKey(id);
	}

	@Override
	public List<AdvLocationModel> findAllAdvlocation() {
		
		return advLocationDao.findAllAdvlocation();
	}

	@Override
	public ResultResp updateStatus(long locationId, int status) {
		AdvLocationModel advLocation = advLocationDao.getByPrimaryKey(locationId);
		if (null == advLocation) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在"); 
			
		advLocation.setStatus(status);
		advLocationDao.update(advLocation);
		return ResultResp.SUCCESS;
	}

}
