package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.ISelectionDao;
import com.iwancool.dsm.domain.SelectionModel;
import com.iwancool.dsm.service.ISelectionService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 精选service实现类
 * @ClassName SelectionServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午10:28:40
 * @version 1.0.0
 */
@Service(value = "selectionService")
public class SelectionServiceImpl extends AbstractBaseService implements ISelectionService {

	@Resource(name = "selectionDao")
	private ISelectionDao selectionDao;

	@Override
	public ResultResp save(SelectionModel selection) {
		SelectionModel selectionModel = selectionDao.getSelectionByGoodsId(selection.getGoodsId());
		if (null != selectionModel) return ResultResp.createResult(ResultCode.ERROR, "请勿重复推荐");
			
		selection.setCreateUtc(new Date().getTime());
		selectionDao.save(selection);
		return ResultResp.SUCCESS;
	}

	@Override
	public SelectionModel getSelection(long id) {
		
		return selectionDao.getByPrimaryKey(id);
	}

	@Override
	public ResultResp deleteBatchSelection(String idsStr) {
		log.debug("deleteBatchSelection ->" + idsStr);
		if (StringUtils.isEmpty(idsStr)) 
			return ResultResp.ERROR;
		
		List<Long> idList = new ArrayList<Long>();
		for (String str : idsStr.split(",")) {
			idList.add(Long.valueOf(str));
		}
		selectionDao.deleteBatchSelection(idList);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp updateSort(String idsStr, String sortNos) {
		log.debug("updateSort -> idsStr:" + idsStr + ",sortNos:" + sortNos);
		if (StringUtils.isEmpty(idsStr) || StringUtils.isEmpty(sortNos))
			return ResultResp.SUCCESS;
		
		String[] idsArr = idsStr.split(",");
		String[] sortNosArr = sortNos.split(",");
		if (null == idsArr || null == sortNosArr
				|| 0 == idsArr.length || 0 == sortNosArr.length)
			return ResultResp.SUCCESS;
		
		
		List<Long> idList = new ArrayList<Long>();
		List<Integer> sortNoList = new ArrayList<Integer>();
		for (int i = 0, len = sortNosArr.length;i < len; i++) {
			idList.add(Long.valueOf(idsArr[i]));
			sortNoList.add(Integer.valueOf(sortNosArr[i]));
		}
		
		selectionDao.updateSort(idList, sortNoList);
		return ResultResp.SUCCESS;
	}

	@Override
	public DataGrid<SelectionModel> findSelectionList(int offset, int limit) {
		log.debug("findSelectionList -> offset:" + offset + ",limit:" + limit);
		List<SelectionModel> selectionList = new ArrayList<SelectionModel>();
		int totalSize = selectionDao.findSelectionListCount();
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			selectionList = selectionDao.findSelectionList(currPage, limit);
		}
		return new DataGrid<SelectionModel>(totalSize, selectionList);
	}

	@Override
	public long saveSelection(SelectionModel selection) {
		SelectionModel selectionModel = selectionDao.getSelectionByGoodsId(selection.getGoodsId());
		if (null != selectionModel) return 0;
		return (Long) selectionDao.save(selection);
	}
	
	
}
