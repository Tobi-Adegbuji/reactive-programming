package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OnErrorOperator {


    public static void main(String[] args) {

        //This will cause error.Below are several ways to handle errors.
        Flux.range(1,10)
                .log()
                .map(i -> 10/ (5-i))
               // .onErrorReturn(-1) //When an error occurs, -1 is returned
               // .onErrorResume(e -> fallback()) //Fallback method is called when error occurs
                .onErrorContinue((error, obj) ->
                    System.out.println("Do this when an error occurs, but don't stop the pipeline because of this error. Continue executing please."))
                .subscribe(Utils.getSubscriber(""));
    }


    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Utils.faker().random().nextInt(100,200));
    }

}
