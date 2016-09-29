package com.iwancool.dsm.service;

import com.iwancool.dsm.bean.OrderBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.OrderModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 订单service
 * @ClassName IOrderService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:34:09
 * @version 1.0.0
 */
public interface IOrderService extends IGenericService{
	
	/**
	 * 获得订单
	 * @Description (TODO
	 * @param id
	 * @return
	 */
	public OrderModel getOrder(long id);

	/**
	 * 更新订单信息
	 * @Description (TODO
	 * @param order
	 * @return
	 */
	public ResultResp updateOrder(OrderModel order);
	
	/**
	 * 批量更新状态
	 * @Description (TODO
	 * @param idsStr
	 * @param status
	 * @return
	 */
	public ResultResp updateBatchOrder(String idsStr,int status);
	
	/**
	 * 批量删除订单
	 * @Description (TODO
	 * @param idsStr
	 * @return
	 */
	public ResultResp deleteBatchOrder(String idsStr);
	
	/**
	 * 条件查找订单并分页
	 * @Description (TODO
	 * @param status
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<OrderBean> findOrderList(String statusStr, String keyword, int offset, int limit);

}
