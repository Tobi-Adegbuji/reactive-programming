package dev.tobiadegbuji.reactiveprogramming.mono_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {


    public static void main(String[] args) {

        userRepo(3)
                .subscribe(
                        Utils.onNext(), //What to do with each item returned
                        Utils.onError(), //What to when there is an error
                        Utils.onComplete() //What to do when complete transaction of items are done
                );


    }

    private static Mono<String> userRepo(int userId) {

        //1
        if (userId == 1)
            return Mono.just(Utils.faker().name().fullName());
        else if (userId == 2)
            return Mono.empty(); //Just like Optional.
        else
            return Mono.error(new RuntimeException("ID not allowed in range.."));

    }


}
