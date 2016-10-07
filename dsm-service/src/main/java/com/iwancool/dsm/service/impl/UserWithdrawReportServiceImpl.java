package com.iwancool.dsm.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserWithdrawReportDao;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;
import com.iwancool.dsm.domain.UserWithdrawReportModel;
import com.iwancool.dsm.service.IOSSService;
import com.iwancool.dsm.service.IUserAccountService;
import com.iwancool.dsm.service.IUserWithdrawRecordService;
import com.iwancool.dsm.service.IUserWithdrawReportService;
import com.iwancool.dsm.utils.bean.AccountStatementBean;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.bean.MerchantsRecordBean;
import com.iwancool.dsm.utils.bean.WithdrawExportBean;
import com.iwancool.dsm.utils.bean.WithdrawReportBean;
import com.iwancool.dsm.utils.bean.WithdrawResultBean;
import com.iwancool.dsm.utils.util.ExcelUtils;
import com.iwancool.dsm.utils.util.Folders;
import com.iwancool.dsm.utils.util.StringUtil;

/**
 * 提款报表Service实现类
 * @author hch
 *
 */
@Service(value = "userWithdrawReportService")
public class UserWithdrawReportServiceImpl extends AbstractBaseService implements IUserWithdrawReportService{

	private static final String ALIPAY_ACCOUNT = "alipay_account";						//阿里支付宝账号KEY
	private static final String ALIPAY_USERNAME = "alipay_username";					//阿里支付宝账号名KEY
	private static final String EXCEL_FOLDER = "excel_folder";							//本地服务器阿里支付宝excel文件夹
	
	private static final String DEFAULT_ALIPAY_ACCOUNT = "iwancool@126.com";
	private static final String DEFAULT_ALIPAY_USERNAME = "深圳恒兴方圆科技有限公司";
	private static final String DEFAULT_EXCEL_NAME = "剁手猫提现excel";
	
	@Resource(name = "config")
	private Properties config;
	
	@Resource(name = "userWithdrawReportDao")
	private IUserWithdrawReportDao userWithdrawReportDao;
	
	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;
	
	@Resource(name = "userWithdrawRecordService")
	private IUserWithdrawRecordService userWithdrawRecordService;
	
	@Resource(name = "OSSService")
	private IOSSService OSSService;

	@Override
	public ResultResp saveBatch(List<Object[]> objs) {
		if (null == objs) 
				return ResultResp.ERROR;
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long batchNo = Long.valueOf(sdf.format(new Date()) + "001");
			for (int i = 0, len = objs.size(); i < len; i++) {
				long batchNum = batchNo + len/MAX_WITHDRAW_NUM;
				UserWithdrawReportModel userWithdrawReport = new UserWithdrawReportModel();
				
				Object[] objArrays = objs.get(i);
				userWithdrawReport.setUserId(((BigInteger)objArrays[0]).longValue());
				userWithdrawReport.setBatchNo(String.valueOf(batchNum));
				userWithdrawReport.setStatus(UserWithdrawReportModel.NOT_EXPORT);
				userWithdrawReport.setDate(Integer.valueOf(sdf.format(new Date())));
				userWithdrawReport.setAmount(((BigDecimal)objArrays[1]).intValue());
				
				userWithdrawReportDao.save(userWithdrawReport);
			}
		
		return ResultResp.SUCCESS;
	}

	@Override
	public DataGrid<WithdrawReportBean> findWithdrawReportList(int offset, int limit) {
		List<WithdrawReportBean> list = new ArrayList<WithdrawReportBean>();
		int totalSize = userWithdrawReportDao.findWithdrawReportListCount();
		if (totalSize > 0) {
			list = userWithdrawReportDao.findWithdrawReportList(offset, limit);
		}
		return new DataGrid<WithdrawReportBean>(totalSize, list);
	}

