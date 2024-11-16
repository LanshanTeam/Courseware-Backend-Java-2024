package lesson5.thread;

public class RunnableDemo implements Runnable {

    public static void main(String[] args) {

        // 实例化RunnableDemo任务对象
        RunnableDemo runnableDemo = new RunnableDemo();
        // 将任务交由线程执行
        Thread thread = new Thread(runnableDemo);
        thread.start();

        try {
            // 线程进入有时限等待状态，但仍持有cpu时间片
            Thread.sleep(1000); // 单位:mm(毫秒)，可以选择使用下面的方法
            // TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread = new Thread(() -> System.out.println("执行lambda表达式实现runnable接口定义的任务"));
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("执行Runnable实现的线程任务");
    }

}
