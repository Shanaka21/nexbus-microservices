package com.nexbus.bus_operator_service.repository;

import com.nexbus.bus_operator_service.entity.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusOperatorRepository
        extends JpaRepository<BusOperator, Long> {
}