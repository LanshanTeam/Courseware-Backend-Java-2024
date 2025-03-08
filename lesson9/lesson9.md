# 1.MyBatis

## 1.1MyBatis是什么

MyBatis 是一款流行的 Java 持久层框架，它提供了一种优雅的方式来将数据库操作和 SQL 语句与 Java 代码分离。MyBatis 的核心思想是通过 XML 或注解方式将 SQL 语句映射到 Java 方法上，从而实现了数据的持久化。

## 1.2MyBatis的框架设计

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ODhlNmRmMjMzZDc0ZjM0MzM3MTEwNTUyNDA3Njc4MzhfYXlKeUlHSEZwanJXNVNsRzZNcHNvdmR6YkZESnRzYnpfVG9rZW46VWNXMGIzWWJQb2I5VXJ4SlBsZGNGd2h0bkxjXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

### 1.2.1接口层---和数据库交互的方式

MyBatis和数据库的交互有两种方式：

> a.使用传统的MyBatis提供的API；
>
> *b. 使用Mapper接口*

#### 1.2.1.1使用传统的MyBatis提供的API

> 传统的方法是传递**Statement Id** 和查询参数给 **SqlSession** 对象，使用 **SqlSession**对象完成和数据库的交互；**MyBatis** 提供了非常方便和简单的API，供用户实现对数据库的增删改查数据操作，以及对数据库连接信息和**MyBatis** 自身配置信息的维护操作。

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MTExZjkyZDM2ODQwOWJhZjI2MmM1ODU4NWI2MzA5NzFfbW5HZHV1QzUzTXkxUEV6RHJ1Rk85cjVTd2Q5c05YN0RfVG9rZW46SFdGNmJScGdUb2dVR1h4eFJWUGNSQ29Tbm5jXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

> 上述使用**MyBatis** 的方法，是创建一个和数据库打交道的**SqlSession**对象，然后根据**Statement Id** 以及参数来操作数据库，这种方式虽然很简单和实用，但是它不符合面向对象语言的概念和面向接口编程的编程习惯。由于面向接口的编程是面向对象的大趋势，**MyBatis** 为了适应这一趋势，增加了第二种使用**MyBatis** 支持接口（**Interface**）调用的方式。

#### 1.2.1.2 使用Mapper接口

> **MyBatis** 将配置文件中的每一个<mapper> 节点抽象为一个 **Mapper** 接口，而这个接口中声明的方法和跟<mapper> 节点中的<select|update|delete|insert> 节点项对应，即<select|update|delete|insert> 节点的id值为**Mapper** 接口中的方法名称，parameterType 值表示**Mapper** 对应方法的入参类型，而resultMap 值则对应了**Mapper** 接口表示的返回值类型或者返回结果集的元素类型。

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NjUzNzFiNDcxNGU1MmRkZTZkZTg1MGZiYmFiN2Q1NjRfVldUM1AzTlA3ZUJLNWZFaVhEa2xpVzIwT0UxZ1ZjOUNfVG9rZW46Qm1FRGIwalZib3ZHRlh4ZndCMmNXakY4bkcxXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

根据**MyBatis** 的配置规范配置好后，通过SqlSession.getMapper(XXXMapper.class) 方法，**MyBatis** 会根据相应的接口声明的方法信息，通过动态代理机制生成一个**Mapper** 实例，我们使用**Mapper** 接口的某一个方法时，**MyBatis** 会根据这个方法的方法名和参数类型，确定**Statement Id**，底层还是通过SqlSession.select("statementId",parameterObject);或者SqlSession.update("statementId",parameterObject); 等等来实现对数据库的操作，**MyBatis** 引用**Mapper** 接口这种调用方式，是为了满足面向接口编程的需要。（其实还有一个原因是在于，面向接口的编程，使得用户在接口上可以使用注解来配置SQL语句，这样就可以脱离XML配置文件，实现“0配置”）。

### 1.2.2数据处理层

数据处理层可以说是**MyBatis** 的核心，从大的方面上讲，它要完成三个功能：

> a. 通过传入参数构建动态SQL语句；
>
> *b. SQL语句的执行以及*
>
> *c. 封装查询结果集为List<E>*

#### 1.2.2.1.参数映射和动态SQL语句生成

