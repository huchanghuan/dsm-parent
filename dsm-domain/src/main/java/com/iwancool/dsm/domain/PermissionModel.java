package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

@Entity
@Table(name = "permission")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PermissionModel extends AbstractBaseModel{

	private static final long serialVersionUID = 7776653867935298713L;

	@Id
	private long id;
	
	@Column(name ="name", length = 32, columnDefinition = "char")
	private String name;
	
	@Column(name = "pid")
	private long pid;
	
	@Column(name = "url", length = 50)
    private String url;
	
	@Column(name = "class", length = 250)
	private String clazz;

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

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	
}
