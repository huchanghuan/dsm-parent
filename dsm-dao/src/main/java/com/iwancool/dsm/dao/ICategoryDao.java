package com.iwancool.dsm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iwancool.dsm.domain.CategoryModel;

/**
 * categroyDao 接口
 * @ClassName ICategoryDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:43:23
 * @version 1.0.0
 */
@Repository(value = "categoryDao")
public interface ICategoryDao extends IGeneralORMDao<CategoryModel, Integer>{

	/**
	 * 根据pid查找
	 * @Description (TODO
	 * @param columnId
	 * @return
	 */
	public List<CategoryModel> findCategoryListByPid(int columnId);

	/**
	 * 名称查找
	 * @Description (TODO
	 * @param name
	 * @return
	 */
	public CategoryModel getCategory(String name);
	
	/**
	 * 根据Fid获得
	 * @Description (TODO
	 * @param pid
	 * @return
	 */
	public int getMaxId(int pid);

}
