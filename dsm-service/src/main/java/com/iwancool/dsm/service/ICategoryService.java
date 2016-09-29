package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.CategoryModel;

/**
 * 二级分类service 接口
 * @ClassName ICategoryService
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:39:49
 * @version 1.0.0
 */
public interface ICategoryService extends IGenericService{

	/**
	 * 查指定pid下的子类
	 * @Description (TODO
	 * @param columnId
	 * @return
	 */
	public List<CategoryModel> findCategoryListByPid(int columnId);

	/**
	 * 保存更新
	 * @Description (TODO
	 * @param category
	 * @return
	 */
	public ResultResp saveOrUpdate(CategoryModel category);

	/**
	 * 删除
	 * @Description (TODO
	 * @param categoryId
	 * @return
	 */
	public ResultResp delete(int categoryId);
	
	/**
	 * 获得
	 * @Description (TODO
	 * @param categoryId
	 * @return
	 */
	public CategoryModel getCategory(int categoryId);

	/**
	 * 更新名称
	 * @Description (TODO
	 * @param id
	 * @param name
	 * @return
	 */
	public ResultResp updateName(int id, String name);

}
