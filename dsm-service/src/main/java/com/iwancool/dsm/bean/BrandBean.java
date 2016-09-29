package com.iwancool.dsm.bean;

import com.iwancool.dsm.domain.BrandModel;

/**
 * brand扩展
 * @ClassName BrandBean
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午4:27:57
 * @version 1.0.0
 */
public class BrandBean extends BrandModel{

	private static final long serialVersionUID = 8693336781350049467L;

	private int columnId;
	
	private String columnName;

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	
}
