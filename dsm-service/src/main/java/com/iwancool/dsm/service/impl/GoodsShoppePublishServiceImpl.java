package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IGoodsShoppePublishDao;
import com.iwancool.dsm.domain.GoodsShoppePublishModel;
import com.iwancool.dsm.service.IGoodsShoppePublishService;
import com.iwancool.dsm.utils.bean.DataGrid;
import com.iwancool.dsm.utils.util.StringUtil;

/**
 * 商品专柜发布
 * @author hch
 *
 */
@Service(value = "goodsShoppePublishService")
public class GoodsShoppePublishServiceImpl extends AbstractBaseService implements IGoodsShoppePublishService{

	@Resource(name = "goodsShoppePublishDao")
	private IGoodsShoppePublishDao goodsShoppePublishDao;
	
	@Override
	public ResultResp saveOrUpdate(GoodsShoppePublishModel goodsShoppePublish) {
		if (goodsShoppePublish.getIdx() > 0) {
			goodsShoppePublishDao.update(goodsShoppePublish);
		} else {
			goodsShoppePublish.setUtc(new Date().getTime());
			goodsShoppePublishDao.save(goodsShoppePublish);
		}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteGoodsShoppePublish(int id) {
		goodsShoppePublishDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteGoodsShoppePublishByShoppeId(int shoppeId) {
		goodsShoppePublishDao.deleteGoodsShoppePublishByShoppeId(shoppeId);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBatchGoodsShoppePublish(String idStrs) {
		if (StringUtils.isEmpty(idStrs))
			return ResultResp.ERROR;
		goodsShoppePublishDao.deleteBatchGoodsShoppePublish(StringUtil.arrayToIntegerList(idStrs.split(",")));
		return ResultResp.SUCCESS;
	}

	@Override
	public GoodsShoppePublishModel getGoodsShoppePublish(int id) {
		return goodsShoppePublishDao.getByPrimaryKey(id);
	}

	@Override
	public DataGrid<GoodsShoppePublishModel> findGoodsShoppePublishList(int shoppeId, int offset, int limit) {
		List<GoodsShoppePublishModel> goodsShoppePublishList = new ArrayList<GoodsShoppePublishModel>();
		int totalSize = goodsShoppePublishDao.findGoodsShoppePublishList(shoppeId);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			goodsShoppePublishList = goodsShoppePublishDao.findGoodsShoppePublishList(shoppeId, currPage, limit);
		}
		return new DataGrid<GoodsShoppePublishModel>(totalSize, goodsShoppePublishList);
	}

	@Override
	public int saveGoodsShoppePublish(GoodsShoppePublishModel goodsShoppePublish) {
		
		return (Integer) goodsShoppePublishDao.save(goodsShoppePublish);
	}

}
