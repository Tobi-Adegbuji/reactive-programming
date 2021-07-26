package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class MultipleSubscribeOn {

    public static void main(String[] args) {

        //The threadPool closest to the publisher will take precedence.

        //As a developer you will be exposing your publishers for consumption.
        //Who ever subscribes to your publisher is NOT responsible for creating schedulers.
        //Example: You wrote a Flux that emits data from a DB. Lets say this is very time consuming.
        //YOU should use scheduleOn as an operator on your flux before exposing the flux for consumption.

        Flux<Object> create = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
                .subscribeOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> create
                .doFirst(() -> printThreadName("first2"))
                //Notice subscribeOn has the sam precedence as doFirst
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub " + v));

        //Notice the pipeline is created twice in 2 different threads but, the data is emitted is handled by 2 different bounded elastic threads
        for(int i = 0; i < 2; i++){
            new Thread(runnable).start();
        }

        Utils.sleepSeconds(3);


    }

    public static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread: " +Thread.currentThread().getName());
    }

}
