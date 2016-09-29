package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品专柜实体类
 * @Description	TODO
 * @ClassName	GoodsShoppeModel
 * @Date		2016年8月20日 下午7:19:34
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_shoppe")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsShoppeModel extends AbstractBaseModel{

	private static final long serialVersionUID = -3789073762857784773L;

	//专柜id
	@Id
	private int id;
	
	//专柜名称
	@Column(name = "title", length = 64)
	private String title;
	
	//专柜广告语
	@Column(name = "slogan", length = 128)
	private String slogan;
	
	//专柜描述
	@Column(name= "description", length = 1024)
	private String description;
	
	//专柜宣传图1
	@Column(name = "image1", length = 128)
	private String image1;
	
	//专柜宣传图2
	@Column(name = "image2", length = 128)
	private String image2;
	
	//状态 0-关闭，1-开放
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;
	
	//专柜序号，排序字段(用于调整各个专柜展示的顺序)
	@Column(name = "ordinal")
	private int ordinal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	
	
}
