package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.helper.NameGenerator;
import dev.tobiadegbuji.reactiveprogramming.utils.Utils;

import java.util.List;

public class FluxVsList {


    public static void main(String[] args) {

        //Notice this will take 5 secs but the data comes in parts instead of a whole.
        NameGenerator.getNamesFlux(5)
                .subscribe(Utils.onNext());


        //After 5 seconds everything will be given, with the code below
        List<String> names = NameGenerator.getNames(5);
        System.out.println(names);



    }

}
