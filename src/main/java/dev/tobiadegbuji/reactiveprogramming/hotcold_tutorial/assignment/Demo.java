package dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment;

import dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.services.InventoryService;
import dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.services.OrderService;
import dev.tobiadegbuji.reactiveprogramming.hotcold_tutorial.assignment.services.RevenueService;
import dev.tobiadegbuji.reactiveprogramming.utils.Utils;

public class Demo {


    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();


        //Both services have described, so now Order Publisher can begin emitting data
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream()
                .subscribe(Utils.getSubscriber("Inventory"));

        revenueService.revenueStream()
                .subscribe(Utils.getSubscriber("Revenue"));

        Utils.sleepSeconds(120);

    }

}
