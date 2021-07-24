package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxGenerateWithState {


    public static void main(String[] args) {

        Flux.generate(
                () -> 1,
                (state, sink) -> {

                    String country = Utils.faker().country().name();

                    sink.next(country);

                    if(state >= 10 || country.equalsIgnoreCase("canada"))
                        sink.complete();


                    //Once this blocks exit, new state must be provided.
                    return state + 1;
                }
        )
//                .take(7)
                .subscribe(Utils.getSubscriber(""));

    }


}
