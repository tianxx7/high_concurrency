package cn.txx.ch5_future_jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.out.println("请求完毕");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据="+future.get());
        executorService.shutdown();
    }
}
