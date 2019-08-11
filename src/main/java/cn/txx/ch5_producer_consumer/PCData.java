package cn.txx.ch5_producer_consumer;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/11
 */
public final class PCData {
    private final int data;

    public PCData(int intData) {
        this.data = intData;
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