	@Override
	public synchronized Map<String, Object> exportAliPayExcel(HttpServletRequest request, HttpServletResponse response, int date, String batchNo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MerchantsRecordBean> exportDataList = userWithdrawReportDao.findWithdrawExportList(date, batchNo);
		int totalAmount = userWithdrawReportDao.findToatlAmount(date, batchNo);
		
		if (null != exportDataList && !exportDataList.isEmpty()) {
			WithdrawExportBean withdrawExport = new WithdrawExportBean();
			withdrawExport.setBatchNo(batchNo);
			withdrawExport.setPayDay(String.valueOf(date));
			withdrawExport.setDrawee(config.getProperty(ALIPAY_ACCOUNT, DEFAULT_ALIPAY_ACCOUNT));
			withdrawExport.setAccountName(config.getProperty(ALIPAY_USERNAME, DEFAULT_ALIPAY_USERNAME));
			withdrawExport.setTotalAmount(totalAmount);
			withdrawExport.setTotalNum(exportDataList.size());
			
			withdrawExport.setMerchantsRecordList(exportDataList);
			
			try {
				String fileName = date + "-" + batchNo + DEFAULT_EXCEL_NAME + StringUtil.getCurrentDateToString("yyyyMMddHHmmss") + ".xls";
				File file = new File(getProjectRealPath() + config.getProperty(EXCEL_FOLDER) + File.separator + fileName);
				if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
				if (!file.exists()) file.createNewFile();
				
				ExcelUtils.exportAliPayExcel(file, withdrawExport, fileName);
				
				if (file.length() > 0) {
					
					List<Long> userIdList = new ArrayList<Long>();
					for (MerchantsRecordBean merchants : exportDataList) {
						userIdList.add(Long.valueOf(merchants.getUserId()));
					}
					//更新提现记录表状态
					//userWithdrawRecordService.updateBatchUserWithdrawRecord(userIdList, UserWithdrawRecordModel.WITHDRAW_SUCCESS, date);
					
					//更新提现报表状态
					userWithdrawReportDao.updateBatchStatus(date, batchNo, UserWithdrawReportModel.WITHDRAW);
					
					//上传到阿里云
					resultMap = OSSService.uploadAliPayExcel(FileUtils.openInputStream(file), Folders.EXCEL_EXPORT_KEY, fileName);
					return resultMap;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultMap.put("code", ResultCode.ERROR);
		return resultMap;
	}

	@Override
	public synchronized ResultResp importAliPayExcel(MultipartFile file) {
		//1、读取结果文件
		WithdrawResultBean withdrawResult = null;
		try {
			withdrawResult = ExcelUtils.readAliPayExcel(file.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (null == withdrawResult || null == withdrawResult.getStatementList() 
				|| withdrawResult.getStatementList().isEmpty()){
			return ResultResp.ERROR;
		}
		
		ResultResp resultResp = null;
		
		int day = Integer.valueOf(withdrawResult.getBatchNo().substring(0, 8));
		List<Long> successUserIdList = new ArrayList<Long>();
		List<Long> failUserIdList = new ArrayList<Long>();
		for (AccountStatementBean accountStatement : withdrawResult.getStatementList()) {
			if (accountStatement.getStatus() == AccountStatementBean.SUCCESS) {
				successUserIdList.add(accountStatement.getUserId());
			} else {
				failUserIdList.add(accountStatement.getUserId());
			}
		}
		
		//2、更新账户余额
		resultResp = userAccountService.updateBatchUserWithdraw(day, withdrawResult.getStatementList());
		if (!ResultResp.isSuccess(resultResp)) 
			return resultResp;
		//3、更新提款记录状态
		if (null != successUserIdList && !successUserIdList.isEmpty()) {
			resultResp = userWithdrawRecordService.updateBatchUserWithdrawRecord(successUserIdList, UserWithdrawRecordModel.WITHDRAW_SUCCESS, day);
			if (!ResultResp.isSuccess(resultResp)) 
				return resultResp;
		}
		
		if (null != failUserIdList && !failUserIdList.isEmpty()) {
			resultResp = userWithdrawRecordService.updateBatchUserWithdrawRecord(failUserIdList, UserWithdrawRecordModel.WITHDRAW_FAIL, day);
			if (!ResultResp.isSuccess(resultResp)) 
				return resultResp;
		}
		
		//4、更新提款报表状态
		userWithdrawReportDao.updateBatchStatus(day, withdrawResult.getBatchNo(), UserWithdrawReportModel.COMPLETE);
		
		try {
			OSSService.uploadAliPayExcel(file.getInputStream(), Folders.EXCEL_IMPORT_KEY, file.getOriginalFilename().replace(".xls", "(结果).xls"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultResp;
	}

	@Override
	public DataGrid<UserWithdrawReportModel> findUserWithdrawReportList(int date, String batchNo, int offset,
			int limit) {
		List<UserWithdrawReportModel> userWithdrawReportList = new ArrayList<UserWithdrawReportModel>();
		int totalSize = userWithdrawReportDao.findWithdrawReportListCount(date, batchNo);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			userWithdrawReportList = userWithdrawReportDao.findWithdrawReportList(date, batchNo, currPage, limit);
		}
		return new DataGrid<UserWithdrawReportModel>(totalSize, userWithdrawReportList);
	}

}
