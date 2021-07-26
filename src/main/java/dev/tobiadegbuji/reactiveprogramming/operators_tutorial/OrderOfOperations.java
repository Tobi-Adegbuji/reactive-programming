package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OrderOfOperations {


    //If we have 3 doFirst operators attached on a pipeline, which one will execute first?

    /*
    The bottom doFirst is executed first. Why/
    In the lifecycle, the subscriber makes a request to the publisher.
    It makes sense that the subscribe operator would be the first to execute since that must happen for the pipeline to execute first
    SO REACTIVE PIPELINES WORK FROM BOTTOM TO TOP!
     */

    public static void main(String[] args) {
        Flux.create(sink -> {
            sink.next("Hello World");
            sink.complete();
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirst 1: This is the THIRD very first thing that get executed in the pipeline."))
                .doOnNext(obj -> System.out.println("doOnNext: " + obj))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe: " + subscription))
                .doOnRequest(l -> System.out.println("doOnRequest: " + l))
                .doFirst(() -> System.out.println("doFirst 2: This is the SECOND very first thing that get executed in the pipeline."))
                .doOnError(err -> System.out.println("doOnError: " + err.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doFirst(() -> System.out.println("doFirst 3: This is the very first thing that get executed in the pipeline."))
                .doFinally(signalType -> System.out.println("doFinally: " + signalType))
                .doOnDiscard(Object.class, obj -> System.out.println("doOnDiscard: " + obj))
                .subscribe(Utils.getSubscriber(""));
    }

}
