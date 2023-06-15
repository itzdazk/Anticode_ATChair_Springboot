package com.example.service;


import com.example.dto.CustomerDto;
import com.example.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    CustomerDto save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfor(Customer customer);

    long getTotalUser();

    CustomerDto getById(Long id);

    Customer update(CustomerDto customerDto);
}
