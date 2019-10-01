package com.example.demo.controller.producer;

import com.example.demo.entity.Producer;
import com.example.demo.service.ProducerService;
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
@RequestMapping(value = "/producers")
public class ViewProducers {
    private static final String PRODUCERS = "producers";
    private final ProducerService producerService;

    @Autowired
    public ViewProducers(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String producers(Model model) {
        List<Producer> producers = readProducers();
        model.addAttribute(PRODUCERS, producers);
        log.log(Level.INFO, "producers was add to model");

        return PRODUCERS;
    }

    private List<Producer> readProducers() {
        return producerService.findAll();
    }
}
