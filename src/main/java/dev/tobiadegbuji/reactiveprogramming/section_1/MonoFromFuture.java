package dev.tobiadegbuji.reactiveprogramming.section_1;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {


    public static void main(String[] args) {

        System.out.println("Before future");

        Mono.fromFuture(getName())
        .subscribe(Utils.onNext());

        System.out.println("After future");

        Utils.sleepSeconds(1000);

        //Mono fromFuture will execute in separate thread & is non blocking

    }

    private static CompletableFuture<String> getName(){
        //Ran in different thread
        return CompletableFuture.supplyAsync(() -> Utils.faker().name().firstName());

    }


}
