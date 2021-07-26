package dev.tobiadegbuji.reactiveprogramming.backpressure;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class StartDemo {

    public static void main(String[] args) {


        //Notice that the publisher is emitting items faster than the subscriber is recieving them.
        //This is because the subscriber is doing some kind of time-consuming task
        //As we know, this can lead to memory overflow errors since by default reactor stores the emitted items in memory
        //Thankfully, There are several other strategies to overcome this
        Flux.create(sink -> {
            IntStream.rangeClosed(1,500)
                    .forEach(i -> {
                        sink.next(i);
                        System.out.println("Pushed: " + i);
                    });
            sink.complete();
        })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(v -> {
                    Utils.sleepMilliSecs(10);
                })
                .subscribe(Utils.getSubscriber());


        Utils.sleepSeconds(10);

    }

}
