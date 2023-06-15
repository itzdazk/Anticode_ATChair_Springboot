package com.example.controller.admin;

import com.example.model.Order;
import com.example.service.CustomerService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@Slf4j
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("admin/dashboard")
    public String home(Model model){
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }

        Long countPendingOrder = orderService.countPendingOrder();
        Long countCompletedOrder = orderService.countCompletedOrder();
        Long totalProducts = productService.getTotalProducts();
        Long totalUser = customerService.getTotalUser();
        List<Order> orderList = orderService.findAllOrderPending();
        model.addAttribute("totalUser", totalUser);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("countPendingOrder", countPendingOrder);
        model.addAttribute("countCompletedOrder", countCompletedOrder);
        model.addAttribute("orderList", orderList);
        return "dashboard";
    }
}
