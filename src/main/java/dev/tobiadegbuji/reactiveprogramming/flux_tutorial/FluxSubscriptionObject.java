package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class FluxSubscriptionObject {


    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Subscription: " + subscription);
                        atomicReference.set(subscription);
                        //subscription has to request more items from publisher
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext : " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError : " + throwable.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed!");
                    }
                });

        Utils.sleepSeconds(3);
        atomicReference.get().request(3);
        Utils.sleepSeconds(10);
        atomicReference.get().request(3);
        Utils.sleepSeconds(10);
        System.out.println("Going to cancel");
        //After cancellation, the publisher will not emit anymore items
        atomicReference.get().cancel();
        Utils.sleepSeconds(10);
        //No longer will emit items
        atomicReference.get().request(4);
        Utils.sleepSeconds(2000);

    }

}
