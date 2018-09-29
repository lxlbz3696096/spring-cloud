package com.prometheus.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.application.mapper.OrderMapper;
import com.prometheus.application.model.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	public void order() {
		for (int i = 20; i < 30; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orderMapper.insert(order);
        }
        for (int i = 30; i < 40; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orderMapper.insert(order);
        }
	}

}
