package com.iwancool.dsm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.dao.IIdGenerateDao;
import com.iwancool.dsm.domain.IdGenerateModel;
import com.iwancool.dsm.service.IIdGenerateService;

/**
 * ID生成
 * @ClassName IdGenerateServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 上午11:05:05
 * @version 1.0.0
 */
@Service(value = "idGenerateService")
public class IdGenerateServiceImpl extends AbstractBaseService implements IIdGenerateService{

	@Resource(name = "idGenerateDao")
	private IIdGenerateDao idGenerateDao; 
	
	@Override
	public long generateID(String key) {
		long nextId = 0;
		try{
			IdGenerateModel idGenerateModel = idGenerateDao.getByPrimaryKey(key);
			nextId = idGenerateModel.getUid() + 1;
			idGenerateModel.setUid(nextId);
			idGenerateDao.update(idGenerateModel);
		}catch (Exception e) {
			log.debug("get " + key + " id fail");
			e.printStackTrace();
		}
		return nextId;
	}

}
