package com.iwancool.dsm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IGoodsShoppeDao;
import com.iwancool.dsm.domain.GoodsShoppeModel;
import com.iwancool.dsm.service.IGoodsShoppePublishService;
import com.iwancool.dsm.service.IGoodsShoppeService;
import com.iwancool.dsm.utils.util.StringUtil;

/**
 * 专柜Service实现类
 * @author hch
 *
 */
@Service(value = "goodsShoppeService")
public class GoodsShoppeServiceImpl extends AbstractBaseService implements IGoodsShoppeService{

	@Resource(name = "goodsShoppeDao")
	private IGoodsShoppeDao goodsShoppeDao;
	
	@Resource(name = "goodsShoppePublishService")
	private IGoodsShoppePublishService goodsShoppePublishService;
	
	@Override
	public ResultResp saveOrUpdate(GoodsShoppeModel goodsShoppe) {
		if (goodsShoppe.getId() > 0) {
			goodsShoppeDao.update(goodsShoppe);
		} else {
			goodsShoppe.setId(goodsShoppeDao.getMaxId() + 1);
			goodsShoppeDao.save(goodsShoppe);
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteGoodsShoppe(int id) {
		goodsShoppePublishService.deleteGoodsShoppePublishByShoppeId(id);
		goodsShoppeDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public GoodsShoppeModel getGoodsShoppe(int id) {
		
		return goodsShoppeDao.getByPrimaryKey(id);
	}

	@Override
	public List<GoodsShoppeModel> findAllGoodsShoppe() {
		
		return goodsShoppeDao.findAllGoodsShoppe();
	}

	@Override
	public ResultResp updateStatus(String idStrs, int status) {
		if (StringUtils.isEmpty(idStrs))
			return ResultResp.ERROR;
		
		goodsShoppeDao.updateStatus(StringUtil.arrayToIntegerList(idStrs.split(",")), status);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp updateSort(String idsStrs, String sortNos) {
		if (StringUtils.isEmpty(idsStrs) || StringUtils.isEmpty(sortNos))
			return ResultResp.ERROR;
		
		goodsShoppeDao.updateSort(StringUtil.arrayToIntegerList(idsStrs.split(",")), StringUtil.arrayToIntegerList(sortNos.split(",")));
		return ResultResp.SUCCESS;
	}
	

}
