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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/producer/{id}/update")
public class UpdateProducer {
    private static final String ADD_PRODUCER = "addProducer";
    private final ProducerService producerService;

    @Autowired
    public UpdateProducer(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String getPage(Model model, @PathVariable Integer id) {
        Producer producer;
        Optional<Producer> optionalProducer = findProducer(id);
        producer = optionalProducer.orElseGet(Producer::new);
        model.addAttribute("producer", producer);
        return ADD_PRODUCER;
    }

    private Optional<Producer> findProducer(Integer id) {
        return producerService.findById(id);
    }


    @PostMapping
    public String addProducer(@Valid Producer producer, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            return ADD_PRODUCER;
        }
        saveProducer(producer);
        log.log(Level.INFO, "producer has been updated " + producer);
        return "redirect:/producers";
    }

    private void saveProducer(Producer product) {
        producerService.save(product);
    }
}
