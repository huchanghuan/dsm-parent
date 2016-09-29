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

@Entity
@Table(name = "manager")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ManagerModel extends  AbstractBaseModel{

	private static final long serialVersionUID = -4041656298582241713L;
	
	public static final int ENABLE_STATUS = 0;   	//启用状态
	public static final int DISABLE_STATUS = 1;		//禁用状态

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "username", length = 32, columnDefinition = "char")
	private String username;
	
	@Column(name = "password", length = 32, columnDefinition = "char")
	private String password;
	
	@Column(name = "phone_no", length = 32)
	private String phoneNo;
	
	@Column(name ="email", length = 255)
    private String email;
	
	@Column(name = "roleId")
	private long roleId;
	
	@Column(name = "status", columnDefinition = "tinyint")
	private int status;
	
	@Column
	private long utc;
	
	@Formula("(select r.role_name from role r where r.id = roleId)")
	private String roleName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUtc() {
		return utc;
	}

	public void setUtc(long utc) {
		this.utc = utc;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public ManagerModel() {}

	public ManagerModel(long id, String username, String phoneNo, String email, long roleId, int status, long utc, String roleName) {
		this.id = id;
		this.username = username;
		this.phoneNo = phoneNo;
		this.email = email;
		this.roleId = roleId;
		this.status = status;
		this.utc = utc;
		this.roleName = roleName;
	}
	
	
}
