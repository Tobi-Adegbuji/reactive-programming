package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

public class FluxLog {


    public static void main(String[] args) {

        //You can use log() to

        Flux.range(1, 5)
                .log()
                //Map subscribes to the flux
                .map(i -> Utils.faker().funnyName().name())
                .log()
                //map returns a flux (similar to how streams create a pipelines by intermediate methods returning streams)
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        () -> System.out.println("Printed Funny Names")
                );

    }


}
