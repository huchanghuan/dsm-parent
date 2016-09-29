package com.iwancool.dsm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.iwancool.dsm.dao.ISelectionDao;
import com.iwancool.dsm.domain.SelectionModel;

/**
 * 精选dao实现类
 * @ClassName SelectionDaoImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月7日 上午10:31:59
 * @version 1.0.0
 */
@Repository(value = "selectionDao")
public class SelectionDaoImpl extends AbstractBaseGenericORMDaoImpl<SelectionModel, Long> implements ISelectionDao {

	private static final long serialVersionUID = -2099550857492504558L;

	@Override
	public void deleteBatchSelection(List<Long> idList) {
		String hql = "DELETE FROM SelectionModel WHERE id IN (:idList)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}

	@Override
	public void updateSort(List<Long> idList, List<Integer> sortNoList) {
		StringBuffer sql = new StringBuffer("update selection set sortNo = case id");
		for (int i = 0, len = idList.size(); i < len; i++) {
			sql.append(" when ").append(idList.get(i)).append(" then ").append(sortNoList.get(i));
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append("end where id in (:idList)");
		params.put("idList", idList);
		executeSql(sql.toString(), params);
	}
	
	@Override
	public int findSelectionListCount() {
		String hql = "SELECT COUNT(id) FROM SelectionModel";
		return count(hql).intValue();
	}

	@Override
	public List<SelectionModel> findSelectionList(int currPage, int limit) {
		String hql = "FROM SelectionModel ORDER BY sortNo DESC, id DESC";
		
		if (currPage == 0 && limit == 0)
			return find(hql);
		return find(hql, currPage, limit);
	}

	@Override
	public SelectionModel getSelectionByGoodsId(long goodsId) {
		String hql = "FROM SelectionModel WHERE goodsId = :goodsId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", goodsId);
		return get(hql, params);
	}

}
