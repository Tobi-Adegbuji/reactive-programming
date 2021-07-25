package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayOperator {


    public static void main(String[] args) {

        //Used to set the default low tide of delayElements. Usually it is set to 32 and it can be no smaller than 8.
        //If you choose a value smaller than 8, then by default 8 will be chosen as the low tide.
        System.setProperty("reactor.bufferSize.x", "9");

        //delayElements sits in the middle between the
        // subscriber and the publisher to regulate the amount of items being emitted through duration
        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Utils.getSubscriber(""));

        //Display Elements also uses LimitRateInternally.

        Utils.sleepSeconds(120);


    }




}
