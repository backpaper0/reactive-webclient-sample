package com.example.blockinguh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class BlockingUhApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BlockingUhApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(BlockingUhApplication.class);

    @Value("${appleUrl:http://localhost:8082}")
    private String appleUrl;
    @Value("${penUrl:http://localhost:8083}")
    private String penUrl;

    @GetMapping("/uh")
    public String getApplePen() {

        final long start = System.currentTimeMillis();

        final RestTemplate restTemplate = new RestTemplate();

        final String pen = restTemplate.getForObject(penUrl + "/pen", String.class);

        final String apple = restTemplate.getForObject(appleUrl + "/apple", String.class);

        final long end = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("Uh!");
        }

        return String.format("%s%s(%dmsec)", apple, pen, end - start);
    }
}
