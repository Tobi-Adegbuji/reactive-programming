package dev.tobiadegbuji.reactiveprogramming.utils;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

public class Utils {

    private static final Faker faker = Faker.instance();

    public static Consumer<Object> onNext(){
        return item -> System.out.println("Received: " + item);
    }

    public static Consumer<Throwable> onError(){
        return e -> System.out.println("ERROR: " + e.getMessage());
    }

    public static Runnable onComplete(){
        return () -> System.out.println("COMPLETED");
    }

    public static Faker faker(){
        return faker;
    }

}
