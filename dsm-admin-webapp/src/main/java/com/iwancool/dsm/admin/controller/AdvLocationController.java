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
import com.iwancool.dsm.domain.AdvLocationModel;
import com.iwancool.dsm.service.IAdvLocationService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 广告位controller
 * @ClassName AdvLocationController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午5:03:28
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/advLocation")
public class AdvLocationController extends AbstractBaseController{

	@Resource(name = "advLocationService")
	private IAdvLocationService advLocationService;
	
	/**
	 * 初始化页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList() {
		return "advLocation/list";
	}
	
	/**
	 * 添加页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "toAdd")
	public String toAdd () {
		return "advLocation/add";
	}
	
	/**
	 * 编辑页面
	 * @Description (TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "toEdit")
	public String toEdit (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		AdvLocationModel advLocation = advLocationService.getAdvLocation(locationId);
		if (null != advLocation) {
			model.addAttribute("advLocation", advLocation);
		}
		return "advLocation/edit";
	}
	
	/**
	 * 列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) {
		List<AdvLocationModel> advLocationList = advLocationService.findAllAdvlocation();
		responseDataGrid(response, new DataGrid<AdvLocationModel>(advLocationList.size(), advLocationList));
	}
	
	/**
	 * 保存更新
	 * @Description (TODO
	 * @param advLocation
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(AdvLocationModel advLocation, HttpServletRequest request, HttpServletResponse response) {
		ResultResp resultResp = advLocationService.saveOrUpdate(advLocation);
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
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		ResultResp resultResp = advLocationService.deleteAdvlocation(locationId);
		responseDataGrid(response, resultResp);
	}
	
	@RequestMapping(value = "/updateStatus")
	public void updateSttaus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		int status = ServletRequestUtils.getRequiredIntParameter(request, "status");
		ResultResp resultResp = advLocationService.updateStatus(locationId, status);
		responseDataGrid(response, resultResp);
	}
}
