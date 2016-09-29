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
 * 商品活动发布实体
 * @Description	TODO
 * @ClassName	GoodsActivityPublishModel
 * @Date		2016年8月20日 下午7:58:19
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_activity_publish")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsActivityPublishModel extends AbstractBaseModel{

	private static final long serialVersionUID = 4352054734798313032L;

	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	//活动id
	@Column(name = "activity_id")
	private int activityId;
	
	//商品id
	@Column(name = "goods_id")
	private long goodsId;
	
	//发布时间utc，单位ms
	@Column(name = "utc")
	private long utc;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getUtc() {
		return utc;
	}

	public void setUtc(long utc) {
		this.utc = utc;
	}
	
	
}
