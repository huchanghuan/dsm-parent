package com.iwancool.dsm.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.UserAccountModel;
import com.iwancool.dsm.service.IUserAccountService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 提现controller
 * @ClassName UserWithdrawController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月5日 下午7:29:05
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/userWithdraw")
public class UserWithdrawController extends AbstractBaseController{

	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;
	
	/**
	 * 进入初始化页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping("/toList")
	public String toList() {
		return "withdraw/list";
	}
	
	/**
	 * 查看钱包信息
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) {
		String keyword = ServletRequestUtils.getStringParameter(request, "keyword", "");
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<UserAccountModel> dataGrid = userAccountService.findUserAccountList(keyword, offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 导出提现excel报表
	 * @Description (TODO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		userAccountService.exportWithdrawData(request, response, idsStr);
	}
	
}
