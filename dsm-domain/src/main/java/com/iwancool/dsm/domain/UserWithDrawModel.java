package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户提现实体
 * @Description	TODO
 * @ClassName	UserWithDrawModel
 * @Date		2016年8月20日 下午7:00:04
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_withdraw")
public class UserWithDrawModel extends AbstractBaseModel{

	private static final long serialVersionUID = -5020067849647558053L;

	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	//用户id
	@Column(name = "user_id")
	private long userId;
	
	//提现时间
	@Column(name = "utc")
	private long utc;
	
	//提现金额
	@Column(name = "amount")
	private int amount;
	
	//提现后余额
	@Column(name = "balance")
	private int balance;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
