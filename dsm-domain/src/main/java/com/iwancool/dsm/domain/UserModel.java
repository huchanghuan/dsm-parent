package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;


@Entity
@Table(name = "user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserModel extends AbstractBaseModel{

	private static final long serialVersionUID = -2206522336844207692L;

	//用户ID，主键，按某特定规则生成（如何保证全局唯一性？）
	@Id
	private long id;
	
	//密码
	@Column(name = "passwd", length = 36)
	private String passwd;
	
	//手机号码（应用需要保证其全局唯一性）
	@Column(name = "phone", length = 16, columnDefinition = "char")
	private String phone;
	
	//Email（应用需要保证其全局唯一性）
	@Column(name = "email", length = 64)
	private String email;
	
	//性别 -1-女性 0-未知 1-男性
	@Column(name = "sex", columnDefinition = "tinyint default 0")
	private int sex;
	
	//昵称
	@Column(name = "nickname", length = 64)
	private String nickname;
	
	//头像图片url
	@Column(name = "photo", length = 512)
	private String photo;
	
	//个性签名
	@Column(name = "sign", length = 512)
	private String sign;
	
	//用户所在地
	@Column(name = "addr", length = 512)
	private String addr;
	
	//默认收货地址
	@Column(name = "express_addr")
	private long expressAddr;
	
	//状态 0-正常， 1-禁用\
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public long getExpressAddr() {
		return expressAddr;
	}

	public void setExpressAddr(long expressAddr) {
		this.expressAddr = expressAddr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
