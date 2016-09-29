package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 品牌实体类
 * @Description	TODO
 * @ClassName	BrandModel
 * @Date		2016年8月20日 下午2:24:22
 * @Author		huchanghuan
 */
@Entity
@Table(name = "brand")
@DynamicInsert(true)
@DynamicUpdate(true)
public class BrandModel extends AbstractBaseModel{

	private static final long serialVersionUID = -6852912348093508642L;
	
	public static final int HIDE = 0;      			//隐藏状态
	public static final int SHOW = 1;				//显示状态

	@Id
	private String name;
	
	//首字母
	@Column(name = "initial", length = 1, columnDefinition = "char")
	private String initial;
	
	@Column(name = "pinyin", length = 16)
	private String pinyin;
	
	@Column(name = "visible", columnDefinition = "tinyint")
	private int visible;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	
}
