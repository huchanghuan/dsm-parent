package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 尺寸实体类
 * @Description	TODO
 * @ClassName	SizeModel
 * @Date		2016年8月20日 下午2:39:28
 * @Author		huchanghuan
 */
@Entity
@Table(name = "size")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SizeModel extends AbstractBaseModel {

	private static final long serialVersionUID = 4318722041646105621L;

	// 尺寸size ID，主键（01xx-衣服，02xx-裤子，03xx-鞋子）
	@Id
	private int id;
	// 实际尺寸描述（字符串形式）
	@Column(name = "size", length = 8, columnDefinition = "char")
	private String size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
