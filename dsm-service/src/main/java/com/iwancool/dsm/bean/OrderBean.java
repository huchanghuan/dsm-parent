package com.iwancool.dsm.bean;

import com.iwancool.dsm.domain.OrderModel;

/**
 * 订单扩展类
 * @ClassName OrderBean
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月31日 下午2:42:39
 * @version 1.0.0
 */
public class OrderBean extends OrderModel{

	private static final long serialVersionUID = -8519627272337340226L;

	//商品名
	private String goodsName;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
}
