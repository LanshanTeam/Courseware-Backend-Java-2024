package lesson5.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

// 定义返回值的类型
public class CallableDemo implements Callable<String> {

    public static void main(String[] args) {
        // 实例化CallableDemo回调任务对象
        CallableDemo callableDemo = new CallableDemo();
        // 将回调任务对象交由未来任务对象进行管理
        FutureTask<String> futureTask = new FutureTask<>(callableDemo);
        // 将未来任务交由线程执行
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("主线程苏醒");
            // 获取异步任务的结果，如果异步任务还未完成，将阻塞主线程（执行本条指令的线程）
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) { // 处理捕获执行异步任务可能发生的异常
            // 这里并未处理，而是继续往上抛
            throw new RuntimeException(e);
        }
    }

    @Override
    public String call() throws Exception {
        System.out.println("执行Callable实现的线程任务");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Callable接口返回的结果";
    }

}