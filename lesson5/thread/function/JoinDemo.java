package lesson5.thread.function;

public class JoinDemo extends Thread{
    public static void main(String[] args) {
        JoinDemo thread = new JoinDemo();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程在子线程结束后执行");
    }
    @Override
    public void run() {
        try {
            System.out.println("子线程开始执行");
            Thread.sleep(2000);
            System.out.println("子线程执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}