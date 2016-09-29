package com.iwancool.dsm.admin.controller;

import java.text.SimpleDateFormat;
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
import com.iwancool.dsm.domain.AdvResourceModel;
import com.iwancool.dsm.service.IAdvResourceService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 广告位资源
 * @ClassName AdvResourceController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 下午5:04:44
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/advResource")
public class AdvResourceController extends AbstractBaseController{
	
	@Resource(name = "advResourceService")
	private IAdvResourceService advResourceService;

	/**
	 * 列表页面
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toList")
	public String toList (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		model.addAttribute("locationId", locationId);
		return "advResource/list";
	}
	
	/**
	 * 添加页面
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		model.addAttribute("locationId", locationId);
		return "advResource/add";
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
	@RequestMapping(value = "/toEdit")
	public String toEdit (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		AdvResourceModel advResource = advResourceService.getAdvResource(id);
		if (null != advResource) {
			model.addAttribute("advResource", advResource);
		}
		return "advResource/edit";
	}
	
	/**
	 * 列表页面
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) throws Exception {
		long locationId = ServletRequestUtils.getRequiredLongParameter(request, "locationId");
		List<AdvResourceModel> advResourceList = advResourceService.findAdvResource(locationId);
		responseDataGrid(response, new DataGrid<AdvResourceModel>(advResourceList.size(), advResourceList));
	}
	
	/**
	 * 保存更新
	 * @Description (TODO
	 * @param advResource
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(AdvResourceModel advResource, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startUtcStr = ServletRequestUtils.getRequiredStringParameter(request, "startUtcStr");
		String endUtcStr = ServletRequestUtils.getRequiredStringParameter(request, "endUtcStr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		advResource.setStartUtc(sdf.parse(startUtcStr).getTime());
		advResource.setEndUtc(sdf.parse(endUtcStr).getTime());
		ResultResp resultResp = advResourceService.saveOrUpdate(advResource);
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
	public void delete (HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		ResultResp resultResp = advResourceService.deleteAdvResource(id);
		responseDataGrid(response, resultResp);
	}
}
