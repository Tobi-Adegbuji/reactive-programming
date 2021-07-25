package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class TransformOperator {


    public static void main(String[] args) {
        getPerson()
                //Transform allows us to create our own custom operator, that can be reusable.
                .transform(applyFilterOnNext())
                .subscribe(Utils.getSubscriber());


//Here I am reusing the applyFilterOnNext logic with the transform method.
        getPerson()
                .filter(p -> p.name.toLowerCase().startsWith("a"))
                .transform(applyFilterOnNext())
                .subscribe(Utils.getSubscriber());

    }



    public static Flux<Person> getPerson(){
        return Flux.range(1,100)
                .map(i -> new Person());
    }


public static Function<Flux<Person>, Flux<Person>> applyFilterOnNext(){
        return flux -> flux
                .filter(p -> p.getAge() > 10)
                //DoOnNext allows us to use a consumer. Similar to forEach in streams API.
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("These people are not allowed in pipeline: " + p));
}



    @Data
    @ToString
    static class Person{
        private String name;
        private int age;

        public Person(){
            this.name = Utils.faker().name().firstName();
            this.age = Utils.faker().random().nextInt(1,99);
        }

    }





}



