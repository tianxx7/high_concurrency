package cn.txx.ch3_concurrent_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/10
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread ID:"+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
    }
}
