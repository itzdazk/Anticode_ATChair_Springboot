package com.example.service;

import com.example.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    List<OrderDetail> findAllByOrderId(Long id);
}
