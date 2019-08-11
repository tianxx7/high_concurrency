package cn.txx.ch4_nolock;

import java.util.concurrent.atomic.AtomicReference;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public class AtomicReferenceDemo {
    static AtomicReference<Integer> money = new AtomicReference<>(19);

    public static void main(String[] args) {
        //模拟多线程同时更新后台
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        Integer integer = money.get();
                        if (integer < 20) {
                            if (money.compareAndSet(integer,integer+20)) {
                                System.out.println("余额小于20元,充值成功,余额:" + money.get() + "元");
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                }
            }.start();
        }
        //用户消费行为,模拟消费
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true){
                        Integer integer = money.get();
                        if (integer > 10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(integer,integer - 10)){
                                System.out.println("成功消费10元,余额:"+money.get());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的钱");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
