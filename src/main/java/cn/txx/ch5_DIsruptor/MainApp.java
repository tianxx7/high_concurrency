package cn.txx.ch5_DIsruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        PCDataFactory pcDataFactory = new PCDataFactory();
        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<>(pcDataFactory,
                bufferSize,
                pool,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            allocate.putLong(0,1);
            producer.pushData(allocate);
            Thread.sleep(100);
            System.out.println("add data " + i);
        }
    }
}
