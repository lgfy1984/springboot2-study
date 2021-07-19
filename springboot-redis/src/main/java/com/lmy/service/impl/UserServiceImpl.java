package com.lmy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmy.dao.UserDao;
import com.lmy.pojo.User;
import com.lmy.service.UserService;

/**
 * 
* @Title: UserServiceImpl
* @Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author lmy
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl implements UserService {
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
    private UserDao userDao;
	
	
	@Override
	public boolean addUser(User user) {
		boolean flag=false;
		try{
			userDao.addUser(user);
			flag=true;
		}catch(Exception e){
			logger.error("新增失败!",e);
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) {
		boolean flag=false;
		try{
			userDao.updateUser(user);
			flag=true;
		}catch(Exception e){
			logger.error("修改失败!",e);
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean flag=false;
		try{
			userDao.deleteUser(id);
			flag=true;
		}catch(Exception e){
			logger.error("删除失败!",e);
		}
		return flag;
	}


	@Override
	public User findByUserId(int id) {
		return userDao.findByUserId(id);
	}
}
