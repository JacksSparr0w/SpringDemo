package com.example.demo.controller.correspondent;

import com.example.demo.entity.Correspondent;
import com.example.demo.service.CorrespondentService;
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
@RequestMapping(value = "/correspondent/add")
public class AddCorrespondent {
    private static final String ADD_EVENT = "addCorrespondent";
    private final CorrespondentService correspondentService;

    @Autowired
    public AddCorrespondent(CorrespondentService correspondentService) {
        this.correspondentService = correspondentService;
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("correspondent", new Correspondent());
        model.addAttribute("aim", "add");
        return ADD_EVENT;
    }

    @PostMapping
    public String addCorrespondent(Correspondent correspondent, Errors errors) {
        if (errors.hasErrors()) {
            return ADD_EVENT;
        }
        saveCorrespondent(correspondent);
        log.log(Level.INFO, "correspondent has been saved " + correspondent);
        return "redirect:/correspondents";
    }

    private void saveCorrespondent(Correspondent correspondent) {
        correspondentService.save(correspondent);
    }
}
