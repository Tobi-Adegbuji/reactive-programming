package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import reactor.core.publisher.Flux;

public class ThreadDemo {

    public static void main(String[] args) {

        //Default behavior is that everything will only run in the main thread
        //In this demo we test running these subscriptions on different threads.
        //Ultimately we do not want to create and maintain the threads ourselves. r

        Flux<Object> create = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
                .doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> create.subscribe(v -> printThreadName("sub " + v));

        for(int i = 0; i < 2; i++){
            new Thread(runnable).start();
        }

    }

    public static void printThreadName(String msg){
        System.out.println(msg + "\t\t: Thread: " +Thread.currentThread().getName());
    }


}
