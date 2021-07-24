package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class LimitRateOperator {

    public static void main(String[] args) {


        //What is happening here?

        /*
        LimitRate will request 100 items only from publisher.
        From there the subscribe method will keep receiving the emitted items from the
         LimitRate. Once limit rates sees that the subscriber has received
         75% out of the 100 items requested, then it will send a request to the publisher
          for another 75% items.=%.

          We can adjust the 75% to our likings by passing to num args in the method
          Below, I increased it by 1.

          If you want to produce another 100 after reaching 100, make the lowTide 0.
         */

        Flux.range(1,1000)
                .log()
                .limitRate(100, 76)
                .subscribe(Utils.getSubscriber(""));


    }


}
