package lesson4.exception;

import java.util.ArrayList;
import java.util.List;

public class CommonException {
    public static void main(String[] args) {

        //NullPointerException--空指针异常
        Integer a = null;
        System.out.println(a.toString());

        //ArithmeticException--算数异常
        int b = 10;
        int c = 0;
        int result = b / c;
        System.out.println("Result: " + result);

        //IndexOutOfBoundsException--索引越界异常
        List<Integer> list = new ArrayList<>();
        System.out.println(list.get(0));
    }
}