## 第四节课：泛型、异常处理、集合框架

> 相信大家经过前面几周的学习，对Java或多或少有了一定程度的了解。本节课主要介绍Java的一些高级特性和集合框架，下面我们就开始今天的学习 ovo ！



### 异常处理

---

##### 1. 什么是异常？

​     Java中的异常又称：**" 例外 "**，发生在一个程序执行的期间，它会中断正在执行程序的**正常指令**，或者**指令流**( 也就是一串指令 ) , 即指程序在运行过程中发生的意外情况或错误。

在Java中，一个异常的产生，主要有以下三种情况：

- Java内部错误发生异常，Java虚拟机产生的异常
- 编写的程序代码中的错误所产生的的异常，例如`NullPointException` 空指针异常、`IndexBoundException` 数组越界异常等等
- 通过`throw`语句手动生成的异常，一般用来告知该方法的调用者一些必要信息

---

##### 2. 异常的分类

​    为了能够及时有效地处理程序中的运行错误，这个时候就必须使用 **异常类 ( Exception )**，这可以让程序具有极其可观的**容错性和健壮性**。

>  *Java异常类层次结构概览图* --- 图源小林coding

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/Snipaste_2024-10-26_17-08-50.png)

​    在 Java 中，所有的异常都有一个**共同**的祖先— › `java.lang` 包中的 `Throwable` 类。按照错误的严重性，从 `Throwable` 父类中衍生出`Error`和`Exception`两大派系

- `Error`( 错误 )

  程序在执行过程中所遇到的硬件或操作系统的错误。错误对程序而言是致命的，将导致程序无法运行。

  常见的错误 eg :  

  |      异常类型名称      |         异常说明          |
  | :--------------------: | :-----------------------: |
  |   `OutOfMemoryErro`    |         内存溢出          |
  | `Vitural MachineError` | JVM虚拟机自身的非正常运行 |
  |  `NoSuchMethodError`   |    class文件没有主方法    |
  |          ...           |            ...            |

  程序本身是不能处理错误的，只能依靠外界干预。Error是系统内部的错误，由JVM抛出，交给系统来处理。

  > *JVM相关知识暂不具体阐述 感兴趣的同学可先自行了解*

- `Exception` ( 异常 )

  程序正常运行中，可以预料的意外情况，程序本身可以进行处理。同时异常按照性质，又分为 非运行时异常 ( 可检查 ) 和运行时异常 ( 不可检查 )。

  * 非运行时异常

    这类异常在编译时会被检查，如果没有被 **`catch`**或者**`throws` 关键字 **处理的话，就没办法通过编译。他们通常是外部错误 

    eg : 

    |       异常类型名称       |         异常说明          |
    | :----------------------: | :-----------------------: |
    | `FileNotFoundException`  | 文件不存在 ( IO类型异常 ) |
    | `ClassNotFoundException` |         类未找到          |
    |      `SQLException`      |      数据库操作异常       |
    |           ...            |            ...            |

  * 运行时异常

    在编译过程中 ，即使不处理该类异常也可以正常通过编译。运行时异常由程序错误导致，包括`RuntimeException` 及其子类和 `Error` 错误 

    常见 eg : 

    > *可以多眼熟一下 日常开发中可以提高效率*

    |           异常类型名称           |                   异常说明                    |
    | :------------------------------: | :-------------------------------------------: |
    |      `NullPointerException`      |                  空指针错误                   |
    |    `IllegalArgumentException`    |        参数错误 ( 如方法入参类型错误 )        |
    |     `NumberFormatException`      | 字符串转换为数字格式错误 ( 上一个错误的子类 ) |
    | `ArrayIndexOutOfBoundsException` |                 数组越界错误                  |
    |       `ClassCastException`       |                 类型转换错误                  |
    |      `ArithmeticException`       |                   算术错误                    |
    |       `SecurityException`        |            安全错误 ( 如权限不够 )            |
    |               ...                |                      ...                      |

  下面简单展示几个异常的🌰

```java
package example.exception;

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
```



##### 3. 自定义异常

