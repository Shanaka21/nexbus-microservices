
package com.nexbus.busschedule;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bus-schedules")
public class BusScheduleController {

    private final BusScheduleService service;

    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }

    // GET all schedules
    @GetMapping
    public ResponseEntity<List<BusSchedule>> getAllSchedules() {
        return ResponseEntity.ok(service.getAllSchedules());
    }

    // GET schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<BusSchedule> getScheduleById(
            @PathVariable Long id) {
        return service.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE schedule
    @PostMapping
    public ResponseEntity<BusSchedule> createSchedule(
            @RequestBody BusSchedule schedule) {
        BusSchedule created = service.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // UPDATE schedule
    @PutMapping("/{id}")
    public ResponseEntity<BusSchedule> updateSchedule(
            @PathVariable Long id,
            @RequestBody BusSchedule schedule) {
        return service.updateSchedule(id, schedule)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (!service.deleteSchedule(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
