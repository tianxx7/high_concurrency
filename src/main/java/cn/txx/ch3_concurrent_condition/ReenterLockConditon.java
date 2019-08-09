package cn.txx.ch3_concurrent_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/8
 */
public class ReenterLockConditon implements Runnable {
    public static ReentrantLock lock =new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockConditon tl = new ReenterLockConditon();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        //通知线程继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
