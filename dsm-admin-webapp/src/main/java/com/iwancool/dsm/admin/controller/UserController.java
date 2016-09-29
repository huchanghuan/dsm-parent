package com.iwancool.dsm.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.UserModel;
import com.iwancool.dsm.service.IUserService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 用户controller
 * @ClassName UserController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:31:01
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractBaseController{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	 * 列表页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList() {
		return "user/list";
	}
	
	/**
	 * 添加页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
		return "user/add";
	}
	
	/**
	 * 编辑页面
	 * @Description (TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getLongParameter(request, "id");
		UserModel user = userService.getUser(id);
		if (null != user) {
			user.setPasswd("");
			model.addAttribute("user", user);
		}
		return "user/edit";
	}
	
	/**
	 * 查询用户
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int status = ServletRequestUtils.getIntParameter(request, "status", -1);
		String keyword = ServletRequestUtils.getStringParameter(request, "keyword", "");
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<UserModel> dataGrid = userService.findUserList(status, keyword, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 保存或更新
	 * @Description (TODO
	 * @param user
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(UserModel user, HttpServletResponse response) {
		ResultResp resultResp = userService.saveOrUpdate(user);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = ServletRequestUtils.getRequiredStringParameter(request, "ids");
		ResultResp resultResp = userService.deleteBatchUser(ids);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 更新状态
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		int status = ServletRequestUtils.getRequiredIntParameter(request, "status");
		ResultResp resultResp = userService.updateStatus(id, status);
		responseDataGrid(response, resultResp);
	}
}
