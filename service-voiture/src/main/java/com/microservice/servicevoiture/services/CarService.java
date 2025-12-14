package com.microservice.servicevoiture.services;

import com.microservice.servicevoiture.entities.Car;
import com.microservice.servicevoiture.entities.Client;
import com.microservice.servicevoiture.models.CarResponse;
import com.microservice.servicevoiture.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    private static final String CLIENT_SERVICE_URL =
            "http://SERVICE-CLIENT/api/client/";

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse findById(Long id) throws Exception {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new Exception("Voiture non trouvée avec l'ID: " + id));
        return mapToCarResponse(car);
    }

    public Optional<CarResponse> findByIdOptional(Long id) {
        return carRepository.findById(id)
                .map(this::mapToCarResponse);
    }


    public List<CarResponse> findByClientId(Long clientId) {
        return carRepository.findByClientId(clientId)
                .stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }


    private CarResponse mapToCarResponse(Car car) {
        Client client = null;
        try {
            if (car.getClientId() != null) {
                client = webClientBuilder.build()
                        .get()
                        .uri(CLIENT_SERVICE_URL + car.getClientId())
                        .retrieve()
                        .bodyToMono(Client.class)
                        .block();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du client: " + e.getMessage());
        }

        return CarResponse.builder()
                .id(car.getId())
                .marque(car.getMarque())
                .model(car.getModel())
                .matricule(car.getMatricule())
                .client(client)
                .build();
    }
}
