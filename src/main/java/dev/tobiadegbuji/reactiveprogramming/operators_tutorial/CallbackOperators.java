package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class CallbackOperators {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("Completed");
        })
                //HERE ARE THE VARIOUS CALLBACK OPERATORS THAT CAN BE CALLED ON A PUBLISHER
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
//B/C PIPELINES EXECUTE FROM BOTTOM UP
//DoOnTerminate will always be executed regardless if the publisher throws an error or the publisher completes

    //DoFinally will ALWAYS BE EXECUTED. If you need to do anything last, useDoFinally
    //Always place doFinally at the end
}
