package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.ICategoryDao;
import com.iwancool.dsm.domain.CategoryModel;
import com.iwancool.dsm.service.ICategoryService;

/**
 * categoryService 实现类
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午3:41:07
 * @version 1.0.0
 */
@Service(value = "categoryService")
public class CategoryServiceImpl extends AbstractBaseService implements ICategoryService{

	@Resource(name = "categoryDao")
	private ICategoryDao categoryDao;

	@Override
	public List<CategoryModel> findCategoryListByPid(int columnId) {
		log.debug("findCategoryListByPid -> " + columnId);
		List<CategoryModel> categoryList = categoryDao.findCategoryListByPid(columnId);
		if (null != categoryList) return categoryList;
		return new ArrayList<CategoryModel>();
	}

	@Override
	public ResultResp saveOrUpdate(CategoryModel category) {
		log.debug("saveOrUpdate -> " + category);
		if (category.getId() <= 0) {
			int id = categoryDao.getMaxId(category.getPid());
			id = id > 0 ? id : category.getPid() * 1000 + 1;
			category.setId(id);
		}
		categoryDao.saveOrUpdate(category);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp delete(int categoryId) {
		log.debug("delete -> " + categoryId);
		categoryDao.deleteByPrimaryKey(categoryId);
		return ResultResp.SUCCESS;
	}

	@Override
	public CategoryModel getCategory(int categoryId) {
		log.debug("getCategory -> " + categoryId);
		return categoryDao.getByPrimaryKey(categoryId);
	}

	@Override
	public ResultResp updateName(int id, String name) {
		log.debug("updateName -> id:" + id + ",name:" + name);
		CategoryModel category = categoryDao.getCategory(name);
		
		if (null != category && category.getId() != id) return ResultResp.createResult(ResultCode.NAME_HAS_EXITS, "名称已存在");
		
		CategoryModel categoryModel = categoryDao.getByPrimaryKey(id);
		if (null == categoryModel) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
		
		categoryModel.setName(name);
		categoryDao.update(categoryModel);
		return ResultResp.SUCCESS;
	}
}
