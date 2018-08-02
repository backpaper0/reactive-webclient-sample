package com.example.uh;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class UhService {

    private final WebClient client = WebClient.create();

    private final UhProperties properties;

    public UhService(final UhProperties properties) {
        this.properties = properties;
    }

    public Mono<String> apple() {
        return client.get()
                .uri(properties.getAppleUrl() + "/apple")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> pen() {
        return client.get()
                .uri(properties.getPenUrl() + "/pen")
                .retrieve()
                .bodyToMono(String.class);
    }
}