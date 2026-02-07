package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueueScheduler {

    private final OrderService orderService;
    private final BaristaService baristaService;

    public QueueScheduler(OrderService orderService,
                          BaristaService baristaService) {
        this.orderService = orderService;
        this.baristaService = baristaService;
    }

    // Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void processQueue() {

        // 1️⃣ Free completed baristas
        baristaService.releaseCompletedBaristas();

        // 2️⃣ Get waiting orders
        List<Order> waitingOrders = orderService.getWaitingOrdersSorted();

        // 3️⃣ Assign orders
        baristaService.assignOrders(waitingOrders);
    }
}
