package com.example.demo.controller.producer;

import com.example.demo.service.ProducerService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/producer/{id}/delete")
public class DeleteProducer {
    private final ProducerService producerService;

    @Autowired
    public DeleteProducer(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String deleteProducer(@PathVariable Integer id) {
        producerService.deleteById(id);
        log.log(Level.INFO, "producer has been deleted by id" + id);
        return "redirect:/producers";
    }
}
