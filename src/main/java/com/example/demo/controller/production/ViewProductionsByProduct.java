package com.example.demo.controller.production;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.service.PriceListService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/product/{id}/productions")
public class ViewProductionsByProduct {
    private static final String PRODUCTIONS = "productions";
    private final ProductionService productionService;
    private final ProductService productService;
    private final PriceListService priceListService;

    @Autowired
    public ViewProductionsByProduct(ProductionService productionService,
                                    ProductService productService,
                                    PriceListService priceListService) {
        this.productionService = productionService;
        this.productService = productService;
        this.priceListService = priceListService;
    }

    @GetMapping
    public String getByProduct(Model model, @PathVariable Integer id) {
        Optional<Product> optionalProduct = findProduct(id);
        List<Production> productions = optionalProduct.map(this::readByProduct).orElseGet(ArrayList::new);
        productions.forEach(this::updateProduct);
        model.addAttribute(PRODUCTIONS, productions);
        log.log(Level.INFO, "productions was add to model");

        return "/productions";
    }

    private void updateProduct(Production production) {
        Optional<PriceList> optionalPriceList = priceListService.findByProducerAndTypeAndDate(
                production.getProducer(), production.getProduct().getType(), new Date());
        Double coefficient;
        if (optionalPriceList.isPresent()) {
            coefficient = optionalPriceList.get().getCoefficient();
        } else {
            coefficient = 1d;
        }
        production.getProduct().updateActualPrice(coefficient);
    }

    private Optional<Product> findProduct(Integer id) {
        return productService.findById(id);
    }

    private List<Production> readByProduct(Product product) {
        return productionService.findAllByProduct(product);
    }
}
