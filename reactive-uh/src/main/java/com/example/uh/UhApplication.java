package com.example.uh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@SpringBootApplication
@RestController
public class UhApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UhApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(UhApplication.class);

    @Value("${appleUrl:http://localhost:8082}")
    private String appleUrl;
    @Value("${penUrl:http://localhost:8083}")
    private String penUrl;

    @GetMapping("/uh")
    public Mono<String> getApplePen() {

        final long start = System.currentTimeMillis();

        final WebClient client = WebClient.create();

        final Mono<String> maybePen = client.get()
                .uri(penUrl + "/pen")
                .retrieve()
                .bodyToMono(String.class);

        final Mono<String> maybeApple = client.get()
                .uri(appleUrl + "/apple")
                .retrieve()
                .bodyToMono(String.class);

        return maybeApple.zipWith(maybePen)
                .map(TupleUtils.function((apple, pen) -> {
                    if (logger.isInfoEnabled()) {
                        logger.info("Uh!");
                    }
                    final long end = System.currentTimeMillis();
                    return String.format("%s%s(%dmsec)", apple, pen, end - start);
                }));
    }
}
