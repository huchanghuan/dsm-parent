package com.iwancool.dsm.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用响应结果类
 * @author Administrator
 *
 */
public class ResultResp implements Serializable{

	private static final long serialVersionUID = -2901431059055208584L;
	
	public static ResultResp SUCCESS = createResult(ResultCode.SUCCESS);
	public static ResultResp ERROR = createResult(ResultCode.ERROR);
			
	private String code;
	
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	public ResultResp(String code) {
		this.code = code;
		this.msg = "";
	}

	public ResultResp(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static ResultResp createResult(String code){
		return new ResultResp(code);
	}
	
	public static ResultResp createResult(String code, String msg){
		return new ResultResp(code, msg);
	}
	
	public static boolean isSuccess(ResultResp resultResp) {
		return resultResp.getCode().equals(ResultCode.SUCCESS)?true:false;
	}
}
