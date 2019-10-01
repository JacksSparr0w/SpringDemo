package com.example.demo.controller.product;

import com.example.demo.entity.Product;
import com.example.demo.entity.Type;
import com.example.demo.service.ProductService;
import com.example.demo.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/product/add")
public class AddProduct {
    private static final String ADD_PRODUCT = "addProduct";
    private final ProductService productService;
    private final TypeService typeService;

    @Autowired
    public AddProduct(ProductService productService, TypeService typeService) {
        this.productService = productService;
        this.typeService = typeService;
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("types", readTypes());
        model.addAttribute("product", new Product());
        model.addAttribute("aim", "add");
        return ADD_PRODUCT;
    }

    private List<Type> readTypes() {
        return typeService.findAll();
    }

    @PostMapping
    public String addProduct(@Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ADD_PRODUCT;
        }
        findType(product.getType().getId()).ifPresent(product::setType);
        saveProduct(product);
        log.log(Level.INFO, "product has been saved " + product);
        return "redirect:/products";
    }

    private Optional<Type> findType(Integer id) {
        return typeService.findById(id);
    }

    private void saveProduct(Product product) {
        productService.save(product);
    }
}
