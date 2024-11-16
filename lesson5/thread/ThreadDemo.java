package lesson5.thread;

public class ThreadDemo extends Thread{

    public static void main(String[] args) {
        // 直接实例化自定义的ThreadDemo对象
        ThreadDemo threadDemo = new ThreadDemo();
        // 启动线程，进入就绪状态，等待cpu调度
        threadDemo.start();
    }

    @Override
    public void run() {
        System.out.println("执行Thread实现的线程任务");
    }

}
