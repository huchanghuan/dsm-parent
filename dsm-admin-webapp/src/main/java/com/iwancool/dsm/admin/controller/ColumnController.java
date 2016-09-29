package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.service.IColumnService;

/**
 * 一级分类 controller
 * @ClassName ColumnController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午4:15:49
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/column")
public class ColumnController extends AbstractBaseController{

	@Resource(name = "columnService")
	private IColumnService columnService;

	/**
	 * 列表页
	 * @Description (TODO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList(Model model) {
		List<ColumnModel> columnList = columnService.findAll(-1);
		if (null != columnList) {
			model.addAttribute("columnlist", columnList);
		}
		return "column/list";
	}
	
	/**
	 * 保存或者更新
	 * @Description (TODO
	 * @param column
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(ColumnModel column,HttpServletRequest request, HttpServletResponse response) {
		ResultResp resultResp = columnService.saveOrUpdate(column);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = ServletRequestUtils.getIntParameter(request, "columnId", -1);
		ResultResp resultResp = columnService.deleteColumn(id);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 更新名称
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateName")
	public void updateName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		ResultResp resultResp = columnService.updateName(id, name);
		responseDataGrid(response, resultResp);
	}
}
