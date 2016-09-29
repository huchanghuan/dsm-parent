package com.iwancool.dsm.service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.SelectionModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 精选service接口
 * @ClassName SelectionService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午10:25:49
 * @version 1.0.0
 */
public interface ISelectionService extends IGenericService{
	
	/**
	 * 保存更新
	 * @Description (TODO
	 * @param selection
	 * @return
	 */
	public ResultResp save(SelectionModel selection);
	
	/**
	 * 获得
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public SelectionModel getSelection(long id);
	
	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idsStr
	 * @return
	 */
	public ResultResp deleteBatchSelection(String idsStr);
	
	/**
	 * 更新排序
	 * @Description (TODO
	 * @param idsStr
	 * @param sortNos
	 * @return
	 */
	public ResultResp updateSort(String idsStr, String sortNos);
	
	/**
	 * 查找记录
	 * @Description (TODO
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<SelectionModel> findSelectionList(int offset, int limit);
	
	/**
	 * 保存
	 * @Description (TODO
	 * @param selection
	 * @return
	 */
	public long saveSelection(SelectionModel selection);
}
