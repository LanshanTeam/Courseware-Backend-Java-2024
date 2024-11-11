package lesson4.generics;

public class GenericsInterfaceImpl<E> implements GenericsInterfaceExample<E> {

    //GenericsInterfaceImpl类实现泛型接口 进一步延续泛型 创建该实现类对象时再确定类型
    @Override
    public void show(E t) {
        System.out.println(t);
    }

}