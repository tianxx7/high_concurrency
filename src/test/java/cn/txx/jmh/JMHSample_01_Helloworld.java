package cn.txx.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/8/10
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHSample_01_Helloworld {

    @Benchmark
    public void wellHelloThere(){
        //
    }

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder()
                .include(JMHSample_01_Helloworld.class.getSimpleName()) //指定测试类
                .forks(1)  //使用进程个数
                .warmupIterations(4)//预热迭代次数
                .build();
        new Runner(build).run();
    }
}
