package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublishAutoConnect {

    public static void main(String[] args) {

        Flux<String> tvChannel = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                //Auto connects makes a publisher completely hot.
                //This means only data will be emitted once unlike the refCount
                .autoConnect(0);

        Utils.sleepSeconds(2);

        tvChannel.subscribe(Utils.getSubscriber("Tobi"));

        Utils.sleepSeconds(10);

        System.out.println("Steve is about to join, but the show is already over");

        tvChannel.subscribe(Utils.getSubscriber("Steve"));

        Utils.sleepSeconds(60);


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
