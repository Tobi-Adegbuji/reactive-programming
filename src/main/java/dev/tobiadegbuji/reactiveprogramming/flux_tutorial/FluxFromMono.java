package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {

    public static void main(String[] args) {

        /*
        In some scenarios, you will have a method that accepts a flux but you only have a mono.
        In these cases, yo can create a flux from a mono and pass to the method.
         */
        Mono<String> mono = Mono.just("Example");

        //Flux has a handy methid called From which converts any Publisher to a Flux.
        Flux<String> stringFlux = Flux.from(mono);

        doSomething(stringFlux);


    }

    private static void doSomething(Flux<String> flux){

    };


}
