package cn.txx.ch2;

/* *
 * 描述: join()方法实例
 * @user tianxinxing
 * @date 2019/8/7
 *
 * 在主函数中,如果不使用join()方法等待AddThread,那么得到的i很有可能是0
 */
public class JoinMain {
    public volatile static int i=0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (i=0;i<10_000_000;i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
