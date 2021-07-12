package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxJust {

    public static void main(String[] args) {

        //Just method using flux.

        Flux<Object> flux = Flux.just(1,2,3,4,5, Utils.faker().name().firstName(), "Hello world");

        flux.subscribe(
                Utils.onNext(),
                Utils.onError(),
                Utils.onComplete());


    }


}
