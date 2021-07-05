package dev.tobiadegbuji.reactiveprogramming.section_1;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class FromCallable {

    public static void main(String[] args) {


        /*
        The Callable interface is similar to Runnable,
        in that both are designed for classes whose instances
        are potentially executed by another thread. A Runnable,
        however, does not return a result and cannot throw a checked exception.
         */

        Callable<String> stringCallable = MonoFromSupplier::getName;

        //Will be executed in a new thread. getName method will not be called if there are no subscribers.
        //Exactly the same as the fromSupplier effect (AKA Lazy Loading).
        Mono<String> mono = Mono.fromCallable(stringCallable);

        //Subscribing as seen below will only execute getName static method
        mono.subscribe(
                Utils.onNext()
        );

    }



}
