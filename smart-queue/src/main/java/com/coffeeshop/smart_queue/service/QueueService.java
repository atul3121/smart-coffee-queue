package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Order;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueueService {

    private final OrderService orderService;
    private final BaristaService baristaService;

    public QueueService(OrderService orderService,
                        BaristaService baristaService) {
        this.orderService = orderService;
        this.baristaService = baristaService;
    }

    // Main queue processing logic
    public void processQueue() {

        // 1. Release completed baristas
        baristaService.releaseCompletedBaristas();

        // 2. Get sorted waiting orders
        List<Order> waitingOrders = orderService.getWaitingOrdersSorted();

        // 3. Update skip counts (fairness)
        updateSkipCounts(waitingOrders);

        // 4. Assign orders
        baristaService.assignOrders(waitingOrders);
    }

    // Fairness logic
    private void updateSkipCounts(List<Order> orders) {
        if (orders.size() <= 1) return;

        Order top = orders.get(0);

        for (int i = 1; i < orders.size(); i++) {
            Order skipped = orders.get(i);

            long waited =
                    Duration.between(skipped.getArrivalTime(), LocalDateTime.now())
                            .toMinutes();

            if (waited > 2) {
                skipped.incrementSkipCount();
            }
        }
    }
}
