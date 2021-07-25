package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TimeoutOperator {


    public static void main(String[] args) {

        getOrderNumbers()
                //Each item can not take longer than 2 seconds when be retrieved or a timeout will be thrown.
                //A fallback can be provided in case an error is thrown.
                .timeout(Duration.ofSeconds(2), fallback())
                .subscribe(Utils.getSubscriber(""));

        Utils.sleepSeconds(60);

    }

    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> fallback(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }


}
