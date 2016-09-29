package com.iwancool.dsm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class AbstractBaseController {

public static final String RET = "ret";
	
	public static final int DEFAULT_PAGE_SIZE = 20;

	protected Log log = LogFactory.getLog(getClass());

	/**
	 * 回写数据至终端
	 * 
	 * @author long
	 * @create 2014-3-14 下午1:58:17
	 * @since
	 * @param data
	 * @param response
	 */
	public void addDataToResponse(String data, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(data);
		} catch (IOException e) {
			log.error("write data to response failed:" + e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 会写数据流道终端
	 * 
	 * @author long
	 * @create 2014年9月18日 上午11:46:11
	 * @since 
	 * @param map
	 * @param response
	 */
	public void addDataToResponse(Map<String, Object> map, HttpServletResponse response){
		String data = JSON.toJSONString(map, true);
		addDataToResponse(data, response);
	}
	
	
	/**
	 * 回写终端数据
	 * 
	 * @author   long
	 * @param response
	 * @param dataGrid
	 * @return void
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年7月29日 上午11:19:00
	 */
	public void responseDataGrid(HttpServletResponse response, Object obj){
		String str = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);  
		addDataToResponse(str, response);
	}
	
	/**
	 * 转化数据对象为json字符串
	 * 
	 * @author   long
	 * @param obj
	 * @return
	 * @return String
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年8月17日 上午10:56:48
	 */
	public String objFormatJSON(Object obj){
		return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
	}

	/**
	 * 错误码添加result头
	 * 
	 * @author long
	 * @create 2014-4-26 下午3:03:10
	 * @since
	 * @param errorCode
	 * @throws Exception
	 */
	public String addResultHeader(String errorCode) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ret", errorCode);
		return jsonObject.toJSONString();
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
	
	/**
	 * 获取POST参数
	 * 
	 * @author long
	 * @create 2014年10月28日 上午10:07:05
	 * @since 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected String getPostParam(HttpServletRequest request) throws Exception{
		StringBuffer strBuff = new StringBuffer() ; 
		InputStreamReader isr = new InputStreamReader(request.getInputStream(), "utf-8");   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s = br.readLine())!=null){ 
			strBuff.append(s) ; 
		} 
		return strBuff.toString();
	}
	
	/**
	 * 字符串转化成Int数组
	 * 
	 * @author   long
	 * @param str
	 * @param regex
	 * @return
	 * @return int[]
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年8月7日 下午3:04:18
	 */
	protected Integer[] strToIntArr(String str) {
		return strToIntArr(str, ",");
	}
	
	/**
	 * 字符串转化成Int数组
	 * 
	 * @author   long
	 * @param str
	 * @param regex
	 * @return
	 * @return int[]
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年8月7日 下午3:04:18
	 */
	protected Integer[] strToIntArr(String str, String regex) {
		String[] strArr = str.split(regex);
		Integer[] result = new Integer[strArr.length];
		for(int i = 0; i < strArr.length; i++){
			result[i] = Integer.parseInt(strArr[i]);
		}
		return result;
	}
	
	/**
	 * 获取主题包绝对路径
	 * 
	 * @author   long
	 * @param request
	 * @return
	 * @return String
	 * @exception
	 * @author   long
	 * @since 1.0.0
	 * @date 2015年9月14日 上午11:29:42
	 */
	protected String getThemePath(HttpServletRequest request){
		return (String)request.getAttribute("themePath");
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
}
