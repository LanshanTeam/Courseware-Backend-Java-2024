package lesson4.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        //准备List类集合
        ArrayList<String> personList = new ArrayList<>();
        personList.add("甲");
        personList.add("乙");
        personList.add("丙");
        personList.add("丁");

        //1.迭代器(iterator)遍历
        //获取集合的迭代器对象
        Iterator<String> iterator1 = personList.iterator();
        //利用迭代器 iterator1 遍历集合
        while(iterator1.hasNext()){
            String result1 = iterator1.next();
            System.out.println("personList迭代器遍历：" + result1);
        }

        //2.普通for循环遍历
        for (int i = 0; i < personList.size(); i++) {
            String result2 = personList.get(i);
            System.out.println("personList普通for循环遍历：" + result2);
        }

        //3.增强for循环遍历
        for (String result3 : personList) {
            System.out.println("personList增强for循环遍历:" + result3);
        }

        //4.forEach+Lambda表达式遍历
        personList.forEach(result4 -> {
                    System.out.println("personList forEach+Lambda遍历：" + result4);
                }
        );

        //若需要修改集合，应用迭代器提供的`remove()`方法
        Iterator<String> it = personList.iterator();
        while(it.hasNext()) {
            String r = it.next();
            if (r.equals("丙")) {
                it.remove();
            }
        }
        System.out.println("personList使用代器提供的`remove()`方法后：" + personList);

        System.out.println("----------------------------");

        //准备Map类集合
        HashMap<String,Integer> personMap = new HashMap<>();
        personMap.put("张三",19);
        personMap.put("王五",31);

        //1.1 迭代器 + EntrySet()遍历
        Iterator<Map.Entry<String, Integer>> iterator2 = personMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, Integer> entry = iterator2.next();
            System.out.println("personMap迭代器 + EntrySet()遍历--->"
                    + "姓名：" + entry.getKey() + " " + "年龄：" + entry.getValue());
        }
        //1.2 迭代器 + KeySet()遍历
        Iterator<String> iterator3 = personMap.keySet().iterator();
        while (iterator3.hasNext()) {
            String keys = iterator3.next();
            System.out.println("personMap迭代器 + KeySet()遍历--->"
                    + "姓名：" + keys + " " + "年龄：" + personMap.get(keys));
        }

        //2.1 增强for循环 + EntrySet()遍历
        for (Map.Entry<String, Integer> entry : personMap.entrySet()) {
            System.out.println("personMap增强for循环 + EntrySet()遍历--->"
                    + "姓名：" + entry.getKey() + " " + "年龄：" + entry.getValue());
        }
        //2.2 增强for循环 + KeySet()遍历
        for (String key : personMap.keySet()) {
            System.out.println("personMap增强for循环 + KeySet()遍历--->"
                    + "姓名：" + key + " " + "年龄：" + personMap.get(key));
        }

        //3. forEach + Lambda表达式遍历
        personMap.forEach((key, value) -> {
            System.out.println("personMap forEach + Lambda表达式遍历--->" +
                    "姓名：" + key + " " + "年龄：" + value);
        });

    }

}
