package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class PublishOn {
    public static void main(String[] args) {

        /*
        Notice here that the data is emitted on the producer side in the main thread.
        While the data being consumed is happening in the scheduler thread pool.
        This is essentially what publishOn method does for us.
         */

        Flux<Object> create = Flux.create(fluxSink -> {
            printThreadName("pub create");
            IntStream.rangeClosed(1,4).forEach(i -> {
                        fluxSink.next(i);
                        Utils.sleepSeconds(1);
            });
            fluxSink.complete();
        }).doOnNext(i -> printThreadName("pub onNext " + i));


        create
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("sub onNext: " + i)) //Lets say this is an IO intensive task
                .publishOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub subOnNext " + v)); //lets say this is a CPU intensive task

        Utils.sleepSeconds(5);

    }

    public static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread: " +Thread.currentThread().getName());
    }

}


