package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.domain.SelectionModel;
import com.iwancool.dsm.service.IColumnService;
import com.iwancool.dsm.service.ISelectionService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 精选controller
 * @ClassName SelectionController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午4:34:00
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/selection")
public class SelectionController extends AbstractBaseController{
	
	@Resource(name= "columnService")
	private IColumnService columnService;
	
	@Resource(name = "selectionService")
	private ISelectionService selectionService;

	/**
	 * 初始化页面
	 * @Description (TODO
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList (ModelAndView mav) {
		mav.setViewName("selection/list");
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != columnList) {
			mav.addObject("columnList", columnList);
		}
		
		return mav;
	}
	
	/**
	 * 精选列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) {
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", DEFAULT_PAGE_SIZE);
		DataGrid<SelectionModel> dataGrid = selectionService.findSelectionList(offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 保存精选
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveSelection")
	public void saveSelection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long goodsId = ServletRequestUtils.getRequiredLongParameter(request, "goodsId");
		SelectionModel selectionModel = new SelectionModel();
		selectionModel.setGoodsId(goodsId);
		long id = selectionService.saveSelection(selectionModel);
		String json = "{\"id\":"+id+"}";
		responseDataGrid(response, JSON.parse(json));
	}
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = ServletRequestUtils.getRequiredStringParameter(request, "id");
		ResultResp resultResp = selectionService.deleteBatchSelection(id);
		responseDataGrid(response, resultResp);
	}
}
