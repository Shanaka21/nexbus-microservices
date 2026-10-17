
package com.nexbus.bus_route_service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BusRouteService {

    private final BusRouteRepository busRouteRepository;

    public BusRouteService(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public List<BusRoute> getAllRoutes() {
        return busRouteRepository.findAll();
    }

    public BusRoute getRouteById(Long id) {
        return busRouteRepository.findById(id).orElse(null);
    }

    public BusRoute createRoute(
            String routeNumber,
            String origin,
            String destination
    ) {
        BusRoute route = new BusRoute(
                null,
                routeNumber,
                origin,
                destination
        );

        return busRouteRepository.save(route);
    }

    public BusRoute updateRoute(
            Long id,
            String routeNumber,
            String origin,
            String destination
    ) {
        return busRouteRepository.findById(id)
                .map(existingRoute -> {
                    existingRoute.setRouteNumber(routeNumber);
                    existingRoute.setOrigin(origin);
                    existingRoute.setDestination(destination);

                    return busRouteRepository.save(existingRoute);
                })
                .orElse(null);
    }

    public boolean deleteRoute(Long id) {
        if (!busRouteRepository.existsById(id)) {
            return false;
        }

        busRouteRepository.deleteById(id);
        return true;
    }
}
