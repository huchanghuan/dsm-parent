package com.iwancool.dsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwancool.dsm.common.ResultCode;
import com.iwancool.dsm.common.ResultResp;
import com.iwancool.dsm.dao.IUserDao;
import com.iwancool.dsm.domain.IdGenerateModel;
import com.iwancool.dsm.domain.UserModel;
import com.iwancool.dsm.service.IIdGenerateService;
import com.iwancool.dsm.service.IUserService;
import com.iwancool.dsm.utils.bean.DataGrid;


/**
 * userService实现类
 * @ClassName UserServiceImpl
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年8月29日 上午10:21:29
 * @version 1.0.0
 */
@Service(value = "userService")
public class UserServiceImpl extends AbstractBaseService implements IUserService{

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	@Resource(name = "idGenerateService")
	private IIdGenerateService idGenerateService;
	
	@Override
	public ResultResp saveOrUpdate(UserModel user) {
		log.debug("saveOrUpdate -> " + user);
		//保证phone唯一
		UserModel userPhone = userDao.getUserByPhone(user.getPhone());
		if (null != userPhone && userPhone.getId() != user.getId()) {
			return ResultResp.createResult(ResultCode.ERROR, "手机号已存在");
		}
		//保证email唯一
		UserModel userEmail = userDao.getUserByEmail(user.getEmail());
		if (null != userEmail && userEmail.getId() != user.getId()) {
			return ResultResp.createResult(ResultCode.ERROR, "邮箱已存在");
		}
		
		if (user.getId() > 0) {
			UserModel userModel = userDao.getByPrimaryKey(user.getId());
			if (null == userModel) {
				return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
			}
			
			if (!StringUtils.isEmpty(user.getPasswd())) {
				userModel.setPasswd(user.getPasswd());
			}
			
			userModel.setNickname(user.getNickname());
			userModel.setPhone(user.getPhone());
			userModel.setSex(user.getSex());
			userDao.update(userModel);
		} else {
			long userId = idGenerateService.generateID(IdGenerateModel.USER_ID_GENERATOR_KEY);
			user.setId(userId);
			userDao.save(user);}
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteUser(long id) {
		log.debug("deleteUser -> " + id);
		userDao.deleteByPrimaryKey(id);
		return ResultResp.SUCCESS;
	}

	@Override
	public ResultResp deleteBatchUser(String idsStr) {
		log.debug("deleteBatchUser -> " + idsStr);
		if (StringUtils.isEmpty(idsStr)) return ResultResp.ERROR;
		
		List<Long> idList = new ArrayList<Long>();
		for (String idStr : idsStr.split(",")) {
			idList.add(Long.valueOf(idStr));
		}
		
		userDao.deleteBatchUser(idList);
		return ResultResp.SUCCESS;
	}

	@Override
	public UserModel getUser(long id) {
		log.debug("getUser -> " + id);
		return userDao.getByPrimaryKey(id);
	}

	@Override
	public DataGrid<UserModel> findUserList(int status, String keyword, int offset, int limit) {
		log.debug("findUserList -> status:" + status + ",keyword:" + keyword + ",offset:" + offset + ",limit:" + limit);
		List<UserModel> userList = new ArrayList<UserModel>();
		int totalSize = userDao.findUserListCount(status, keyword);
		if (totalSize > 0) {
			int currPage = (offset + limit) / limit;
			userList = userDao.findUserList(status, keyword, currPage, limit);
			if (null != userList) {
				for (UserModel userModel : userList) {
					userModel.setPasswd("");
				}
			}
		}
		return new DataGrid<UserModel>(totalSize, userList);
	}

	@Override
	public ResultResp updateStatus(long id, int status) {
		UserModel user = userDao.getByPrimaryKey(id);
		if (null == user) {
			return ResultResp.createResult(ResultCode.OBJECT_HAS_NOT_EXITS, "记录不存在");
		}
		user.setStatus(status);
		userDao.update(user);
		return ResultResp.SUCCESS;
	}

}
