package com.coffeeshop.smart_queue.repository;

import com.coffeeshop.smart_queue.model.Barista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaristaRepository extends JpaRepository<Barista, Long> {
}
