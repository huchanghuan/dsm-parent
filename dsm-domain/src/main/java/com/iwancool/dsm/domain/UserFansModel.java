package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户粉丝实体类
 * @Description	TODO
 * @ClassName	UserFansModel
 * @Date		2016年8月20日 下午5:48:58
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_fans")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserFansModel extends AbstractBaseModel {

	private static final long serialVersionUID = -5791076386514678922L;

	//用户ID（我）
	@Id
	@Column(name = "user_id")
	private long userId;
	
	//粉丝ID（我的粉丝）
	@Id
	@Column(name= "fans_id")
	private long fansId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFansId() {
		return fansId;
	}

	public void setFansId(long fansId) {
		this.fansId = fansId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (fansId ^ (fansId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFansModel other = (UserFansModel) obj;
		if (fansId != other.fansId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
