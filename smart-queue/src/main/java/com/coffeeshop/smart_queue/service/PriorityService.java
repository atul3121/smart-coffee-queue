package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Order;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PriorityService {

    private static final int MAX_WAIT_TIME = 10; // minutes

    public double calculatePriority(Order order) {

        double waitScore = calculateWaitScore(order);
        double complexityScore = calculateComplexityScore(order);
        double urgencyScore = calculateUrgencyScore(order);
        double fairnessPenalty = calculateFairnessPenalty(order);

        double priority =
                (0.40 * waitScore) +
                        (0.25 * complexityScore) +
                        (0.25 * urgencyScore) -
                        (0.10 * fairnessPenalty);

        return Math.max(priority, 0);
    }

    // ---------------- HELPER METHODS ----------------

    private double calculateWaitScore(Order order) {
        long waitedMinutes = Duration.between(
                order.getArrivalTime(),
                LocalDateTime.now()
        ).toMinutes();

        return Math.min(waitedMinutes * 10, 40); // cap at 40
    }

    private double calculateComplexityScore(Order order) {
        int prepTime = order.getDrinkType().getPrepTimeInMinutes();

        return switch (prepTime) {
            case 1 -> 25;
            case 2 -> 18;
            case 4 -> 8;
            case 6 -> 0;
            default -> 5;
        };
    }

    private double calculateUrgencyScore(Order order) {
        long waitedMinutes = Duration.between(
                order.getArrivalTime(),
                LocalDateTime.now()
        ).toMinutes();

        if (waitedMinutes >= 8) {
            return 25; // emergency boost
        }
        return 0;
    }

    private double calculateFairnessPenalty(Order order) {
        return order.getSkipCount() * 5;
    }
}