> 动态语句生成可以说是MyBatis框架非常优雅的一个设计，**MyBatis** 通过传入的参数值，使用 **Ognl** 来动态地构造SQL语句，使得**MyBatis** 有很强的灵活性和扩展性。
>
> 参数映射指的是对于**java** 数据类型和**jdbc**数据类型之间的转换：这里有包括两个过程：查询阶段，我们要将java类型的数据，转换成**jdbc**类型的数据，通过 preparedStatement.setXXX() 来设值；另一个就是对**resultset**查询结果集的**jdbcType** 数据转换成**java** 数据类型。

#### 1.2.2.2. SQL语句的执行以及封装查询结果集为List<E>

动态SQL语句生成之后，**MyBatis** 将执行SQL语句，并将可能返回的结果集转换成**List<E>** 列表。**MyBatis** 在对结果集的处理中，支持结果集关系一对多和多对一的转换，并且有两种支持方式，一种为嵌套查询语句的查询，还有一种是嵌套结果集的查询。

### 1.2.3. 框架支撑层

#### 1.2.3.1事务管理机制

> 事务管理机制对于**ORM**框架而言是不可缺少的一部分，事务管理机制的质量也是考量一个**ORM**框架是否优秀的一个标准.

#### 1.2.3.2连接池管理机制

> 由于创建一个数据库连接所占用的资源比较大， 对于数据吞吐量大和访问量非常大的应用而言，连接池的设计就显得非常重要.

#### 1.2.3.3缓存机制

> 为了提高数据利用率和减小服务器和数据库的压力，**MyBatis** 会对于一些查询提供会话级别的数据缓存，会将对某一次查询，放置到**SqlSession** 中，在允许的时间间隔内，对于完全相同的查询，**MyBatis** 会直接将缓存结果返回给用户，而不用再到数据库中查找.

#### 1.2.3.4SQL语句的配置方式

> 传统的**MyBatis** 配置**SQL** 语句方式就是使用XML文件进行配置的，但是这种方式不能很好地支持面向接口编程的理念，为了支持面向接口的编程，**MyBatis** 引入了**Mapper**接口的概念，面向接口的引入，对使用注解来配置**SQL** 语句成为可能，用户只需要在接口上添加必要的注解即可，不用再去配置**XML**文件了，但是，目前的**MyBatis** 只是对注解配置**SQL** 语句提供了有限的支持，某些高级功能还是要依赖**XML**配置文件配置**SQL** 语句。

### 1.2.4 引导层

> 引导层是配置和启动**MyBatis** 配置信息的方式。**MyBatis** 提供两种方式来引导**MyBatis** ：基于XML配置文件的方式和基于**Java API** 的方式。

## 1.3MyBatis的主要构件及其相互关系

从MyBatis代码实现的角度来看，MyBatis的主要的核心部件有以下几个：

> - **SqlSession** 作为MyBatis工作的主要顶层API，表示和数据库交互的会话，完成必要数据库增删改查功能
> - **Executor** MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
> - **StatementHandler** 具体操作数据库相关的handler接口
> - **ParameterHandler** 负责对用户传递的参数转换成JDBC Statement 所需要的参数，
> - **ResultSetHandler** 具体操作数据库返回结果的handler接口
> - **MappedStatement** MappedStatement维护了一条<select|update|delete|insert>节点的封装，
> - **SqlSource** 负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
> - **BoundSql** 表示动态生成的SQL语句以及相应的参数信息
> - **Configuration** 管理mysqlConfig.xml全局配置关系类

## 1.4MyBatis的入门程序

### 1.4.1先准备数据库

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=YmZlYzQ4YjI5ZTQ5ZjQ5NWJjNjVjNzA1Yzk5OWM2NzJfZTg4Vkt4djdkdDk5NXNZRDM3Z3lhTXNSQlZHWm5FTnFfVG9rZW46SW9BdWJEZXk0b2VmaFJ4Y05kTGM4Z1ZnbkVkXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

### 1.4.2加入mysql驱动依赖

```XML
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>
<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.32</version>
        </dependency>
```

### 1.4.3编写mybatis核心配置文件：mybatisConfig.xml

```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/students"/>
                <property name="username" value="root"/>
                <property name="password" value="123123"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/studentMapper.xml"/>
    </mappers>
</configuration>
```

### 1.4.4编写mapper文件和映射的对象类文件

```Java
@Mapper
public interface StudentMapper {
    List<Student> queryAll();
}

@Data
public class Student {
    private int id;
    private String name;
    private String gender;
    private int age;
}
```

### 1.4.5编写mapper.xml文件

```XML
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.lesson9.mapper.StudentMapper">

    <select id="queryAll" resultType="com.example.lesson9.pojo.Student">
        select id,name,gender,age from students.test
    </select>

</mapper>
```

