package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotShare {

    public static void main(String[] args) {

        Flux<String> tvChannel = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .share(); //Share converts a cold publisher to a hot publisher.


        tvChannel.subscribe(Utils.getSubscriber("Tobi"));

        Utils.sleepSeconds(5);

        tvChannel.subscribe(Utils.getSubscriber("Steve"));

        Utils.sleepSeconds(60);

        //Notice the getMovie() method is invoked ONCE.
        //Notice Steve begins watching the show at the same spot tobi is at. Steve tuned in to the program at a later time.

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
