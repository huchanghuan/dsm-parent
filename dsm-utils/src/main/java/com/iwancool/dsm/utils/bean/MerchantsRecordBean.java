package com.iwancool.dsm.utils.bean;

import java.io.Serializable;

/**
 * 导出的商户记录
 * @ClassName MerchantsRecordBean
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月28日 上午11:01:58
 * @version 1.0.0
 */
public class MerchantsRecordBean implements Serializable{

	/**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	//商户号
	private String userId;
	
	//收款人email
	private String payee;
	
	//收款人姓名
	private String payeeName;
	
	//付款金额
	private double amount;
	
	//付款理由
	private String reason;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
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
	
	
}
