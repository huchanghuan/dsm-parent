package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.bean.GoodsBean;
import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IGoodsDao;
import com.iwancool.dsm.domain.CategoryModel;
import com.iwancool.dsm.domain.ColumnModel;
import com.iwancool.dsm.domain.GoodsModel;
import com.iwancool.dsm.domain.UserModel;
import com.iwancool.dsm.service.ICategoryService;
import com.iwancool.dsm.service.IColumnService;
import com.iwancool.dsm.service.IGoodsService;
import com.iwancool.dsm.service.IUserService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * goods service实现类
 * @ClassName GoodsServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 下午2:53:51
 * @version 1.0.0
 */
@Service(value = "goodsService")
public class GoodsServiceImpl extends AbstractBaseService implements IGoodsService {

	@Resource(name = "goodsDao")
	private IGoodsDao goodsDao;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "columnService")
	private IColumnService columnService;
	
	@Resource(name = "categoryService")
	private ICategoryService categoryService;
	
	@Override
	public DataGrid<GoodsBean> findGoodsList(int columnId, int categoryId, int status, String keyword, int offset,
			int limit) {
		log.debug("findGoodsList -> columnId" + columnId + ",categortId:" + categoryId + ",status:" + status + ",keyword:" + keyword + ",offset:" + offset + ",limit:" + limit);
		List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
		int totalSize = goodsDao.findGoodsListCount(columnId, categoryId, status, keyword);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			List<GoodsModel> goodsModelList = goodsDao.findGoodsList(columnId, categoryId, status, keyword, currPage, limit); 
			if (null != goodsModelList) {
				for (GoodsModel goodsModel : goodsModelList) {
					GoodsBean goodsBean = new GoodsBean();
					
					_modelToBean(goodsModel, goodsBean);
					
					goodsList.add(goodsBean);
				}
			}
		}
		return new DataGrid<GoodsBean>(totalSize, goodsList);
	}


	@Override
	public GoodsModel getGoods(long id) {
		log.debug("getGoods -> " + id);
		
		return goodsDao.getByPrimaryKey(id);
	}

	@Override
	public ResultResp updateStatus(long id, int status) {
		log.debug("updateStatus -> id" + id + ",status:" + status);
		GoodsModel goods = goodsDao.getByPrimaryKey(id);
		if (null == goods) return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在"); 
		
		goods.setStatus(status);
		goodsDao.update(goods);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBatchGoods(String ids) {
		if (StringUtils.isEmpty(ids)) return ResultResp.ERROR;
		
		String[] idsArr = ids.split(",");
		if (null == idsArr || 0 == idsArr.length) return ResultResp.ERROR;
		
		List<Long> idList = new ArrayList<Long>();
		for (String idStr : idsArr) {
			idList.add(Long.valueOf(idStr));
		}
		
		goodsDao.deleteBatchGoods(idList);
		return ResultResp.SUCCESS;
	}

	private void _modelToBean(GoodsModel goodsModel, GoodsBean goodsBean) {
		goodsBean.setBrand(goodsModel.getBrand());
		goodsBean.setCategoryId(goodsModel.getCategoryId());
		goodsBean.setColumnId(goodsModel.getColumnId());
		goodsBean.setCommentCount(goodsModel.getCommentCount());
		goodsBean.setCreateUtc(goodsModel.getCreateUtc());
		goodsBean.setCurPrice(goodsModel.getCurPrice());
		goodsBean.setDegree(goodsModel.getDegree());
		goodsBean.setDesp(goodsModel.getDesp());
		goodsBean.setFavCount(goodsModel.getFavCount());
		goodsBean.setId(goodsModel.getId());
		goodsBean.setImages(goodsModel.getImages());
		goodsBean.setName(goodsModel.getName());
		goodsBean.setOriPrice(goodsModel.getOriPrice());
		goodsBean.setPubUtc(goodsModel.getPubUtc());
		goodsBean.setRcmLevel(goodsModel.getRcmLevel());
		goodsBean.setSize(goodsModel.getSize());
		goodsBean.setStatus(goodsModel.getStatus());
		goodsBean.setTag(goodsModel.getTag());
		goodsBean.setUserId(goodsModel.getUserId());
		goodsBean.setVideoCover(goodsModel.getVideoCover());
		goodsBean.setVideoDur(goodsModel.getVideoDur());
		goodsBean.setVideoUrl(goodsModel.getVideoUrl());
		
		UserModel user = userService.getUser(goodsModel.getUserId());
		ColumnModel column = columnService.getColumn(goodsModel.getColumnId());
		CategoryModel category = categoryService.getCategory(goodsModel.getCategoryId());
		if (null !=  user) {
			goodsBean.setUsername(user.getNickname());
			goodsBean.setPhone(user.getPhone());
		}
		
		if (null != column)
			goodsBean.setColumnName(column.getName());
		
		if (null != category) 
			goodsBean.setCategoryName(category.getName());
		
	}


	@Override
	public List<Map<String, Object>> findChopHandUser(long startUtc, long endUtc, int size) {
		
		return goodsDao.findChopHandUser(startUtc, endUtc, size);
	}
}
