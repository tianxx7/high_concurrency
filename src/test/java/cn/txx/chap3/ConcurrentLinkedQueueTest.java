package cn.txx.chap3;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/10
 */
public class ConcurrentLinkedQueueTest {

    @Test
    public void testConcurrentLinkedQueue(){
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        System.out.println(        );
        queue.add(1);
        queue.add(2);
        queue.add(3);
    }
}
