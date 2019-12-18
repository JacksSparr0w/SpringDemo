package com.example.demo.controller.correspondent;

import com.example.demo.service.CorrespondentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/correspondent/{id}/delete")
public class DeleteCorrespondent {
    private final CorrespondentService eventService;

    @Autowired
    public DeleteCorrespondent(CorrespondentService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String delete(@PathVariable Long id) {
        eventService.deleteById(id);
        log.log(Level.INFO, "event has been deleted by id" + id);
        return "redirect:/events";
    }
}
