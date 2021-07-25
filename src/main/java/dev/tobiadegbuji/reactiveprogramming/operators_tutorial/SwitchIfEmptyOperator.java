package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class SwitchIfEmptyOperator {



    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                //SwitchIfEmptyOperator takes a publisher as an arg. So you can create a fallback with it
                .switchIfEmpty(fallback())
                .subscribe(Utils.getSubscriber());
    }

    //REAL WORLD SCENARIO
    //Imagine this is you trying to receive data from Redis Cache. But the cache returns empty data
    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 10);
    }

    //You can then query the data from a database using this fallback
    private static Flux<Integer> fallback(){
        return Flux.range(20, 5);
    }



}
