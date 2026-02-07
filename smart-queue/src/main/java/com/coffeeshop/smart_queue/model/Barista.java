package com.coffeeshop.smart_queue.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "baristas")
public class Barista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean busy;

    private LocalDateTime busyUntil;

    @OneToOne
    private Order currentOrder;

    public Barista() {}

    public Barista(String name) {
        this.name = name;
        this.busy = false;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public LocalDateTime getBusyUntil() {
        return busyUntil;
    }

    public void setBusyUntil(LocalDateTime busyUntil) {
        this.busyUntil = busyUntil;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
