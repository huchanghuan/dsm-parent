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

/**
 * 用户提现记录实体
 * @author hch
 *
 */
@Entity
@Table(name = "user_withdraw_record")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserWithdrawRecordModel extends  AbstractBaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int DEAL = 0;						//处理中
	public static final int WITHDRAW_SUCCESS = 1; 			//完成
	public static final int WITHDRAW_FAIL = 2; 				//失败
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "utc")
	private long utc;
	
	@Column(name = "request_amount")
	private int requestAmount;
	
	@Column(name = "pay_amount")
	private int payAmount;
	
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

	public long getUtc() {
		return utc;
	}

	public void setUtc(long utc) {
		this.utc = utc;
	}

	public int getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
