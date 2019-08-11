package cn.txx.ch5_future;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }
}
