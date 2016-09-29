package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.bean.BrandBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.BrandModel;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.service.IBrandService;
import com.iwancool.dsm.service.IColumnService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 品牌
 * @ClassName BrandController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午3:40:55
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "brand")
public class BrandController extends AbstractBaseController{

	@Resource(name = "brandService")
	private IBrandService brandService;
	
	@Resource(name = "columnService")
	private IColumnService columnService;
	
	/**
	 * 列表页面
	 * @Description (TODO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList(Model model) {
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != model) {
			model.addAttribute("columnList", columnList);
		}
		return "brand/list";
	}
	
	/**
	 * 添加页面
	 * @Description (TODO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model) {
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != model) {
			model.addAttribute("columnList", columnList);
		}
		return "brand/add";
	}
	
	/**
	 * 编辑页面
	 * @Description (TODO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Model model, HttpServletRequest request) throws Exception {
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		BrandBean  brand = brandService.getBrand(name);
		if (null != brand) {
			model.addAttribute("brand", brand);
		}
		
		List<ColumnModel> columnList = columnService.findAll(ColumnModel.SHOW);
		if (null != model) {
			model.addAttribute("columnList", columnList);
		}
		return "brand/edit";
	}
	
	/**
	 * 条件查找并分页
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) {
		int columnId = ServletRequestUtils.getIntParameter(request, "columnId", 0);
		int status = ServletRequestUtils.getIntParameter(request, "status", -1);
		String keyword = ServletRequestUtils.getStringParameter(request, "keyword", "");
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		DataGrid<BrandBean> dataGrid = brandService.findBrandList(columnId, status, keyword, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 更新
	 * @Description (TODO
	 * @param brand
	 * @param response
	 */
	@RequestMapping(value = "/update")
	public void update(BrandModel brand,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String columnIdsStr = ServletRequestUtils.getRequiredStringParameter(request, "columnIdsStr");
		ResultResp resultResp = brandService.update(brand, columnIdsStr);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 保存
	 * @param brand
	 * @param response
	 */
	@RequestMapping(value = "/save")
	public void save(BrandModel brand,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String columnIdsStr = ServletRequestUtils.getRequiredStringParameter(request, "columnIdsStr");
		ResultResp resultResp = brandService.save(brand, columnIdsStr);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		ResultResp resultResp = brandService.deleteBacthBrand(idsStr);
		responseDataGrid(response, resultResp);
	}
	
	
	/**
	 * 更新状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		int visible = ServletRequestUtils.getRequiredIntParameter(request, "visible");
		ResultResp resultResp = brandService.updateStatus(name, visible);
		responseDataGrid(response, resultResp);
	}
}
