package com.example.controller.customer;

import com.example.dto.CategoryDto;
import com.example.model.Category;
import com.example.model.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductCusController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/allproduct")
    public String products(Model model, @RequestParam(defaultValue = "0") int page){
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getAllProducts();
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        long totalProducts = productService.getTotalProducts();
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("productRandom", productRandom);
        model.addAttribute("products", products);
        return "shop-category";
    }

    @GetMapping("/product-detail-{id}")
    public String findProductById(@PathVariable("id") Long id, Model model){
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product>  products = productService.getRelatedProducts(categoryId);
        Category category = categoryService.findById(categoryId);
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        model.addAttribute("category",category);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        model.addAttribute("productRandom", productRandom);
        return "product-detail";
    }

    @GetMapping("/products-in-category-{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId ,Model model){
        Category category = categoryService.findById(categoryId);
        List<CategoryDto> categories = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(categoryId);
        long totalProducts = productService.getTotalProducts();
        List<Product> productRandom = productService.getRandomProduct().subList(0,4);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("category",category);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("productRandom", productRandom);
        return "products-in-category";
    }

    @GetMapping("/high-price")
    public String filterHighPrice(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterHighPrice();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "filter-high-price";
    }


    @GetMapping("/low-price")
    public String filterLowPrice(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterLowPrice();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "filter-low-price";
    }

}