​    可能有的人会疑惑为什么要自定义异常，不会很多余吗？实际上，自定义异常的目的是 **细化异常以便我们更精确地处理异常** ，通俗来讲就是当遇到代码里抛出了`RuntimeException` 这个异常，那我们怎么去确定到底是出了哪种类型的问题或是多种问题的结合呢。

一个小🌰

```java
public class MeiMiException extends RuntimeException{
    private Double money;

    public MeiMiException(Double money) {
        super("余额不足，还差：" + money);
        this.money = money;
    }

    public static class AccountAdmin {
        private Double balance;

        public AccountAdmin(Double balance) {
            this.balance = balance;
        }

        //存钱的方法
        public void deposit(double money) {
            this.balance += money;
        }

        //取钱的方法
        public void withdraw(double money) throws MeiMiException {
            if (balance >= money) {
                balance -= money;
            } else {
                double needMoney = money - balance;
                throw new MeiMiException(needMoney);
            }
        }
    }

    public static void main(String[] args) {
        AccountAdmin accountAdmin = new AccountAdmin(100.0);
        accountAdmin.deposit(200);//先存二伯块
        try {
            //再取四伯块
            accountAdmin.withdraw(400);
        } catch (MeiMiException e) {
            throw new RuntimeException(e);
        }
    }
}
```



##### 4. 异常处理

了解到这里，你可能会有 讲了这么多我还是不知道到底该怎么处理异常 ~~感觉在听废话(bushi~~ 的想法

下面介绍 **Java处理异常的常用方法**

- **try-catch-finally**

  使用 `try` 和 `catch ` 关键字可以捕获异常。try/catch 代码块放在异常可能发生的地方。try/catch代码块中的代码称为保护代码

  ```java
  try{
     // 程序代码
  }catch(ExceptionName e1){
    // 程序代码
  }catch(ExceptionName e2){
    // 程序代码
  }catch(ExceptionName e3){
    // 程序代码
  }finally{
    // 程序代码
  }
  ```

  

  Catch 语句包含要捕获异常类型的声明。当保护代码块中发生一个异常时，try 后面的 catch 块就会被检查。

  如果发生的异常包含在 catch 块中，异常会被传递到该 catch 块，这和传递一个参数到方法是一样。

  同时我们还能进行无限套娃，根据不同的异常处理，我们可以一直catch。

  Finally关键字finally 关键字用来创建在 try 代码块后面执行的代码块。无论是否发生异常，finally 代码块中的代码总会被执行。在 finally 代码块中，可以运行清理类型等收尾善后性质的语句。

- **throws+异常类型**

  在Java中， **throw** 和 **throws** 关键字是用于处理异常的。

  **throw** 关键字用于在代码中抛出异常，而 **throws** 关键字用于在方法声明中指定可能会抛出的异常类型。

  * **throw关键字**

    **`throw` 关键字** 用于在当前方法中抛出一个异常。通常情况下，当代码执行到某个条件下无法继续正常执行时，可以使用 **`throw` 关键字** 抛出异常，以告知调用者当前代码的执行状态。它可以在我们自己指定的地方报异常跳出程序，而不一定在错误的地方，这样可以自己进行监控错误，不让JVM处理错误。

  * **throws关键字**

    **`throws` 关键字** 用于在方法声明中指定该方法可能抛出的异常。当方法内部抛出指定类型的异常时，该异常会被传递给调用该方法的代码，并在该代码中处理异常。throws用来声明方法在运行过程中可能出现的异常，以便调用者根据不同的异常类型预先定义不同的处理方式。

  ```java
  package example.exception;
  
  public class ExceptionHandlingDemo {
  
      //在方法中用throws关键字声明可能抛出的异常
      public static String print(String content) throws IllegalArgumentException{
          if(content == null) {
              //用throw关键字抛出实际异常
              throw new IllegalArgumentException("内容不可为空");
          }
          return content;
      }
  
      public static void main(String[] args) {
          try {
              String result = print(null);
              System.out.println("结果：" + result);
          }
          //捕获特定的异常
          catch (IllegalArgumentException e){
              //处理异常
              System.out.println("捕获异常：" + e);
          }
          //捕获所有其他异常
          catch (Exception e){
              //处理其余异常
              System.out.println("捕获其他异常" + e);
          }finally {
              //无论是否发生异常都会执行
              System.out.println("执行finally中的代码");
          }
  
      }
  }
  
  ```

### 泛型 T\R

---

##### 1. 什么是泛型？

​    Java **泛型 ( Generics ) ** 是JDK5中引入的一个新特性，提供编译时类型安全检查机制，允许程序员在编译时检测到非法的类型，并且在编译后能够保留类型信息，从而避免在运行时出现`ClassCastException` 异常。

​    或许还不是很清楚 ？下面结合一个例子说明：

> *泛型在很大的程度上方便了集合的使用 后面会详细介绍集合的相关知识 此处可先简单理解*

```java
package example.generics;

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
```

执行结果如下: 

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/image-20241026193333750.png)

   在上述未使用泛型的例子中，原生 `List` 返回类型是 `Object` ，获取的 `object` 类型不能使用子类的特有行为，需要手动转换类型才能使用，而转换的时候容易出现`ClassCastException` 异常，我们在运行到有问题的代码前是不知道会出现报错的。

   因此，我们可以通过泛型约束参数类型，从而省去强转步骤，这样一旦传入不同于指定类型的参数时就会报错，帮助我们在编译阶段就找出问题。

