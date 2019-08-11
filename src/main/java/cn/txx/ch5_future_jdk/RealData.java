package cn.txx.ch5_future_jdk;

import java.util.concurrent.Callable;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class RealData implements Callable {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public Object call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){

            }
        }
        return sb.toString();
    }
}
