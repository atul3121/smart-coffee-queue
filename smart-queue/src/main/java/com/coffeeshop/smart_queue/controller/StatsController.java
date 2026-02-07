package com.coffeeshop.smart_queue.controller;

import com.coffeeshop.smart_queue.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@CrossOrigin("*")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public Map<String, Object> getStats() {

        Map<String, Object> stats = new HashMap<>();
        stats.put("averageWaitTime", statsService.averageWaitTime());
        stats.put("slaViolations", statsService.slaViolations());
        stats.put("ordersPerBarista", statsService.ordersPerBarista());
        stats.put("ordersByDrinkType", statsService.ordersByDrinkType());


        return stats;
    }
}
