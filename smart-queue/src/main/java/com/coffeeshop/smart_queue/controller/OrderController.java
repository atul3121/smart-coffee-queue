package com.coffeeshop.smart_queue.controller;

import com.coffeeshop.smart_queue.dto.OrderResponseDTO;
import com.coffeeshop.smart_queue.model.DrinkType;
import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place a new order
    @PostMapping
    public Order placeOrder(@RequestParam DrinkType drinkType) {
        return orderService.createOrder(drinkType);
    }

    // Get current queue (sorted by priority)
    @GetMapping("/queue")
    public List<OrderResponseDTO> getQueue() {
        return orderService.getWaitingOrdersSorted()
                .stream()
                .map(OrderResponseDTO::from)
                .toList();
    }

    @GetMapping("/all")
    public List<Order> getAllOrdersDebug() {
        return orderService.getAllOrders();
    }

}
