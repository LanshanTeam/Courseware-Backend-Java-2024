package lesson4.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args) {
        // 创建一个HashMap对象
        Map<String, Integer> map = new HashMap<>();
        // 向map中添加键值对
        map.put("语文", 108);
        map.put("数学",123);
        map.put("英语",136);
        // 从map中获取键为"数学"的值，赋值给math变量
        int math = map.get("数学");
        System.out.println("分数为：" + math);
        // 检查map中是否存在键为"五咯"的元素，如果存在返回true，否则返回false
        boolean exists = map.containsKey("物理");
        System.out.println(exists);
        // 获取map中所有的键，返回一个Set集合
        Set<String> subject = map.keySet();
        System.out.println("所有学科：" + subject);
        // 获取map中所有的值，返回一个Collection集合
        Collection<Integer> score = map.values();
        System.out.println("所有分数：" + score);
        // 获取map中所有的键值对，返回一个Collection集合
        Collection<Map.Entry<String, Integer>> result = map.entrySet();
        System.out.println("成绩：" + result);
        // 获取map中第一个键，返回一个String类型的字符串
        String key = map.keySet().toArray(new String[1])[0];
        System.out.println(key);
        // 从map中移除键为"语文"的元素
        map.remove("语文");
        System.out.println(map);
        // 获取map中元素的个数，返回一个int类型的整数
        int size = map.size();
        System.out.println("元素个数：" + size);
        // 清空map中的所有元素
        map.clear();
        System.out.println(map);
        // 检查map是否为空，如果为空返回true，否则返回false
        boolean isEmpty = map.isEmpty();
        System.out.println(isEmpty);

    }
}