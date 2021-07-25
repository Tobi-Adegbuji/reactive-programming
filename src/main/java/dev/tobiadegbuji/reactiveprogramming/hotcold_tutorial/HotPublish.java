package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublish {

    public static void main(String[] args) {

        Flux<String> tvChannel = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .publish() //Publish returns a connectable flux.
                .refCount(2);  //Connectable flux has the refCount method which determines how many subscribers must subscribe before emitting data.

                //share() uses publish internally too but it defaults refCount to 1. (share = publish().refCount(1))


        //Notice the data was not emitted until the second subscriber subscribed since refCount is equal to 2
        tvChannel.subscribe(Utils.getSubscriber("Tobi"));

        Utils.sleepSeconds(5);

        tvChannel.subscribe(Utils.getSubscriber("Steve"));

        Utils.sleepSeconds(60);

        //Note: if a subscriber signals onComplete before another subscriber subscribes to the same publisher, then a REQ will be made to the publisher again
        //A REQ is not made to the publisher when another subscriber subscribes while another subscriber is still streaming and has not signaled onComplete yet

    }

    private static Stream<String> getMovie(){
        System.out.println("Retrieved Movie Streaming Req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6"
        );
    }

}
