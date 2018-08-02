package com.example.uh;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UhControllerSpringBootTest {

    @MockBean
    private UhService service;

    @Autowired
    private UhController controller;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToController(controller).build();
    }

    @Test
    void getApplePen() {
        given(service.apple()).willReturn(Mono.just("Apple"));
        given(service.pen()).willReturn(Mono.just("Pen"));

        final Flux<String> applePen = client.get().uri("/uh").exchange()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(applePen)
                .expectNextMatches(s -> s.contains("ApplePen"))
                .verifyComplete();
    }
}
