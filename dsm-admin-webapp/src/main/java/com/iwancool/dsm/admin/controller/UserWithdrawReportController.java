package com.iwancool.dsm.admin.controller;



import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;
import com.iwancool.dsm.service.IUserWithdrawRecordService;
import com.iwancool.dsm.service.IUserWithdrawReportService;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;

/**
 * 用户提现报表Controller
 * @ClassName UserWithdrawReportController
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月28日 下午4:56:53
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/userWithdrawReport")
public class UserWithdrawReportController extends AbstractBaseController{

	@Resource(name = "userWithdrawReportService")
	private IUserWithdrawReportService userWithdrawReportService;
	
	@Resource(name = "userWithdrawRecordService")
	private IUserWithdrawRecordService userWithdrawRecordService;
	
	/**
	 * 提现页面
	 * @Description (TODO
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList() {
		return "userWithdrawReport/list";
	}
	
	@RequestMapping(value = "/toDetail")
	public String toDetail(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int date = ServletRequestUtils.getRequiredIntParameter(request, "date");
		String batchNo = ServletRequestUtils.getRequiredStringParameter(request, "batchNo");
		model.addAttribute("date", date);
		model.addAttribute("batchNo", batchNo);
		return "userWithdrawReport/detail_list";
	}
	
	/**
	 * 提现记录列表
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		DataGrid<WithdrawReportBean> dataGrid = userWithdrawReportService.findWithdrawReportList(offset, limit);
		responseDataGrid(response, dataGrid);
	}
	
	/**
	 * 导出支付宝转账付款excel
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportAliPayExcel")
	public void exportAliPayExcel (HttpServletRequest request, HttpServletResponse response) throws Exception {
		int date = ServletRequestUtils.getRequiredIntParameter(request, "date");
		String batchNo = ServletRequestUtils.getRequiredStringParameter(request, "batchNo");
		Map<String, Object> resultMap = userWithdrawReportService.exportAliPayExcel(request, response, date, batchNo);
		responseDataGrid(response, resultMap);
	}
	
	/**
	 * 导入支付宝转账付款结果excel
	 * @Description (TODO
	 * @param file
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importAliPayExcel")
	public void importAliPayExcel (MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (file.isEmpty()) {
			responseDataGrid(response, ResultResp.ERROR);
			return;
		}
			
		ResultResp resultResp = userWithdrawReportService.importAliPayExcel(file);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 报表结果详情
	 * @Description (TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int date = ServletRequestUtils.getRequiredIntParameter(request, "date");
		String batchNo = ServletRequestUtils.getRequiredStringParameter(request, "batchNo");
		//int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		//int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		//DataGrid<UserWithdrawReportModel> dataGrid = userWithdrawReportService.findUserWithdrawReportList(date, batchNo, offset, limit);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		long startUtc = sdf.parse(String.valueOf(date)).getTime();
		long endUtc = startUtc + (24 * 60 * 60 * 1000);
		List<UserWithdrawRecordModel> list = userWithdrawRecordService.findUserWithdrawRecordListByUtc(startUtc, endUtc, batchNo);
		responseDataGrid(response, list);
	}
}
