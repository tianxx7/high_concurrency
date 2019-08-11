package cn.txx.ch5_DIsruptor;

import com.lmax.disruptor.WorkHandler;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --"+pcData.getValue() * pcData.getValue()+"--");
    }
}
