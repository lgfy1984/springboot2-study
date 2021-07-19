package com.lmy.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.lmy.pojo.User;

/**
 * 
* Title: UserDao
* Description:
* spring-data-es 查询接口
* Version:1.0.0  
* @author lmy
* @date 2018年4月25日
 */
public interface UserDao extends ElasticsearchRepository<User, Long>{

}
