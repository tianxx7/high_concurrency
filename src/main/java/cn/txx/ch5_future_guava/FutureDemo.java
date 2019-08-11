package cn.txx.ch5_future_guava;

import cn.txx.ch5_future_jdk.RealData;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture task = service.submit(new RealData("x"));
        task.addListener(() ->{
            System.out.println("异步成功");
            try{
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        },MoreExecutors.directExecutor());
        System.out.println("main task done...");
        Thread.sleep(3000);
        service.shutdown();
    }
}
