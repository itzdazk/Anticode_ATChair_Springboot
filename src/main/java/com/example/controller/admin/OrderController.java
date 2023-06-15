package com.example.controller.admin;

import com.example.dto.ProductDto;
import com.example.model.*;
import com.example.service.CategoryService;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping("admin/orders")
    public String orders(Model model){
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders",orderList);
        return "order";
    }

    @GetMapping("admin/order-detail/{id}")
    public String orderDetail(@PathVariable("id") Long id,Model model){
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(id);
        Order thisOrder = orderService.findOne(id);
        model.addAttribute("orderDetailList",orderDetailList);
        model.addAttribute("thisOrder",thisOrder);
        return "orderdetail";
    }

    @GetMapping("/admin/update-order-status-pending/{id}")
    public String updateOrderStatusPending(@PathVariable("id") Long id){
        orderService.pendingOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/update-order-status-completed/{id}")
    public String updateOrderStatusCompleted(@PathVariable("id") Long id){
        orderService.acceptOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/update-order-detail-status-pending/{id}")
    public String updateOrderDetailStatusPending(@PathVariable("id") Long id){
        orderService.pendingOrder(id);
        return "redirect:/admin/order-detail/"+id;
    }

    @GetMapping("/admin/update-order-detail-status-completed/{id}")
    public String updateOrderDetailStatusCompleted(@PathVariable("id") Long id){
        orderService.acceptOrder(id);
        return "redirect:/admin/order-detail/"+id;
    }


}
