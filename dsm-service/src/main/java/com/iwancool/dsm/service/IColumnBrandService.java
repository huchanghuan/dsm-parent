package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.ColumnBrandModel;
import com.iwancool.dsm.service.IGenericService;

/**
 * 栏目品牌 service
 * @author hch
 *
 */
public interface IColumnBrandService extends IGenericService{

	/**
	 * 保存
	 * @param brandName
	 * @param columnIdsStr
	 * @return
	 */
	public ResultResp save(String brandName, String columnIdsStr);
	
	/**
	 * 更新
	 * @param brandName
	 * @param columnIdsStr
	 * @return
	 */
	public ResultResp update(String brandName, String columnIdsStr);
	
	/**
	 * 根据品牌名查找
	 * @param brandName
	 * @return
	 */
	public List<ColumnBrandModel> findColumnBrandList(String brandName);
}
