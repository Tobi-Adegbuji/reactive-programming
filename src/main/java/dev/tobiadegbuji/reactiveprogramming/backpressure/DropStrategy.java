package dev.tobiadegbuji.reactiveprogramming.backpressure;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DropStrategy {

    public static void main(String[] args) {

        /*
        Below we set the total queue size to 16.
        After 75% (12) of the buffer size has been filled it drops the 12 spots so
        they can be filled.

        This saves the app from throwing an out of memory exception but at the cost of dropping all those elements

        The backPressureDrop() also accepts a consumer in which you can decide what to do with the dropped items.
        In this case I am pretending to save it in a database (The List).
         */

       System.setProperty("reactor.bufferSize.small", "16");

       List<Object> listDB = new ArrayList<>();

        Flux.create(sink -> {
            IntStream.rangeClosed(1,500)
                    .forEach(i -> {
                        sink.next(i);
                        System.out.println("Pushed: " + i);
                        Utils.sleepMilliSecs(1);
                    });
            sink.complete();
        })
                .onBackpressureDrop(listDB::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(v -> Utils.sleepMilliSecs(10))
                .subscribe(Utils.getSubscriber());


        Utils.sleepSeconds(2);
        System.out.println(listDB);

    }

}
