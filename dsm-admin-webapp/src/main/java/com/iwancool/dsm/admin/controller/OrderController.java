package com.iwancool.dsm.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.bean.OrderBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.OrderModel;
import com.iwancool.dsm.service.IOrderService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 订单controller
 * @ClassName OrderController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:26:30
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends AbstractBaseController{
	
	@Resource(name = "orderService")
	private IOrderService orderService;

	/**
	 * 订单列表页
	 * @Description (TODO
	 */
	@RequestMapping(value = "/toList")
	public String toList() {
		return "order/list";
	}
	
	/**
	 * 批量付款页
	 * @Description (TODO
	 */
	@RequestMapping(value = "/toBatchPayList")
	public String toBatchPayList() {
		return "order/batchPay_list";
	}
	
	/**
	 * 批量付款页
	 * @Description (TODO
	 */
	@RequestMapping(value = "/toBatchRefundList")
	public String toBatchRefundList() {
		return "order/batchRefund_list";
	}
	
	/**
	 * 订单详情
	 * @Description (TODO
	 */
	@RequestMapping(value = "/toDetail")
	public String toDetail(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
		long orderId = ServletRequestUtils.getRequiredLongParameter(request, "orderId");
		OrderModel order = orderService.getOrder(orderId);
		if (null != order) {
			model.addAttribute("order", order);
		}
		return "order/detail";
	}
	
	/**
	 * 条件查找列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		String statusStr = ServletRequestUtils.getStringParameter(request, "statusStr", "");
		String keyword = ServletRequestUtils.getStringParameter(request, "keyword", "");
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<OrderBean> dataGrid = orderService.findOrderList(statusStr, keyword, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 批量更新状态
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateBatchOrder")
	public void updateBatchOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		int status = ServletRequestUtils.getRequiredIntParameter(request, "status");
		ResultResp resultResp = orderService.updateBatchOrder(idsStr, status);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 批量删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBatchOrder")
	public void deleteBatchOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		ResultResp resultResp = orderService.deleteBatchOrder(idsStr);
		responseDataGrid(response, resultResp);
	}
}