##### 2. 泛型的用法

​    泛型的三种使用方式：泛型类，泛型方法，泛型接口

* **泛型类**

  * 泛型类概述：把泛型定义在类上

  * 定义格式：

    ```java
    public class 类名 <泛型类型1,...> { }
    ```

* **泛型方法**

  * 泛型方法概述：把泛型定义在方法上

  * 定义格式：

    ```java
    public <泛型类型> 返回类型 方法名（泛型类型 变量名） { }
    ```

    **注意**：方法声明中定义的形参只能在该方法里使用，而接口、类声明中定义的类型形参则可以在整个接口、类中使用。当调用fun()方法时，根据传入的实际对象，编译器就会判断出类型形参T所代表的实际类型。

    ```java
    public class GenericsMethodExample {
        public <T> T fun(T t){   // 可以接收任意类型的数据
            return t ;     // 直接把参数返回
        }
    }
    public class GenericsMethodTest {
        public static void main(String args[]){
            GenericsMethodExample d = new GenericsMethodExample() ; // 实例化Demo对象
            String str = d.fun("汤姆") ; // 传递字符串
            int i = d.fun(30) ;  // 传递数字，自动装箱
            System.out.println(str) ; // 输出内容
            System.out.println(i) ;  // 输出内容
        }
    }
    ```

* **泛型接口**

  * 泛型接口概述：把泛型定义在接口

  * 定义格式：

    ```java
    修饰符 interface 接口名<泛型类型> {}
    ```

  * 实例：

    ```java
    public interface GenericsInterfaceExample<T> {
        void show(T t);
    }
    ----
    public class GenericsInterfaceImpl<E> implements GenericsInterfaceExample<E> {
    
        //GenericsInterfaceImpl类实现泛型接口 进一步延续泛型 创建该实现类对象时再确定类型
        @Override
        public void show(E t) {
            System.out.println(t);
        }
    
    }
    ----
    public class GenericsInterfaceTest {
        public static void main(String[] args) {
            //实例化GenericsInterfaceImpl类对象 并声明参数类型为String
            GenericsInterfaceExample<String> inter = new GenericsInterfaceImpl<String>();
            inter.show("hello,world!");
        }
    }
    
    
    ```

*注意*: 

* **泛型类型必须是引用类型(非基本数据类型)**，若需要填入基本数据类型，则应填写对应的包装类

* **不能在类中定义泛型参数化的静态属性**，会产生编译时错误：`Cannot make a static reference to the non-static type T`

  eg:

  ```java
  public class GenericsExample<T>
  {
     private static T member; //编译不通过
  }
  ```

* **不能创建类型参数的实例。** 任何创建T实例的尝试都将失败 抛出编译错误：`Cannot instantiate the type T` 

  eg:

  ```java
  public class GenericsExample<T>
  {
     public GenericsExample(){
        new T();
     }
  }
  ```

* **不能创建泛型的异常类**

  ```java
  public class GenericException<T> extends Exception {}
  ```

##### 3. 泛型构造器

