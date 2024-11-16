package lesson5.thread.function;

public class YieldDemo {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();
    }
}

class MyThread1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("线程1执行: " + i);
            if (i == 2) {
                Thread.yield();
            }
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("       线程2执行: " + i);
        }
    }
}
