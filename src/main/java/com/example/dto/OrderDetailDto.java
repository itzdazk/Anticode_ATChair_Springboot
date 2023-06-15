package com.example.dto;

import com.example.model.Order;
import com.example.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;
    private int quantity;
    private double totalPrice;
    private double unitPrice;
    private Order order;
    private Product product;
}
