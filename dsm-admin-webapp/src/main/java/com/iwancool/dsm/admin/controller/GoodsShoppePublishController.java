package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.domain.GoodsShoppePublishModel;
import com.iwancool.dsm.service.IColumnService;
import com.iwancool.dsm.service.IGoodsShoppePublishService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 商品专柜发布controller
 * @author hch
 *
 */
@Controller
@RequestMapping(value = "/goodsShoppePublish")
public class GoodsShoppePublishController extends AbstractBaseController {
	
	@Resource(name = "goodsShoppePublishService")
	private IGoodsShoppePublishService goodsShoppePublishService;
	
	@Resource(name = "columnService")
	private IColumnService columnService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int shoppeId = ServletRequestUtils.getRequiredIntParameter(request, "shoppeId");
		model.addAttribute("shoppeId", shoppeId);
		
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != columnList) {
			model.addAttribute("columnList", columnList);
		}
		return "goodsShoppePublish/list";
	}
	
	/**
	 * 添加页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model, HttpServletRequest request) throws Exception {
		int shoppeId = ServletRequestUtils.getRequiredIntParameter(request, "shoppeId");
		model.addAttribute("shoppeId", shoppeId);
		return "goodsShoppePublish/add";
	}
	
	/**
	 * 编辑页面
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Model model, HttpServletRequest request) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		GoodsShoppePublishModel goodsShoppePublish = goodsShoppePublishService.getGoodsShoppePublish(id);
		if (null != goodsShoppePublish) {
			model.addAttribute("goodsShoppePublish", goodsShoppePublish);
		}
		return "goodsShoppePublish/edit";
	}
	
	/**
	 * 保存更新
	 * @param goodsShoppePublish
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(GoodsShoppePublishModel goodsShoppePublish, HttpServletResponse response) {
		int id = goodsShoppePublishService.saveGoodsShoppePublish(goodsShoppePublish);
		String json = "{\"id\":"+id+"}";
		responseDataGrid(response, JSON.parse(json));
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchDelete")
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		ResultResp resultResp = goodsShoppePublishService.deleteBatchGoodsShoppePublish(idsStr);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 查找专柜下商品并分页
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) throws Exception {
		int shoppeId = ServletRequestUtils.getRequiredIntParameter(request, "shoppeId");
		int offset = ServletRequestUtils.getIntParameter(request, "offste", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<GoodsShoppePublishModel> dataGrid = goodsShoppePublishService.findGoodsShoppePublishList(shoppeId, offset, limit);
		responseDataGrid(response, dataGrid);
	}
}
