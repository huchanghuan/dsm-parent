package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品被喜欢实体类
 * @Description	TODO
 * @ClassName	GoodsUserModel
 * @Date		2016年8月20日 下午6:01:15
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsUserModel extends AbstractBaseModel {

	private static final long serialVersionUID = 8203279621281333675L;
	
	//商品id
	@Id
	@Column(name = "goods_id")
	private long goodsId;

	//用户ID
	@Id
	@Column(name = "user_id")
	private long userId;

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (goodsId ^ (goodsId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsUserModel other = (GoodsUserModel) obj;
		if (goodsId != other.goodsId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
		
	
}
