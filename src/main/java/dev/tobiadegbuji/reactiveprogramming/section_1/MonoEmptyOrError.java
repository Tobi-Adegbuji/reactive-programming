package dev.tobiadegbuji.reactiveprogramming.section_1;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import jdk.jshell.execution.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {


    public static void main(String[] args) {

        userRepo(3)
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
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
