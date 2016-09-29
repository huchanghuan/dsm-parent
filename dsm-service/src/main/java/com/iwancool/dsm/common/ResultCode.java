package com.iwancool.dsm.common;

/**
 * 结果码
 * @Description	TODO
 * @ClassName	ResultCode
 * @Date		2016年8月20日 下午8:28:49
 * @Author		huchanghuan
 */
public interface ResultCode {

	/** 全局参数 **/
	public static final String SUCCESS 											= "000000";		// 操作成功
	public static final String ERROR 											= "000001";		// 操作失败
	public static final String APPKEY_ERROR										= "000002"; 	// APPKEY参数有误
	public static final String APPKEY_STATUS_EXCEPTION							= "000003"; 	// 商户状态异常(锁定)，联系客户解锁
	public static final String BAD_REQUEST										= "000004"; 	// 非法请求, 通常sign签名有误
	public static final String PARAMS_ERROR										= "000005"; 	// 参数错误
	public static final String CLIENT_SERVER_ERROR								= "000006"; 	// 客户端访问服务器未做备注
	public static final String SYSTEM_EXCEPTION									= "000007"; 	// 系统内部错误
	public static final String TOKEN_HAS_EXPIRED								= "000008"; 	// 会话令牌已过期
	public static final String SERVICE_NOT_FOUND								= "000009"; 	// 请求服务返回404
	public static final String SERVICE_ERROR									= "000010"; 	// 请求服务返回500
	public static final String GATEWAY_SERVICE_NOT_FOUND						= "000011"; 	// 未找到有效的相关服务
	
	public static final String NAME_HAS_EXITS									= "100010";		// 名称已存在
	public static final String OBJECT_HAS_NOT_EXITS								= "100011";		// 对象不存在
}
