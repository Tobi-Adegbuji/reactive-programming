package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublishCache {


    public static void main(String[] args) {


        Flux<String> tvChannel = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                //cache = publish().replay()
                //will store a certain amount in cache memory so when
                // a new subscriber joins, they will also be given whatever is in the cache
                //You can set the max amount of items that can be stored in cache by pass an integer argument
                .cache(2);

        Utils.sleepSeconds(2);

        tvChannel.subscribe(Utils.getSubscriber("Tobi"));

        Utils.sleepSeconds(3);

        System.out.println("Steve is about to tune into the channel, Steve missed a lot of the show but lets give him what is in cache so he can rewind and watch later.");

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
