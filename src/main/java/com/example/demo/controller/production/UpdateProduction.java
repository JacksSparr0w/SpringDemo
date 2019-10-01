package com.example.demo.controller.production;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.service.ProducerService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductionService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/production/{id}/update")
public class UpdateProduction {
    private static final String ADD_PRODUCTION = "addProduction";
    private final ProductionService productionService;
    private final ProducerService producerService;
    private final ProductService productService;

    @Autowired
    public UpdateProduction(ProductionService productionService,
                         ProductService productService,
                         ProducerService producerService) {
        this.productionService = productionService;
        this.productService = productService;
        this.producerService = producerService;
    }
    @GetMapping
    public String getPage(Model model, @PathVariable Integer id) {
        model.addAttribute("products", readProducts());
        model.addAttribute("producers", readProducers());
        Production production = findProduction(id).orElseGet(Production::new);
        model.addAttribute("production", production);
        return ADD_PRODUCTION;
    }

    private Optional<Production> findProduction(Integer id) {
        return productionService.findById(id);
    }


    @PostMapping
    public String addProduction(@Valid Production production, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            return ADD_PRODUCTION;
        }
        findProducer(production.getProducer().getId()).ifPresent(production::setProducer);
        findProduct(production.getProduct().getId()).ifPresent(production::setProduct);
        saveProduction(production);
        log.log(Level.INFO, "production has been updated " + production);
        return "redirect:/";
    }

    private void saveProduction(Production production) {
        productionService.save(production);
    }

    private List<Producer> readProducers() {
        return producerService.findAll();
    }

    private List<Product> readProducts() {
        return productService.findAll();
    }

    private Optional<Producer> findProducer(Integer id) {
        return producerService.findById(id);
    }

    private Optional<Product> findProduct(Integer id) {
        return productService.findById(id);
    }
}
