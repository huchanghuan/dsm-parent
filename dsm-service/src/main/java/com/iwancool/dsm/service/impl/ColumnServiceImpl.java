package com.iwancool.dsm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IColumnDao;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.service.IColumnService;

/**
 * 一级分类servcie 实现类
 * @ClassName ColumnServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午4:19:21
 * @version 1.0.0
 */
@Service(value = "columnService")
public class ColumnServiceImpl implements IColumnService{
	
	@Resource(name = "columnDao")
	private IColumnDao columnDao;

	@Override
	public List<ColumnModel> findAll(int status) {
		return columnDao.findAll(status);
	}

	@Override
	public ResultResp saveOrUpdate(ColumnModel column) {
		columnDao.saveOrUpdate(column);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteColumn(int id) {
		columnDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public ColumnModel getColumn(int id) {
		
		return columnDao.getByPrimaryKey(id);
	}

	@Override
	public ResultResp updateName(int id, String name) {
		ColumnModel column = columnDao.getColumn(name);
		
		if (null != column && column.getId() != id) return ResultResp.createResult(ResultCode.NAME_HAS_EXITS, "名称已存在");
		
		ColumnModel columnModel = columnDao.getByPrimaryKey(id);
		if (null == columnModel) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
		
		columnModel.setName(name);
		columnDao.update(columnModel);
		return ResultResp.SUCCESS;
	}

}
