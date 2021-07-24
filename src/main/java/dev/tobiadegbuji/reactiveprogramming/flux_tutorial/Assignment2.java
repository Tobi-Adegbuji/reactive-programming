package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Assignment2 {

    //STOCK PRICE OBSERVER

    public static void main(String[] args) throws InterruptedException {

        int stockPriceForPublisher = 112 ;



        CountDownLatch latch = new CountDownLatch(1);

        //Create A Publisher from interval to emit every second
        Flux.interval(Duration.ofSeconds(1))
                .map(i -> stockPriceForPublisher + generateRandomNumber(-10, 10))
                .subscribeWith(new Subscriber<Integer>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        //Requesting as much items as possible
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer price) {
                        System.out.println(LocalDateTime.now() + " : Price : " + price);

                        if(price >= 115 || price < 90)
                            this.subscription.cancel();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("ERROR");
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("COMPLETED");
                        latch.countDown();
                    }
                });


        latch.await();;

    }

    public static int generateRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


}
