package com.microservice.servicevoiture.web;

import com.microservice.servicevoiture.entities.Client;
import com.microservice.servicevoiture.services.ClientApi;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final ClientApi clientApi;

    public TestController(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    @GetMapping("/client/{id}")
    public Client testClient(@PathVariable Long id) {
        return clientApi.findClientById(id);
    }
}