- 构造器也是一种方法，所以也就产生了所谓的泛型构造器。
- 和使用普通方法一样没有区别，一种是显式指定泛型参数，另一种是隐式推断

eg：

```java
public class Person {
  public <T> Person(T t) {
  System.out.println(t);
  }
}
public static void main(String[] args) {
new Person(22);// 隐式
new <String> Person("hello");//显式
}
```

- 特殊说明：

  - 如果构造器是泛型构造器，同时该类也是一个泛型类的情况下应该如何使用泛型构造器：因为泛型构造器可以显式指定自己的类型参数（需要用到菱形，放在构造器之前），而泛型类自己的类型实参也需要指定（菱形放在构造器之后），这就同时出现了两个菱形了，这就会有一些小问题，具体用法在这里总结一下。 以下面这个例子为代表

    ```java
    public class Person<E> {
    
            public <T> Person(E e,T t) {
                System.out.println(e);
                System.out.println(t);
            }
    
        public static void main(String[] args) {
              //在这个例子中，Person被实例化为String类型，但是构造器的第二个参数是Integer类型。
                Person<String> person = new Person("sss",123);
        }
    }
    ```

  - PS：编译器会提醒你怎么做的

##### 4. 通配符

* **< ? >**无界通配符 

  `?` 表示不确定的类型，例如 List<?> 就代表着可以传入任意数据类型

* **<? extends T>** 上界通配符

  上界通配符顾名思义，<? extends T>表示的是类型的上界【包含自身】，因此通配的参数化类型可能是T或T的子类。

  ```java
    //它表示集合中的所有元素都是Animal类型或者其子类
    List<? extends Animal>
  ```

  这就是所谓的上限通配符，使用关键字extends来实现，实例化时，指定类型实参只能是extends后类型的子类或其本身。 例如： 这样就确定集合中元素的类型，虽然不确定具体的类型，但最起码知道其父类。然后进行其他操作。

  ```java
    //Cat是其子类
    List<? extends Animal> list = new ArrayList<Cat>();
  ```

* **<? super T> **下界通配符

  下界通配符<? super T>表示的是参数化类型是T的超类型（包含自身），层层至上，直至Object

  ```java
    //它表示集合中的所有元素都是Cat类型或者其父类
    List <? super Cat>
  ```

  这就是所谓的下界通配符，使用关键字super来实现，实例化时，指定类型实参只能是extends后类型的子类或其本身

  ```java
    //Animal是其父类
        List<? super Cat> list = new ArrayList<Animal>();
  ```

   更详细的内容可以自行学习-->[这里](https://juejin.cn/post/7126717331522191368)

### 集合框架

---

集合与数组一样，都是存储数据的容器。为什么要学习集合？下面是两者的对比

- 数组使用前需要声明其容纳的元素的类型，但集合不需要。
- 数组是静态的，即数组创建后具有固定的大小（长度），但集合的长度是可变的，集合存在一个扩容机制。
- 数组只能存储相同类型的元素，但是集合可以存储不同类型的元素。
- 集合支持泛型，具有内建算法。



##### 1. 集合框架体系

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/1730806789263.png)

   集合体系分成了接口、具体类、算法：

* 接口：在上两节课的学习中，大家学习了Java面向对象的思想。这里定义的接口就代表着集合的抽象数据类型，例如例如 Collection、List、Set、Map 等。之所以定义了多个接口，是为了以不同的方式操作集合对象 。

* 实现类：实现类就是接口的具体实现，比如说ArrayList就是List接口的实现类，HashSet就是Set接口的实现类，我们在使用集合的时候，就是使用这些实现类进行操作。

* 算法：在Java中，还存在着一些操作集合的方法，例如搜索和排序，学习这些方法也利于我们更好的操作集合。 Java 集合框架提供了一套性能优良，使用方便的接口和类，Java集合框架位于`java.util`包中， 所以当使用集合框架的时候需要进行导包。

  Java的集合类主要由两个接口派生而出。其中，Collection是单列集合的总接口，常用的是Set和List，Queue用得不多。Map是双列集合的总接口。

> *集合框架概览图*

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/image-20241105194654150.png)

##### 2. List

​     List系列的集合的显著特点： **有序、可重复、有索引** ，包含 `ArrayList`、`LinkedList`、`Vector`三个实现类

