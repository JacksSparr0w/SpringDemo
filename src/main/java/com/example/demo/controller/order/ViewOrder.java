package com.example.demo.controller.order;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
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
@RequestMapping(value = "/orders")
public class ViewOrder {
    private static final String PRODUCTS = "orders";
    private final OrderService orderService;

    @Autowired
    public ViewOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orders(Model model) {
        List<Order> orders = readOrders();
        model.addAttribute(PRODUCTS, orders);
        log.log(Level.INFO, "orders was add to model");

        return PRODUCTS;
    }

    private List<Order> readOrders() {
        return orderService.findAll();
    }
}
