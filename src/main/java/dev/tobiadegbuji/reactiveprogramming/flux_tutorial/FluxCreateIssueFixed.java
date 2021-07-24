package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxCreateIssueFixed {

    public static void main(String[] args) {

        //In the FluxCreate we did not check if down stream is cancelled yet.
        //You should add this check to make sure

        Consumer<FluxSink<Object>> fluxSinkConsumer = FluxCreateIssueFixed::fluxActionsFixed;


        //Only 1 instance of flux sink is given.
        Flux.create(fluxSinkConsumer)
                .take(3)
                .subscribe(Utils.getSubscriber("Sub"));



    }

    private static void fluxActionsFixed(FluxSink<Object> fluxSink) {
        //Lets keep generating country names until Canada is mentioned.

        String thread = Thread.currentThread().getName();

        String countryName = "";

        //Added check to make sure flux sink is not cancelled
        while (!countryName.equalsIgnoreCase("canada") && !fluxSink.isCancelled()) {
            countryName = Utils.faker().country().name();

//            if (countryName.equalsIgnoreCase("canada"))
//                break;
            System.out.println("EMITTING : " + countryName);

            fluxSink.next(thread + ": " + countryName);
        }

        fluxSink.complete();

    }

}
