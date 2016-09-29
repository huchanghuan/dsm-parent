package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.bean.BrandBean;
import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IBrandDao;
import com.iwancool.dsm.domain.BrandModel;
import com.iwancool.dsm.domain.ColumnBrandModel;
import com.iwancool.dsm.service.IBrandService;
import com.iwancool.dsm.service.IColumnBrandService;
import com.iwancool.dsm.utils.bean.DataGrid;


/**
 * 品牌service实现类
 * @ClassName BrandServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月30日 下午3:42:48
 * @version 1.0.0
 */
@Service(value = "brandService")
public class BrandServiceImpl extends AbstractBaseService implements IBrandService{

	@Resource(name = "brandDao")
	private IBrandDao brandDao;
	
	@Resource(name = "columnBrandService")
	private IColumnBrandService columnBrandService;

	@Override
	public ResultResp save(BrandModel brand, String columnIdsStr) {
		log.debug("saveOrUpdate -> brand:" + brand + ",columnIdsStr:" + columnIdsStr);
		if (null != brandDao.getByPrimaryKey(brand.getName())) return ResultResp.createResult(ResultCode.NAME_HAS_EXITS, "品牌已存在");
		
		brandDao.save(brand);
		columnBrandService.save(brand.getName(), columnIdsStr);
		return ResultResp.SUCCESS;
	}
	
	@Override
	public ResultResp update(BrandModel brand, String columnIdsStr) {
		log.debug("saveOrUpdate -> " + brand);
		columnBrandService.update(brand.getName(), columnIdsStr);
		brandDao.update(brand);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBacthBrand(String idsStr) {
		log.debug("deleteBacthBrand -> " + idsStr);
		if (StringUtils.isEmpty(idsStr)) return ResultResp.ERROR;
		
		List<String> idList = Arrays.asList(idsStr.split(","));
		if (null == idList || idList.isEmpty()) return ResultResp.ERROR;
		
		brandDao.deleteBacthBrand(idList);
		return ResultResp.SUCCESS;
	}

	@Override
	public BrandBean getBrand(String name) {
		log.debug("getBrand -> " + name);
		BrandModel brandModel = brandDao.getByPrimaryKey(name);
		BrandBean brand = new BrandBean();
		if (null != brandModel) {
			
			BeanUtils.copyProperties(brandModel, brand);
			List<ColumnBrandModel> columnBrandList = columnBrandService.findColumnBrandList(name);
			if(null != columnBrandList) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < columnBrandList.size(); i++) {
					sb.append(columnBrandList.get(i).getColumnId()+",");
				}
				brand.setColumnName(sb.substring(0, sb.length()-1));
			}
		}
		
		return brand;
	}

	@Override
	public DataGrid<BrandBean> findBrandList(int columnId, int status, String keyword, int offset, int limit) {
		log.debug("findBrandList -> columnId:" + columnId + ",keyword:" + keyword + ",offset:" + offset + ",limit:" + limit);
		List<BrandBean> brandBeanList = new ArrayList<BrandBean>();
		int totalSize = brandDao.findBrandListCount(columnId, status, keyword);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			List<Object[]> brandList = brandDao.findBrandList(columnId, status, keyword, currPage, limit);
			if (null != brandList) {
				for (Object[] obj : brandList) {
					BrandBean brandBean = new BrandBean();
					
					brandBean.setName((String)obj[0]);
					brandBean.setInitial(((Character)obj[1]).toString());
					brandBean.setPinyin((String)obj[2]);
					brandBean.setVisible(((Byte)obj[3]).intValue());
					brandBean.setColumnId((Integer)obj[4]);
					brandBean.setColumnName((String)obj[5]);
					brandBeanList.add(brandBean);
				}
			}
		}
		return new DataGrid<BrandBean>(totalSize, brandBeanList);
	}

	@Override
	public ResultResp updateStatus(String name, int visible) {
		log.debug("updateStatus -> name:" + name + ",visible:" + visible);
		BrandModel brand = brandDao.getByPrimaryKey(name);
		if (null == brand) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
		
		brand.setVisible(visible);
		brandDao.update(brand);
		return ResultResp.SUCCESS;
	}
}
