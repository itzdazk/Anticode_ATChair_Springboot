package com.example.service;

import com.example.model.Order;
import com.example.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> findAllOrderPending();

    Long countPendingOrder();

    Long countCompletedOrder();

    void saveOrder(ShoppingCart Cart);

    void cancelOrder(Long id);

    void acceptOrder(Long id);

    void pendingOrder(Long id);

    List<Order> findAll();


    Order findOne(Long id);
}
