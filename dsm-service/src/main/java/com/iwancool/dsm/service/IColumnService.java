package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.ColumnModel;

/**
 * 一级分类 servcie接口
 * @ClassName IColumnService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午4:16:35
 * @version 1.0.0
 */
public interface IColumnService extends IGenericService{
	
	
	/**
	 * 查找指定状态的记录
	 * @Description (TODO
	 * @param status
	 * @return
	 */
	public List<ColumnModel> findAll(int status);

	/**
	 * 保存或者更新
	 * @Description (TODO
	 * @param column
	 * @return
	 */
	public ResultResp saveOrUpdate(ColumnModel column);

	/**
	 * 删除
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ResultResp deleteColumn(int id);
	
	/**
	 * 获得column
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public ColumnModel getColumn(int id);

	/**
	 * 更新名称
	 * @Description (TODO
	 * @param id
	 * @param name
	 * @return
	 */
	public ResultResp updateName(int id, String name);
}
