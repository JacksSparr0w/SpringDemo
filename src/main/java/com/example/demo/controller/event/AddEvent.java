package com.example.demo.controller.event;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
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
@RequestMapping(value = "/event/add")
public class AddEvent {
    private static final String ADD_EVENT = "addEvent";
    private final EventService eventService;

    @Autowired
    public AddEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("aim", "add");
        return ADD_EVENT;
    }

    @PostMapping
    public String addEvent(Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ADD_EVENT;
        }
        saveEvent(event);
        log.log(Level.INFO, "event has been saved " + event);
        return "redirect:/events";
    }

    private void saveEvent(Event event) {
        eventService.save(event);
    }
}
