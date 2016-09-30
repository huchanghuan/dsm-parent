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

/**剁手达人实体类
 * 
 * @author hch
 *
 */
@Entity
@Table(name = "user_chop_hand")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserChopHandModel extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	//用户ID
	@Column(name = "user_id")
	private long userId;
	
	//本月销售次数
	@Column(name = "sale_frequency")
	private int saleFrequency;
	
	//时间
	@Column(name = "date")
	private int date;

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

	public int getSaleFrequency() {
		return saleFrequency;
	}

	public void setSaleFrequency(int saleFrequency) {
		this.saleFrequency = saleFrequency;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	
	
}
