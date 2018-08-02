package com.example.uh;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UhControllerTest {

    @Test
    void getApplePen() {
        final UhService service = Mockito.mock(UhService.class);

        given(service.apple()).willReturn(Mono.just("Apple"));
        given(service.pen()).willReturn(Mono.just("Pen"));

        final UhController controller = new UhController(service);

        final Mono<String> applePen = controller.getApplePen();

        StepVerifier.create(applePen)
                .expectNextMatches(s -> s.contains("ApplePen"))
                .verifyComplete();
    }
}
