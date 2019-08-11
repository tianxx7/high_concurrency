package cn.txx.ch5_future;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class MainApp {
    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回,因为得到的是FutureData而不是realData
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据="+data.getResult());
    }
}
