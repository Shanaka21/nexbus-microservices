package com.nexbus.bus_operator_service.controller;

import com.nexbus.bus_operator_service.entity.BusOperator;
import com.nexbus.bus_operator_service.service.BusOperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus-operators")
public class BusOperatorController {

    private final BusOperatorService service;

    public BusOperatorController(BusOperatorService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusOperator> getAllOperators() {
        return service.getAllOperators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusOperator> getOperatorById(@PathVariable Long id) {
        return service.getOperatorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusOperator> createOperator(
            @RequestBody BusOperator operator) {
        BusOperator created = service.createOperator(operator);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusOperator> updateOperator(
            @PathVariable Long id,
            @RequestBody BusOperator details) {
        return service.updateOperator(id, details)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long id) {
        return service.deleteOperator(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}