* **`ArrayList`**：底层数据结构是数组，查询快，增删慢，线程不安全，效率高，可以存储重复元素 
* **`LinkedList`**：底层数据结构是双向链表，查询慢，增删快，线程不安全，效率高，可以存储重复元素
* **`Vector`**：底层数据结构是数组，查询快，增删慢，线程安全，效率低，可以存储重复元素

---

**ArrayList**

​      ArrayList是Java中的一种基于数组实现的动态数组类，它可以在数组的基础上实现增加、删除、访问等操作。ArrayList中的元素可以是任意类型的对象，它提供了以下方法：

* 添加元素 

  * `add(element);` ---- 直接在列表末尾添加元素 添加成功后返回true
  * `add(index,element);` ---- 在集合中指定索引位置添加元素

* 删除元素 

  * `remove(index);` ---- 根据索引删除指定位置元素并返回被删除的元素

  * `remove(element)；` ---- 直接删除指定元素 成功则返回true

    **注意**：*直接删除时，若存在相同元素，默认删除相同元素中第一个出现的元素。调用方法时，若方法出现了重载现象，会优先调用实参跟形参类型一致的那个方法*

* 获取元素 

  * `get(index)`   ---- 返回指定索引位置的元素

* 修改元素 

  * `set(index,element)`  ---- 修改指定索引位置的元素 并返回原来的元素

* 返回集合大小 即元素个数

  * `size()` 

* 清空集合中所有元素 

  * `clear()`

```java
package example.collection;

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
```

**ArrayList扩容机制**

​     打开idea中ArrayList类的源码，ArrayList中有两个属性，size：表示ArrayList的长度，elementData数组：用来存储数据的数组。 在Arraylist扩容时会首先判断得到最小扩容量，如果你构造的ArrayList是用无参构造，即你创建ArrayList时没有确定它的长度，最小扩容量就为10和size+1当中的最大值， 然后再判断是否需要扩容，如果最小扩容量大于elementData.length，就需要扩容，然后调用grow（）方法，其中旧容量为elementData.length，新容量为elementData.length的1.5倍（new=old+old>>1）， 若新容量大于size+1,最小需要容量就为新容量，若新容量小于size+1,最小需要容量就为size+1,之后再将原来的数组用Arrays.copyOf方法复制到新数组，使新数组的长度为最小需要容量。

---

 **LinkedList**

​        LinkedList是Java中的一个双向链表实现的集合类，提供了专门针对列表首位操作的方法，可以当作栈和队列来使用。常用方法如下：

* 添加元素
  * `add(element)` ---- 在链表末尾添加一个元素
  * `addFirst(element)` ---- 在链表头部添加一个元素
  * `addLast(element)` ---- 在链表尾部添加一个元素
* 删除元素
  * `remove(index)`  ---- 删除指定位置的元素
  * `removeFirst()` ---- 移除并返回列表的第一个元素，如果列表为空 会抛出`NoSuchElementException`
  * `removeLast()`  ---- 移除并返回列表的最后一个元素，如果列表为空 会抛出`NoSuchElementException`
* 修改元素
  * `set(index,element)` ---- 替换指定位置的元素
* 获取元素
  * `get(int index)`  ---- 返回指定位置的元素。
  * `getFirst()`  ---- 返回列表第一个元素，如果列表为空 会抛出`NoSuchElementException`
  * `getLast()`  ----  返回列表最后一个元素，如果列表为空 会抛出`NoSuchElementException`
* 返回链表的大小
  * `size( );` 
* 清空链表中所有元素
  * ` clear( );`

```java
package example.collection;

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

```

**Vector**

​       Vector是Java中的一个基于数组实现的动态数组类，它可以在数组的基础上实现增加、删除、访问等操作。 Vector与ArrayList唯一的区别就是Vector是线程安全的。

##### 3. Set

​    Set系列集合的显著特点：  **无序、不重复、无索引**  ，包含  `HashSet`、`LinkedHashSet`、`TreeSet` 三个实现类。

* **`HashSet`**：底层数据结构是哈希表，线程不安全，元素唯一 
* **`LinkedHashSet`**：底层数据结构是链表和哈希表，线程不安全，元素唯一
* **`TreeSet`**：底层数据结构是红黑树，线程安全，元素唯一

