package com.iwancool.dsm.service;

import com.iwancool.dsm.bean.GoodsBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.GoodsModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * goods service接口
 * @ClassName IGoodsService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:52:24
 * @version 1.0.0
 */
public interface IGoodsService extends IGenericService{

	
	/**
	 * 条件查找并分页
	 * @Description (TODO
	 * @param columnId
	 * @param categoryId
	 * @param status
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<GoodsBean> findGoodsList(int columnId, int categoryId, int status, String keyword, int offset, int limit);

	/**
	 * 获得goods
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public GoodsModel getGoods(long id);

	/**
	 * 更新状态
	 * @Description (TODO
	 * @param id
	 * @param status
	 * @return
	 */
	public ResultResp updateStatus(long id, int status);

	/**
	 * 批量删除
	 * @Description (TODO
	 * @param ids
	 * @return
	 */
	public ResultResp deleteBatchGoods(String ids);

}
