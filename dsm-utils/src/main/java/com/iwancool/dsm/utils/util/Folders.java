package com.iwancool.dsm.utils.util;

/**
 * 阿里云OSS文件夹
 * @ClassName Folders
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月9日 下午2:33:57
 * @version 1.0.0
 */
public interface Folders {

	/**
	 * 图片文件夹名称
	 */
	public static final String PICTURE_PREFIX = "picture/"; 
	
	/**
	 * 其它媒资文件夹名称
	 */
	public static final String MEDIA_PREFIX = "media/"; 
	
	/**
	 * 阿里支付宝批量付款
	 */
	public static final String EXCEL_EXPORT_PREFIX = "excel/export/";
	
	/**
	 * 阿里支付宝批量付款结果
	 */
	public static final String EXCEL_IMPORT_PREFIX = "excel/import/";
	

	////////////////////////////////////////////
	///////////////////对应的KEY
	////////////////////////////////////////////
	
	public static final String PICTURE_KEY = "picture";
	public static final String MEDIA_KEY = "media";
	public static final String EXCEL_EXPORT_KEY = "excel_export";
	public static final String EXCEL_IMPORT_KEY = "excel_import";
}
