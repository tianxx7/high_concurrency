package cn.txx.ch5_future;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realData){
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }
    @Override
    public String getResult() {
        while (!isReady) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
