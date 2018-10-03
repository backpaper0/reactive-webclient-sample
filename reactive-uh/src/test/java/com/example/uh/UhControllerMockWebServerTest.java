package com.example.uh;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UhControllerMockWebServerTest {

    private MockWebServer appleServer;
    private MockWebServer penServer;
    private UhController controller;

    @BeforeEach
    void startMockWebServers() throws Exception {
        appleServer = new MockWebServer();
        penServer = new MockWebServer();

        final UhProperties properties = new UhProperties();
        properties.setAppleUrl(appleServer.url("/").uri().toString());
        properties.setPenUrl(penServer.url("/").uri().toString());
        final UhService service = new UhService(properties);
        controller = new UhController(service);
    }

    @AfterEach
    void shutdownMockWebServers() throws Exception {
        appleServer.shutdown();
        penServer.shutdown();
    }

    @Test
    void getApplePen() throws Exception {

        appleServer.enqueue(new MockResponse().setBody("Apple"));
        penServer.enqueue(new MockResponse().setBody("Pen"));

        final Mono<String> applePen = controller.getApplePen();

        StepVerifier.create(applePen)
                .expectNextMatches(s -> s.contains("ApplePen"))
                .verifyComplete();

        final RecordedRequest appleRequest = appleServer.takeRequest();
        assertEquals("/apple", appleRequest.getRequestUrl().encodedPath());

        final RecordedRequest penRequest = penServer.takeRequest();
        assertEquals("/pen", penRequest.getRequestUrl().encodedPath());
    }
}
