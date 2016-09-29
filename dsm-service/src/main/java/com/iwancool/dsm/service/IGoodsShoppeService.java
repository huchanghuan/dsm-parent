package com.iwancool.dsm.service;

import java.util.List;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.domain.GoodsShoppeModel;

/**
 * 商品专柜Service接口
 * @author hch
 *
 */
public interface IGoodsShoppeService extends IGenericService{

	/**
	 * 保存更新
	 * @param goodsShoppe
	 * @return
	 */
	public ResultResp saveOrUpdate(GoodsShoppeModel goodsShoppe);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public ResultResp deleteGoodsShoppe(int id);
	
	/**
	 * 获得GoodsShoppe
	 * @param id
	 * @return
	 */
	public GoodsShoppeModel getGoodsShoppe(int id);
	
	/**
	 * 查所有专柜
	 * @return
	 */
	public List<GoodsShoppeModel> findAllGoodsShoppe();
	
	/**
	 * 更新状态
	 * @param idStrs
	 * @param status
	 * @return
	 */
	public ResultResp updateStatus(String idStrs, int status);
	
	/**
	 * 排序
	 * @param idsStrs
	 * @param sortNos
	 * @return
	 */
	public ResultResp updateSort(String idsStrs, String sortNos);
}
