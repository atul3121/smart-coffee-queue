package com.coffeeshop.smart_queue.repository;

import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Orders by status (WAITING / IN_PROGRESS / COMPLETED)
    List<Order> findByStatus(OrderStatus status);

    // For stats (completed orders only)
    List<Order> findByStatusOrderByEndTimeAsc(OrderStatus status);
}
