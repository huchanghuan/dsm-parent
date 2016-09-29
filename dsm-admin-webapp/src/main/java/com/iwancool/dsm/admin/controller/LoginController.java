package com.iwancool.dsm.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwancool.dsm.admin.bean.PermissionGroupBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.PermissionModel;
import com.iwancool.dsm.service.IManagerService;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;
import com.iwancool.dsm.utils.cache.SessionUtils;

/**
 * 登录注销控制器
 * @ClassName LoginController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月25日 下午5:53:27
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends AbstractBaseController{
	
	@Resource(name = "managerService")
	private IManagerService managerService;
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	@Resource(name = "permissionService")
	private IPermissionService permissionService;
	
	/**
	 * 跳转到首页前判断是否登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		 if(SessionUtils.isLogin(request)){
			 //mav.setViewName("index/index");
			 mav.setViewName("redirect:/main/index");
		 } else {
			 mav.setViewName("index/login");
		 }
		return mav;
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public void login (HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*ModelAndView mav = new ModelAndView();
		String username = ServletRequestUtils.getRequiredStringParameter(request, "username");
		String password = ServletRequestUtils.getRequiredStringParameter(request, "password");
		ResultResp resultResp = managerService.login(username, password);
		if (ResultResp.isSuccess(resultResp)) {
			List<PermissionModel> permissionList = managerService.findManagerPermissionList(username);
			if (null != permissionList && !permissionList.isEmpty()) {
				List<PermissionGroupBean> permissionGroupList = getPermissionMap(permissionList);
				//访问权限
				SessionUtils.setAttr(request, SessionUtils.ADMIN_PERMISSION_CACHE_KEY, permissionList);
				//显示在页面的权限
				SessionUtils.setAttr(request, SessionUtils.ADMIN_PERMISSION_SHOW_KEY, permissionGroupList);
			}
			SessionUtils.setAttr(request, SessionUtils.ADMIN_NAME_CACHE_KEY,username);
			mav.setViewName("redirect:/main/index");
		} else {
			mav.setViewName("index/login");
			mav.addObject("msg", resultResp.getMsg());
		}
		
		return mav;*/
		String username = ServletRequestUtils.getRequiredStringParameter(request, "username");
		String password = ServletRequestUtils.getRequiredStringParameter(request, "password");
		ResultResp resultResp = managerService.login(username, password);
		if (ResultResp.isSuccess(resultResp)) {
			List<PermissionModel> permissionList = managerService.findManagerPermissionList(username);
			if (null != permissionList && !permissionList.isEmpty()) {
				List<PermissionGroupBean> permissionGroupList = getPermissionMap(permissionList);
				//访问权限
				SessionUtils.setAttr(request, SessionUtils.ADMIN_PERMISSION_CACHE_KEY, permissionList);
				//显示在页面的权限
				SessionUtils.setAttr(request, SessionUtils.ADMIN_PERMISSION_SHOW_KEY, permissionGroupList);
			}
			SessionUtils.setAttr(request, SessionUtils.ADMIN_NAME_CACHE_KEY,username);
		}
		responseDataGrid(response, resultResp);
	}

	/**
	 * 注销登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login/index");
	}
	
	/**
	 * 显示的权限
	 * @param permissionList
	 * @return
	 */
	private List<PermissionGroupBean> getPermissionMap(List<PermissionModel> permissionList) {
		List<PermissionGroupBean> permissionGroupList = new ArrayList<PermissionGroupBean>();
		TreeMap<Long, List<PermissionModel>> permissionMap = new TreeMap<Long, List<PermissionModel>>();
		TreeMap<Long, PermissionModel> parentMap = new TreeMap<Long, PermissionModel>();
		for (PermissionModel permission : permissionList) {
			if (permission.getPid() != 1) {
				permissionMap.get(permission.getPid()).add(permission);
			} else {
				List<PermissionModel> list = new ArrayList<PermissionModel>();
				permissionMap.put(permission.getId(), list);
				parentMap.put(permission.getId(), permission);
			}
		}
		
		for (Entry<Long, PermissionModel> entry : parentMap.entrySet()) {
			PermissionModel permissionModel = entry.getValue();
			PermissionGroupBean permissionGroupBean = new PermissionGroupBean();
			permissionGroupBean.setGroupId(entry.getKey());
			permissionGroupBean.setGroupName(permissionModel.getName());
			permissionGroupBean.setClassName(permissionModel.getClazz());
			permissionGroupBean.setPermissionList(permissionMap.get(entry.getKey()));
			
			permissionGroupList.add(permissionGroupBean);
		}
		
		return permissionGroupList;
	}
}
