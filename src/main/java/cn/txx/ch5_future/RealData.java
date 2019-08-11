package cn.txx.ch5_future;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class RealData implements Data {
    protected final String result;
    public RealData(String para){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}
