package dev.tobiadegbuji.reactiveprogramming.schedulers_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParallelEmissionOfItems {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<Integer> syncList = Collections.synchronizedList(list);

        Flux.range(1, 1000)
                .parallel(2) //parallel() returns a parallel flux. Each item will be emitted on different threads.
                .runOn(Schedulers.boundedElastic()) //Choose the thread scheduler thread pool you want to use.
//                .doOnNext(i -> printThreadName("sub onNext: " + i)) //Lets say this is an IO intensive task
                .subscribe(syncList::add); //lets say this is a CPU intensive task

        Utils.sleepSeconds(5);

        //The size wouldn't equate to 1000 if I used an arrayList. This is because ArrayList is NOT thread safe.
        //As a developer, you will be responsible for ensuring everything is thread safe
        //In this example I used a synchronized list to ensure thread safety.
        System.out.println(syncList.size());

    }

    public static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
