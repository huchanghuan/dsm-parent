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
 * 精选model
 * @ClassName SelectionModel
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月6日 上午11:46:15
 * @version 1.0.0
 */
@Entity
@Table(name = "selection")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SelectionModel extends AbstractBaseModel{

	private static final long serialVersionUID = 6527226615914094381L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//商品ID
	@Column(name = "goods_id", length = 20)
	private long goodsId;
	
	//排序值
	@Column(name = "sortNo")
	private int sortNo;
	
	//创建时间
	@Column(name = "create_utc")
	private long createUtc;
	
	//商品名
	@Formula("(select g.name from goods g where g.id = goods_id)")
	private String goodsName;
	
	//图片
	@Formula("(select g.images from goods g where g.id = goods_id)")
	private String images;
	
	@Formula("(select g.status from goods g where g.id = goods_id)")
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public long getCreateUtc() {
		return createUtc;
	}

	public void setCreateUtc(long createUtc) {
		this.createUtc = createUtc;
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
