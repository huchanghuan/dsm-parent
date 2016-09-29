package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.ColumnModel;

/**
 * 类别dao
 * @ClassName IColumnDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:05:27
 * @version 1.0.0
 */
public interface IColumnDao extends IGeneralORMDao<ColumnModel, Integer>{

	/**
	 * 查找所有此状态数据
	 * @Description (TODO
	 * @param status
	 * @return
	 */
	public List<ColumnModel> findAll(int status);

	/**
	 * 名称查找
	 * @Description (TODO
	 * @param name
	 * @return
	 */
	public ColumnModel getColumn(String name);

}
