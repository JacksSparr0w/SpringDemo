package com.example.demo.controller.production;

import com.example.demo.service.ProducerService;
import com.example.demo.service.ProductionService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/production/{id}/delete")
public class DeleteProduction {
    private final ProductionService productionService;

    @Autowired
    public DeleteProduction(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public String deleteProduction(@PathVariable Integer id) {
        productionService.deleteById(id);
        log.log(Level.INFO, "production has been deleted by id" + id);
        return "redirect:/";
    }
}
