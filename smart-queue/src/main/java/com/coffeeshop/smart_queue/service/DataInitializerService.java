package com.coffeeshop.smart_queue.service;

import com.coffeeshop.smart_queue.model.Barista;
import com.coffeeshop.smart_queue.repository.BaristaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitializerService {

    private final BaristaRepository baristaRepository;

    public DataInitializerService(BaristaRepository baristaRepository) {
        this.baristaRepository = baristaRepository;
    }

    @PostConstruct
    public void initBaristas() {
        if (baristaRepository.count() == 0) {

            Barista b1 = new Barista("Barista-1");
            Barista b2 = new Barista("Barista-2");
            Barista b3 = new Barista("Barista-3");

            baristaRepository.saveAll(List.of(b1, b2, b3));

            System.out.println("✅ Default baristas created");
        }
    }
}
