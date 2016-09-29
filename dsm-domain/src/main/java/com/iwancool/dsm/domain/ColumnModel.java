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
 * 一级分类
 * @Description	TODO
 * @ClassName	ColumnModel
 * @Date		2016年8月20日 下午2:06:58
 * @Author		huchanghuan
 */
@Entity
@Table(name="`column`")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ColumnModel extends AbstractBaseModel{

	private static final long serialVersionUID = 3770158998472362190L;
	
	public static final int SHOW = 1;					 //显示
	public static final int HIDE = 0;                    //隐藏
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 16)
	private String name;
	
	@Column(name = "visible", columnDefinition = "tinyint")
	private int visible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}


	
}
