package com.microservice.servicevoiture.controllers;

import com.microservice.servicevoiture.entities.Car;
import com.microservice.servicevoiture.models.CarResponse;
import com.microservice.servicevoiture.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car saved = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findById(@PathVariable Long id) {
        return carService.findByIdOptional(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byClient/{clientId}")
    public List<CarResponse> findByClient(@PathVariable Long clientId) {
        return carService.findByClientId(clientId);
    }
}
