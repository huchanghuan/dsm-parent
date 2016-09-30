package com.iwancool.dsm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserWithdrawRecordDao;
import com.iwancool.dsm.domain.UserWithdrawRecordModel;
import com.iwancool.dsm.service.IUserWithdrawRecordService;
import com.iwancool.dsm.service.IUserWithdrawReportService;

/**
 * 用户提现申请记录Service实现类
 * @author hch
 *
 */
@Service(value = "userWithdrawRecordService")
public class UserWithdrawRecordServiceImpl extends AbstractBaseService implements IUserWithdrawRecordService{
	
	@Resource(name = "userWithdrawRecordDao")
	private IUserWithdrawRecordDao userWithdrawRecordDao;
	
	@Override
	public ResultResp saveOrUpdate(UserWithdrawRecordModel userWithdrawRecord) {
		userWithdrawRecordDao.saveOrUpdate(userWithdrawRecord);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp updateBatchUserWithdrawRecord(List<Long> userIdList, int status, int day) {
		if (null == userIdList || userIdList.isEmpty())
			return ResultResp.ERROR;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(String.valueOf(day));
			//开始UTC
			long startUtc = date.getTime();
			long endUtc = startUtc + 24 * 60 * 60 * 1000;
			userWithdrawRecordDao.updateBatchUserWithdrawRecord(userIdList, status, startUtc, endUtc);
			return ResultResp.SUCCESS;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResultResp.ERROR;
	}

	@Override
	public List<Object[]> findUserWithdrawRecordListByUtc(long startUtc, long endUtc) {
		
		return userWithdrawRecordDao.findUserWithdrawRecordListByUtc(startUtc, endUtc);
	}

	@Override
	public List<UserWithdrawRecordModel> findUserWithdrawRecordListByUtc(long startUtc, long endUtc, String batchNo) {
		int currPage = Integer.valueOf(batchNo.substring(8));
		int offset = (currPage - 1) * IUserWithdrawReportService.MAX_WITHDRAW_NUM;
		int limit = offset + IUserWithdrawReportService.MAX_WITHDRAW_NUM;
		return userWithdrawRecordDao.findUserWithdrawRecordListByUtc(startUtc, endUtc, offset, limit);
	}

}
