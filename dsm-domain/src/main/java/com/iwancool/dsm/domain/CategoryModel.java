package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 二级分类
 * @Description	TODO
 * @ClassName	CategoryModel
 * @Date		2016年8月20日 下午2:19:07
 * @Author		huchanghuan
 */
@Entity
@Table(name = "category")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CategoryModel extends AbstractBaseModel{

	private static final long serialVersionUID = 906551651182026163L;
	
	@Id
	private int id;
	
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "name", length = 16)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
