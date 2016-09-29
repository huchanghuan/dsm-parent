package com.iwancool.dsm.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * oss service
 * @ClassName IOSSService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月9日 下午3:24:28
 * @version 1.0.0
 */
public interface IOSSService extends IGenericService{

	/**
	 * 上传本地文件,并返回远程URL
	 * @author	huchanghuan
	 * @param request
	 * @param localFile    相对路径
	 * @param prefix
	 * @return
	 * @since   1.0.0
	 */
	public String upload(HttpServletRequest request, String localFile, String prefix);
	
	/**
	 *  上传本地文件,并返回远程URL
	 * @author	huchanghuan
	 * @param localFilePath    绝对路径
	 * @param prefix
	 * @return
	 * @since   1.0.0
	 * @date    2016年8月15日 下午4:57:55
	 */
	public String upload(String localFilePath, String prefix);

	/**
	 * 上传文件到aliyunOSS
	 * @author	huchanghuan
	 * @param multipartFile
	 * @param type
	 * @return
	 * @since   1.0.0
	 */
	public Map<String, Object> upload(MultipartFile multipartFile, String type);

	/**
	 * 终端上传（临时方案）
	 * @Description (TODO
	 * @param files
	 * @return
	 */
	public Map<String, Object> terminalUpload(MultipartFile[] files);
}
