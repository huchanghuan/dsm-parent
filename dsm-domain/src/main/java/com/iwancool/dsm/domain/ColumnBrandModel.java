package com.iwancool.dsm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iwancool.dsm.base.AbstractBaseModel;

/**
 * 栏目品牌实体类
 * @Description	TODO
 * @ClassName	ColumnBrandModel
 * @Date		2016年8月20日 下午2:35:08
 * @Author		huchanghuan
 */
@Entity
@Table(name = "column_brand")
public class ColumnBrandModel extends AbstractBaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;

	@Column(name = "column_id")
	private int columnId;
	// 品牌名称
	@Column(name = "brand_name", length = 32)
	private String brandName;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
