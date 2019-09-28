package com.example.demo.controller;

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
    public static final String PRODUCERS = "producers";
    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String producers(Model model) {
        model.addAllAttributes(readProducers());
        log.log(Level.INFO, "producers was add to model");
        return PRODUCERS;
    }

    private List<Producer> readProducers() {
        return producerService.findAll();
    }
}
