package com.iwancool.dsm.utils.bean;

import java.io.Serializable;

/**
 * 阿里支付宝返回结果xls 流水记录
 * @author hch
 *
 */
public class AccountStatementBean implements Serializable{
	
	public static final int SUCCESS = 0;			//成功
	public static final int FAIL = 1; 				//失败

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//商户流水号（用户ID）
	private long userId;
	
	//收款人
	private String payee;
	
	//收款人名称
	private String payeeName;
	
	//付款金额
	private double amount;
	
	//付款理由
	private String reason;
	
	//阿里支付宝流水号
	private String aliPayNo;
	
	//预计收费
	private double estimatedCharge;
	
	//参考成功收费
	private double successFees;
	
	//状态
	private int status;
	
	//失败原因
	private String failReason;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAliPayNo() {
		return aliPayNo;
	}

	public void setAliPayNo(String aliPayNo) {
		this.aliPayNo = aliPayNo;
	}

	public double getEstimatedCharge() {
		return estimatedCharge;
	}

	public void setEstimatedCharge(double estimatedCharge) {
		this.estimatedCharge = estimatedCharge;
	}

	public double getSuccessFees() {
		return successFees;
	}

	public void setSuccessFees(double successFees) {
		this.successFees = successFees;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.equals("处理成功") ? SUCCESS : FAIL;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	
	
}
