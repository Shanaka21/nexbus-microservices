
package com.nexbus.bus_route_service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/bus-routes")
public class BusRouteController {

    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @GetMapping
    public List<BusRoute> getAllRoutes() {
        return busRouteService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public BusRoute getRouteById(@PathVariable Long id) {
        BusRoute route = busRouteService.getRouteById(id);

        if (route == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Bus route not found"
            );
        }

        return route;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusRoute createRoute(@RequestBody BusRoute route) {
        return busRouteService.createRoute(
                route.getRouteNumber(),
                route.getOrigin(),
                route.getDestination()
        );
    }

    @PutMapping("/{id}")
    public BusRoute updateRoute(
            @PathVariable Long id,
            @RequestBody BusRoute route
    ) {
        BusRoute updatedRoute = busRouteService.updateRoute(
                id,
                route.getRouteNumber(),
                route.getOrigin(),
                route.getDestination()
        );

        if (updatedRoute == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Bus route not found"
            );
        }

        return updatedRoute;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable Long id) {
        boolean deleted = busRouteService.deleteRoute(id);

        if (!deleted) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Bus route not found"
            );
        }
    }
}
