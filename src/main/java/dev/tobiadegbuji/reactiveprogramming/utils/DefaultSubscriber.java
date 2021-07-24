package dev.tobiadegbuji.reactiveprogramming.utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Flow;

public class DefaultSubscriber implements Subscriber{


    private String name = "";

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    public DefaultSubscriber() {
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE); //UNBOUNDED REQUEST
    }

    @Override
    public void onNext(Object item) {
        System.out.println(this.name + " received: " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(this.name + " error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(this.name + " completed");
    }
}
