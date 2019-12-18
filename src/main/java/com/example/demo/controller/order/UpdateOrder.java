package com.example.demo.controller.order;

import com.example.demo.entity.Order;
import com.example.demo.entity.Correspondent;
import com.example.demo.service.OrderService;
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
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping(value = "/order/{id}/update")
public class UpdateOrder {
    private static final String ADD_PRODUCT = "addOrder";
    private final OrderService orderService;
    private final CorrespondentService typeService;

    @Autowired
    public UpdateOrder(OrderService orderService, CorrespondentService typeService) {
        this.orderService = orderService;
        this.typeService = typeService;
    }

    @GetMapping
    public String getPage(Model model, @PathVariable Long id) {
        model.addAttribute("types", readCorrespondents());
        Order order;
        Optional<Order> optionalOrder = findOrder(id);
        order = optionalOrder.orElseGet(Order::new);
        model.addAttribute("order", order);
        return ADD_PRODUCT;
    }

    private Optional<Order> findOrder(Long id) {
        return orderService.findById(id);
    }

    private List<Correspondent> readCorrespondents() {
        return typeService.findAll();
    }

    @PostMapping
    public String addOrder(@Valid Order order, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            return ADD_PRODUCT;
        }
        findCorrespondent(order.getCorrespondent().getId()).ifPresent(order::setCorrespondent);
        saveOrder(order);
        log.log(Level.INFO, "order has been updated " + order);
        return "redirect:/orders";
    }

    private Optional<Correspondent> findCorrespondent(Long id) {
        return typeService.findById(id);
    }

    private void saveOrder(Order order) {
        orderService.save(order);
    }
}