**HashSet**

​      HashSet可以存储任意类型的对象，包括自定义类。此外，HashSet中的元素是无序的，不保持元素的插入顺序，因此在遍历HashSet时，元素的顺序可能会发生变化。HashSet的常用方法如下：

* 添加元素
  * `add(element)`   ----  在集合中添加元素 成功返回true
* 移除元素
  * `remove(Object o)`  ---- 删除指定元素 成功返回true
* 检查是否包含某元素
  * `contains(Object o)`  ---- 如果集合中包含指定的元素，则返回true
* 清空集合中的元素
  * `clear();` 
* 获取集合大小
  * `size();` 

```java
package example.collection;

import java.util.*;

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
```

----

**List 和 Set的区别**

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/1730806520362.png)

##### 4. Map

Map 是一个双列集合，也即键值对集合，用来存储键、值和之间的映射。

**HashMap**

HashMap是Java中的一个Map接口的实现类，它使用哈希表来存储键值对。HashMap中的键和值可以是任意对象，而且它支持自动扩容，以提高查询效率。 HashMap的特点如下：

* 通过键来获取值，可以通过键来设置、删除值。
* 支持空键和空值。
* 支持快速查找，时间复杂度通常为O(1)。
* 不保证元素的顺序。

常用方法如下：

* 根据键获取值
  * `get(key)`
* 将键值对存入HashMap中
  * `put(key, value)`
* 根据键删除对应的键值对
  * `remove(key)`
* 返回HashMap中键值对的数量
  * `size()`
* 清空HashMap中的所有键值对
  * `clear()`
* 判断HashMap是否为空
  * `isEmpty()`
* 返回一个Set集合，包含所有的键
  * `keySet()`
* 返回一个Collection集合，包含所有的值
  * `values()`

```java
package example.map;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
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

    }.
}
```

**HashMap的底层结构**

