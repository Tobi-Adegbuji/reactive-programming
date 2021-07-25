package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisher {


    public static void main(String[] args) {

        Flux<String> netflix = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2));


        netflix.subscribe(Utils.getSubscriber("Tobi"));

        Utils.sleepSeconds(5);

        netflix.subscribe(Utils.getSubscriber("Steve"));

        Utils.sleepSeconds(60);

        //Notice the getMovie() method is invoked twice by Tobi and Steve (The Subscribers)

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
