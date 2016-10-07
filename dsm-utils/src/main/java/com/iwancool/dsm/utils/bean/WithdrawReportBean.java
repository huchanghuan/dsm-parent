package com.iwancool.dsm.utils.bean;

import java.io.Serializable;

/**
 * 提款报表bean(页面展示)
 * @author hch
 *
 */
public class WithdrawReportBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7225285402073190144L;

	private int date;
	
	private int userNum;
	
	private int withdrawAmount;    //扣除费用后的
	
	private int status;
	
	private String batchNo;

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(int withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
