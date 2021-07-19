package com.lmy.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.search.Query;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.lmy.dao.UserDao;
import com.lmy.pojo.User;
import com.lmy.service.UserService;

/**
 * 
* Title: UserServiceImpl
* Description:
* 用户操作实现类 
* Version:1.0.0  
* @author lmy
* @date 2018年1月9日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;
	@Override
	public boolean insert(User user) {
		boolean falg=false;
		try{
			userDao.save(user);
			falg=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public List<User> search(String searchContent) {
		  QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
		  System.out.println("查询的语句:"+builder);
          Iterable<User> searchResult = userDao.search(builder);
          Iterator<User> iterator = searchResult.iterator();
          List<User> list=new ArrayList<User>();
          while (iterator.hasNext()) {
       	   	list.add(iterator.next());
          }
       return list;
	}
	
	
	
	@Override
	public List<User> searchUser(Integer pageNumber, Integer pageSize,String searchContent) {
		 // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<User> searchPageResults = userDao.search(searchQuery);
        return searchPageResults.getContent();
	}
	

	@Override
	public List<User> searchUserByWeight(String searchContent) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.matchQuery("title", "雨伞"));

		ScoreFunctionBuilder<?> scoreFunctionBuilder = ScoreFunctionBuilders.fieldValueFactorFunction("sales").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(0.1f);
		FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(queryBuilder,scoreFunctionBuilder).boostMode(CombineFunction.SUM);
        // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(queryBuilder);
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
//                    ScoreFunctionBuilders.weightFactorFunction(10))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
        Iterator<User> iterator = searchResult.iterator();
        List<User> list=new ArrayList<User>();
        while (iterator.hasNext()) {
     	   	list.add(iterator.next());
        }
        return list;
	}


}
