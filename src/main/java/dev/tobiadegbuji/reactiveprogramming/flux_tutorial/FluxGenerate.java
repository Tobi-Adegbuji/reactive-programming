package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.function.Consumer;

public class FluxGenerate {

    public static void main(String[] args) {


        //With Flux.generate you do not need to handle the looping unlike Flux.create.
        //The consumer you pass to the generate method will be looped over and over again.


        //Whatever is in this block will be looped over and over again (creating an ifinite loop)
        Consumer<SynchronousSink<Object>> synchronousSinkConsumer = sink -> {
            System.out.println("emitting");
            sink.next(Utils.faker().animal()); //You can only emit max 1 item from sync sink, or else you will get an error.
            //sink.next(Utils.faker().funnyName().name()); <---- Would cause an error

            //The loop eill be discontinued if you use a take operator or, you can use sink.complete or sink.error

        };


        //You can use take operator with no extra checks b/c this is handled by generate method

        Flux.generate(synchronousSinkConsumer)
                .take(2)
                .subscribe(Utils.getSubscriber(""));

    }

}
