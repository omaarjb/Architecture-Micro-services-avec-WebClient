package com.microservice.servicevoiture;

import com.microservice.servicevoiture.entities.Car;
import com.microservice.servicevoiture.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVoitureApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(CarRepository carRepository) {
        return args -> {
            if (carRepository.count() == 0) {
                carRepository.save(new Car(null, "Toyota", "ABC123", "Camry", 1L, null));
                carRepository.save(new Car(null, "Honda", "XYZ789", "Civic", 2L, null));
                carRepository.save(new Car(null, "Ford", "DEF456", "Focus", 1L, null));
                System.out.println("Database initialized with sample cars!");
            }
        };
    }
}
