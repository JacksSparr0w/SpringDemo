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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/correspondent/{id}/update")
public class UpdateCorrespodnent {
    private static final String ADD_PRODUCER = "addCorrespondent";
    private final CorrespondentService correspondentService;

    @Autowired
    public UpdateCorrespodnent(CorrespondentService correspondentService) {
        this.correspondentService = correspondentService;
    }

    @GetMapping
    public String getPage(Model model, @PathVariable Long id) {
        Correspondent correspondent;
        Optional<Correspondent> optionalCorrespondent = findCorrespondent(id);
        correspondent = optionalCorrespondent.orElseGet(Correspondent::new);
        model.addAttribute("correspondent", correspondent);
        return ADD_PRODUCER;
    }

    private Optional<Correspondent> findCorrespondent(Long id) {
        return correspondentService.findById(id);
    }


    @PostMapping
    public String addCorrespondent(@Valid Correspondent correspondent, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            return ADD_PRODUCER;
        }
        saveCorrespondent(correspondent);
        log.log(Level.INFO, "correspondent has been updated " + correspondent);
        return "redirect:/correspondents";
    }

    private void saveCorrespondent(Correspondent product) {
        correspondentService.save(product);
    }
}
