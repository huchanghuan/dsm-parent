package com.iwancool.dsm.admin.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.service.IOSSService;
import com.iwancool.dsm.utils.util.Folders;

/**
 * 公共controller
 * @ClassName PublicController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月9日 下午5:38:38
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/public")
public class PublicController extends AbstractBaseController{

	@Resource(name = "OSSService")
	private IOSSService OSSService;
	
	/**
	 * 上传
	 * @Description (TODO
	 * @param files
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/upload")
	public void upload(MultipartFile fileList, HttpServletRequest request, HttpServletResponse response) {
		String type = fileList.getContentType().contains("image")?Folders.PICTURE_KEY:Folders.MEDIA_KEY;
		Map<String, Object> resultMap = OSSService.upload(fileList, type);
		responseDataGrid(response, resultMap);
	}
	
	/**
	 * 终端临时上传
	 * @Description (TODO
	 * @param fileList
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/terminalUpload")
	public void terminalUpload(@RequestParam(value = "files", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = OSSService.terminalUpload(files);
		responseDataGrid(response, resultMap);
	}
}
