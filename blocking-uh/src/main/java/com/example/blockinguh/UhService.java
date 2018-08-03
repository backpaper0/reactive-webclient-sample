package com.example.blockinguh;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UhService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final UhProperties properties;

    public UhService(final UhProperties properties) {
        this.properties = properties;
    }

    public String apple() {
        return restTemplate.getForObject(properties.getAppleUrl() + "/apple", String.class);
    }

    public String pen() {
        return restTemplate.getForObject(properties.getPenUrl() + "/pen", String.class);
    }
}