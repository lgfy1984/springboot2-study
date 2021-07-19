package com.lmy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmy.dao.BaseDao;
import com.lmy.dao.cluster.StudentDao;
import com.lmy.pojo.Student;
import com.lmy.service.StudentService;


/**
 * 
* Title: StudentServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author lmy
* @date 2018年3月19日
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student>  implements  StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}
	
}
