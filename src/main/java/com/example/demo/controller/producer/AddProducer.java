package com.example.demo.controller.producer;

import com.example.demo.entity.Producer;
import com.example.demo.service.ProducerService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/producer/add")
public class AddProducer {
    private static final String ADD_PRODUCER = "addProducer";
    private final ProducerService producerService;

    @Autowired
    public AddProducer(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("producer", new Producer());
        model.addAttribute("aim", "add");
        return ADD_PRODUCER;
    }

    @PostMapping
    public String addProducer(Producer producer, Errors errors) {
        if (errors.hasErrors()) {
            return ADD_PRODUCER;
        }
        saveProducer(producer);
        log.log(Level.INFO, "producer has been saved " + producer);
        return "redirect:/producers";
    }

    private void saveProducer(Producer producer) {
        producerService.save(producer);
    }
}
