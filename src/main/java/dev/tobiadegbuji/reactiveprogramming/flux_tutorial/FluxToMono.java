package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxToMono {

    public static void main(String[] args) {

        Flux.range(1,10)
                .filter(i -> i == 3) // Filtering for the item I want to be emitted (which is 3)
                .next() //Grabs the first emitted Item Only and completes
                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

    }


}
