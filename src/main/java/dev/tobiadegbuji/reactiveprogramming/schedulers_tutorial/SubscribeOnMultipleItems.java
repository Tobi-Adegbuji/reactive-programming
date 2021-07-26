package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class SubscribeOnMultipleItems {

    public static void main(String[] args) {


        /*
        I expected the subscribeOn Will emit data on several threads in the thread pool but that is not the case.
        It is whenever you have multiple subscribers that they will be executed om different threads in the thread pool.
        Take a look at the img attache to this package
         */

        Flux<Object> create = Flux.create(fluxSink -> {
            printThreadName("create");
            IntStream.rangeClosed(1,20)
                    .forEach(i -> {
                        fluxSink.next(i);
                        Utils.sleepSeconds(1);
                    });
            fluxSink.complete();
        })
                .doOnNext(i -> printThreadName("next " + i));


        //What happens if you subscribe with 2 different thread pools?
        //Which one will take precedence?

        //Well they will just run in their own respected thread pools. Simple.

        create
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));

        create
                .subscribeOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub " + v));




        //Notice each subscriber will run on a different thread

//       Runnable runnable = () ->  create
//                .subscribeOn(Schedulers.boundedElastic())
//                .subscribe(v -> printThreadName("sub " + v));
//
//
//       IntStream.rangeClosed(1,5)
//               .forEach(i -> new Thread(runnable).start());

        Utils.sleepSeconds(5);


    }

    public static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread: " +Thread.currentThread().getName());
    }
}
