package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 第三方用户实体类
 * @Description	TODO
 * @ClassName	UserTrirdModel
 * @Date		2016年8月20日 下午5:23:25
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_third")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserTrirdModel extends AbstractBaseModel{

	private static final long serialVersionUID = -2002330144447343324L;

	//用户ID，主键
	@Id
	private long id;
	
	//qq
	@Column(name = "qq_id", length = 36, columnDefinition = "char")
	private String qqId;
	
	//qq昵称
	@Column(name = "qq_name", length = 64)
	private String qqName;
	
	//微信号
	@Column(name = "weixin_id", length = 36, columnDefinition = "char")
	private String weixinId;
	
	//微信昵称
	@Column(name = "weixin_name", length = 64)
	private String weixinName;
	
	//微博id
	@Column(name = "weibo_id", length = 36, columnDefinition = "char")
	private String weiboId;
	
	//微博昵称
	@Column(name = "weibo_name", length = 64)
	private String weiboName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getQqName() {
		return qqName;
	}

	public void setQqName(String qqName) {
		this.qqName = qqName;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getWeixinName() {
		return weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public String getWeiboName() {
		return weiboName;
	}

	public void setWeiboName(String weiboName) {
		this.weiboName = weiboName;
	}
	
	
}
