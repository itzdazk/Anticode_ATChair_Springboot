package com.example.dto;

import com.example.model.Customer;
import com.example.model.Order;
import com.example.model.OrderDetail;
import com.example.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private String orderStatus;
    private String notes;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String address;
    private Customer customer;
    private List<OrderDetail> orderDetailList;
}
