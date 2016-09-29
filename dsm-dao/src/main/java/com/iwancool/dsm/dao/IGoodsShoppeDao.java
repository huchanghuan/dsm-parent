package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.GoodsShoppeModel;

/**
 * 专柜dao
 * @author hch
 *
 */
public interface IGoodsShoppeDao extends IGeneralORMDao<GoodsShoppeModel, Integer>{

	/**
	 * 查所有专柜
	 * @return
	 */
	public List<GoodsShoppeModel> findAllGoodsShoppe();

	/**
	 * 更新状态
	 * @param idList
	 * @param status
	 */
	public void updateStatus(List<Integer> idList, int status);

	/**
	 * 排序
	 * @param idList
	 * @param sortNoList
	 */
	public void updateSort(List<Integer> idList, List<Integer> sortNoList);


	/**
	 * 获得当前最大的ID
	 * @return
	 */
	public int getMaxId();
}
