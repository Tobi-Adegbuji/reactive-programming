package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Assignment1 {


    /*
    Keep generating country names
    until you reach Canada using the handle method.
     */


    public static void main(String[] args) {


        Flux.generate(sink -> sink.next(Utils.faker().country().name()))
                .handle(synchronousSinkBiConsumer)
                .subscribe(Utils.getSubscriber(""));

    }

    private static BiConsumer<Object, SynchronousSink<Object>> synchronousSinkBiConsumer = (countryName, sink) -> {
        sink.next(countryName);
        if(((String) countryName).equalsIgnoreCase("canada"))
            sink.complete();
    };


}
