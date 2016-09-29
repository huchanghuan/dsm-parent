package com.iwancool.dsm.utils.cache;


import javax.servlet.http.HttpServletRequest;


public class SessionUtils {
	
	//管理员名
	public static final String ADMIN_NAME_CACHE_KEY = "ADMIN_CACHE_KEY";  
	//权限
	public static final String ADMIN_PERMISSION_CACHE_KEY = "ADMIN_PERMISSION_CACHE_KEY";
	//显示在页面的权限
	public static final String ADMIN_PERMISSION_SHOW_KEY = "ADMIN_PERMISSION_SHOW_KEY";

	/**
	 * 判断seesion中是否有值
	 * @Description (TODO
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		 return null == getAttr(request, ADMIN_NAME_CACHE_KEY)?false:true;
	}
	
	/**
	 * 设置值
	 * @Description (TODO
	 * @param request
	 * @param obj
	 */
	public static void setAttr(HttpServletRequest request,String key, Object obj){
		request.getSession().setAttribute(key, obj);
	}
	
	/**
	 * 获得值
	 * @Description (TODO
	 * @param request
	 * @return
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
}
