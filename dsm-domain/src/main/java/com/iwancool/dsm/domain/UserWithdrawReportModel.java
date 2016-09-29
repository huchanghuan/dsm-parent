package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

@Entity
@Table(name = "user_withdraw_report")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserWithdrawReportModel extends AbstractBaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int NOT_EXPORT = 0;			//未导出
	public static final int WITHDRAW = 1; 			//提款中
	public static final int COMPLETE = 2; 			//提款完成
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "date")
	private int date;
	
	//批次号
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "amount")
	private int amount;

	@Column(name = "status", columnDefinition = "tinyint")
	private int status;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getNotExport() {
		return NOT_EXPORT;
	}

	public static int getWithdraw() {
		return WITHDRAW;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
}
