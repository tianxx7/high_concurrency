package cn.txx.ch4_ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class GoodSimipleDateFormatUseThreadLocal {
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            if (threadLocal.get() == null){
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            try {
                Date date = threadLocal.get().parse("2019-08-11 10:50:" + i % 60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.execute(new BadSimpleDateFormat.ParseDate(i));
        }
        pool.shutdown();
    }
}
