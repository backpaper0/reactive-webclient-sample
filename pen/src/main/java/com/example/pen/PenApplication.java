package com.example.pen;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PenApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PenApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(PenApplication.class);

    @Value("${sleep:3}")
    private long sleep;

    @GetMapping("/pen")
    public String getPen() {
        if (logger.isInfoEnabled()) {
            logger.info("I have a pen.");
        }
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (final InterruptedException e) {
        }
        return "Pen";
    }
}
