package com.coffeeshop.smart_queue.dto;

public class BaristaStatsDTO {

    public String name;
    public int ordersHandled;
    public double workloadRatio;

    public BaristaStatsDTO(String name,
                           int ordersHandled,
                           double workloadRatio) {
        this.name = name;
        this.ordersHandled = ordersHandled;
        this.workloadRatio = workloadRatio;
    }
}
