package com.iwancool.dsm.utils.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 支付宝导出载体类
 * @ClassName WithdrawExportBean
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月28日 上午11:07:12
 * @version 1.0.0
 */
public class WithdrawExportBean implements Serializable{

	/**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	//批次号
	private String batchNo;
	
	//付款日期
	private String payDay;
	
	//付款人email
	private String drawee;
	
	//账户名称
	private String accountName;
	
	//总金额
	private double totalAmount;
	
	//总笔数
	private int totalNum;
	
	//商户方列表
	private List<MerchantsRecordBean> merchantsRecordList;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPayDay() {
		return payDay;
	}

	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}

	public String getDrawee() {
		return drawee;
	}

	public void setDrawee(String drawee) {
		this.drawee = drawee;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<MerchantsRecordBean> getMerchantsRecordList() {
		return merchantsRecordList;
	}

	public void setMerchantsRecordList(List<MerchantsRecordBean> merchantsRecordList) {
		this.merchantsRecordList = merchantsRecordList;
	}
	
	
}
