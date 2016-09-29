package com.iwancool.dsm.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.bean.OrderBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IOrderDao;
import com.iwancool.dsm.domain.GoodsModel;
import com.iwancool.dsm.domain.OrderModel;
import com.iwancool.dsm.service.IGoodsService;
import com.iwancool.dsm.service.IOrderService;
import com.iwancool.dsm.utils.bean.DataGrid;


/**
 * 订单service实现类
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:35:04
 * @version 1.0.0
 */
@Service(value = "orderService")
public class OrderServiceImpl extends AbstractBaseService implements IOrderService{

	@Resource(name = "orderDao")
	private IOrderDao orderDao;
	
	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@Override
	public DataGrid<OrderBean> findOrderList(String statusStr, String keyword, int offset, int limit) {
		log.debug("findOrderList -> statusStr:" + statusStr + ",keyword:" + keyword + ",offset:" + offset + ",limit:" + limit);
		List<OrderBean> OrderList = new ArrayList<OrderBean>();
		List<Integer> statusList = getStatus(statusStr);
		int totalSize = orderDao.findOrderListCount(statusList, keyword);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			List<OrderModel> orderModelList = orderDao.findOrderList(statusList, keyword, currPage, limit);
			if (null != orderModelList) {
				for (OrderModel orderModel : orderModelList) {
					OrderBean orderBean = new OrderBean();
					BeanUtils.copyProperties(orderModel, orderBean);
					
					//设置商品名
				    GoodsModel goods = goodsService.getGoods(orderModel.getGoodsId());
					if (null != goods) {
						orderBean.setGoodsName(goods.getName());
					}
					
					//放入list
					OrderList.add(orderBean);
				}
			}
		}
		return new DataGrid<OrderBean>(totalSize, OrderList);
	}

	/**
	 * 获得状态列表
	 * @param statusStr
	 * @return
	 */
	private List<Integer> getStatus(String statusStr) {
		List<Integer> statuList = null;
		if (!StringUtils.isEmpty(statusStr)) {
			statuList = new ArrayList<Integer>();
			for (String status : statusStr.split(",")) {
				statuList.add(Integer.valueOf(status));
			}
		} 
		return statuList;
	}

	@Override
	public ResultResp updateOrder(OrderModel order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultResp updateBatchOrder(String idsStr, int status) {
		log.debug("updateBatchOrder -> idsStr:" + idsStr + ",sttaus:" + status);
		if (StringUtils.isEmpty(idsStr)) return ResultResp.ERROR;
		
		String[] idsArr = idsStr.split(",");
		List<Long> idList = new ArrayList<Long>();
		if (null == idsArr || 0 == idsArr.length) return ResultResp.ERROR;
		for (String str : idsArr) {
			idList.add(Long.valueOf(str));
		}
		orderDao.updateBatchOrder(idList, status);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBatchOrder(String idsStr) {
		log.debug("updateBatchOrder -> idsStr:" + idsStr);
		if (StringUtils.isEmpty(idsStr)) return ResultResp.ERROR;
		
		String[] idsArr = idsStr.split(",");
		List<Long> idList = new ArrayList<Long>();
		if (null == idsArr || 0 != idsArr.length) return ResultResp.ERROR;
		for (String str : idsArr) {
			idList.add(Long.valueOf(str));
		}
		
		orderDao.deleteBatchOrder(idList);
		return ResultResp.SUCCESS;
	}

	@Override
	public OrderModel getOrder(long id) {
		log.debug("getOrder -> " + id);
		
		return orderDao.getByPrimaryKey(id);
	}
}
