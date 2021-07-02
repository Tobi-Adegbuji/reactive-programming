package dev.tobiadegbuji.reactiveprogramming.section_1;

import reactor.core.publisher.Mono;

public class Mono_Just {


    public static void main(String[] args) {

        //The Publisher
        //Easy way to generate to data for Mono (just method)
        Mono<Integer> mono = Mono.just(1);

        //To get data from Mono, you must subscribe to it.
        //Below will not work.
        //System.out.println(mono);

        //Just printing whatever is received
        mono.subscribe(System.out::println);


    }


}
