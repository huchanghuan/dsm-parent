package com.iwancool.dsm.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.UserAccountModel;
import com.iwancool.dsm.service.IUserAccountService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 用户余额
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractBaseController{
	
	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;

	/**
	 * 批量付款（退款）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBatchPay")
	public void updateBatchPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderIdsStr = ServletRequestUtils.getRequiredStringParameter(request, "orderIdsStr");
		String userIdsStr = ServletRequestUtils.getRequiredStringParameter(request, "userIdsStr");
		String balancesStr = ServletRequestUtils.getRequiredStringParameter(request, "balancesStr");
		ResultResp resultResp = userAccountService.updateBatchPay(orderIdsStr, userIdsStr, balancesStr);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 待提现的账户
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
	
	@RequestMapping(value = "/exportExcel")
	public void doWithdraw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "idsStr");
		userAccountService.exportWithdrawData(request, response, idsStr);
	}
}
