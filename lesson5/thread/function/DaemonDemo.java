package lesson5.thread.function;

public class DaemonDemo {
    public static void main(String[] args) {
        MyDaemonThread daemonThread = new MyDaemonThread();
        // 将线程设置为守护线程
        daemonThread.setDaemon(true);
        daemonThread.start();
        try {
            Thread.sleep(5000);
            System.out.println("主线程结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyDaemonThread extends Thread {
    public void run() {
        while (true) {
            System.out.println("守护线程在运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}