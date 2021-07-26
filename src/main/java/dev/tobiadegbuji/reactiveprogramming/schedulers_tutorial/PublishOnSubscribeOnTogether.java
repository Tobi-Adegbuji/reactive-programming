package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class PublishOnSubscribeOnTogether {

    public static void main(String[] args) {
        Flux<Object> create = Flux.create(fluxSink -> {
            printThreadName("pub create");
            IntStream.rangeClosed(1,4).forEach(i -> {
                fluxSink.next(i);
                Utils.sleepSeconds(1);
            });
            fluxSink.complete();
        }).doOnNext(i -> printThreadName("pub onNext " + i));


        //Here you can just see that the publisher portion (Data Emission) is using boundedElastic
        //The doOnNext is then being placed in another parallel thread.

        //Use PARALLEL for cpu intensive and bounded elastic for Network/Time consuming tasks
        //If you don't know which one to use, use bound elastic
        create
                .publishOn(Schedulers.parallel()) //publish
                .doOnNext(i -> printThreadName("sub onNext: " + i)) //Lets say this is an IO intensive task
                .subscribeOn(Schedulers.boundedElastic()) //subscribeOn
                .subscribe(v -> printThreadName("sub subOnNext " + v)); //lets say this is a CPU intensive task

        Utils.sleepSeconds(5);

    }

    public static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread: " +Thread.currentThread().getName());
    }

}
