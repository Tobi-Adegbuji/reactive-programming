package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.services;

import dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.domain.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService{


    private Map<String,Double> db = new HashMap<>();

    public InventoryService(){
        db.put("Kids", 100d);
        db.put("Automotive", 100d);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream(){
        return
                p -> db.computeIfPresent(p.getCategory(), (key, currentVal) -> currentVal - p.getQuantity());
    }

    public Flux<String> inventoryStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString());
    }


}


