package lesson5.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSecurity {

    public static void main(String[] args) throws InterruptedException {
        // 计数器实例
        Counter counter = new Counter();

        // t1线程让计数器自增10000次
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });
        // t2线程让计数器自增10000次
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.increment();
        });

        t1.start();
        t2.start();
        // 等待t1、t2执行完
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }

    static class Counter {

        private int count;
        private ReentrantLock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try { // 临界区
                count++;
            } finally { // 避免发生异常导致锁未释放
                lock.unlock();
            }
        }

        public int getCount() {
            return count;
        }
    }
}