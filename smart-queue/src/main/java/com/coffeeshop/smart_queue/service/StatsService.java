package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;
import com.coffeeshop.smart_queue.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final OrderRepository orderRepo;

    public StatsService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public double averageWaitTime() {

        return orderRepo.findByStatus(OrderStatus.COMPLETED)
                .stream()
                .filter(o -> o.getStartTime() != null)
                .mapToLong(o ->
                        Duration.between(
                                o.getArrivalTime(),
                                o.getStartTime()
                        ).toMinutes()
                )
                .average()
                .orElse(0);
    }

    public long slaViolations() {

        return orderRepo.findByStatus(OrderStatus.COMPLETED)
                .stream()
                .filter(o ->
                        o.getEndTime() != null &&
                                Duration.between(
                                        o.getArrivalTime(),
                                        o.getEndTime()
                                ).toMinutes() > 10
                )
                .count();
    }

    public Map<Long, Long> ordersPerBarista() {

        return orderRepo.findByStatus(OrderStatus.COMPLETED)
                .stream()
                .filter(o -> o.getBaristaId() != null)
                .collect(Collectors.groupingBy(
                        Order::getBaristaId,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> ordersByDrinkType() {

        return orderRepo.findByStatus(OrderStatus.COMPLETED)
                .stream()
                .collect(Collectors.groupingBy(
                        o -> o.getDrinkType().name(),
                        Collectors.counting()
                ));
    }

}
