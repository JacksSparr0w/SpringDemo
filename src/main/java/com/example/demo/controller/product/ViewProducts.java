package com.example.demo.controller.product;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/products")
public class ViewProducts {
    private static final String PRODUCTS = "products";
    private final ProductService productService;

    @Autowired
    public ViewProducts(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = readProducts();
        model.addAttribute(PRODUCTS, products);
        log.log(Level.INFO, "products was add to model");

        return PRODUCTS;
    }

    private List<Product> readProducts() {
        return productService.findAll();
    }
}
