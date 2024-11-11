package lesson4.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("message");
        list.add(123);
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);
            System.out.println(str);
        }
    }
}