package com.example.demo.controller.event;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
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
@RequestMapping(value = "/events")
public class ViewEvent {
    private static final String EVENTS = "events";
    private final EventService eventService;

    @Autowired
    public ViewEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String events(Model model) {
        List<Event> events = readEvents();
        model.addAttribute(EVENTS, events);
        log.log(Level.INFO, "events was add to model");

        return EVENTS;
    }

    private List<Event> readEvents() {
        return eventService.findAll();
    }
}
