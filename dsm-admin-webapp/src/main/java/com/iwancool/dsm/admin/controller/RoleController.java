package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.RoleModel;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;

/**
 * 角色权限
 * @ClassName RoleController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午7:15:54
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends AbstractBaseController{
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	@Resource(name = "permissionService")
	private IPermissionService permissionService;

	/**
	 * 列表页面
	 * @Description (TODO
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(ModelAndView mav) {
		List<RoleModel> roleList = roleService.findRoleAllList();
		if (null != roleList) {
			mav.addObject("roleList", roleList);
		}
		mav.setViewName("role/list");
		return mav;
	}
	
	/**
	 * 查找角色的权限
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findPermissionList")
	public void findPermissionList(HttpServletRequest request, HttpServletResponse response) {
		long roleId = ServletRequestUtils.getLongParameter(request, "roleId", -1);
		List<JSONObject> permissionList = permissionService.findPermissionListByRoleId(roleId);
		responseDataGrid(response, permissionList);
	}
	
	/**
	 * 保存或修改
	 * @Description (TODO
	 * @param role
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(RoleModel role, HttpServletResponse response) {
		ResultResp resultResp = roleService.saveOrUpdate(role);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 保存权限字符串
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/savePermission")
	public void savePermission(HttpServletRequest request,HttpServletResponse response) throws Exception {
		long roleId = ServletRequestUtils.getRequiredLongParameter(request, "roleId");
		String permissionStr = ServletRequestUtils.getRequiredStringParameter(request, "permissionStr");
		ResultResp resultResp = roleService.savePermission(roleId, permissionStr);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除角色
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long roleId = ServletRequestUtils.getLongParameter(request, "roleId");
		ResultResp resultResp = roleService.deleteRole(roleId);
		responseDataGrid(response, resultResp);
	}
	
	@RequestMapping(value = "/updateRoleName")
	public void updateRoleName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long roleId = ServletRequestUtils.getLongParameter(request, "roleId");
		String roleName = ServletRequestUtils.getRequiredStringParameter(request, "roleName");
		ResultResp resultResp = roleService.updateRoleName(roleId, roleName);
		responseDataGrid(response, resultResp);
	}
}
