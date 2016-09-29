package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwancool.dsm.bean.GoodsBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.domain.GoodsModel;
import com.iwancool.dsm.service.IColumnService;
import com.iwancool.dsm.service.IGoodsService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 商品controller
 * @ClassName GoodsController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:51:42
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "goods")
public class GoodsController extends AbstractBaseController{

	@Resource(name = "goodsService")
	private IGoodsService goodsService;
	
	@Resource(name = "columnService")
	private IColumnService columnService;
	
	/**
	 * 列表页
	 * @Description (TODO
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(ModelAndView mav) {
		
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != columnList) {
			mav.addObject("columnList", columnList);
		}
		mav.setViewName("goods/list");
		return mav;
	}
	
	/**
	 * 列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int columnId = ServletRequestUtils.getIntParameter(request, "columnId", -1);    //一级分类
		int categoryId = ServletRequestUtils.getIntParameter(request, "categoryId", -1);	//二级分类
		int status = ServletRequestUtils.getIntParameter(request, "status", -1);
		String keyword = ServletRequestUtils.getStringParameter(request, "keyword", "");
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<GoodsBean> dataGrid = goodsService.findGoodsList(columnId, categoryId, status, keyword, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 查看详情
	 * @Description (TODO
	 * @param mav
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toDetail")
	public ModelAndView getDetail(ModelAndView mav, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		GoodsModel goods = goodsService.getGoods(id);
		if (null != goods) {
			mav.addObject("goods", goods);
		}
		mav.setViewName("goods/detail");
		return mav;
	}
	
	/**
	 * 更新状态
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		int status = ServletRequestUtils.getIntParameter(request, "status");
		ResultResp resultResp = goodsService.updateStatus(id, status);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = ServletRequestUtils.getRequiredStringParameter(request, "ids");
		ResultResp resultResp = goodsService.deleteBatchGoods(ids);
		responseDataGrid(response, resultResp);
	}
}
