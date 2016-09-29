package com.iwancool.dsm.admin.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.service.IUserWithdrawRecordService;
import com.iwancool.dsm.service.IUserWithdrawReportService;
import com.iwancool.dsm.utils.util.StringUtil;

/**
 * 每天统计用户提款记录
 * @author hch
 *
 */
public class UserWithdrawReportTask {

	private final Logger log = Logger.getLogger(UserWithdrawReportTask.class);
	
	@Resource(name = "userWithdrawRecordService")
	private IUserWithdrawRecordService userWithdrawRecordService;
	
	@Resource(name = "userWithdrawReportService")
	private IUserWithdrawReportService userWithdrawReportService;
	
	//任务执行入口
	public void execute () {
		long startUtc = System.currentTimeMillis();
		log.debug("start UserWithdrawReportTask...");
		
		try{
			startTask();
		} catch (Exception e) {
			log.error("fail to execute UserWithdrawReportTask...");
			e.printStackTrace();
		}
		log.debug("complete UserWithdrawReportTask...");
		long endUtc = System.currentTimeMillis();
		log.debug("耗时：" + (endUtc - startUtc) + "ms");
	}

	/**
	 * 开始任务
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private void startTask() throws Exception {
		long dayStartUtc = StringUtil.getDayStartUtc();
		long dayEndUtc = dayStartUtc + 24 * 60 * 60 * 1000;
		List<Object[]> objs = userWithdrawRecordService.findUserWithdrawRecordListByUtc(dayStartUtc, dayEndUtc);
		if (null != objs) {
			ResultResp resultResp = userWithdrawReportService.saveBatch(objs);
			if (!resultResp.isSuccess(resultResp)) throw new Exception();
		}
	}
}
