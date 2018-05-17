package com.example.uh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class UhApplication {

    public static void main(String[] args) {
        SpringApplication.run(UhApplication.class, args);
    }

    @Value("${appleUrl:http://localhost:8082}")
    private String appleUrl;
    @Value("${penUrl:http://localhost:8083}")
    private String penUrl;

    @GetMapping("/uh")
    public Mono<String> getApplePen() {

        final long start = System.currentTimeMillis();

        final WebClient client = WebClient.create();

        final Mono<String> maybeApple = client.get()
                .uri(appleUrl + "/apple")
                .retrieve()
                .bodyToMono(String.class);

        final Mono<String> maybePen = client.get()
                .uri(penUrl + "/pen")
                .retrieve()
                .bodyToMono(String.class);

        return maybeApple.zipWith(maybePen)
                .map(x -> {
                    final String apple = x.getT1();
                    final String pen = x.getT2();
                    final long end = System.currentTimeMillis();
                    return String.format("%s%s(%dmsec)", apple, pen, end - start);
                });
    }
}
