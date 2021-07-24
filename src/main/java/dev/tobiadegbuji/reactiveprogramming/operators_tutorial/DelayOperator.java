package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayOperator {


    public static void main(String[] args) {
        //delayElements sits in the middle between the
        // subscriber and the publisher to regulate the amount of items being emitted through duration
        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Utils.getSubscriber(""));

        //Display Elements also uses LimitRateInternally.

        Utils.sleepSeconds(60);


    }




}
