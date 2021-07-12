package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxStream {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);

        Stream<Integer> stream = list.stream();

        stream.forEach(System.out::println);

        //Streams can only be executed once. This line will throw a runtime exception.
        //stream.forEach(System.out::println);

        Stream<Integer> integerStream = List.of(1, 2, 3, 4, 5).stream();

        Flux<Integer> integerFlux = Flux.fromStream(integerStream);

        integerFlux.subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

        //this line also will result in error because it is trying to reuse the same stream integerStream
        integerFlux.subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());


        //To avoid this, we can use the overloaded fromStream() method which takes a supplier of Stream Type
        Flux<Integer> flux = Flux.fromStream(() -> Stream.of(1, 2, 3, 4, 5));

        //Notice there is no more errors when the below lines are run.
        flux.subscribe(Utils.onNext());

        flux.subscribe(Utils.onNext());


    }


}
