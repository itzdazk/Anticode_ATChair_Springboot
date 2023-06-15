package com.example.controller.admin;


import com.example.dto.CustomerDto;
import com.example.dto.ProductDto;
import com.example.model.Category;
import com.example.model.Customer;
import com.example.service.CategoryService;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("admin/users")
    public String listUser(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Customer> customers = customerService.findAll();

        model.addAttribute("title", "Manage User");
        model.addAttribute("customers", customers);
        return "users";
    }

    @GetMapping("admin/update-user/{id}")
    public String getUserUpdate(@PathVariable("id") Long id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Update products");
        CustomerDto customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "update-user";
    }

    @PostMapping("admin/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute("customer") CustomerDto customer, Principal principal,RedirectAttributes attributes){
        if(principal == null) {
            return "redirect:/login";
        }
            try {
                customerService.update(customer);
                attributes.addFlashAttribute("success", "Update successfully!");
            }catch (Exception e){
                e.printStackTrace();
                attributes.addFlashAttribute("error", "Failed to update!");
            }
            return "redirect:/admin/users";
    }


}
