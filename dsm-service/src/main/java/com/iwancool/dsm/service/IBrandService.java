package com.iwancool.dsm.service;

import com.iwancool.dsm.bean.BrandBean;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.BrandModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 品牌 service接口
 * @ClassName IBrandService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午3:41:46
 * @version 1.0.0
 */
public interface IBrandService {

	/**
	 * 保存
	 * @Description (TODO
	 * @param brand
	 * @param columnIdsStr 
	 * @return
	 */
	public ResultResp save(BrandModel brand, String columnIdsStr);
	
	/**
	 * 保存
	 * @Description (TODO
	 * @param brand
	 * @return
	 */
	public ResultResp update(BrandModel brand, String columnIdsStr);
	
	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idsStr
	 * @return
	 */
	public ResultResp deleteBacthBrand(String idsStr);
	
	/**
	 * 根据名称获得brand
	 * @Description (TODO
	 * @param name
	 * @return
	 */
	public BrandBean  getBrand(String name);
	
	/**
	 * 条件查找并分页
	 * @Description (TODO
	 * @param columnId
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public DataGrid<BrandBean> findBrandList(int columnId, int status, String keyword, int offset, int limit);

	/**
	 * 更新状态
	 * @param name
	 * @param visible
	 * @return
	 */
	public ResultResp updateStatus(String name, int visible);
}
