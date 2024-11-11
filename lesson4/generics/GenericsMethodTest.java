package lesson4.generics;

public class GenericsMethodTest {
    public static void main(String args[]){
        GenericsMethodExample d = new GenericsMethodExample() ; // 实例化Demo对象
        String str = d.fun("汤姆") ; // 传递字符串
        int i = d.fun(30) ;  // 传递数字，自动装箱
        System.out.println(str) ; // 输出内容
        System.out.println(i) ;  // 输出内容
    }
}