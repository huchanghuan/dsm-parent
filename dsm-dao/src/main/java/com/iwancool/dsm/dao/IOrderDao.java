package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.OrderModel;

/**
 * 订单dao 接口
 * @ClassName IOrderDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:38:41
 * @version 1.0.0
 */
public interface IOrderDao extends IGeneralORMDao<OrderModel, Long>{

	/**
	 * 条件查找订单记录数
	 * @Description (TODO
	 * @param statusList
	 * @param keyword
	 * @return
	 */
	public int findOrderListCount(List<Integer> statusList, String keyword);

	/**
	 * 条件查找记录并分页
	 * @Description (TODO
	 * @param statusList
	 * @param keyword
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<OrderModel> findOrderList(List<Integer> statusList, String keyword, int currPage, int limit);

	/**
	 * 批量更新订单状态
	 * @Description (TODO
	 * @param idList
	 * @param status
	 */
	public void updateBatchOrder(List<Long> idList, int status);

	/**
	 * 批量删除订单
	 * @Description (TODO
	 * @param idList
	 */
	public void deleteBatchOrder(List<Long> idList);

}
