package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.BrandModel;

/**
 * 品牌dao 接口
 * @ClassName IBrandDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午3:44:47
 * @version 1.0.0
 */
public interface IBrandDao extends IGeneralORMDao<BrandModel, String>{
	
	/**
	 * 查记录数
	 * @Description (TODO
	 * @param columnId
	 * @param status
	 * @param keyword
	 * @return
	 */
	public int findBrandListCount(int columnId, int status, String keyword);

	/**
	 * 查记录并分页
	 * @Description (TODO
	 * @param columnId		一级分类ID
	 * @param status		状态
	 * @param keyword		关键词
	 * @param currPage		当前页
	 * @param limit			页大小
	 * @return
	 */
	public List<Object[]> findBrandList(int columnId, int status, String keyword, int currPage, int limit);

	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idList
	 */
	public void deleteBacthBrand(List<String> idList);

}
