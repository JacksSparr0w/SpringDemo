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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/event/{id}/update")
public class UpdateEvent {
    private static final String ADD_PRODUCER = "addEvent";
    private final EventService eventService;

    @Autowired
    public UpdateEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getPage(Model model, @PathVariable Long id) {
        Event event;
        Optional<Event> optionalEvent = findEvent(id);
        event = optionalEvent.orElseGet(Event::new);
        model.addAttribute("event", event);
        return ADD_PRODUCER;
    }

    private Optional<Event> findEvent(Long id) {
        return eventService.findById(id);
    }


    @PostMapping
    public String addEvent(@Valid Event event, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            return ADD_PRODUCER;
        }
        saveEvent(event);
        log.log(Level.INFO, "event has been updated " + event);
        return "redirect:/events";
    }

    private void saveEvent(Event product) {
        eventService.save(product);
    }
}
