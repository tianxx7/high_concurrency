package cn.txx.ch2;

import com.sun.org.apache.xpath.internal.SourceTree;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/6
 *
 * t2通知后并没有立即释放锁,而是sleep 2000 之后释放锁,t1获取锁,执行代码结束
 */
public class SimpleWN {
    final static Object object = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+": T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + "T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+": T1 end!");
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify on thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end!");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
