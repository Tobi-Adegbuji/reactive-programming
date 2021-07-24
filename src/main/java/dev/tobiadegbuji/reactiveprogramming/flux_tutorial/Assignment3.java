package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.function.Consumer;

public class Assignment3 {


    public static void main(String[] args) {

        Flux.generate(Assignment3::generateCountryNames)
                .subscribe(Utils.getSubscriber(""));

    }

    private static void generateCountryNames(SynchronousSink<Object> sink){

        String countryName = "";
        countryName = Utils.faker().country().name();

        sink.next(countryName);

        if(countryName.equalsIgnoreCase("canada"))
            sink.complete();

    }

}
