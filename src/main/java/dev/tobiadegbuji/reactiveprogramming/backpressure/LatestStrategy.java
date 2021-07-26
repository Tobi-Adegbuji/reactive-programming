package dev.tobiadegbuji.reactiveprogramming.backpressure;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LatestStrategy {


    public static void main(String[] args) {

        /*
        The latest strategy is very much like drop except latest keeps
        the "latest item emitted by the publisher in cache". When Q is full and
        items are still being emitted from upstream then the latest item will be
        kept in cache while the older one will be dropped.

        Notice how 200 is always received at the end. Because the latest item is always kept.


         */

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(sink -> {
            IntStream.rangeClosed(1,200)
                    .forEach(i -> {
                        sink.next(i);
                        System.out.println("Pushed: " + i);
                        Utils.sleepMilliSecs(1);
                    });
            sink.complete();
        })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(v -> Utils.sleepMilliSecs(10))
                .subscribe(Utils.getSubscriber());


        Utils.sleepSeconds(2);

    }

}
