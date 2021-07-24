package dev.tobiadegbuji.reactiveprogramming.helper;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> getNames(int count){
        List<String> list = new ArrayList<>();

        IntStream.range(0, count)
               // .parallel()
                .forEach(i -> list.add(getName()));

        return list;

    }

    public static Flux<String> getNamesFlux(int count){
        return Flux.range(0, count)
                .map(i -> getName());
    }




    //Assume this is a time consuming process hence Utils.sleepSeconds
    public static String getName(){
        Utils.sleepSeconds(1);
        return Utils.faker().name().fullName();
    }

}
