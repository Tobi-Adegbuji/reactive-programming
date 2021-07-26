package dev.tobiadegbuji.reactiveprogramming.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadSafety {


    public static void main(String[] args) throws InterruptedException {

        var counter = new Counter();

        var  t1 = new Thread(() -> IntStream.rangeClosed(1, 2000)
                .forEach(i -> counter.increment()));

        var t2 = new Thread(() -> IntStream.rangeClosed(1, 1000)
                .forEach(i -> counter.increment()));


        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //Notice the amount printed is different every single time.
        //When you have multiple threads mutating data via a method you exposed,
        // hen that makes the method not thread safe.



        System.out.println(counter.count);

    }


}

class Counter{

    //int count;
    AtomicInteger count = new AtomicInteger();
    /*
   To increment count, there are multiple steps involved here.
   First, the current value of count needs to determined.
   Second, 1 is added to that current value.
   Third, the new value is assigned to count.

   The issue is occurs when two threads are on the exact same step and
   ask for the value of count. Well they will both get that the current
   value is the same. That is where the problem occurs.

   Thread safety simply means that a particular method can only be called by one thread at a time
   There are several ways to achieve this:

   1) Use the synchronized keyword on the increment method.
   2) You can also use an AtomicWrapper Class

    */

    //Using synchronized
//    public synchronized void increment(){
//        count = count + 1;
//    }

    //Using atomic integer
    public synchronized void increment(){
        count.incrementAndGet();
    }

}