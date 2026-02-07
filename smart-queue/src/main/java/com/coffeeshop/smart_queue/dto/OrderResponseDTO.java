package com.coffeeshop.smart_queue.dto;

import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrderResponseDTO {

    public Long id;
    public String drinkType;
    public double priorityScore;
    public long waitedMinutes;
    public boolean completed;

    public static OrderResponseDTO from(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.id = order.getId();
        dto.drinkType = order.getDrinkType().name();
        dto.priorityScore = order.getPriorityScore();

        LocalDateTime now = LocalDateTime.now();
        dto.waitedMinutes = Duration.between(
                order.getArrivalTime(),
                now
        ).toMinutes();

        // ✅ completed derived from status
        dto.completed = order.getStatus() == OrderStatus.COMPLETED;

        return dto;
    }
}
