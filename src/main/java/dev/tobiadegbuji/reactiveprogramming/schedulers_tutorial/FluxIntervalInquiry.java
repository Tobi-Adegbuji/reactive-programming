package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxIntervalInquiry {

    public static void main(String[] args) {

        //Internally, interval method and delaySecond Operator uses parallel thread pool
        //This means that you cannot use subscribeOn method the thread-pool. You can still use publishOn though.
        Flux.interval(Duration.ofSeconds(1))
               // .delayElements(Duration.ofSeconds(2))
                //.publishOn(Schedulers.boundedElastic()) //This will still work
                .subscribeOn(Schedulers.boundedElastic()) //Notices this does not work since the one closest to the publisher takes priority
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + " received value: " + v));

        Utils.sleepSeconds(10);

    }

}
