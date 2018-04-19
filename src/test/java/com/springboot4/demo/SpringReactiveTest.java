package com.springboot4.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SpringReactiveTest {

    private static List<String> streamOfNames = Arrays.asList("John", "Adam", "Joe", "Doe", "Jane");

    class SystemOutConsumer implements Consumer<String> {


        @Override
        public void accept(String t) {
            System.out.println("Received " + t + " at " + new Date());
        }
    }

    @Test
    public void monoExample() throws InterruptedException {
        Mono<String> stubMonoWithADelay = Mono.just("John").delayElement(Duration.ofSeconds(2));

        // Using class implementation of the Consumer<String> interface.
        stubMonoWithADelay.subscribe(new SystemOutConsumer());

        // Using Java8 lambda for the consumer.
        stubMonoWithADelay.subscribe(t -> {
            System.out.println("Welcome " + t);
        });

        TimeUnit.SECONDS.sleep(4);
    }

    @Test
    public void simpleFluxStream() {
        Flux<String> stubFluxStream = Flux.just("Jane", "Joe");
        stubFluxStream.subscribe(new SystemOutConsumer());
    }

    @Test
    public void fluxStreamWithDelay() throws InterruptedException {
        Flux<String> stubFluxWithNames = Flux.fromIterable(streamOfNames).delayElements(Duration.ofMillis(1000));

        // Consumer 1
        stubFluxWithNames.subscribe(new SystemOutConsumer());

        // Consumer 2
        stubFluxWithNames.subscribe(t -> {
            System.out.println("Welcome " + t);
        });
        Thread.sleep(10000);
    }
}
