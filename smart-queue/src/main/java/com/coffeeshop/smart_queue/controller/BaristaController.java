package com.coffeeshop.smart_queue.controller;

import com.coffeeshop.smart_queue.model.Barista;
import com.coffeeshop.smart_queue.repository.BaristaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baristas")
@CrossOrigin(origins = "*")
public class BaristaController {

    private final BaristaRepository baristaRepository;

    public BaristaController(BaristaRepository baristaRepository) {
        this.baristaRepository = baristaRepository;
    }

    @GetMapping
    public List<Barista> getAllBaristas() {
        return baristaRepository.findAll();
    }
}
