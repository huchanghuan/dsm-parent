package com.iwancool.dsm.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.controller.AbstractBaseController;

/**
 * 首页广告
 * @ClassName AdvController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月5日 下午9:01:30
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/adv")
public class AdvController extends AbstractBaseController{

	@RequestMapping(value = "/toList")
	public String toList () {
		return "adv/list";
	}
	
	@RequestMapping(value = "/toAdd")
	public String toAdd () {
		return "adv/add";
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate() {
		
	}
	
	@RequestMapping(value = "/delete")
	public void delete() {
		
	}
}
