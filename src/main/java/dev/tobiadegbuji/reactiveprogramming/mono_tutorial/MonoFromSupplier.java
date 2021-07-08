package dev.tobiadegbuji.reactiveprogramming.mono_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

public class MonoFromSupplier {


    public static void main(String[] args) {

        //Use just ONLY when you already have data present.
        //There are no subscribers for this publisher, but the getName method will still execute
        //We do not want this (getName is CPU intensive). We can use a supplier instead to stop this behavior.
        Mono<String> mono = Mono.just(getName());

        //Using supplier, will not call getMethod if there are no subscribers.
        //Notice the sout is not seen when using the supplier.
        Mono.fromSupplier(() -> getName());


        //If you have data already use just, otherwise use fromSupplier

    }

    //Imagine that to retrieve this method it takes some time and is CPU intensive
    public static String getName(){
        System.out.println("Generating name..");
        return Utils.faker().name().fullName();
    }

}
