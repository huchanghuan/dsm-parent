package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户喜爱商品实体类
 * @Description	TODO
 * @ClassName	UserGoodsModel
 * @Date		2016年8月20日 下午5:57:36
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_goods")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserGoodsModel extends AbstractBaseModel{

	private static final long serialVersionUID = 238260662168106310L;

	//用户ID
	@Id
	@Column(name = "user_id")
	private long userId;
	
	//商品id
	@Id
	@Column(name = "goods_id")
	private long goodsId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
