package cn.txx.ch2;

/* *
 * 描述: 线程优先级
 * @user tianxinxing
 * @date 2019/8/7
 */
public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class){
                    count++;
                    if (count > 10_000_000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;
        @Override
        public void run(){
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if (count > 10_000_000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
