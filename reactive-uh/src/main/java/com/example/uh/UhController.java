package com.example.uh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@RestController
public class UhController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UhService service;

    public UhController(final UhService service) {
        this.service = service;
    }

    @GetMapping("/uh")
    public Mono<String> getApplePen() {

        final long start = System.currentTimeMillis();

        final Mono<String> maybePen = service.pen();

        final Mono<String> maybeApple = service.apple();

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
