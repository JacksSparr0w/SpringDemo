package com.example.demo.controller.product;

import com.example.demo.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/product/{id}/delete")
public class DeleteProduct {
    private final ProductService productService;

    @Autowired
    public DeleteProduct(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        log.log(Level.INFO, "product has been deleted by id" + id);
        return "redirect:/products";
    }
}
