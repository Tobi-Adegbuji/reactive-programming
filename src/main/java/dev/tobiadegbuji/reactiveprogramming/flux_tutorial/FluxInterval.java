package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {

    public static void main(String[] args) {

        //Here is another way to create a flux
        //This will emit items periodically when subscribed to
        //This can be useful when you want to push any info to the user periodically
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Utils.onNext());

        Utils.sleepSeconds(5);

    }

}
