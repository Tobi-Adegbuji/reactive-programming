package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class SwitchOnFirstOperator {


    //SwitchInFirstOperator ensures that the incoming elements are in the proper format
    //by checking the first item. If the first item is in the correct format, then it will
    //assume the rest of the elements are in the correct format. Otherwise, if the first element
    //is in the wrong format, then SwitchInFirstOperator will transform the data into a new

    public static void main(String[] args) {
        //Signal means, onError, onComplete, or onNext
        getPerson2()
                //If first person age is not greater than 40, then applyFilterOnNext is called on all the remaining elements.
                //If it is greater than 40, then applyFilterOnNext will never be called.
                .switchOnFirst((signal, personFlux) ->
                        signal.isOnNext() && signal.get().getAge() > 40 ? personFlux : applyFilterOnNext().apply(personFlux))
                .subscribe(Utils.getSubscriber());

        //This is why it is called a switch. Based on first element a switch of execution is made for the rest of the elements.

    }



    public static Flux<Person2> getPerson2(){
        return Flux.range(1,100)
                .map(i -> new Person2());
    }


    public static Function<Flux<Person2>, Flux<Person2>> applyFilterOnNext(){
        return flux -> flux
                .filter(p -> p.getAge() < 21)
                //DoOnNext allows us to use a consumer. Similar to forEach in streams API.
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person2.class, p -> System.out.println("These people are not allowed in pipeline: " + p));
    }



    @Data
    @ToString
    static class Person2 {
        private String name;
        private int age;

        public Person2(){
            this.name = Utils.faker().name().firstName();
            this.age = Utils.faker().random().nextInt(1,99);
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }


}