### 1.4.6编写mybatis程序代码

```Java
package com.example.lesson9;

import com.example.mybatis.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mybatisTest {
    public static void main(String[] args) throws Exception{
         /* 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
          * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用*/
        InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //2. 从SqlSession工厂 SqlSessionFactory中创建一个SqlSession，进行数据库操作
        SqlSession sqlSession = factory.openSession();
        //3.使用SqlSession查询list表
        List<Student> result = sqlSession.selectList("queryAll");
        System.out.println(result);
    }
}
```

### 1.4.7查看结果

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NTYxNjA0NmQxMGI2NWIxNDY0MTAzMzk3ZWE1YTA0OTlfMFFab3ZDZUlSY0VZM09NU0txQkVHVE1kbHVFZTRNbUZfVG9rZW46UXFJWGJ4OWlybzVTQzJ4eW1SVGNCclFabkVnXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

## 1.5源码分析举例

```Java
SqlSessionFactoryBuilder的build方法解析XML配置，创建SqlSessionFactory实例。
public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
        SqlSessionFactory var5;
        try {
            //通过传入的inputstream进行xml文件配置，并传入sqlsession工厂
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
            var5 = this.build(parser.parse());
        } catch (Exception var14) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", var14);
        } finally {
            ErrorContext.instance().reset();

            try {
                inputStream.close();
            } catch (IOException var13) {
                ;
            }

        }

        return var5;
    }
```

builder方法的主要作用是从xml文件中读取数据并将其加载到内存中。

接下来，让我们再详细分析第三步操作，即通过 List<Student> result = sqlSession.selectList("queryAll"); 这条语句来获取数据。

```Java
private <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        List var6;
        try {
            MappedStatement ms = this.configuration.getMappedStatement(statement);
            this.dirty |= ms.isDirtySelect();
            var6 = this.executor.query(ms, this.wrapCollection(parameter), rowBounds, handler);
        } catch (Exception var10) {
            throw ExceptionFactory.wrapException("Error querying database.  Cause: " + var10, var10);
        } finally {
            ErrorContext.instance().reset();
        }

        return var6;
    }
```

这段代码的主要功能是通过 `this.configuration.getMappedStatement(statement)` 方法来获取我们编写的 mapper XML 对象，这样可以为后续的 SQL 拼写和其他操作提供必要的数据和配置信息。

```Java
public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        CacheKey key = this.createCacheKey(ms, parameter, rowBounds, boundSql);
        return this.query(ms, parameter, rowBounds, resultHandler, key, boundSql);
    }
```

query方法被反复调用，其中包含了this.creatCatheKey方法，这个方法非常强大，建议课下深入理解其实现原理，它展示了MyBatis的一级缓存机制，大概的内容是在执行sql之前，MyBatis会检查缓存中是否存在这个数据，如果存在，则直接返回缓存中的数据，如果没有，则会执行sql查询，并将结果再次存入缓存中，这就提升了效率，避免下次查询时再次访问数据库，从而有效减轻了数据库的负载压力。

# 2.springboot

## 2.1前置知识

springmvc框架：拦截所有请求，并处理请求，分发给相应的处理器，覆盖表述层，实现表述层的简化

restful api的设计规范：一些请求方式（如get，delete，put，post）以及状态码（200表示客户端请求成功，404表示资源不存在，500表示服务器发生不可预期的错误等） 

## 2.2springboot是什么

