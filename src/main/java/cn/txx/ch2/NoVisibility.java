package cn.txx.ch2;

/* *
 * 描述: volatile保证有序性和可见性
 * @user tianxinxing
 * @date 2019/8/7
 * 在server模式下,由于系统优化的结果,ReaderThread线程无法"看到"主线程的修改,导致ReaderThread永远无法退出(while(!ready))
 * 在client下,由于JIT并没有做足够的优化
 * 64位虚拟机只支持server模式
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
