package dev.tobiadegbuji.reactiveprogramming.mono_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Assignment1 {

    private static final Path PATH = Paths.get("src/main/java/dev/tobiadegbuji/reactiveprogramming/mono_tutorial/");

    private static String readFile(String fileName){
        try {
            return Files.readString(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    //When someone subscribes to this, then the work will be done.
    //This method return a mono, that the subscriber can subscribe to.
    public static Mono<String> read(String fileName){
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public static Mono<Void> write(String fileName, String content){
        writeFile(fileName, content);
        return Mono.empty();
         //return Mono.fromRunnable(() -> writeFile(fileName, content));
    }


    public static Mono<Void> delete(String fileName){
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }


    private static void writeFile(String fileName, String content){
        Utils.sleepSeconds(10);
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    private static void deleteFile(String fileName){
        try {
             Files.deleteIfExists(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



class DEMO{

    public static void main(String[] args) {

        //OnNext
        Assignment1.read("assignment1")
                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());



        Assignment1.write("assignment2", "I just wrote to a file.")
                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

//        Assignment1.delete("assignment2")
//                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());


//        //OnError
//        Assignment1.read("assignment9")
//                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

    }


}