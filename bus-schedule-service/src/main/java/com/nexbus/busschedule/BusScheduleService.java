
package com.nexbus.busschedule;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BusScheduleService {

    private final BusScheduleRepository repository;

    public BusScheduleService(BusScheduleRepository repository) {
        this.repository = repository;
    }

    public List<BusSchedule> getAllSchedules() {
        return repository.findAll();
    }

    public Optional<BusSchedule> getScheduleById(Long id) {
        return repository.findById(id);
    }

    public BusSchedule createSchedule(BusSchedule schedule) {
        return repository.save(schedule);
    }

    public Optional<BusSchedule> updateSchedule(
            Long id,
            BusSchedule updatedSchedule
    ) {
        return repository.findById(id).map(existingSchedule -> {
            existingSchedule.setRouteNumber(updatedSchedule.getRouteNumber());
            existingSchedule.setStartLocation(updatedSchedule.getStartLocation());
            existingSchedule.setDestination(updatedSchedule.getDestination());
            existingSchedule.setDepartureTime(updatedSchedule.getDepartureTime());
            existingSchedule.setArrivalTime(updatedSchedule.getArrivalTime());
            existingSchedule.setOperatingDays(updatedSchedule.getOperatingDays());

            return repository.save(existingSchedule);
        });
    }

    public boolean deleteSchedule(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}

