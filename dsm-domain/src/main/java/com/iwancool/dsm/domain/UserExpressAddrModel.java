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
 * 用户收货地址实体
 * @Description	TODO
 * @ClassName	UserExpressAddrModel
 * @Date		2016年8月20日 下午7:06:35
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_express_addr")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserExpressAddrModel extends AbstractBaseModel {

	private static final long serialVersionUID = 6641997101402490513L;

	//主键, 配合id_generate生成
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//所属用户id
	@Column(name = "user_id")
	private long userId;
	
	//接收者姓名
	@Column(name = "name", length = 32)
	private String name;
	
	//联系人电话
	@Column(name = "phone", length = 16)
	private String phone;
	
	//邮编
	@Column(name = "postcode", length = 10)
	private String postcode;
	
	//收货地址
	@Column(name = "addr", length = 512)
	private String addr;
	
	//生成时间utc（单位：ms）
	@Column(name = "create_utc")
	private long createUtc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public long getCreateUtc() {
		return createUtc;
	}

	public void setCreateUtc(long createUtc) {
		this.createUtc = createUtc;
	}
	
	
}
