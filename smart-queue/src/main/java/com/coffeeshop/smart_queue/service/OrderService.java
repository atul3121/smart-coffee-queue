package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.DrinkType;
import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;
import com.coffeeshop.smart_queue.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PriorityService priorityService;

    public OrderService(OrderRepository orderRepository,
                        PriorityService priorityService) {
        this.orderRepository = orderRepository;
        this.priorityService = priorityService;
    }

    // ✅ Create new order
    public Order createOrder(DrinkType drinkType) {
        Order order = new Order(drinkType);
        order.setStatus(OrderStatus.WAITING);
        order.setPriorityScore(priorityService.calculatePriority(order));
        return orderRepository.save(order);
    }

    // ✅ Waiting queue sorted by priority
    public List<Order> getWaitingOrdersSorted() {

        List<Order> orders =
                orderRepository.findByStatus(OrderStatus.WAITING);

        orders.forEach(o ->
                o.setPriorityScore(priorityService.calculatePriority(o))
        );

        orders.sort(
                Comparator.comparingDouble(Order::getPriorityScore).reversed()
        );

        return orders;
    }

    // ✅ Mark order completed
    public void completeOrder(Order order) {
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }

    // Optional: get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Emergency orders (>8 min waiting)
    public List<Order> getUrgentOrders() {

        return orderRepository
                .findByStatus(OrderStatus.WAITING)
                .stream()
                .filter(o ->
                        java.time.Duration.between(
                                o.getArrivalTime(),
                                java.time.LocalDateTime.now()
                        ).toMinutes() >= 8
                )
                .toList();
    }
}
