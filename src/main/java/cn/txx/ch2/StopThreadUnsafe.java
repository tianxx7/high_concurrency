package cn.txx.ch2;

/* *
 * 描述: 使用Thread.stop()方法造成数据不一致
 * @user tianxinxing
 * @date 2019/8/6
 *
 * Thread.sleep(),为了暂停当前线程,把cpu时间片让出给其他线程,减缓当前线程的执行
 *
 *
 * 释放CPU时间片:sleep,wait
 * 释放锁:wait,
 * 不释放锁:sleep,yield
 */
public class StopThreadUnsafe {
    public static User u = new User();

    public static class User{
        private int id;
        private String name;
        public User(){
            id=0;
            name="0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"id\":")
                    .append(id);
            sb.append(",\"name\":\"")
                    .append(name).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (u){
                    int v = (int)(System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
