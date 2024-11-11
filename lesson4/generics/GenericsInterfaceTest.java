package lesson4.generics;

public class GenericsInterfaceTest {
    public static void main(String[] args) {
        //实例化GenericsInterfaceImpl类对象 并声明参数类型为String
        GenericsInterfaceExample<String> inter = new GenericsInterfaceImpl<String>();
        inter.show("hello,world!");
    }
}