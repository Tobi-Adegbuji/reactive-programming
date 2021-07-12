package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class FluxFromListOrArray {

    public static void main(String[] args) {

        //LIST
        List<String> lettersList = Arrays.asList("a", "b", "c", "d");

        //Used to get data from list. Similar to just method except data is already in a list.
        Flux.fromIterable(lettersList)
                .subscribe(Utils.onNext());


        //ARRAY: Can not take primitive types. Only objects
        Integer [] nums = new Integer[]{11,72,34,25,16};

        Flux.fromArray(nums)
                .subscribe(Utils.onNext());
    }


}
