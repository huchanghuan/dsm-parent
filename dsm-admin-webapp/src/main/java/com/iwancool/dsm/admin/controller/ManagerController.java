package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.ManagerModel;
import com.iwancool.dsm.domain.RoleModel;
import com.iwancool.dsm.service.IManagerService;
import com.iwancool.dsm.service.IRoleService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 
 * @ClassName ManagerController
 * @Description TODO  管理员控制器
 * @author huchanghuan
 * @Date 2016年8月25日 下午5:31:30
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController extends AbstractBaseController{
	
	@Resource(name = "managerService")
	private IManagerService managerService;
	
	@Resource(name = "roleService")
	private IRoleService  roleService;

	/**
	 * 列表页
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList () {
		return "manager/list";
	}
	
	/**
	 * 添加页
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd (Model model) {
		List<RoleModel> roleList = roleService.findRoleAllList();
		if (null != roleList) {
			model.addAttribute("roleList", roleList);
		}
		return "manager/add";
	}
	
	/**
	 * 列表页
	 * @Description (TODO
	 * @return
	 * @throws ServletRequestBindingException 
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit (HttpServletRequest request, Model model) throws ServletRequestBindingException {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		ManagerModel manager = managerService.getManager(id);
		if (null != manager) {
			model.addAttribute("manager", manager);
		}
		
		List<RoleModel> roleList = roleService.findRoleAllList();
		if (null != roleList) {
			model.addAttribute("roleList", roleList);
		}
		return "manager/edit";
	}
	
	/**
	 * 管理员列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		int status = ServletRequestUtils.getIntParameter(request, "status", -1);
		DataGrid<ManagerModel> dataGrid = managerService.findManagerList(status, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 删除管理员
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		ResultResp resultResp = managerService.deleteManager(id);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 保存或更新
	 * @param manager
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(ManagerModel manager, HttpServletRequest request, HttpServletResponse response){
		ResultResp resultResp = managerService.saveOrUpdate(manager);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 更新状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		long  id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		int status = ServletRequestUtils.getRequiredIntParameter(request, "status");
		ResultResp resultResp = managerService.updateStatus(id, status);
		responseDataGrid(response, resultResp);
	}
}
