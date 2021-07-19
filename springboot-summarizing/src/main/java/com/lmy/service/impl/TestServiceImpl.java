package com.lmy.service.impl;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.lmy.service.ITestService;

/**
* @Title: TestServiceImpl
* @Description: 
* @Version:1.0.0  
* @author lmy
* @date 2019年1月18日
*/
@Service
public class TestServiceImpl implements ITestService {

	public TestServiceImpl(){
		System.out.println("testService构造方法调用!");
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("testService开始初始化！");
	}
	
	@PreDestroy
	public void destory() {
		System.out.println("testService开始销毁！");
	}
	
}
