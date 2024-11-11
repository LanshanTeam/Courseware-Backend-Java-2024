package lesson4.collection;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        // 添加元素
        hashSet.add("apple");
        hashSet.add("banana");
        hashSet.add("orange");
        System.out.println(hashSet);  //[banana, orange, apple]
        System.out.println(hashSet.add("apple"));  //已存在元素apple --> 输出:false
        // 删除元素
        hashSet.remove("banana");
        System.out.println(hashSet);  //[orange, apple]
        // 查看是否包含元素 contains()
        System.out.println(hashSet.contains("apple")); // 输出：true
        System.out.println(hashSet.contains("banana")); // 输出：false
        // 获取长度
        System.out.println(hashSet.size()); // 输出：2
        // 清空集合元素
        hashSet.clear();
        System.out.println(hashSet.size()); // 输出：0
    }
}
