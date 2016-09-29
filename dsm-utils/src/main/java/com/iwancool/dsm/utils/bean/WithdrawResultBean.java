package com.iwancool.dsm.utils.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 阿里支付宝提款结果 xls
 * @author hch
 *
 */
public class WithdrawResultBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//批次号
	private String batchNo;
	
	//付款日期
	private String paymentDate;
	
	//付款人
	private String drawee;
	
	//账号名称
	private String accountName;
	
	//总金额
	private double totalAmount;
	
	//总笔数
	private int totalNum;
	
	//预计总收费金额
	private double totalEstimatedCharge;
	
	//参考成功收费金额
	private double totalSuccessFees;
	
	//处理的用户流水记录
	private List<AccountStatementBean> statementList;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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

	public void setTotalNum(int toatlNum) {
		this.totalNum = toatlNum;
	}

	public double getTotalEstimatedCharge() {
		return totalEstimatedCharge;
	}

	public void setTotalEstimatedCharge(double totalEstimatedCharge) {
		this.totalEstimatedCharge = totalEstimatedCharge;
	}

	public double getTotalSuccessFees() {
		return totalSuccessFees;
	}

	public void setTotalSuccessFees(double totalSuccessFees) {
		this.totalSuccessFees = totalSuccessFees;
	}

	public List<AccountStatementBean> getStatementList() {
		return statementList;
	}

	public void setStatementList(List<AccountStatementBean> statementList) {
		this.statementList = statementList;
	}
	
}
