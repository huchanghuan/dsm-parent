package com.iwancool.dsm.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.utils.cache.SessionUtils;

/**
 * 主入口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends AbstractBaseController{

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index/index");
		if(!SessionUtils.isLogin(request)){
			mav.setViewName("index/login");
			SessionUtils.setAttr(request, "msg", "请您先登录");
		 } 
		return mav;
	}
}
