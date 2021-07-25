package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DefaultIfEmptyOperator {


    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                //If no results are provided, you can set a default value to be returned
                .defaultIfEmpty(-100)
                .subscribe(Utils.getSubscriber());
    }

    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 10);
    }

}


