package com.iwancool.dsm.utils.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class StringUtil {

	/**
	 * String[]转List<Long>
	 * @param arrays
	 * @return
	 */
	public static List<Long> arrayToLongList(String[] arrays) {
		List<Long> list = new ArrayList<Long>();
		
		if (null != arrays && 0 != arrays.length) {
			for (String str : arrays) {
				list.add(Long.valueOf(str));
			}
		}
		return list;
	}

	public static List<Integer> arrayToIntegerList(String[] arrays) {
		List<Integer> list = new ArrayList<Integer>();
		
		if (null != arrays && 0 != arrays.length) {
			for (String str : arrays) {
				list.add(Integer.valueOf(str));
			}
		}
		return list;
	}
	
	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty (Collection<Object> collection) {
		return null == collection || collection.isEmpty();
	}
	
	/**
	 * 一天开始的UTC
	 * @return
	 */
	public static long getDayStartUtc() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 当前时间转String
	 * @param pattern 格式
	 * @return
	 */
	public static String getCurrentDateToString(String pattern) {
		return getDateToString(new Date(), pattern);
	}
	
	/**
	 * 时间转String
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
