package com.example.service.impl;


import com.example.model.*;
import com.example.repository.CartItemRepository;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ShoppingCartRepository;
import com.example.service.CustomerService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {



    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<Order> findAllOrderPending() {
        return orderRepository.findAllOrderPending();
    }

    @Override
    public Long countPendingOrder() {
        return orderRepository.findAllPendingOrder();
    }

    @Override
    public Long countCompletedOrder() {
        return orderRepository.findAllCompletedOrder();
    }

    @Override
    public void saveOrder(ShoppingCart cart) {
        Order order = new Order();
        order.setOrderStatus("Pending");
        order.setOrderDate(new Date());
        order.setCustomer(cart.getCustomer());
        Customer customer = customerService.findByUsername(cart.getCustomer().getUsername());
        order.setFirstName(customer.getFirstName());
        order.setLastName(customer.getLastName());
        order.setPhoneNumber(customer.getPhoneNumber());
        order.setUsername(customer.getUsername());
        order.setAddress(customer.getAddress());
        order.setTotalPrice(cart.getTotalPrices());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(CartItem item : cart.getCartItem()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setTotalPrice(item.getQuantity()*item.getProduct().getCostPrice());
            orderDetail.setUnitPrice(item.getProduct().getCostPrice());
            orderDetailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);
            cartItemRepository.delete(item);
        }

        order.setOrderDetailList(orderDetailList);
        cart.setCartItem(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrices(0);
        shoppingCartRepository.save(cart);
        order.setShippingFee(order.getTotalPrice()/100*5);
        order.setTotalPrice(order.getShippingFee()+order.getTotalPrice());
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setOrderStatus("Cancelled");
        orderRepository.save(order);
    }

    @Override
    public void acceptOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setDeliveryDate(new Date());
        order.setOrderStatus("Completed");
        orderRepository.save(order);
    }

    public void pendingOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setOrderStatus("Pending");
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


    @Override
    public Order findOne(Long id) {
        return orderRepository.getById(id);
    }


}
