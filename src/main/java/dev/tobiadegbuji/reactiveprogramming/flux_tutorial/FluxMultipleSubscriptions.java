package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxMultipleSubscriptions {

//Multiple subscribers can subscribe to a publisher like so
    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(i -> System.out.println("Sub 1 : " + i ));


        flux.filter(i -> i % 2 == 0).subscribe(
                i -> System.out.println("Sub 2 (Even) : " + i),
                Utils.onError(),
                Utils.onComplete());
    }


}
