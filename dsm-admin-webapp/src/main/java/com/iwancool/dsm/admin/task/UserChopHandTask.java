package com.iwancool.dsm.admin.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwancool.dsm.service.IGoodsService;
import com.iwancool.dsm.service.IUserChopHandService;

/**
 * 统计一个月内销售商品最多的
 * @author hch
 *
 */
public class UserChopHandTask {
	
	private final Logger log = Logger.getLogger(UserChopHandTask.class);
	
	//挑选最大销售数前多少名
	private int size;
	
	@Resource(name = "goodsService")
	private IGoodsService goodsService;
	
	@Resource(name = "userChopHandService")
	private IUserChopHandService userChopHandService;

	/**
	 * 定时器入口
	 */
	public void execute () {
		long startUtc = System.currentTimeMillis();
		log.debug("start UserChopHandTask...");
		
		try{
			startTask();
		} catch (Exception e) {
			log.error("fail to execute UserChopHandTask...");
			e.printStackTrace();
		}
		log.debug("complete UserChopHandTask...");
		long endUtc = System.currentTimeMillis();
		log.debug("耗时：" + (endUtc - startUtc) + "ms");
	}

	private void startTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		long endUtc = calendar.getTimeInMillis();
		calendar.add(Calendar.MONTH, -1);
		long startUtc = calendar.getTimeInMillis();
		int date = Integer.valueOf(sdf.format(calendar.getTime()));
		
		List<Map<String, Object>> chopHandUserList = goodsService.findChopHandUser(startUtc, endUtc, size);
		if (null != chopHandUserList && !chopHandUserList.isEmpty()) {
			userChopHandService.saveBatchUserChopHand(chopHandUserList, date);
		}
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
