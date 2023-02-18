package edu.learn.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsyncProgrammingDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.println(completableFuture.get());


        completableFuture = completableFuture.thenApply(s -> s + " World");
        System.out.println(completableFuture.get());

        CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("Async ....");
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Cancelable Async ....");
        });

        voidCompletableFuture = CompletableFuture.supplyAsync(() -> 1 + 2).thenApply(sum -> {
            return sum + 5;
        }).thenRun(()-> System.out.println("Computation has been finished ...."));


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        System.out.println("Combine all " + combinedFuture.get());
        System.out.println(future1.isDone());
        System.out.println(future2.isDone());
        System.out.println(future3.isDone());

        String combined = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.joining(" "));
        System.out.println("Combined result : " + combined);



        try {
            Thread.sleep(1000);
            voidCompletableFuture.cancel(false);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
