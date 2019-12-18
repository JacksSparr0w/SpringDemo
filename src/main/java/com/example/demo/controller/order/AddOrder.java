package com.example.demo.controller.order;

import com.example.demo.entity.Correspondent;
import com.example.demo.entity.Order;
import com.example.demo.service.CorrespondentService;
import com.example.demo.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/order/add")
public class AddOrder {
    private static final String ADD_PRODUCT = "addOrder";
    private final OrderService orderService;
    private final CorrespondentService correspondentService;

    @Autowired
    public AddOrder(OrderService orderService, CorrespondentService correspondentService) {
        this.orderService = orderService;
        this.correspondentService = correspondentService;
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("correspondents", readCorrespondents());
        model.addAttribute("order", new Order());
        model.addAttribute("aim", "add");
        return ADD_PRODUCT;
    }

    private List<Correspondent> readCorrespondents() {
        return correspondentService.findAll();
    }

    @PostMapping
    public String addOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return ADD_PRODUCT;
        }
        findCorrespondent(order.getCorrespondent().getId()).ifPresent(order::setCorrespondent);
        saveOrder(order);
        log.log(Level.INFO, "order has been saved " + order);
        return "redirect:/orders";
    }

    private Optional<Correspondent> findCorrespondent(Long id) {
        return correspondentService.findById(id);
    }

    private void saveOrder(Order order) {
        orderService.save(order);
    }
}
