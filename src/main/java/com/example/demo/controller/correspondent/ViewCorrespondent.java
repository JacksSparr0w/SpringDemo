package com.example.demo.controller.correspondent;

import com.example.demo.entity.Correspondent;
import com.example.demo.service.CorrespondentService;
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
@RequestMapping(value = "/correspondents")
public class ViewCorrespondent {
    private static final String EVENTS = "correspondents";
    private final CorrespondentService correspondentService;

    @Autowired
    public ViewCorrespondent(CorrespondentService correspondentService) {
        this.correspondentService = correspondentService;
    }

    @GetMapping
    public String correspondents(Model model) {
        List<Correspondent> correspondents = readCorrespondents();
        model.addAttribute(EVENTS, correspondents);
        log.log(Level.INFO, "correspondents was add to model");

        return EVENTS;
    }

    private List<Correspondent> readCorrespondents() {
        return correspondentService.findAll();
    }
}
