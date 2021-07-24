package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class Assignment3 {

    //Generate country names until you hit Canada but using  Flux.generate or complete flux
    //after 10 items are emitted.

    //THIS IS ONE WAY OF USING A COUNTER


    public static int counter = 1;

    private static void main(String[] args) {

        Flux.generate(Assignment3::generateCountryNames)
                .subscribe(Utils.getSubscriber(""));

    }

    private static void generateCountryNames(SynchronousSink<Object> sink){

        String countryName = Utils.faker().country().name();



        if(countryName.equalsIgnoreCase("canada")  || counter > 40){
            counter = 1;
            sink.complete();
        }

        sink.next(countryName + " - " + counter);
        counter++;
    }

}
