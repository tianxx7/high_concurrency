package cn.txx.ch5_DIsruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    public void pushData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try {
            PCData pcData = ringBuffer.get(sequence);
            pcData.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
