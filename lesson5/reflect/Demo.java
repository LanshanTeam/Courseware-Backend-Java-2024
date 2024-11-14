package lesson5.reflect;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) throws Exception {
        // 创建解析器
        SAXReader reader = new SAXReader();
        // 解析xml文档,得到document对象
        Document document = reader.read("lesson5/reflect/Car.xml");
        // 根据document对象获取根节点(这里对应的是Bean)
        Element root = document.getRootElement();
        // 查找根节点下的子节点
        Element bean = root.element("Car");
        // 获得全限定名
        String className = bean.element("class").getText();
        // 获取字节码对象
        Class<?> clazz = Class.forName(className);

        // 创建 Car 对象
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object car = constructor.newInstance(
                bean.element("model").getText(),
                Integer.parseInt(bean.element("year").getText()));

        // 访问和修改字段
        Field modelField = clazz.getDeclaredField("model");
        Field yearField = clazz.getDeclaredField("year");

        // 设置字段为可访问（如果字段是私有的）
        modelField.setAccessible(true);
        yearField.setAccessible(true);

        // 打印原始字段值
        System.out.println("Original Model: " + modelField.get(car));
        System.out.println("Original Year: " + yearField.get(car));

        // 修改字段值
        modelField.set(car, "XiaoMi SU7");
        yearField.set(car, 2024);

        // 打印修改后的字段值
        System.out.println("Updated Model: " + modelField.get(car));
        System.out.println("Updated Year: " + yearField.get(car));

        // 调用方法
        Method startMethod = clazz.getMethod("start");
        startMethod.invoke(car);
    }

}

class Car {
    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public void start() {
        System.out.println("The " + model + " car of year " + year + " is starting.");
    }
}


