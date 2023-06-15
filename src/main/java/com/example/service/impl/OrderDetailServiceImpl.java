package com.example.service.impl;

import com.example.model.Order;
import com.example.model.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Override
    public List<OrderDetail> findAllByOrderId(Long id){
        Order order = orderService.findOne(id);
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        return orderDetailList;
    }


}
