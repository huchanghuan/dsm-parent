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
 * 商品专柜报名实体
 * @Description	TODO
 * @ClassName	GoodsShoppeEnrollModel
 * @Date		2016年8月20日 下午7:24:24
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_shoppe_enroll")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsShoppeEnrollModel extends AbstractBaseModel{

	private static final long serialVersionUID = 1L;

	//自增主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	
	//专柜id
	@Column(name = "shoppe_id")
	private int shoppeId;
	
	//商品id
	@Column(name = "goods_id")
	private long goodsId;
	
	//报名时间utc，单位ms
	@Column(name = "utc")
	private long utc;
	//状态 0-未审核，1-审核通过，2-审核不通过
	@Column(name = "stauts", columnDefinition = "tinyint")
	private int stauts;
	
	//备注
	@Column(name = "remark", length = 64)
	private String remark;

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

	public int getStauts() {
		return stauts;
	}

	public void setStauts(int stauts) {
		this.stauts = stauts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
