package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxTakeOperator{


    public static void main(String[] args) {

        //Map, filter are example of operators. They decorate your code

        Flux.range(1, 10)
                .log()
                .take(3) //After 3rd item is emitted,  subscription is cancelled
                .log()
                .subscribe(Utils.getSubscriber("sub"));

        //As soon as take is cancelled, no more work is done.

    }

}
