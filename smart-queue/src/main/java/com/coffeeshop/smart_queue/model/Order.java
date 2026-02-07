package com.coffeeshop.smart_queue.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;   // ✅ REQUIRED

    private LocalDateTime arrivalTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long baristaId;

    private double priorityScore;
    private int skipCount;

    public Order() {}

    public Order(DrinkType drinkType) {
        this.drinkType = drinkType;
        this.arrivalTime = LocalDateTime.now();
        this.status = OrderStatus.WAITING;
        this.skipCount = 0;
    }

    // ===== getters & setters =====

    public Long getId() { return id; }

    public DrinkType getDrinkType() { return drinkType; }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getArrivalTime() { return arrivalTime; }

    public LocalDateTime getStartTime() { return startTime; }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() { return endTime; }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getBaristaId() { return baristaId; }

    public void setBaristaId(Long baristaId) {
        this.baristaId = baristaId;
    }

    public double getPriorityScore() { return priorityScore; }

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }

    public int getSkipCount() { return skipCount; }

    public void incrementSkipCount() { this.skipCount++; }
}
