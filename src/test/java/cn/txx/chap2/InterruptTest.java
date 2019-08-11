package cn.txx.chap2;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* *
 * 描述: 测试线程中断
 * @user tianxinxing
 * @date 2019/5/31
 */
public class InterruptTest {

    /**
     * 这里,虽然对t1进行了中断,但是t1中没有中断处理的逻辑,因此,即使t1线程被置为中断状态,
     * 这个中断也不会发生任何作用
     * 如果希望t1中断后退出,就必须为它增加相应的中断处理代码 -> test2()
     * @throws InterruptedException
     */
    @Test
    public void testThreadInterupt() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true) {
                System.out.println("running...");
                Thread.yield();
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }


    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }
                System.out.println("running...");
                Thread.yield();
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

    @Test
    public void test3() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger();

        Thread read = new Thread(()->{
            int i = integer.get();
            while (integer.compareAndSet(i,i+1)){
                System.out.println("当前i值:" + integer.get());
            }
        });
        Thread write = new Thread(()->{
            int i = integer.incrementAndGet();
            System.out.println("i++:" + i);
        });

        read.start();
        write.start();
        read.join();
        write.join();
        System.out.println("main is end!");
    }

    @Test
    public void test4() throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println("running...");
                Thread.yield();
            }
        });
        t1.start();
        t1.join();
        t1.interrupt();
    }

    @Test
    public void test5(){
        AtomicInteger integer = new AtomicInteger();
        int i = integer.get();
        System.out.println("get():" + i);
        System.out.println(integer.compareAndSet(i, i + 1));
        System.out.println(integer.get());
    }

    @Test
    public void test6(){
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test7(){
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(new Exception("123"));
    }



}
