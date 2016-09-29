package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.ColumnBrandModel;
/**
 * 栏目品牌dao
 * @author hch
 *
 */
public interface IColumnBrandDao extends IGeneralORMDao<ColumnBrandModel, Integer>{

	/**
	 * 根据品牌名查找
	 * @param brandName
	 * @return
	 */
	public List<ColumnBrandModel> findColumnBrandList(String brandName);

}
