package com.lmy.dao.master;

import org.apache.ibatis.annotations.Mapper;

import com.lmy.dao.BaseDao;
import com.lmy.pojo.User;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0  
* @author lmy
* @date 2018年1月9日
 */
@Mapper
public interface UserDao extends BaseDao<User>{
    
}
