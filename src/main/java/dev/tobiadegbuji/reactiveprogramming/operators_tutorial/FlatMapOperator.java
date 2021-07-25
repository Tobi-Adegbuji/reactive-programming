package dev.tobiadegbuji.reactiveprogramming.operators_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatMapOperator {


    public static void main(String[] args) {

        UserService.getUsers()
                //.flatMap(user -> OrderService.getOrders(user.getUserId())) //Internally subscribes to all publishers returned and flattens them
                .concatMap(user -> OrderService.getOrders(user.getUserId()).log().delayElements(Duration.ofSeconds(1)))
                .filter(purchaseOrder -> Double.parseDouble(purchaseOrder.getPrice()) > 50.0)
                .doOnNext((purchaseOrder) -> System.out.println(" "))
                .subscribe(Utils.getSubscriber());


        Utils.sleepSeconds(100);

    }

    //YOU CAN ALSO USE CONTACT MAP. concatMap ensures that the onComplete signal is called for every publisher


}



class UserService{

    public static Flux<User> getUsers(){
        return Flux.range(1, 3)
                .map(i -> new User(i));
    }

}


class OrderService {
    public static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> list1 = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
                );
        List<PurchaseOrder> list2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        List<PurchaseOrder> list3 = Arrays.asList(
                new PurchaseOrder(3),
                new PurchaseOrder(3),
                new PurchaseOrder(3)
        );

        db.put(1,list1);

        db.put(2,list2);

        db.put(3,list3);

    }

    public static Flux<PurchaseOrder> getOrders(int userId){
        return Flux.create(purchaseOrderFluxSink -> {
            db.get(userId)
                    .forEach(purchaseOrderFluxSink::next);
            purchaseOrderFluxSink.complete();
        });
    }

}

@Data
@ToString
class User {

    private int userId;
    private String userName;

    public User(int userId) {
        this.userId = userId;
        userName = Utils.faker().name().firstName();
    }
}

@Data
@ToString
class PurchaseOrder {
    private String item;
    private String price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item = Utils.faker().commerce().productName();
        this.price = Utils.faker().commerce().price();
    }
}