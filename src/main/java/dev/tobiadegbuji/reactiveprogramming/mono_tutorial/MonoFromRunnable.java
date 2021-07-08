package dev.tobiadegbuji.reactiveprogramming.mono_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {


    public static void main(String[] args) {

        //Mono from runnable is useful when you want to be notified a
        // certain task will be complete

        Mono.fromRunnable(timeConsumingProcess())
        .subscribe(Utils.onNext(),
                Utils.onError(),
                () ->System.out.println("Process is done, sending emails."));


    }


    private static Runnable timeConsumingProcess(){
        return () -> {
            Utils.sleepSeconds(3);
            System.out.println("Operation Completed!");
        };
    }



}
