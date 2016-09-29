package com.iwancool.dsm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IColumnBrandDao;
import com.iwancool.dsm.domain.ColumnBrandModel;
import com.iwancool.dsm.service.IColumnBrandService;

/**
 * 栏目品牌service实现类
 * @author huchanghuan
 *
 */
@Service(value = "columnBrandService")
public class ColumnBrandServiceImpl extends AbstractBaseService implements IColumnBrandService{

	@Resource(name = "columnBrandDao")
	private IColumnBrandDao columnBrandDao;
	
	@Override
	public ResultResp save(String brandName, String columnIdsStr) {
		log.debug("save -> brandName:" + brandName + ",columnIdsStr:" + columnIdsStr);
		 for (String idStr : columnIdsStr.split(",")) {
			ColumnBrandModel columnBrand = new ColumnBrandModel();
			columnBrand.setColumnId(Integer.valueOf(idStr));
			columnBrand.setBrandName(brandName);
			columnBrandDao.save(columnBrand);
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp update(String brandName, String columnIdsStr) {
		List<ColumnBrandModel> columnBrandList = findColumnBrandList(brandName);
		if (null == columnBrandList || columnBrandList.isEmpty())
			save(brandName, columnIdsStr);
		
		String[] idsStr = columnIdsStr.split(",");
		if (idsStr.length >= columnBrandList.size()) {
			int i = 0;
			for (int len = columnBrandList.size(); i < len; i++) {
				ColumnBrandModel columnBrand = columnBrandList.get(i);
				columnBrand.setColumnId(Integer.valueOf(idsStr[i]));
				columnBrandDao.update(columnBrand);
			}
			if (idsStr.length != columnBrandList.size()) {
				while (i < idsStr.length) {
					ColumnBrandModel columnBrand = new ColumnBrandModel();
					columnBrand.setBrandName(brandName);
					columnBrand.setColumnId(Integer.valueOf(idsStr[i]));
					columnBrandDao.save(columnBrand);
					i++;
				}
			}
		} else {
			for (int i = 0,len = columnBrandList.size(); i < len; i++) {
				ColumnBrandModel columnBrand = columnBrandList.get(i);
				if (i < idsStr.length) {
					columnBrand.setColumnId(Integer.valueOf(idsStr[i]));
					columnBrandDao.update(columnBrand);
				} else
					columnBrandDao.delete(columnBrand);
			}
		}
		
		return ResultResp.SUCCESS;
	}

	@Override
	public List<ColumnBrandModel> findColumnBrandList(String brandName) {
		log.debug("findColumnBrandList -> " + brandName);
		return columnBrandDao.findColumnBrandList(brandName);
	}

}
