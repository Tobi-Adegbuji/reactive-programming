package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxRange {


    public static void main(String[] args) {

        //Range allows you to sort of create a for loop
        //Below I am printing a unique name 10 times
        //Notice the start is inclusive.

        Flux.range(1, 10)
        .subscribe(i -> System.out.println(i +": " + Utils.faker().name().fullName()));


        //Here I am using map to print 5 names

        Flux.range(1, 5)
                .map(i -> Utils.faker().funnyName().name())
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        () -> System.out.println("Printed Funny Names")
                );

    }

}
