package com.nexbus.bus_operator_service.service;

import com.nexbus.bus_operator_service.entity.BusOperator;
import com.nexbus.bus_operator_service.repository.BusOperatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusOperatorService {

    private final BusOperatorRepository repository;

    public BusOperatorService(BusOperatorRepository repository) {
        this.repository = repository;
    }

    public List<BusOperator> getAllOperators() {
        return repository.findAll();
    }

    public Optional<BusOperator> getOperatorById(Long id) {
        return repository.findById(id);
    }

    public BusOperator createOperator(BusOperator operator) {
        return repository.save(operator);
    }

    public Optional<BusOperator> updateOperator(Long id, BusOperator details) {
        return repository.findById(id).map(operator -> {
            operator.setCompanyName(details.getCompanyName());
            operator.setContactPerson(details.getContactPerson());
            operator.setPhone(details.getPhone());
            operator.setEmail(details.getEmail());
            operator.setRegistrationNumber(details.getRegistrationNumber());
            return repository.save(operator);
        });
    }

    public boolean deleteOperator(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}