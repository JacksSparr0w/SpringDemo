package com.example.demo.controller.production;

import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductionService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/product/{id}/productions")
public class ViewProductionsByProduct {
    private static final String PRODUCTIONS = "productions";
    private final ProductionService productionService;
    private final ProductService productService;

    @Autowired
    public ViewProductionsByProduct(ProductionService productionService,
                                    ProductService productService) {
        this.productionService = productionService;
        this.productService = productService;
    }

    @GetMapping
    public String getByProduct(Model model, @PathVariable Integer id) {
        Optional<Product> optionalProduct = findProduct(id);
        List<Production> productions = optionalProduct.map(this::readByProduct).orElseGet(ArrayList::new);
        model.addAttribute(PRODUCTIONS, productions);
        log.log(Level.INFO, "productions was add to model");

        return "/productions";
    }

    private Optional<Product> findProduct(Integer id) {
        return productService.findById(id);
    }

    private List<Production> readByProduct(Product product) {
        return productionService.findAllByProduct(product);
    }
}
