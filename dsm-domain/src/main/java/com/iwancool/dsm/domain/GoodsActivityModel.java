package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 商品活动表
 * @Description	TODO
 * @ClassName	GoodsActivityModel
 * @Date		2016年8月20日 下午7:42:03
 * @Author		huchanghuan
 */
@Entity
@Table(name = "goods_activity")
@DynamicInsert(true)
@DynamicUpdate(true)
public class GoodsActivityModel extends AbstractBaseModel {

	private static final long serialVersionUID = 7541158952915932737L;

	//活动id
	@Id
	private int id;
	
	//活动主题
	@Column(name = "title", length = 64)
	private String title;
	
	//活动描述
	@Column(name = "description", length = 1024)
	private String description;
	
	//活动宣传图
	@Column(name = "image", length = 128)
	private String image;
	
	//活动细则
	@Column(name = "detail", length = 2048)
	private String detail;
	
	//活动截至时间, utc时间，单位ms，-1表示活动无截至时间，长期有效
	@Column(name = "end_utc")
	private long endUtc;
	
	//活动状态 0-已结束，1-正在进行
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getEndUtc() {
		return endUtc;
	}

	public void setEndUtc(long endUtc) {
		this.endUtc = endUtc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
