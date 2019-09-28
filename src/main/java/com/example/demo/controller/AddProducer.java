package com.example.demo.controller;

import com.example.demo.entity.Producer;
import com.example.demo.service.ProducerService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/addProducer")
public class AddProducer {
    private static final String ADD_PRODUCER = "addProducer";
    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String getPage(){
        return ADD_PRODUCER;
    }

    @PostMapping
    public String addProducer(Producer producer, Errors errors) {
        if (errors.hasErrors()){
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
