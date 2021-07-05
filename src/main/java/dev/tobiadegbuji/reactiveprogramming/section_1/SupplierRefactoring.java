package dev.tobiadegbuji.reactiveprogramming.section_1;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

public class SupplierRefactoring {


    public static void main(String[] args) {

        getName();
        getName();
        getName();

        //Notice upon running main method, the println is executed very quickly and is not blocked by thread.sleep.
        //Why? B/c you are not subscribing to the mono publisher (otherwise you are not invoking a terminal operation).

    }


    private static Mono<String> getName() {

        System.out.println("Enter GetName Method");

        //fromSupplier BUILDS the pipeline. It does not EXECUTE it unless someone subscribes. (Like terminal operations in Strings)
        //Building the pipeline does not take much time. Executing will take at least 3 seconds for obvious reasons...
        //Seems that fromSupplier is lazily executed
        return Mono.fromSupplier(() -> {
                    System.out.println("Generating Name....");
                    Utils.sleepSeconds(3000);
                    return Utils.faker().funnyName().name();
                }
        ).map(String::toUpperCase);
    }


}
