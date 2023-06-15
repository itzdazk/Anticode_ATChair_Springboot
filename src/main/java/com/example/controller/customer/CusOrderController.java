package com.example.controller.customer;


import com.example.model.*;
import com.example.service.CustomerService;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CusOrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        if(customer.getPhoneNumber() == null || customer.getAddress() == null
        ) {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill the information after checkout!");
            return "account-information";
        }
        if(customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty()
               ){
            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill the information after checkout!");
            return "account-information";
        }else{
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
        }
        return "checkout";
    }


    @GetMapping("/order")
    public String order(Principal principal,Model model){
        if(principal ==null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        List<Order> orderList = customer.getOrders();
        model.addAttribute("productRandom", productRandom);
        model.addAttribute("orders",orderList);
        return "cus-order";
    }

    @GetMapping("/order-detail-{id}")
    public String orderdetail(@PathVariable("id") Long id, Model model,Principal principal){
        if(principal ==null){
            return "redirect:/login";
        }
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(id);
        Order thisOrder = orderService.findOne(id);
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        model.addAttribute("orderDetailList",orderDetailList);
        model.addAttribute("thisOrder",thisOrder);
        model.addAttribute("productRandom", productRandom);
        return "cus-orderdetail";
    }

    @GetMapping("/save-order")
    public String saveOrder(Principal principal){
        if(principal ==null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart cart = customer.getShoppingCart();
        orderService.saveOrder(cart);
        return "redirect:/order";
    }

    @GetMapping("/cancel-order-{id}")
    public String cancelOrder(Principal principal, @PathVariable("id") Long id, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        orderService.cancelOrder(id);
        return "redirect:/order";
    }

}
