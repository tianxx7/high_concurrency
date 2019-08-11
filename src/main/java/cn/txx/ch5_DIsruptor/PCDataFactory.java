package cn.txx.ch5_DIsruptor;

import com.lmax.disruptor.EventFactory;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
