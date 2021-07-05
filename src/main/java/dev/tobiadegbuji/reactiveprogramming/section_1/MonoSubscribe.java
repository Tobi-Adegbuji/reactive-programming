package dev.tobiadegbuji.reactiveprogramming.section_1;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

public class MonoSubscribe {

    public static void main(String[] args) {

        Mono<String> stringMono = Mono.just("Tobi");

        //onNext and onCompleted
        stringMono.subscribe(
                System.out::println,
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("Completed")
        );


        System.out.println();

        //onNext and onError,
        Mono<Integer> errorMono = Mono.just("Tobi")
                .map(String::length)
                .map(i -> i / 0);

        //Notice it provides "optional-like" error handling.
        errorMono.subscribe(
                Utils.onNext(),
                Utils.onError(),
                Utils.onComplete());


    }




}
