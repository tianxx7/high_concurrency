package cn.txx.ch6_completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CFutureMain7 {

    public static Integer calc(Integer para) {
        return para / 2;
    }

    public static void main(String[] args) {
        //这段需要jdk9的支持
        /*CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return calc(50);

        }).orTimeout(1, TimeUnit.SECONDS).exceptionally(e -> {
            System.err.println(e);
            return 0;
        }).thenAccept(System.out::println);*/

        try

        {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }
}
