package com.example.controller.admin;

import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.model.Category;
import com.example.model.Order;
import com.example.model.OrderDetail;
import com.example.model.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.CategoryService;
import com.example.service.OrderDetailService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.utils.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/getAllProduct")
    public List<ProductDto> getAllProduct(){
        List<ProductDto> productDtoList = productService.findAll();
        if(productDtoList.isEmpty()) {
            return (List<ProductDto>) new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(productDtoList, HttpStatus.OK).getBody();
    }

    @GetMapping("/getproduct/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return (Product) productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No post found with id=" + id));
    }


    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        List<Category> categories = categoryService.findAll();
        if(categories.isEmpty()) {
            return (List<Category>) new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK).getBody();
    }

    @GetMapping("/getcategory/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        return (Category) categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No post found with id=" + id));
    }


    @GetMapping("/product-category/{id}")
    public  List<Product> getProductByCategoryId(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        List<CategoryDto> categories = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(id);
        return products;
    }


    @PostMapping("/add-category")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category){
        return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.OK);

    }

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrder(){
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @GetMapping("/order/{id}")
    public List<OrderDetail> getPost(@PathVariable("id") Long id) {
        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(id);
        return orderDetailList ;
    }

}
