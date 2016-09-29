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
 * 广告位model
 * @ClassName AdvLocationModel
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月6日 下午2:25:59
 * @version 1.0.0
 */
@Entity
@Table(name = "adv_location")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AdvLocationModel extends AbstractBaseModel{

	private static final long serialVersionUID = -8649585692239261238L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//广告位名称
	@Column(name = "name", length = 16)
	private String name;
	
	//状态
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;
	
	//创建时间
	@Column(name = "create_utc")
	private long createUtc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateUtc() {
		return createUtc;
	}

	public void setCreateUtc(long createUtc) {
		this.createUtc = createUtc;
	}

	
}
