package com.coffeeshop.smart_queue.dto;

public class StatsSummaryDTO {

    public double averageWaitTime;
    public double maxWaitTime;
    public long emergencyOrders;
    public long timeoutViolations;
    public double ordersPerMinute;
    public long fairnessViolations;

    public StatsSummaryDTO(double averageWaitTime,
                           double maxWaitTime,
                           long emergencyOrders,
                           long timeoutViolations,
                           double ordersPerMinute,
                           long fairnessViolations) {
        this.averageWaitTime = averageWaitTime;
        this.maxWaitTime = maxWaitTime;
        this.emergencyOrders = emergencyOrders;
        this.timeoutViolations = timeoutViolations;
        this.ordersPerMinute = ordersPerMinute;
        this.fairnessViolations = fairnessViolations;
    }
}
