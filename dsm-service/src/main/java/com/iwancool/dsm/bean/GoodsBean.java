package com.iwancool.dsm.bean;

import com.iwancool.dsm.domain.GoodsModel;

/**
 * GoodsModel 扩展
 * @ClassName GoodsBean
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月1日 上午9:40:36
 * @version 1.0.0
 */
public class GoodsBean extends GoodsModel{

	private static final long serialVersionUID = 1728485587716204970L;

	private String username;
	
	private String phone;
	
	private String columnName;
	
	private String categoryName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
