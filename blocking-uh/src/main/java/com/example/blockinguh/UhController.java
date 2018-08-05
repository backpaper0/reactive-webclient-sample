package com.example.blockinguh;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UhController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UhService service;

    public UhController(final UhService service) {
        this.service = Objects.requireNonNull(service);
    }

    @GetMapping("/uh")
    public String getApplePen() {

        final long start = System.currentTimeMillis();

        final String pen = service.pen();

        final String apple = service.apple();

        final long end = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("Uh!");
        }

        return String.format("%s%s(%dmsec)", apple, pen, end - start);
    }
}
