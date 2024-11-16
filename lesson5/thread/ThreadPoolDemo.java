package lesson5.thread;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    private static final ExecutorService pool = new ThreadPoolExecutor(
            3,
            5,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void  main(String[] args) {
        pool.execute(()-> System.out.println("执行无返回值任务"));
        Future<String> future = pool.submit(() -> {
            System.out.println("执行有返回值任务");
            Thread.sleep(1000);
            return "返回任务结果";
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
