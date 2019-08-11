package cn.txx.ch3_concurrent_lock;

import com.google.common.util.concurrent.RateLimiter;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/9
 */
public class RateLimiterDemo {
    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            limiter.acquire();
            new Thread(new Task()).start();
        }
    }

}