Spring Boot是一个基于Spring框架的项目，旨在简化Spring应用的初始搭建以及开发过程。[Spring Boot 中文文档](https://springdoc.cn/spring-boot/)提供了全面的入门指南和参考信息.

## 2.3springboot的三层架构

### 2.3.1三层架构是什么

把各个功能模块划分为表述层，业务逻辑层，和数据访问层三层架构，各层之间采用接口相互访问，并通过对象模型的实体类（pojo）作为数据传递的载体，不同的对象模型实体类一般对应数据库的不同表。而springboot实现的方式依次是controller层，service层和dao(mapper)层。

在我们进行程序设计程序开发时，尽可能让每一个接口、类、方法的职责更单一些（单一职责原则）。

> 单一职责原则：一个类或一个方法，就只做一件事情，只管一块功能。
>
> 这样就可以让类、接口、方法的复杂度更低，可读性更强，扩展性更好，也更好利用后期的维护。

在我们项目开发中呢，可以将代码分为三层：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ODc5MzI1YTljYTgwOTZhNzRmMGNjZmZhN2ZlNDhjYzNfQVpGNDA1MEo0S1VTbDhwUURsc2hsV2RpNzZUOUNXNmNfVG9rZW46SWlZN2JyZXFWbzBvOTN4ZVRYR2M5YXdLbm5oXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

- 数据访问：负责业务数据的维护操作，包括增、删、改、查等操作。
- 逻辑处理：负责业务逻辑处理的代码。
- 请求处理、响应数据：负责，接收页面的请求，给页面响应数据。

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MzE3YTZlMjc1YzNkMWQzMmFjZDU4MjE2ZjBiZWE4YWJfQThVY285OUlWc0NkWm5uQU9mQkpxNUNrSlo0V1NibXNfVG9rZW46RENSYWJONmJab3lrOVJ4c3l1SWMySGdObjRjXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

前端发起的请求，由Controller层接收（Controller响应数据给前端）

Controller层调用Service层来进行逻辑处理（Service层处理完后，把处理结果返回给Controller层）

Serivce层调用Dao层（逻辑处理过程中需要用到的一些数据要从Dao层获取）

Dao层操作文件中的数据（Dao拿到的数据会返回给Service层）

### 2.3.2代码拆分

我们使用三层架构思想，来简单操作一下，项目结构如下：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDhjYTdjMjExY2RiOWQ3ODhkMTZkOTk0NGI2YmVlYmVfajI2M1JMYjk5RG9WdHpISWs0VXQzZks2d0owVXJRalhfVG9rZW46WFV2QmJ6ZE1IbzIyQmF4aUdlTGMwdWVmbjRlXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

导入依赖：

```XML
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>3.0.3</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
```

**控制层：**接收前端发送的请求，对请求进行处理，并响应数据

```Java
@RestController
@RequestMapping("/user")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")
    public List<Student> getUser(){
        List<Student> students = studentService.queryAll();
        return students;
    }      
}
```

**业务逻辑层：**处理具体的业务逻辑

- 业务接口

```Java
public interface StudentService {
    List<Student> queryAll();
}
```

- 业务实现类

```Java
@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public List<Student> queryAll(){
        return studentMapper.queryAll();
    }
}
```

**数据访问层：**负责数据的访问操作，包含数据的增、删、改、查

数据访问接口实现

```Java
@Mapper
public interface StudentMapper {
    @Select("select id,name,gender,age from students.test")
    List<Student> queryAll();
}
```

实体pojo层

```Java
@Data
public class Student {
    private int id;
    private String name;
    private String gender;
    private int age;
}
```

配置yml文件

```YAML
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver #?????
    url: jdbc:mysql://localhost:3306/students?useSSL=FALSE&serverTimezone=UTC
    username: root #??
    password: 123123 #??
```

## 2.4 注解开发

### 2.4.1SpringbootApplication注解

@SpringBootApplication注解是Spring Boot框架中的核心注解，它的主要作用是简化和加速Spring Boot应用程序的配置和启动过程。

具体而言，@SpringBootApplication注解起到以下几个主要作用：

1. 自动配置：@SpringBootApplication注解包含了@EnableAutoConfiguration注解，用于启用Spring Boot的自动配置机制。自动配置会根据应用程序的依赖项和类路径，自动配置各种常见的Spring配置和功能，减少开发者的手动配置工作。它通过智能地分析类路径、加载配置和条件判断，为应用程序提供适当的默认配置。
2. 组件扫描：@SpringBootApplication注解包含了@ComponentScan注解，用于自动扫描并加载应用程序中的组件，例如控制器（Controllers）、服务（Services）、存储库（Repositories）等。它默认会扫描@SpringBootApplication注解所在类的包及其子包中的组件，并将它们纳入Spring Boot应用程序的上下文中，使它们可被自动注入和使用。
3. 声明配置类：@SpringBootApplication注解本身就是一个组合注解，它包含了@Configuration注解，将被标注的类声明为配置类。配置类可以包含Spring框架相关的配置、Bean定义，以及其他的自定义配置。通过@SpringBootApplication注解，开发者可以将配置类与启动类合并在一起，使得配置和启动可以同时发生。

总的来说，@SpringBootApplication注解的主要作用是简化Spring Boot应用程序的配置和启动过程。它自动配置应用程序、扫描并加载组件，并将配置和启动类合二为一，简化了开发者的工作量，提高了开发效率。

### 2.4.2@Service，@Controler，@Mapper注解

在Spring Boot中，使用`@Service``@Controller`注解的类会被自动扫描并注册为Spring容器的Bean，因此可以被其他组件通过依赖注入的方式使用。

`@Controller`用于标记控制层组件，控制层负责处理用户的HTTP请求，并返回响应。通常，控制层会调用服务层的方法来处理业务逻辑，并将结果返回给用户，而且Spring MVC会识别带有`@RequestMapping`系列注解的方法，并将其映射为处理特定HTTP请求的方法。

```Java
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")
    public List<Student> getUser(){
        List<Student> students = studentService.queryAll();
        return students;
    }
}
```

`@Mapper`注解通常用于标记数据访问层的组件，特别是在使用MyBatis时，用于定义与数据库交互的Mapper接口。这个注解不是Spring Boot官方提供的，而是MyBatis提供的，用于指定哪些接口应该被MyBatis处理，并生成相应的代理实现。

### 2.4.3@Autowired注解实现自动装配

1. @Autowired工作流程

- 首先根据所需要的组件类型到 IOC 容器中查找
  - 能够找到唯一的 bean：直接执行装配
  - 如果完全找不到匹配这个类型的 bean：装配失败
  - 和所需类型匹配的 bean 不止一个
    - 加入@Qualifier(value = "bean的名称")后
    - 没有 @Qualifier 注解：根据 @Autowired 标记位置成员变量的变量名作为 bean 的 id 进行匹配
      - 能够找到：执行装配
      - 找不到：装配失败
    - 使用 @Qualifier 注解：根据 @Qualifier 注解中指定的名称作为 bean 的id进行匹配
      - 能够找到：执行装配
      - 找不到：装配失败

## 2.5数据访问与持久化

### 2.5.1Spring Data JPA

Spring Boot与Spring Data JPA无缝集成，使得数据访问层的开发变得更加简单和高效。以下是一个基本的JPA实体和仓库（Repository）定义：

```Java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // getters and setters
}

public interface UserRepository extends JpaRepository<User, Long> {
}
```

在服务类中可以直接注入`UserRepository`并使用：

```Java
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user) {
        return userRepository.save(user);
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User update(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
```

### 2.5.2数据库连接与配置

Spring Boot通过自动配置简化了数据库连接的配置。在`application.properties`或`application.yml`文件中配置数据库连接信息即可,这是以`application.properties`举的例子

```Properties
spring.datasource.url=jdbc:mysql://localhost:3306/students
spring.datasource.username=root
spring.datasource.password=123123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2.5.3事务管理

Spring Boot支持声明式事务管理，可以通过`@Transactional`注解轻松实现事务控制。例如：

```Java
@Transactional
    public User update(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }
```

## 2.6Spring Boot的安全机制

在springboot项目中，可以整合springsecurity，可以实现以下功能：

1. **简化配置**：Spring Boot 通过自动配置机制简化了 Spring Security 的集成，使得开发者可以轻松地添加安全依赖并启用基本的安全设置。
2. **灵活的访问控制**：Spring Security 提供了细粒度的访问控制，允许开发者根据具体需求定义复杂的访问规则，例如通过 `SecurityFilterChain` 来定义访问规则。
3. **身份验证和授权**：Spring Security 提供了强大的身份验证和授权功能，支持多种认证机制，如表单登录、HTTP基本认证、OAuth2等，以及基于角色和权限的访问控制。
4. **防御常见安全漏洞**：Spring Security 帮助开发者防范SQL注入、跨站脚本攻击（XSS）等常见网络攻击，提高了应用的安全性。
5. **内容安全策略（CSP）**：Spring Security 支持配置内容安全策略，进一步增强了应用的防护能力。
6. **高度可定制**：Spring Security 是一个高度可定制的安全框架，它主要提供了身份认证和授权功能，允许开发者定义哪些URL需要认证，哪些用户有权访问某些资源等。
7. **减少重复代码**：Spring Security 提供了一组可以在Spring应用上下文中配置的Bean，充分利用了Spring IoC、DI和AOP功能，为应用系统提供声明式的安全访问控制功能，减少了为企业系统安全控制编写大量重复代码的工作。

### 2.6.1导入依赖

```XML
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

### 2.6.2进行配置

```Java
@Configuration
public class SecurityConfig  {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("123123")).roles("USER")   //定义user用户和密码
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("123123")).roles("USER", "ADMIN")//定义admin用户和密码
                .build());
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用BCryptPasswordEncoder进行密码加密
    }
    // 配置安全过滤链
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/").hasRole("ADMIN") // 只有ADMIN角色可以访问/admin路径
**                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER") // USER和ADMIN角色都可以访问/user路径
                        .requestMatchers("/").permitAll() // 允许所有用户访问根路径
                )
                .formLogin(withDefaults()) // 启用表单登录
                .logout(withDefaults()); // 启用注销功能

        return http.build();
    }

}
```

使得admin用户可以访问"/admin/**"和"/user/*"的接口，而user用户只能访问"user/*"的接口

### 2.6.3定义接口进行访问

```Java
@RestController
public class UserController {
//    @Autowired
//    private UserService userService;
    @GetMapping("/admin/test")
    public String adminEndpoint() {
        return "Admin!";
    }
    @GetMapping("/user/test")
    public String userEndpoint() {
        return "User!";
    }
}
```

### 2.6.4结果

登陆user用户访问admin/test接口，结果就是不能访问

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTg4NmRlNTIyZThlZGU3ZGU4ZjQ4NzcxNDQyNGJiZWNfMHo1VFh2dXNjUjhrNGpGbDlmTlpCTlZGelNLOTZnNFRfVG9rZW46VjZqM2JoMU1pb1AyU0t4Tkt3TGMxWlBmbm1jXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

但user可以访问user/test接口

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MDk5NTM1ZWRiMmUxMWRlZWY2MGRhYjQwNzA4ZmY1YzJfMlpJRGhzeXVZOTBibTRsYkdXRGNSRzJtbVhrcml1M3BfVG9rZW46Q1dYQmI0QXM1b3JGMVR4Ymc3TGM4UTlpbmRoXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

登陆admin用户，可以访问admin/test接口

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjkxYzRmYWY2MzhmMWMxMTY1YTNiNmQ4MTFiZWNlN2NfamJJSU5oVE5hejlkRTFHQ1pzVTM0akxRZ1R1aERoa05fVG9rZW46RWw4UGJ6RFc5b0JYSzN4MHB0NGNTVk9obmNjXzE3NDE0MDk4Mjk6MTc0MTQxMzQyOV9WNA)

