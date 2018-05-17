package com.example.apple;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppleApplication.class, args);
    }

    @Value("${sleep:3}")
    private long sleep;

    @GetMapping("/apple")
    public String getApple() {
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (final InterruptedException e) {
        }
        return "Apple";
    }
}
