package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 用户关注实体类
 * @Description	TODO
 * @ClassName	UserFocusModel
 * @Date		2016年8月20日 下午5:46:55
 * @Author		huchanghuan
 */
@Entity
@Table(name = "user_focus")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserFocusModel extends AbstractBaseModel{

	private static final long serialVersionUID = -7957686387505365500L;

	//用户ID（我）(联合主键)
	@Id
	@Column(name = "user_id")
	private long userId;
	
	//关注者ID（我关注的人）
	@Id
	@Column(name = "focus_id")
	private long focusId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFocusId() {
		return focusId;
	}

	public void setFocusId(long focusId) {
		this.focusId = focusId;
	}
	
	
}
