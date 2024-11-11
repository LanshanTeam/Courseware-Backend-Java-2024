package lesson4.collection;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        //增删改查元素不做过多说明 仅简单展示特殊额外用法
        //1.当作队列使用
        LinkedList<String> queue = new LinkedList<>();
        queue.addFirst("甲");
        queue.addLast("乙");
        queue.addLast("丙");
        queue.addLast("丁");
        System.out.println(queue);   // [甲, 乙, 丙, 丁]
        //获取但不移除列表的第一个元素，如果列表为空，则返回 null
        String firstPerson = queue.peekFirst();
        System.out.println("第一个人：" + firstPerson);  // 第一个人：甲
        //获取但不移除列表的最后一个元素，如果列表为空，则返回 null
        String lastPerson = queue.peekLast();
        System.out.println("最后一个人：" +lastPerson);  // 最后一个人：丁
        //获取并移除列表的第一个元素，如果列表为空，则返回 null
        queue.pollFirst();
        System.out.println(queue);  //[乙, 丙, 丁]
        //获取并移除列表的最后一个元素，如果列表为空，则返回 null
        queue.pollLast();
        System.out.println(queue); //[乙, 丙]

        //2.当作栈使用
        LinkedList<String> stack = new LinkedList<>();
        //将元素推入此列表所表示的栈中，即将元素添加到列表的开头
        stack.push("o");
        stack.push("l");
        stack.push("l");
        stack.push("e");
        stack.push("H");
        System.out.println(stack);  // [H, e, l, l, o]
        //列表开头弹出一个元素，即移除并返回列表的第一个元素
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack);  //[o]
    }
}
