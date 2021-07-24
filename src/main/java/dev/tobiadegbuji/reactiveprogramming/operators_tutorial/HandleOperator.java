package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class HandleOperator {


    public static void main(String[] args) {


        //handle = filter + map

        Flux.range(1, 20)
                .handle((integer, syncSink) -> {
                    //acts like a filter
                    if(integer % 2 == 0)
                    syncSink.next(integer);
                    //acts like a map
                    else
                        syncSink.next(integer + " ODD");
                })
                .subscribe(Utils.getSubscriber(""));

    }

}
