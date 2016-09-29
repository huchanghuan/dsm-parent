package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户统计数据
 * @Description	TODO
 * @ClassName	UserStatisticsModel
 * @Date		2016年8月20日 下午5:26:33
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_statistics")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserStatisticsModel extends AbstractBaseModel{

	private static final long serialVersionUID = -5685782464657445277L;

	@Id
	private long id;
	
	//用户发布的宝贝数量
	@Column(name = "goods_count")
	private int goodsCount;
	
	//喜欢宝贝数
	@Column(name = "fav_goods_count")
	private int fav_goods_count;
	
	//关注数
	@Column(name = "focus_count")
	private int focusCount;
	
	//粉丝数
	@Column(name = "fans_count")
	private int fansCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public int getFav_goods_count() {
		return fav_goods_count;
	}

	public void setFav_goods_count(int fav_goods_count) {
		this.fav_goods_count = fav_goods_count;
	}

	public int getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(int focusCount) {
		this.focusCount = focusCount;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	
	
}
