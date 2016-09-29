package com.iwancool.dsm.utils.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 表格载体
 * @ClassName DataGrid
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月26日 下午2:10:49
 * @version 1.0.0
 * @param <T>
 */
public class DataGrid<T> implements  Serializable {

	private static final long serialVersionUID = 2198144171818628459L;

	//总记录数
	private long total;
	
	//记录
	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public DataGrid() {}

	public DataGrid(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	
}
