/**
 * Program  : NewspaperSecurityFilter.java
 * Author   : long
 * Create   : 2015年3月27日 上午11:32:21
 *
 * Copyright 2014 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */

package com.iwancool.dsm.admin.filter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iwancool.dsm.domain.PermissionModel;
import com.iwancool.dsm.service.IManagerService;
import com.iwancool.dsm.service.IPermissionService;
import com.iwancool.dsm.service.IRoleService;
import com.iwancool.dsm.utils.cache.SessionUtils;






/**
 * 
 * 
 * @author   long
 * @version  1.0.0
 * @2015年3月27日 上午11:32:21
 */
public class DsmSecurityInterceptor implements HandlerInterceptor{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name = "managerService")
	private IManagerService managerService;
	
	@Resource(name = "permissionService")
	private IPermissionService permissionService;
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	

	private List<String> excludeUrls;// 不需要拦截的资源

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 请求进入controller之前的拦截
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String requestPath = request.getRequestURI().substring(request.getContextPath().length());
		//排除不需要拦截的资源
		if (excludeUrls.contains(requestPath)) return true;
		//未登录
		if (!SessionUtils.isLogin(request)) {
			request.setAttribute("msg", "您还未登录，请您先登录");
			request.getRequestDispatcher("/login/index").forward(request, response);
			return false;
		}
		
		List<PermissionModel> permissionList = (List<PermissionModel>) SessionUtils.getAttr(request, SessionUtils.ADMIN_PERMISSION_CACHE_KEY);
		return true;
		//return hasPermission(permissionList, requestPath);
	}

	/**
	 * 响应结果进行渲染前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	/**
	 * 结果渲染后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

	/**
	 * 判断用户是否有访问此路径的权限
	 * @Description (TODO
	 * @param username
	 * @param requestPath
	 * @return
	 */
	private boolean hasPermission(List<PermissionModel> permissionList, String requestPath) {
		if (null != permissionList && !permissionList.isEmpty()) {
			for (PermissionModel permissionModel : permissionList) {
				if (permissionModel.getUrl().equals(requestPath)) {
					return true;
				}
			}
		}
		return false;
	}
	
}

