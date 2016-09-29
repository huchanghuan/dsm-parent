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
 * 商品专柜推荐实体
 * @Description	TODO
 * @ClassName	GoodsShoppeRecommendModel
 * @Date		2016年8月20日 下午7:39:48
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_shoppe_recommend")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsShoppeRecommendModel extends AbstractBaseModel {

	private static final long serialVersionUID = -3586051276396715345L;

	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	//专柜id
	@Column(name = "shoppe_id")
	private int shoppeId;
	
	//推介商品id
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

	public int getShoppeId() {
		return shoppeId;
	}

	public void setShoppeId(int shoppeId) {
		this.shoppeId = shoppeId;
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
