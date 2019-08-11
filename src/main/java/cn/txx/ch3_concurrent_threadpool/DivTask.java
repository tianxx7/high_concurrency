package cn.txx.ch3_concurrent_threadpool;

import java.util.concurrent.*;

/* *
 * 描述: 异常被吞了
 * @user tianxinxing
 * @date 2019/8/10
 */
public class DivTask implements Runnable {
    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re  =a/b;
        System.out.println(re);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
//            Future<?> submit = poolExecutor.submit(new DivTask(100, i));//通过submit方法提交需要Future.get才能报异常
//            submit.get();
            poolExecutor.execute(new DivTask(100,i));  //只报了哪里错,但是没有报调用的地方,这样对调试没有帮助

        }
        poolExecutor.shutdown();

    }
}
