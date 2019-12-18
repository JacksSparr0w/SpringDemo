package com.example.demo.controller.event;

import com.example.demo.service.EventService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/event/{id}/delete")
public class DeleteEvent {
    private final EventService eventService;

    @Autowired
    public DeleteEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        log.log(Level.INFO, "event has been deleted by id" + id);
        return "redirect:/events";
    }
}
