package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Barista;
import com.coffeeshop.smart_queue.model.Order;
import com.coffeeshop.smart_queue.model.OrderStatus;
import com.coffeeshop.smart_queue.repository.BaristaRepository;
import com.coffeeshop.smart_queue.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BaristaService {

    private final BaristaRepository baristaRepository;
    private final OrderRepository orderRepository;

    public BaristaService(BaristaRepository baristaRepository,
                          OrderRepository orderRepository) {
        this.baristaRepository = baristaRepository;
        this.orderRepository = orderRepository;
    }

    // ✅ Assign highest-priority WAITING orders to free baristas
    @Transactional
    public void assignOrders(List<Order> waitingOrders) {

        if (waitingOrders.isEmpty()) return;

        List<Barista> baristas = baristaRepository.findAll();

        for (Barista barista : baristas) {

            if (!barista.isBusy()) {

                // ✅ pick ONLY truly waiting order
                Order order = waitingOrders.stream()
                        .filter(o -> o.getStatus() == OrderStatus.WAITING)
                        .findFirst()
                        .orElse(null);

                if (order == null) continue;

                // 🔒 LOCK ORDER
                order.setStatus(OrderStatus.IN_PROGRESS);
                order.setStartTime(LocalDateTime.now());
                order.setBaristaId(barista.getId());
                orderRepository.save(order);

                // assign to barista
                barista.setBusy(true);
                barista.setCurrentOrder(order);
                barista.setBusyUntil(
                        LocalDateTime.now()
                                .plusMinutes(order.getDrinkType().getPrepTimeInMinutes())
                );

                baristaRepository.save(barista);
            }
        }
    }

    // ✅ Release baristas AFTER prep time & complete orders
    @Transactional
    public void releaseCompletedBaristas() {

        List<Barista> baristas = baristaRepository.findAll();

        for (Barista barista : baristas) {

            if (barista.isBusy()
                    && barista.getBusyUntil() != null
                    && barista.getBusyUntil().isBefore(LocalDateTime.now())) {

                Order order = barista.getCurrentOrder();

                if (order != null) {
                    order.setStatus(OrderStatus.COMPLETED);
                    order.setEndTime(LocalDateTime.now());
                    orderRepository.save(order);
                }

                barista.setBusy(false);
                barista.setBusyUntil(null);
                barista.setCurrentOrder(null);

                baristaRepository.save(barista);
            }
        }
    }
}
