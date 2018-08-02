package com.example.uh;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UhControllerWebTestClientTest {

    private final UhService service = Mockito.mock(UhService.class);

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        final UhController controller = new UhController(service);
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
