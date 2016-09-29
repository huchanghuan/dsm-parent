/**
 * Program  : AbstractBaseService.java
 * Author   : long
 * Create   : 2015年7月7日 上午10:38:53
 *
 * Copyright 2015 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */
	
package com.iwancool.dsm.service.impl;

import java.util.Random;






import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iwancool.dsm.service.IGenericService;


/**
 * 基础service
 * @Description	TODO
 * @ClassName	AbstractBaseService
 * @Date		2016年8月20日 下午8:32:01
 * @Author		huchanghuan
 */
public class AbstractBaseService implements IGenericService{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 获取项目基础路径
	 * 
	 * @author   long
	 * @return
	 * @throws Exception
	 * @return String
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年7月15日 下午6:57:18
	 */
	public String getProjectRealPath() throws Exception{
		return getProjectRealPath("/");
	}
	
	/**
	 * 获取项目基础路径
	 * 
	 * @author   long
	 * @param path
	 * @return
	 * @throws Exception
	 * @return String
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年7月15日 下午6:57:37
	 */
	public String getProjectRealPath(String path) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getServletContext().getRealPath(path);
	}

	/**
	 * 获取随机文件名称
	 * 
	 * @author   long
	 * @param type
	 * @return
	 * @throws Exception
	 * @return String
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年7月15日 下午7:03:40
	 */
	public String getRandomFileName(String type) throws Exception{
		Random random = new Random(1000);
		int randomNum = random.nextInt(9999);
		return System.currentTimeMillis() + "_" + randomNum + "." +StringUtils.getFilenameExtension(type);
	}
	

	/**
	 * 获取客户端IP地址
	 * 
	 * @author long
	 * @create 2014-4-16 下午6:05:46
	 * @since
	 * @param request
	 * @return
	 */
	protected String getRequestIPAddress(HttpServletRequest request) {
		String remoteIp = request.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("X-Real-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("HTTP_CLIENT_IP");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getRemoteAddr();
		}
		if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp = request.getRemoteHost();
		}
		return remoteIp;
	}
}
