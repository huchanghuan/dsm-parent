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
 * 广告位的资源
 * @ClassName AdvResourceModel
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月8日 下午6:19:28
 * @version 1.0.0
 */
@Entity
@Table(name = "adv_resource")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AdvResourceModel extends AbstractBaseModel{

	/**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6144715838058094895L;
	
	public static final int PICTURE = 0;					//图片
	public static final int VIDEO = 1;						//视频
	public static final int TEXT = 2;						//文本
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//广告位ID
	@Column(name = "location_id")
	private long locationId; 
	//类型
	
	@Column(name = "type", columnDefinition = "tinyint")
	private int type;
	
	//内容
	@Column(name = "content", length = 255)
	private String content;
	
	//跳转的URL
	@Column(name = "url", length = 255)
	private String url;
	
	//生效时间
	@Column(name = "start_utc")
	private long startUtc;
	
	//结束时间
	@Column(name = "end_utc")
	private long endUtc;
	
	//
	@Column(name = "sortNo")
	private int sortNo;
	
	//创建时间
	@Column(name = "create_utc")
	private long createUtc;
	
	//备注
	@Column(name = "remark")
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getStartUtc() {
		return startUtc;
	}

	public void setStartUtc(long startUtc) {
		this.startUtc = startUtc;
	}

	public long getEndUtc() {
		return endUtc;
	}

	public void setEndUtc(long endUtc) {
		this.endUtc = endUtc;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
