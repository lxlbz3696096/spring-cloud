package com.prometheus.test;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prometheus.application.mapper.TUser0Mapper;
import com.prometheus.application.model.TUser0;

@SpringBootTest
public class MybatisTest {
	
	@Autowired
	TUser0Mapper tUser0Mapper;
	
	@Test
	public void test(){
		Random random = new Random();
		TUser0 tUser0 = new TUser0();
		tUser0.setAge(29);
		tUser0.setName("30");
		tUser0.setUserId(random.nextInt());
		tUser0Mapper.insertSelective(tUser0);
	}

}