## 2.7Spring Boot的监控与管理

### 2.7.1Spring Boot Actuator

Spring Boot Actuator提供了一系列用于监控和管理Spring Boot应用的端点。这些端点可以用于查看应用的健康状况、环境信息、度量指标等。可以通过引入spring-boot-starter-actuator依赖来启用Actuator功能：

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

在`application.yml`文件中配置Actuator端点的访问权限：

```YAML
management:
  endpoint:
    web:
      exposure:include: health=*
  health:
    show-details: always
```

### 2.7.2健康检查与指标监控

Actuator提供了一个`/health`端点，用于检查应用的健康状况。可以通过实现`HealthIndicator`接口来自定义健康检查。例如：

```Java
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean healthy = checkSomeService();
        if (healthy) {
            return Health.up().withDetail("service", "available").build();
        } else {
            return Health.down().withDetail("service", "unavailable").build();
        }
    }

    private boolean checkSomeService() {
        // 自定义健康检查逻辑
        return true;
    }
}
```

### 2.7.3自定义监控端点

可以通过实现`@Endpoint`注解来自定义Actuator端点。例如：

```Java
@Endpoint(id = "custom")
@Component
public class CustomEndpoint {
    @ReadOperation
    public Map<String, Object> customEndpoint() {
        Map<String, Object> details = new HashMap<>();
        details.put("custom", "This is a custom endpoint");
        return details;
    }
}
```

## 2.8Spring Boot的测试

### 2.8.1单元测试与集成测试

Spring Boot提供了强大的测试支持，可以通过引入`spring-boot-starter-test`依赖来启用测试功能。以下是一个简单的单元测试示例：

```Java
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testFindALL1(){
        List<Student> users = studentService.queryAll();
        assertNotNull(users);
    }
 }
```

### 2.8.2Mock与测试数据

可以使用`@MockBean`注解来创建Mock对象，并注入到测试上下文中。例如：

```Java
@SpringBootTest
public class StudentServiceTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    public void testFindAll() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        List<User> users = userService.findAll();
        assertTrue(users.isEmpty());
    }
}
```

到这里我们springboot的学习就告一段落了，相信你对springboot有了更深的理解，大家学完上面的内容看试着能不能在上面的项目中实现更多的方法，这是最基础的项目模型，可以去多深入看看能不能实现更多的方法
