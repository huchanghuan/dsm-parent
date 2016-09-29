package com.iwancool.dsm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.service.IOSSService;
import com.iwancool.dsm.utils.code.DigestUtil;
import com.iwancool.dsm.utils.util.AliYunOssClient;
import com.iwancool.dsm.utils.util.Folders;

/**
 * oss 上传业务类
 * @ClassName OSSServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月9日 下午3:26:39
 * @version 1.0.0
 */
@Service(value = "OSSService")
public class OSSServiceImpl extends AbstractBaseService implements IOSSService{

	@Resource(name = "config")
	private Properties config;
	private static final String CNAME_KEY = "oss_cname";
	private static Map<String, Object> folders = new HashMap<String, Object>();
	
	static{
		//初始化可选文件夹
		folders.put("picture", Folders.PICTURE_PREFIX);
		folders.put("media", Folders.MEDIA_PREFIX);
	}
	
	@Override
	public String upload(HttpServletRequest request, String localFile, String prefix) {
		//项目部署路径
		String appPath = request.getSession().getServletContext().getRealPath("/");
		return upload(appPath + localFile, prefix);	
	}

	
	@Override
	public String upload(String localFilePath, String prefix) {
		try {
			File file = new File(localFilePath);
			//获得KEY
			String sourceKey = prefix.concat(DigestUtil.md5(file)).concat(".").concat(getExtensionName(localFilePath));
			AliYunOssClient.me.upload(new FileInputStream(file), sourceKey);
			String url = config.getProperty(CNAME_KEY) + sourceKey;
			System.out.println(url);
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Map<String, Object> upload(MultipartFile multipartFile, String type) {
		Map<String , Object> resultMap = new HashMap<String, Object>();
		if (null == multipartFile || null == folders.get(type)) {
			resultMap.put("ret", ResultCode.ERROR);
			return resultMap;
		}
		
		try {
			String sourceKey = folders.get(type) + DigestUtil.md5(multipartFile.getBytes()) + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			AliYunOssClient.me.upload(multipartFile.getInputStream(), sourceKey);
			
			String url = config.getProperty(CNAME_KEY) + sourceKey;
			System.out.println(url);
			resultMap.put("ret", ResultCode.SUCCESS);
			resultMap.put("url", url);
			resultMap.put("size", multipartFile.getSize());
		} catch (Exception e) {
			resultMap.put("ret", ResultCode.ERROR);
		}
		return resultMap;
	}
	
	private String getExtensionName(String url) throws Exception {
		int index = url.lastIndexOf(".");
		if (index > -1) {
			return url.substring(index + 1);
		}
		return "";
	}


	@Override
	public Map<String, Object> terminalUpload(MultipartFile[] files) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null == files || 0 == files.length) {
			resultMap.put("code", "1");
			resultMap.put("msg", "失败");
			return resultMap;
		} 
		
		try {
			
			for (MultipartFile file : files) {
				String sourceKey = folders.get(file.getContentType().contains("image")?"picture":"media") + DigestUtil.md5(file.getBytes()) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
				AliYunOssClient.me.upload(file.getInputStream(), sourceKey);
				
				resultMap.put("code", "0");
				resultMap.put("url", sourceKey);
			}
		} catch (Exception e) {
			resultMap.put("code", "1");
		}
		return resultMap;
	}

	
}
