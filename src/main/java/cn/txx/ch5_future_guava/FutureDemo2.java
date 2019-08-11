package cn.txx.ch5_future_guava;

import cn.txx.ch5_future_jdk.RealData;
import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class FutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture task = service.submit(new RealData("x"));

        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("异步处理成功,result="+s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败,e="+throwable);
            }
        },MoreExecutors.newDirectExecutorService());
        System.out.println("main task done....");
        Thread.sleep(3000);
        service.shutdown();

        System.out.println("main task done...");
        Thread.sleep(3000);
        service.shutdown();
    }
}
