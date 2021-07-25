package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.services;

import dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.domain.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {

    private Flux<PurchaseOrder> flux;


    //We only want to build the pipeline once hence why we exposed this method.
    public Flux<PurchaseOrder> orderStream(){
        if(Objects.isNull(flux))
            flux = getOrderStream();
        return flux;
    }



    private Flux<PurchaseOrder> getOrderStream(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }




}
