package com.example.demo.controller.production;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Production;
import com.example.demo.service.PriceListService;
import com.example.demo.service.ProducerService;
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
@RequestMapping(value = "/producer/{id}/productions")
public class ViewProductionsByProducer {
    private static final String PRODUCTIONS = "productions";
    private final ProductionService productionService;
    private final ProducerService producerService;
    private final PriceListService priceListService;

    @Autowired
    public ViewProductionsByProducer(ProductionService productionService,
                                     ProducerService producerService,
                                     PriceListService priceListService) {
        this.productionService = productionService;
        this.producerService = producerService;
        this.priceListService = priceListService;
    }

    @GetMapping
    public String getByProducer(Model model, @PathVariable Integer id) {
        Optional<Producer> optionalProducer = findProducer(id);
        List<Production> productions = optionalProducer.map(this::readByProducer).orElseGet(ArrayList::new);
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

    private Optional<Producer> findProducer(Integer id) {
        return producerService.findById(id);
    }

    private List<Production> readByProducer(Producer producer) {
        return productionService.findAllByProducer(producer);
    }
}
