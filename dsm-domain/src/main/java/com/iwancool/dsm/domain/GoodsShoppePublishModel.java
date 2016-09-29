package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品专柜发布实体
 * @Description	TODO
 * @ClassName	GoodsShoppePublishModel
 * @Date		2016年8月20日 下午7:34:46
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_shoppe_publish")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsShoppePublishModel extends AbstractBaseModel{

	private static final long serialVersionUID = 6281399070069745569L;

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
	
	//发布时间utc，单位ms
	@Column(name = "utc")
	private long utc;
	
	//商品名
	@Formula("(select g.name from goods g where g.id = goods_id)")
	private String goodsName;
		
	//图片
	@Formula("(select g.images from goods g where g.id = goods_id)")
	private String images;
		
	@Formula("(select g.status from goods g where g.id = goods_id)")
	private int status;

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
