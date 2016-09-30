package com.iwancool.dsm.dao;

import java.util.List;
import java.util.Map;

import com.iwancool.dsm.domain.GoodsModel;

/**
 * goods dao接口
 * @ClassName IGoodsDao
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:55:34
 * @version 1.0.0
 */
public interface IGoodsDao extends IGeneralORMDao<GoodsModel, Long>{

	/**
	 * 批量删除
	 * @Description (TODO
	 * @param idList
	 */
	public void deleteBatchGoods(List<Long> idList);

	/**
	 * 条件查找商品记录数
	 * @Description (TODO
	 * @param columnId
	 * @param categoryId
	 * @param status
	 * @param keyword
	 * @return
	 */
	public int findGoodsListCount(int columnId, int categoryId, int status, String keyword);

	/**
	 * 条件查找记录并分页
	 * @Description (TODO
	 * @param columnId
	 * @param categoryId
	 * @param status
	 * @param keyword
	 * @param currPage
	 * @param limit
	 * @return
	 */
	public List<GoodsModel> findGoodsList(int columnId, int categoryId, int status, String keyword, int currPage, int limit);

	/**
	 * 查找指定时间段销售商品最多的用户
	 * @param startUtc
	 * @param endUtc
	 * @param size 
	 * @return
	 */
	public List<Map<String, Object>> findChopHandUser(long startUtc, long endUtc, int size);

}
