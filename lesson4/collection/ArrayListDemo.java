package lesson4.collection;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        // 直接添加元素
        arrayList.add(20);
        arrayList.add("A");
        arrayList.add("B");
        System.out.println(arrayList);
        //在指定位置添加元素
        arrayList.add(2,"?");
        System.out.println(arrayList);
        // 替换元素  如果索引超过了集合的最大索引，会报错。
        arrayList.set(0,1);
        System.out.println(arrayList);
        // 得到元素 get()
        Object o =  arrayList.get(3);
        System.out.println(o);
        // 删除元素 remove()
        arrayList.remove("A");
        System.out.println(arrayList);
        arrayList.remove(1);  //优先调用实参跟形参类型一致的那个方法
        System.out.println(arrayList);
        // 得到某一元素的索引,如果没有该元素就返回-1
        int index = arrayList.indexOf("B");
        System.out.println("B的索引为" + index);
        // 长度 size()
        System.out.println("集合的长度为" + arrayList.size());
        // 清空列表 clear()
        arrayList.clear();
        System.out.println("集合的长度为" + arrayList.size());

    }
}