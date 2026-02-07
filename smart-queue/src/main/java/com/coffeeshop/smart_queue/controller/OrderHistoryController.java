package com.coffeeshop.smart_queue.controller;

import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;
import com.coffeeshop.smart_queue.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/history")
@CrossOrigin("*")
public class OrderHistoryController {

    private final OrderRepository orderRepository;

    public OrderHistoryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ Full completed order history
    @GetMapping
    public List<Order> getCompletedOrders() {
        return orderRepository.findByStatus(OrderStatus.COMPLETED);
    }
}
