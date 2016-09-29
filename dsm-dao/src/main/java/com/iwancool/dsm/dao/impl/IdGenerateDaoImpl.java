package com.iwancool.dsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.IIdGenerateDao;
import com.iwancool.dsm.domain.IdGenerateModel;

/**
 * id dao实现类
 * @ClassName IdGenerateDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 上午11:09:54
 * @version 1.0.0
 */
@Repository(value = "idGenerateDao")
public class IdGenerateDaoImpl extends AbstractBaseGenericORMDaoImpl<IdGenerateModel, String> implements IIdGenerateDao{

	private static final long serialVersionUID = -6890895043456081244L;


}
