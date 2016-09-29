package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.GoodsShoppePublishModel;

/**
 * 商品专柜发布DAO
 * @author hch
 *
 */
public interface IGoodsShoppePublishDao extends IGeneralORMDao<GoodsShoppePublishModel, Integer>{

	/**
	 * 删除专柜下商品
	 * @param shoppeId
	 */
	public void deleteGoodsShoppePublishByShoppeId(int shoppeId);

	/**
	 * 批量删除
	 * @param idList
	 */
	public void deleteBatchGoodsShoppePublish(List<Integer> idList);

	/**
	 * 专柜下商品数
	 * @param shoppeId
	 * @return
	 */
	public int findGoodsShoppePublishList(int shoppeId);

	/**
	 * 专柜下商品数并分页
	 * @param shoppeId
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<GoodsShoppePublishModel> findGoodsShoppePublishList(int shoppeId, int currPage, int limit);

}
