package dev.tobiadegbuji.reactiveprogramming.flux_tutorial;

import dev.tobiadegbuji.reactiveprogramming.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Assignment4 {


    public static void main(String[] args) throws IOException {


        List<String> lines = Files.lines(Path.of("C:\\Users\\Adegb\\Documents\\Programming\\Tutorials\\reactive-programming\\src\\main\\resources\\random-text"))
                .collect(Collectors.toList());

        Flux.generate(
                () -> 0,
                (state, sink) -> getLines(sink,state,lines)
        )
                .subscribe(Utils.getSubscriber(""));

    }


    private static int getLines(SynchronousSink<Object> sink, int count, List<String> lines){

        boolean flag = false;

        if(count > lines.size() - 1){
            sink.complete();
            flag = true;
        }

        if(!flag)
        sink.next(lines.get(count));

        return count + 1;

    }



}
