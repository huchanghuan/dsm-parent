package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.CategoryModel;
import com.iwancool.dsm.service.ICategoryService;

/**
 * 二级分类controller
 * @ClassName CategoryController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:48:35
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends AbstractBaseController{

	@Resource(name = "categoryService")
	private ICategoryService categoryService;
	
	/**
	 * 根据一级分类ID查二级分类
	 * @Description (TODO
	 */
	@RequestMapping(value = "/findCategoryListByPid")
	public void findCategoryListByPid(HttpServletRequest request, HttpServletResponse response) {
		int columnId = ServletRequestUtils.getIntParameter(request, "columnId", -1);
		List<CategoryModel> categoryList = categoryService.findCategoryListByPid(columnId);
		responseDataGrid(response, categoryList);
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(CategoryModel category, HttpServletResponse response){
		ResultResp resultResp = categoryService.saveOrUpdate(category);
		responseDataGrid(response, resultResp);
	}
	
	@RequestMapping(value = "/delete")
	public void delete (HttpServletRequest request, HttpServletResponse response) {
		int categoryId = ServletRequestUtils.getIntParameter(request, "categoryId", -1);
		ResultResp resultResp = categoryService.delete(categoryId);
		responseDataGrid(response, resultResp);
	}
	
	@RequestMapping(value = "/updateName")
	public void updateName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		String name = ServletRequestUtils.getRequiredStringParameter(request, "name");
		ResultResp resultResp = categoryService.updateName(id, name);
		responseDataGrid(response, resultResp);
	}
}
