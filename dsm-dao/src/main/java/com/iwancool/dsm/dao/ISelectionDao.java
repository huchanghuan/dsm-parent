package com.iwancool.dsm.dao;

import java.util.List;

import com.iwancool.dsm.domain.SelectionModel;

/**
 * 精选dao
 * @ClassName ISelectionDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午10:30:18
 * @version 1.0.0
 */
public interface ISelectionDao extends IGeneralORMDao<SelectionModel, Long> {

	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idList
	 */
	public void deleteBatchSelection(List<Long> idList);

	/**
	 * 排序
	 * @Description (TODO
	 * @param idList
	 * @param sortNoList
	 */
	public void updateSort(List<Long> idList, List<Integer> sortNoList);

	/**
	 * 查精选数
	 * @Description (TODO
	 * @return
	 */
	public int findSelectionListCount();

	/**
	 * 查精选记录
	 * @Description (TODO
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<SelectionModel> findSelectionList(int currPage, int limit);

	/**
	 * 根据商品ID查精选记录
	 * @Description (TODO
	 * @param goodsId
	 * @return
	 */
	public SelectionModel getSelectionByGoodsId(long goodsId);

}
