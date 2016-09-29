package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户账号信息实体类
 * @Description	TODO
 * @ClassName	UserAccountModel
 * @Date		2016年8月20日 下午5:32:23
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserAccountModel extends AbstractBaseModel{

	private static final long serialVersionUID = -4572231263205492165L;

	//用户ID，主键
	@Id
	private long id;
	
	//支付宝账号\
	@Column(name = "alipay_no" ,length = 32)
	private String alipayNo;
	
	//真实姓名
	@Column(name = "realname", length = 32)
	private String realname;
	
	//账户余额
	@Column(name = "balance")
	private int balance;
	
	//总收入（每卖出一件宝贝成交价累加）
	@Column(name = "income")
	private int income;
	
	//提现中金额
	@Column(name = "withdraw")
	private int withdraw;
	
	//积分
	@Column(name = "score")
	private int score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlipayNo() {
		return alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
