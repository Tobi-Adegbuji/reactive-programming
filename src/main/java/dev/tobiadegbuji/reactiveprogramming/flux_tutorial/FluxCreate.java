package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class FluxCreate {


    public static void main(String[] args) {

        //Flux Create allows you to determine when to emit data using a Flux Sink.
        //Flux Sink allows you to determine when to throw an error, when to emit, and when to complete.
        Flux.create(FluxCreate::fluxActions)
                .subscribe(Utils.getSubscriber("Sub"));

    }

    private static void fluxActions(FluxSink<Object> fluxSink) {
        //Lets keep generating country names until Canada is mentioned.

        String countryName = "";

        while (!countryName.equalsIgnoreCase("canada")) {
            countryName = Utils.faker().country().name();

//            if (countryName.equalsIgnoreCase("canada"))
//                break;

            fluxSink.next(countryName);
        }

        fluxSink.complete();

    }

}