![](https://github.com/zxyii/Courseware-Backend-Java-2024/blob/main/Lesson4/images/1730806525452.png)

1. 哈希表 HashMap主要依赖于哈希表（数组）来存储数据。哈希表中的每个元素被称为“bucket”。数组的每个位置（bucket）都可以存放一个元素（键值对），数组的索引是通过键的哈希码经过哈希函数计算得来的。这样我们就可以通过键快速定位到数组的某个位置，取出相应的值，这就是HashMap快速获取数据的原理。
2. 链表 在理想的情况下，哈希函数将每个键均匀地散列到哈希表的各个位置。但在实际中，我们可能会遇到两个不同的键计算出相同的哈希值，这就是所谓的“哈希冲突”。‘’HashMap通过使用链表来解决这个问题。 当哈希冲突发生时，HashMap会在冲突的bucket位置增加一个链表，新的元素会被添加到链表的末尾。每个链表中的元素都包含了相同哈希值的键值对。所以在查找元素时，如果遇到哈希冲突，HashMap需要进行一次线性查找。
3. 红黑树 从Java 8开始，如果链表的长度超过一定的阈值（默认为8），那么链表会被转换为红黑树。红黑树是一种自平衡的二叉查找树，通过保持树的平衡，可以提高查找效率。
4. 扩容与重新哈希 HashMap在初始化时，会有一个默认的初始容量（16），并且有一个加载因子（0.75）。当HashMap的大小（也就是已经存储的键值对数量）超过 容量*加载因子 的时候，HashMap会进行扩容，新的容量是原来的两倍，并且会进行重新哈希，将已经存在的元素重新放入新的bucket位置。 总结 HashMap的底层数据结构包括：哈希表（数组）、链表和红黑树。通过哈希表，我们可以快速定位元素的位置。通过链表和红黑树，我们可以解决哈希冲突的问题。通过扩容和重新哈希，我们可以保证HashMap的性能。
5. 关于HashMap的底层结构可以看看这篇文章:https://juejin.cn/post/7188057359754166331?searchId=20231124205835322C5596C5B424379DE0



##### 5. 集合的遍历

   仅展示了比较常用的一些方法，其余像**Stream API**等方式可自行查阅学习

* **迭代器 (  Iterator ) **

  迭代器 (Iterator ) 是一种**设计模式**，它提供了一种统一的方法来顺序访问集合中的元素，而不暴露集合内部的实现细节。

  Java提供了 **`Iterator` 接口** 作为迭代器的基础接口，该接口定义了一系列用于访问集合元素的方法 --> `hasNext()`、`next()`、`remove()`等。 所有实现了 `Collection` 接口 的集合类都有一个 `iterator( )`方法 用以返回一个实现了 `Iterator`接口 的**对象**，该对象称为迭代器。

  > 注：Iterator仅用于遍历集合 并且本身不存放对象

  部分🌰：

  ```java
  //1.迭代器(iterator)遍历
          //获取集合的迭代器对象
          Iterator<String> iterator1 = personList.iterator();
          //利用迭代器 iterator1 遍历集合
          while(iterator1.hasNext()){
              String result1 = iterator1.next();
              System.out.println("personList迭代器遍历：" + result1);
          }
  ```

  `hasNext()` ：检查是否还有下一个元素        `next()`方法：获取下一个元素

  一些**注意事项:**

  * 在迭代器的使用过程中，应避免在每次迭代时都创建新的迭代器对象。如果需要多次遍历集合，可以在第一次遍历时创建迭代器，并在后续的遍历中重复使用该迭代器

  * 在迭代器遍历过程中 若用集合的方法 ( `add()` 、`remove()` ) 对集合进行增删操作会出现*`ConcurrentModificationException`* --- 并发修改异常，若需要修改集合，应用迭代器提供的`remove()`方法

    ```java
    //若需要修改集合，应用迭代器提供的`remove()`方法
            Iterator<String> it = personList.iterator();
            while(it.hasNext()) {
                String r = it.next();
                if (r.equals("丙")) {
                    it.remove();
                }
            }
            System.out.println("personList使用代器提供的`remove()`方法后：" + personList);
    ```

    

* **普通for循环**

  由于这种方法基于元素索引进行操作，所以适用于实现了`List`接口的集合类

  部分🌰：

  ```java
  //2.普通for循环遍历
          for (int i = 0; i < personList.size(); i++) {
              String result2 = personList.get(i);
              System.out.println("personList普通for循环遍历：" + result2);
          }
  ```

* **增强for循环**

  增强for循环底层实现就是迭代器 可以看作简化版的迭代器，此时不需要手动获取迭代器对象，由编译器内部自动生成，以此来简化迭代器的使用。 只能用于所有实现了 `Collection` 接口 的集合类和数组

  基本格式：

  ```java
  //增强for循环格式
  for(元素数据类型 变量名 ： 数组or集合) {
       System.out.println(变量名) //操作元素
  }
  ```

  > 注：其中 变量名代表的变量作为一个第三方临时变量 在循环中表示集合的每一个数据 对该变量进行修改不会改变集合中原本的数据

  部分🌰：

  ```java
  //3.增强for循环遍历
          for (String result3 : personList) {
              System.out.println("personList增强for循环遍历:" + result3);
          }
  ```

* **forEach+Lambda表达式**

  在Java8中，`Iterable` 接口引入了 `forEach()` 方法

  基本格式：

  ```java
  void forEach(Consumer<? super T> action)
  ```

  * T 指集合中元素的类型
  * `Consumer` --一个函数式接口，它作为参数，接受单个输入参数且不返回任何结果
  * action参数可以是一个Lambda表达式或方法引用，定义了如何处理集合中的每一个元素

  部分🌰：

  ```java
   //4.forEach+Lambda表达式遍历
          personList.forEach(result4 -> {
              System.out.println("personList forEach+Lambda遍历：" + result4);
          }
          );
  ```




### 作业

---

**Level 1**:

复习本节及之前所讲内容 可自行拓展学习相关知识

**Level 2**:

[二叉树的所有路径](https://lanshanteam.feishu.cn/docx/HTNNd8QejoyvI1xjdU4c0XbVnMd#part-Y8QJdb9YKoaANNxUglncVnRZnIe) [最大正方形](https://lanshanteam.feishu.cn/docx/HTNNd8QejoyvI1xjdU4c0XbVnMd#part-ShlLdn6TjoDJpVx8EbmcpMplnQh)
