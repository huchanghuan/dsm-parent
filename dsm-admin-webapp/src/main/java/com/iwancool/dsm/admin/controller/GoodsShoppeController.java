package com.iwancool.dsm.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.controller.AbstractBaseController;
import com.iwancool.dsm.domain.GoodsShoppeModel;
import com.iwancool.dsm.service.IGoodsShoppeService;
import com.iwancool.dsm.utils.bean.DataGrid;

/**
 * 商品专柜控制器
 * @author hch
 *
 */
@Controller
@RequestMapping(value = "/goodsShoppe")
public class GoodsShoppeController extends AbstractBaseController{
	
	@Resource(name = "goodsShoppeService")
	private IGoodsShoppeService goodsShoppeService;

	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
		return "goodsShoppe/add";
	}
	
	/**
	 * 编辑页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		GoodsShoppeModel goodsShoppe = goodsShoppeService.getGoodsShoppe(id);
		if (null != goodsShoppe) {
			model.addAttribute("goodsShoppe", goodsShoppe);
		}
		return "goodsShoppe/edit";
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value = "/toList")
	public String toList () {
		return "goodsShoppe/list";
	}
	
	/**
	 * 保存更新
	 * @param goodsShoppe
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void saveOrUpdate(GoodsShoppeModel goodsShoppe, HttpServletRequest request, HttpServletResponse response) {
		ResultResp resultResp = goodsShoppeService.saveOrUpdate(goodsShoppe);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		ResultResp resultResp = goodsShoppeService.deleteGoodsShoppe(id);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 查找并分页
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list")
	public void list (HttpServletRequest request, HttpServletResponse response) {
		List<GoodsShoppeModel> list = goodsShoppeService.findAllGoodsShoppe();
		responseDataGrid(response, new DataGrid<GoodsShoppeModel>(list.size(), list));
	}
	
	/**
	 * 更新状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatus")
	public void updateStatus (HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		int status = ServletRequestUtils.getRequiredIntParameter(request, "status");
		ResultResp resultResp = goodsShoppeService.updateStatus(String.valueOf(id), status);
		responseDataGrid(response, resultResp);
	}
	
	/**
	 * 排序
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/sort")
	public void sort(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idsStr = ServletRequestUtils.getRequiredStringParameter(request, "ids");
		String sortNos = ServletRequestUtils.getRequiredStringParameter(request, "sortNos");
		ResultResp resultResp = goodsShoppeService.updateSort(idsStr, sortNos);
		responseDataGrid(response, resultResp);
	}
}
