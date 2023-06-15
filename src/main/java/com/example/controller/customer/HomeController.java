package com.example.controller.customer;


import com.example.dto.ProductDto;
import com.example.model.Category;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.service.CategoryService;
import com.example.service.CustomerService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public String productpage(Model model,Principal principal, HttpSession session){
        if(principal != null){
            System.out.print(principal);
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            if(cart != null){
                session.setAttribute("totalItems", cart.getTotalItems());
            }else{
                session.setAttribute("totalItems", 0);
            }





        }else{
            session.removeAttribute("username");
        }
        List<Product> productDESC = productService.getDESCProducts().subList(0,8);
        List<Product> productASC = productService.getASCProducts().subList(0,8);
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        model.addAttribute("productDESC", productDESC);
        model.addAttribute("productASC", productASC);
        model.addAttribute("productRandom", productRandom);
        return "home";
    }

    @GetMapping("/blog")
    public String getBlog(){
        return "blog";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

}
