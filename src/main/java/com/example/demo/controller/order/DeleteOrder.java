package com.example.demo.controller.order;

import com.example.demo.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/order/{id}/delete")
public class DeleteOrder {
    private final OrderService orderService;
    @Autowired
    public DeleteOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        log.log(Level.INFO, "order has been deleted by id" + id);
        return "redirect:/orders";
    }
}
