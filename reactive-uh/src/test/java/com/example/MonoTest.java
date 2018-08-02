package com.example;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

class MonoTest {

    @Test
    void subscribe() throws Exception {
        final Mono<String> mono = Mono.just("Hello, Mono!");
        mono.subscribe(this::println);
    }

    @Test
    void block() throws Exception {
        final Mono<String> mono = Mono.just("Hello, Mono!");
        final String block = mono.block();
        println(block);
    }

    private void println(final Object o) {
        System.out.printf("[%s]%s%n", Thread.currentThread().getName(), o);
    }
}
