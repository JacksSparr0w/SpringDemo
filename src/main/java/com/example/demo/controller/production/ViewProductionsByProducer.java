package com.example.demo.controller.production;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Production;
import com.example.demo.service.ProducerService;
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
@RequestMapping(value = "/producer/{id}/productions")
public class ViewProductionsByProducer {
    private static final String PRODUCTIONS = "productions";
    private final ProductionService productionService;
    private final ProducerService producerService;

    @Autowired
    public ViewProductionsByProducer(ProductionService productionService,
                                     ProducerService producerService) {
        this.productionService = productionService;
        this.producerService = producerService;
    }

    @GetMapping
    public String getByProducer(Model model, @PathVariable Integer id) {
        Optional<Producer> optionalProducer = findProducer(id);
        List<Production> productions = optionalProducer.map(this::readByProducer).orElseGet(ArrayList::new);
        model.addAttribute(PRODUCTIONS, productions);
        log.log(Level.INFO, "productions was add to model");

        return "/productions";
    }

    private Optional<Producer> findProducer(Integer id) {
        return producerService.findById(id);
    }

    private List<Production> readByProducer(Producer producer) {
        return productionService.findAllByProducerOOrderByProduct(producer);
    }
}
