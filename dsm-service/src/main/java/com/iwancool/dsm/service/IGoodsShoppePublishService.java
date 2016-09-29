package com.iwancool.dsm.service;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.GoodsShoppePublishModel;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 商品专柜发布Service
 * @author hch
 *
 */
public interface IGoodsShoppePublishService extends IGenericService{

	/**
	 * 保存或者更新
	 * @param goodsShoppePublish
	 * @return
	 */
	public ResultResp saveOrUpdate(GoodsShoppePublishModel goodsShoppePublish);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ResultResp deleteGoodsShoppePublish(int id);
	
	/**
	 * 通过专柜ID删除
	 * @param shoppeId
	 * @return
	 */
	public ResultResp deleteGoodsShoppePublishByShoppeId(int shoppeId);
	
	/**
	 *批量删除 
	 * @param idStrs
	 * @return
	 */
	public ResultResp deleteBatchGoodsShoppePublish(String idStrs);
	
	/**
	 * 获得发布的专柜商品
	 * @param id
	 * @return
	 */
	public GoodsShoppePublishModel getGoodsShoppePublish(int id);
	
	/**
	 * 根据专柜ID查找
	 * @param shoppeId
	 * @return
	 */
	public DataGrid<GoodsShoppePublishModel> findGoodsShoppePublishList(int shoppeId, int offset, int limit);

	/**
	 * 保存
	 * @param goodsShoppePublish
	 * @return
	 */
	public int saveGoodsShoppePublish(GoodsShoppePublishModel goodsShoppePublish);
}
