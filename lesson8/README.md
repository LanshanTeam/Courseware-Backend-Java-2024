# 第八节：Spring，Spring MVC

> 本课时我们将学习框架相关的知识。毫无疑问，Java中最重要的框架非Spring莫属。有了它，Java就迎来了春天。



# 0、框架

先讲一下什么是框架

框架( Framework )是一个集成了基本结构、规范、设计模式、编程语言和程序库等基础组件的软件系统，它可以用来构建更高级别的应用程序。框架的设计和实现旨在解决特定领域中的常见问题，帮助开发人员更高效、更稳定地实现软件开发目标。



框架的优点包括以下几点：

1. **提高开发效率**：框架提供了许多预先设计好了的组件和工具，能够帮助开发人员快速进行开发。相较于传统手写代码，在框架提供的规范化环境中，开发者可以更快地实现项目的各种要求。
2. **降低开发成本**：框架的提供标准化的编程语言、数据操作等代码片段，避免了重复开发的问题，降低了开发成本，提供深度优化的系统，降低了维护成本，增强了系统的可靠性。
3. **提高**应用程序的**稳定性**：框架通常经过了很长时间的开发和测试，其中的许多组件、代码片段和设计模式都得到了验证。重复利用这些组件有助于减少bug的出现，从而提高了应用程序的稳定性。
4. 提供标准化的解决方案：框架通常是针对某个特定领域的，通过提供标准化的解决方案，可以为开发人员提供一种共同的语言和思想基础，有助于更好地沟通和协作。



框架的缺点包括以下几个方面：

1. 学习成本高：框架通常具有特定的语言和编程范式。对于开发人员而言，需要花费时间学习其背后的架构、模式和逻辑，这对于新手而言可能会耗费较长时间。
2. 可能存在局限性：虽然框架提高了开发效率并可以帮助开发人员解决常见问题，但是在某些情况下，特定的应用需求可能超出框架的范围，从而导致应用程序无法满足要求。开发人员可能需要更多的控制权和自由度，同时需要在框架和应用程序之间进行权衡取舍。
3. 版本变更和兼容性问题：框架的版本发布和迭代通常会导致代码库的大规模变更，进而导致应用程序出现兼容性问题和漏洞。当框架变更时，需要考虑框架是否向下兼容，以及如何进行适当的测试、迁移和升级。
4. 架构风险：框架涉及到很多抽象和概念，如果开发者没有足够的理解和掌握其架构，可能会导致系统出现设计和架构缺陷，从而影响系统的健康性和安全性。



站在文件结构的角度理解框架，可以将框架总结：**框架 = jar包+配置文件**

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NmUxOTQzMzFkM2I2NmE5NzU4ZTlhMjRmYzVjNDU4ZWJfOUJncHpDeVlyQVFSRlBnUW5FeWJGZGgxV3FMMjhsdWhfVG9rZW46TUZMb2JlNVRMb3JaTDZ4QmZhV2M4S1BTbkNmXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

总之，框架已经对**基础的代码**进行了封装并提供相应的API，开发者在使用框架是直接调用封装好的API可以省去很多代码编写，从而提高工作效率和开发速度。



# 1、Spring



## 1.1 简介

Spring是一个支持快速开发Java EE应用程序的框架。它提供了一系列底层容器和基础设施，并可以和大量常用的开源框架无缝集成，可以说是开发Java EE应用程序的必备。



**狭义**的 Spring 特指 **Spring Framework**，通常我们将它称为 Spring 框架。

Spring Framework（Spring框架）是一个开源的应用程序框架，由SpringSource公司开发，最初是为了解决企业级开发中各种常见问题而创建的。它提供了很多功能，例如：控制反转（Inversion Of Control）、面向切面编程（AOP）、声明式事务管理（TX）等。其主要目标是使企业级应用程序的开发变得更加简单和快速，并且Spring框架被广泛应用于Java企业开发领域。



**广义**上的 Spring 泛指以 Spring Framework 为基础的 **Spring 技术栈**。

经过十多年的发展，Spring 已经不再是一个单纯的应用框架，而是逐渐发展成为一个由多个不同子项目（模块）组成的成熟技术，例如 Spring Framework、Spring MVC、SpringBoot、Spring Cloud、Spring Data、Spring Security 等，其中 Spring Framework 是其他子项目的基础。

这些子项目涵盖了从企业级应用开发到云计算等各方面的内容，能够帮助开发人员解决软件发展过程中不断产生的各种实际问题，给开发人员带来了更好的开发体验。



Spring官网是[spring.io](https://spring.io/)，要注意官网有许多项目，我们这里说的Spring是指Spring Framework，可以直接从这里访问[最新版以及文档](https://spring.io/projects/spring-framework)，建议添加到浏览器收藏夹。



## 1.2 IoC 容器

在学习Spring框架时，我们遇到的第一个也是最核心的概念就是容器。

什么是容器？**容器是一种为某种特定组件的运行提供必要支持的一个软件环境**。例如，Tomcat就是一个Servlet容器，它可以为Servlet的运行提供运行环境。类似Docker这样的软件也是一个容器，它提供了必要的Linux环境以便运行一个特定的Linux进程。

通常来说，使用容器运行组件，除了提供一个组件运行环境之外，容器还提供了许多**底层服务**。例如，Servlet容器底层实现了TCP连接，解析HTTP协议等非常复杂的服务，如果没有容器来提供这些服务，我们就无法编写像Servlet这样代码简单，功能强大的组件。早期的JavaEE服务器提供的EJB容器最重要的功能就是通过声明式事务服务，使得EJB组件的开发人员不必自己编写冗长的事务处理代码，所以极大地简化了事务处理。

Spring的核心就是提供了一个IoC容器，它可以管理所有轻量级的JavaBean组件，提供的底层服务包括组件的生命周期管理、配置和组装服务、AOP支持，以及建立在AOP基础上的声明式事务服务等。



### 1.2.1 IoC

Spring提供的容器又称为IoC容器，什么是IoC？

IoC全称Inversion of Control，直译为控制反转。



#### 1.2.1.1 传统模式

在理解IoC之前，我们先看看通常的Java组件是如何协作的。

我们假定一个在线书店，通过`BookService`获取书籍：

```Java
public class BookService {
    private HikariConfig config = new HikariConfig();
    private DataSource dataSource = new HikariDataSource(config);

    public Book getBook(long bookId) {
        try (Connection conn = dataSource.getConnection()) {
            ...
            return book;
        }
    }
}
```

为了从数据库查询书籍，`BookService`持有一个`DataSource`。为了实例化一个`HikariDataSource`，又不得不实例化一个`HikariConfig`。

现在，我们继续编写`UserService`获取用户：

```Java
public class UserService {
    private HikariConfig config = new HikariConfig();
    private DataSource dataSource = new HikariDataSource(config);

    public User getUser(long userId) {
        try (Connection conn = dataSource.getConnection()) {
            ...
            return user;
        }
    }
}
```

因为`UserService`也需要访问数据库，因此，我们不得不也实例化一个`HikariDataSource`。

在处理用户购买的`CartServlet`中，我们需要实例化`UserService`和`BookService`：

```Java
public class CartServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long currentUserId = getFromCookie(req);
        User currentUser = userService.getUser(currentUserId);
        Book book = bookService.getBook(req.getParameter("bookId"));
        cartService.addToCart(currentUser, book);
        ...
    }
}
```

类似的，在购买历史`HistoryServlet`中，也需要实例化`UserService`和`BookService`：

```Java
public class HistoryServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
}
```

上述每个**组件**都采用了一种简单的通过`new`创建实例并持有的方式。仔细观察，会发现以下缺点：

1. **实例化一个组件其实很难**，例如，`BookService`和`UserService`要创建`HikariDataSource`，实际上需要读取配置，才能先实例化`HikariConfig`，再实例化`HikariDataSource`。
2. 没有必要让`BookService`和`UserService`分别创建`DataSource`实例，完全可以共享同一个`DataSource`，但谁负责创建`DataSource`，谁负责获取其他组件已经创建的`DataSource`，不好处理。类似的，`CartServlet`和`HistoryServlet`也应当共享`BookService`实例和`UserService`实例，但也不好处理。
3. 很多组件需要销毁以便释放资源，例如`DataSource`，但如果该组件被多个组件共享，如何确保它的使用方都已经全部被销毁？
4. 随着更多的组件被引入，例如，书籍评论，需要共享的组件写起来会更困难，这些组件的依赖关系会越来越复杂。
5. 测试某个组件，例如`BookService`，是复杂的，因为必须要在真实的数据库环境下执行。

从上面的例子可以看出，如果一个系统有大量的组件，其生命周期和相互之间的依赖关系如果由组件自身来维护，不但大大增加了系统的复杂度，而且会导致组件之间极为紧密的耦合，继而给测试和维护带来了极大的困难。

因此，核心问题是：

1. 谁负责创建组件？
2. 谁负责根据依赖关系组装组件？
3. 销毁时，如何按依赖顺序正确销毁？

解决这一问题的核心方案就是IoC。



#### 1.2.1.2 理解IoC

传统的应用程序中，控制权在程序本身，程序的控制流程完全由开发者控制

例如：`CartServlet`创建了`BookService`，在创建`BookService`的过程中，又创建了`DataSource`组件。这种模式的缺点是，一个组件如果要使用另一个组件，必须先知道如何正确地创建它。

在IoC模式下，控制权发生了反转，即从应用程序转移到了IoC容器，所有组件不再由应用程序自己创建和配置，而是由IoC容器负责，这样，应用程序只需要直接使用已经创建好并且配置好的组件。为了能让组件在IoC容器中被“装配”出来，需要某种“注入”机制，例如，`BookService`自己并不会创建`DataSource`，而是等待外部通过`setDataSource()`方法来注入一个`DataSource`：

```Java
public class BookService {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

不直接`new`一个`DataSource`，而是注入一个`DataSource`，这个小小的改动虽然简单，却带来了一系列好处：

1. `BookService`不再关心如何创建`DataSource`，因此，不必编写读取数据库配置之类的代码；
2. `DataSource`实例被注入到`BookService`，同样也可以注入到`UserService`，因此，共享一个组件非常简单；
3. 测试`BookService`更容易，因为注入的是`DataSource`，可以使用内存数据库，而不是真实的MySQL配置。

因此，IoC又称为依赖注入（DI：Dependency Injection），它解决了一个最主要的问题：将组件的创建+配置与组件的使用相分离，并且，由IoC容器负责管理组件的生命周期。

因为IoC容器要负责实例化所有的组件，因此，有必要告诉容器如何创建组件，以及各组件的依赖关系。

一种最简单的配置是通过XML文件来实现，例如：

```XML
<beans>
    <bean id="dataSource" class="HikariDataSource" />
    <bean id="bookService" class="BookService">
        <property name="dataSource" ref="dataSource" />
    </bean>

    <bean id="userService" class="UserService">
        <property name="dataSource" ref="dataSource" />
    </bean>
</beans>
```

上述XML配置文件指示IoC容器创建3个JavaBean组件，并把id为`dataSource`的组件通过属性`dataSource`（即调用`setDataSource()`方法）注入到另外两个组件中。

在Spring的IoC容器中，我们把所有组件统称为JavaBean，即配置一个组件就是配置一个Bean。



#### 1.2.1.3 依赖注入

我们从上面的代码可以看到，依赖注入可以通过`set()`方法实现。但依赖注入也可以通过构造方法实现。

很多Java类都具有带参数的构造方法，如果我们把`BookService`改造为通过构造方法注入，那么实现代码如下：

```Java
public class BookService {
    private DataSource dataSource;
    
    public BookService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

Spring的IoC容器同时支持属性注入和构造方法注入，并允许混合使用。



### 1.2.2 装配Bean

我们前面讨论了为什么要使用Spring的IoC容器，因为让容器来为我们创建并装配Bean能获得很大的好处，那么到底如何使用IoC容器？装配好的Bean又如何使用？



#### 1.2.2.0 工程示例

我们来看一个具体的用户注册登录的例子。整个工程的结构如下：

```Plain
spring-ioc-appcontext
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── SpringMVC
        │           ├── Main.java
        │           └── service
        │               ├── MailService.java
        │               ├── User.java
        │               └── UserService.java
        └── resources
            └── application.xml
```

首先，我们用Maven创建工程并引入`spring-context`依赖：

- org.springframework:spring-context:6.0.0

我们先编写一个`MailService`，用于在用户登录和注册成功后发送邮件通知：

```Java
public class MailService {
    private ZoneId zoneId = ZoneId.systemDefault();

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void sendLoginMail(User user) {
        System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    public void sendRegistrationMail(User user) {
        System.err.println(String.format("Welcome, %s!", user.getName()));

    }
}
```

再编写一个`UserService`，实现用户注册和登录：

```Java
public class UserService {
    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    // 模拟数据库
    private List<User> users = new ArrayList<>(List.of(
        new User(1, "bob@example.com", "password", "Bob"),
        new User(2, "alice@example.com", "password", "Alice"),
        new User(3, "tom@example.com", "password", "Tom")));

    public User getUser(long id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }
    
    public User register(String email, String password, String name) {
        users.forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });
        User user = new User(users.stream().map(User::getId).max(Comparator.comparingInt(o -> o)).get(),
                email, password, name);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
```

注意到`UserService`通过`setMailService()`注入了一个`MailService`。



#### 1.2.0.1 XML

第一种方式是编写一个特定的`application.xml`配置文件，告诉Spring的IoC容器应该如何创建并组装Bean：

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="mailService" class="com.SpringMVC.service.MailService" />

    <bean id="userService" class="com.SpringMVC.service.UserService">
        <property name="mailService" ref="mailService" />
    </bean>

</beans>
```

注意观察上述配置文件，其中与XML Schema相关的部分格式是固定的，我们只关注两个`<bean ...>`的配置：

- 每个`<bean ...>`都有一个`id`标识，相当于Bean的唯一ID；
- 在`userService`Bean中，通过`<property name="..." ref="..." />`注入了另一个Bean；
- Bean的顺序不重要，Spring根据依赖关系会自动正确初始化。

把上述XML配置文件用Java代码写出来，就像这样：

```Java
UserService userService = new UserService();
MailService mailService = new MailService();
userService.setMailService(mailService);
```

只不过Spring容器是通过读取XML文件后使用反射完成的。

如果注入的不是Bean，而是`boolean`、`int`、`String`这样的数据类型，则通过`value`注入，例如，创建一个`HikariDataSource`：

```XML
<bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource">
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test" />
    <property name="username" value="root" />
    <property name="password" value="password" />
    <property name="maximumPoolSize" value="10" />
    <property name="autoCommit" value="true" />
</bean>
```

最后一步，我们需要创建一个Spring的**IoC****容器实例**，然后加载配置文件，让Spring容器为我们创建并装配好配置文件中指定的所有Bean，这只需要一行代码：

```Java
// 自动从classpath中查找指定的XML配置文件
ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
```

接下来，我们就可以从Spring容器中“取出”装配好的Bean然后使用它：

```Java
// 获取Bean:
UserService userService = context.getBean(UserService.class);
// 正常调用:
User user = userService.login("bob@example.com", "password");
```

完整的`main()`方法如下：

```Java
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}
```



#### 1.2.2.2 Annotation

使用Spring的IoC容器，实际上就是通过类似XML这样的配置文件，把我们自己的Bean的依赖关系描述出来，然后让容器来创建并装配Bean。一旦容器初始化完毕，我们就直接从容器中获取Bean使用它们。

使用XML配置的优点是**所有的Bean都能一目了然地列出来**，并通过配置注入能直观地看到每个Bean的依赖。它的缺点是写起来**非常繁琐**，每增加一个组件，就必须把新的Bean配置到XML中。

有没有其他更简单的配置方式呢？

有！我们可以使用Annotation配置，可以完全不需要XML，让Spring自动扫描Bean并组装它们。

我们把上一节的示例改造一下，先删除XML配置文件，然后，给`UserService`和`MailService`添加几个注解。

首先，我们给`MailService`添加一个`@Component`注解：

```Java
@Component
public class MailService {
    ...
}
```

这个`@Component`注解就相当于定义了一个Bean，它有一个**可选的名称**，默认是`mailService`，即小写开头的类名。

然后，我们给`UserService`添加一个`@Component`注解和一个`@Autowired`注解：

```Java
@Component
public class UserService {
    @Autowired
    MailService mailService;
    ...
}
```

使用`@Autowired`就相当于把指定类型的Bean注入到指定的字段中。和XML配置相比，`@Autowired`大幅简化了注入，因为它不但可以写在`set()`方法上，还可以直接写在字段上，甚至可以写在构造方法中：

```Java
@Component
public class UserService {
    MailService mailService;

    public UserService(@Autowired MailService mailService) {
        this.mailService = mailService;
    }
    ...
}
```

我们一般把`@Autowired`写在字段上，通常使用package权限的字段，便于测试。

最后，编写一个`AppConfig`类启动容器：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}
```

除了`main()`方法外，`AppConfig`标注了`@Configuration`，表示它是一个配置类，因为我们创建`ApplicationContext`时：

```Java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

使用的实现类是`AnnotationConfigApplicationContext`，必须传入一个标注了`@Configuration`的类名。

此外，`AppConfig`还标注了`@ComponentScan`，它告诉容器，自动搜索当前类所在的包以及子包，把所有标注为`@Component`的Bean自动创建出来，并根据`@Autowired`进行装配。

使用Annotation配合自动扫描能大幅简化Spring的配置，我们只需要保证：

- 每个Bean被标注为`@Component`并正确使用`@Autowired`注入；
- 配置类被标注为`@Configuration`和`@ComponentScan`；
- 所有Bean均在指定包以及子包内。

使用`@ComponentScan`非常方便，但是，我们也要特别注意包的层次结构。通常来说，启动配置`AppConfig`位于自定义的顶层包（例如`com.itranswarp.learnjava`），其他Bean按类别放入子包。



### 1.2.3 定制Bean



#### 1.2.3.1 Scope

对于Spring容器来说，当我们把一个Bean标记为`@Component`后，它就会自动为我们创建一个单例（Singleton），即容器初始化时创建Bean，容器关闭前销毁Bean。在容器运行期间，我们调用`getBean(Class)`获取到的Bean总是同一个实例。

还有一种Bean，我们每次调用`getBean(Class)`，容器都返回一个新的实例，这种Bean称为Prototype（原型），它的生命周期显然和Singleton不同。声明一个Prototype的Bean时，需要添加一个额外的`@Scope`注解：

```Java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // @Scope("prototype")
public class MailSession {
    ...
}
```



#### 1.2.3.2 可选注入

默认情况下，当我们标记了一个`@Autowired`后，Spring如果没有找到对应类型的Bean，它会抛出`NoSuchBeanDefinitionException`异常。

可以给`@Autowired`增加一个`required = false`的参数：

```Java
@Component
public class MailService {
    @Autowired(required = false)
    ZoneId zoneId = ZoneId.systemDefault();
    ...
}
```

这个参数告诉Spring容器，如果找到一个类型为`ZoneId`的Bean，就注入，如果找不到，就忽略。

这种方式非常适合有定义就使用定义，没有就使用默认值的情况。



#### 1.2.3.3 创建第三方Bean

如果一个Bean不在我们自己的package管理之内，例如`ZoneId`，如何创建它？

答案是我们自己在`@Configuration`类中编写一个Java方法创建并返回它，注意给方法标记一个`@Bean`注解：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    // 创建一个Bean:
    @Bean
    ZoneId createZoneId() {
        return ZoneId.of("Z");
    }
}
```

Spring对标记为`@Bean`的方法只调用一次，因此返回的Bean仍然是单例。



#### 1.2.3.4 初始化和销毁

有些时候，一个Bean在注入必要的依赖后，需要进行初始化（监听消息等）。在容器关闭时，有时候还需要清理资源（关闭连接池等）。我们通常会定义一个`init()`方法进行初始化，定义一个`shutdown()`方法进行清理，然后，引入JSR-250定义的Annotation：

- jakarta.annotation:jakarta.annotation-api:2.1.1

在Bean的初始化和清理方法上标记`@PostConstruct`和`@PreDestroy`：

```Java
@Component
public class MailService {
    @Autowired(required = false)
    ZoneId zoneId = ZoneId.systemDefault();

    @PostConstruct
    public void init() {
        System.out.println("Init mail service with zoneId = " + this.zoneId);
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Shutdown mail service");
    }
}
```

Spring容器会对上述Bean做如下初始化流程：

- 调用构造方法创建`MailService`实例；
- 根据`@Autowired`进行注入；
- 调用标记有`@PostConstruct`的`init()`方法进行初始化。

而销毁时，容器会首先调用标记有`@PreDestroy`的`shutdown()`方法。

Spring只根据Annotation查找*无参数*方法，对方法名不作要求。



#### 1.2.3.5 使用别名

默认情况下，对一种类型的Bean，容器只创建一个实例。但有些时候，我们需要对一种类型的Bean创建多个实例。例如，同时连接多个数据库，就必须创建多个`DataSource`实例。

如果我们在`@Configuration`类中创建了多个同类型的Bean：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

Spring会报`NoUniqueBeanDefinitionException`异常，意思是出现了重复的Bean定义。

这个时候，需要给每个Bean添加不同的名字：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    @Bean("z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

可以用`@Bean("name")`指定别名，也可以用`@Bean`+`@Qualifier("name")`指定别名。

存在多个同类型的Bean时，注入`ZoneId`又会报错：

```Plain
NoUniqueBeanDefinitionException: No qualifying bean of type 'java.time.ZoneId' available: expected single matching bean but found 2
```

意思是期待找到唯一的`ZoneId`类型Bean，但是找到两。因此，注入时，要指定Bean的名称：

```Java
@Component
public class MailService {
    @Autowired(required = false)
    @Qualifier("z") // 指定注入名称为"z"的ZoneId
    ZoneId zoneId = ZoneId.systemDefault();
    ...
}
```

还有一种方法是把其中某个Bean指定为`@Primary`：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Primary // 指定为主要Bean@Qualifier("z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
```

这样，在注入时，如果没有指出Bean的名字，Spring会注入标记有`@Primary`的Bean。这种方式也很常用。例如，对于主从两个数据源，通常将主数据源定义为`@Primary`：

```Java
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Primary
    DataSource createMasterDataSource() {
        ...
    }

    @Bean
    @Qualifier("slave")
    DataSource createSlaveDataSource() {
        ...
    }
}
```

其他Bean默认注入的就是主数据源。如果要注入从数据源，那么只需要指定名称即可。



## 1.3 AOP

AOP全称为Aspect Oriented Programming，即面向切面编程。



### 1.3.1 什么是AOP

我们先回顾一下OOP：Object Oriented Programming，OOP作为面向对象编程的模式，获得了巨大的成功，OOP的主要功能是数据封装、继承和多态。

而AOP是一种新的编程方式，它和OOP不同，OOP把系统看作**多个对象的交互**，AOP把系统分解为**不同的****横切关注点**，或者称之为**切面**（Aspect）。

要理解AOP的概念，我们先用OOP举例，比如一个业务组件`BookService`，它有几个业务方法：

- createBook：添加新的Book；
- updateBook：修改Book；
- deleteBook：删除Book。

对每个业务方法，例如，`createBook()`，除了业务逻辑，还需要**安全检查**、**日志记录**和**事务处理**，它的代码像这样：

```Java
public class BookService {
    public void createBook(Book book) {
        // 安全检查
        securityCheck();
        // 事务处理
        Transaction tx = startTransaction();
        try {
            // 核心业务逻辑
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
        // 日志记录
        log("created book: " + book);
    }
}
```

继续编写`updateBook()`，代码如下：

```Java
public class BookService {
    public void updateBook(Book book) {
        securityCheck();
        Transaction tx = startTransaction();
        try {
            // 核心业务逻辑
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
        log("updated book: " + book);
    }
}
```

对于安全检查、日志、事务等代码，它们会**重复出现**在每个业务方法中。使用OOP，我们很难将这些四处分散的代码**模块化**。

考察业务模型可以发现，`BookService`关心的是自身的核心逻辑，但整个系统还要求关注安全检查、日志、事务等功能，这些功能实际上“**横跨**”多个业务方法，为了实现这些功能，不得不在每个业务方法上重复编写代码。

一种可行的方式是使用[Proxy模式](https://liaoxuefeng.com/books/java/design-patterns/structural/proxy/index.html)，将某个功能，例如，权限检查，放入Proxy中：

```Java
public class SecurityCheckBookService implements BookService {
    private final BookService target;

    public SecurityCheckBookService(BookService target) {
        this.target = target;
    }

    public void createBook(Book book) {
        securityCheck();
        target.createBook(book);
    }

    public void updateBook(Book book) {
        securityCheck();
        target.updateBook(book);
    }

    public void deleteBook(Book book) {
        securityCheck();
        target.deleteBook(book);
    }

    private void securityCheck() {
        ...
    }
}
```

这种方式的缺点是**比较麻烦**，必须先抽取接口，然后，针对每个方法实现Proxy。

另一种方法是，既然`SecurityCheckBookService`的代码都是标准的Proxy样板代码，不如把权限检查视作一种**切面**（Aspect），把日志、事务也视为切面，然后，以某种**自动化**的方式，把切面**织入**到核心逻辑中，实现Proxy模式。

如果我们以AOP的视角来编写上述业务，可以依次实现：

1. 核心逻辑，即BookService；
2. 切面逻辑，即：
   1. 权限检查的Aspect；
   2. 日志的Aspect；
   3. 事务的Aspect。

然后，以某种方式，让框架来把上述3个Aspect以Proxy的方式“织入”到`BookService`中，这样一来，就不必编写复杂而冗长的Proxy模式。



### 1.3.2 AOP原理

如何把切面织入到核心逻辑中？这正是AOP需要解决的问题。换句话说，如果客户端获得了`BookService`的引用，当调用`bookService.createBook()`时，如何对调用方法进行拦截，并在拦截前后进行安全检查、日志、事务等处理，就相当于完成了所有业务功能。

在Java平台上，对于AOP的织入，有3种方式：

1. 编译期：在编译时，由**编译器**把切面调用编译进字节码，这种方式需要定义新的关键字并扩展编译器，AspectJ就扩展了Java编译器，使用关键字aspect来实现织入；
2. 类加载器：在目标类被装载到JVM时，通过一个特殊的**类加载器**，对目标类的字节码重新“增强”；
3. 运行期：目标对象和切面都是普通Java类，通过JVM的**动态代理**功能或者第三方库实现运行期动态织入。

最简单的方式是第三种，Spring的AOP实现就是基于JVM的动态代理。由于JVM的动态代理要求必须实现接口，如果一个普通类没有业务接口，就需要通过[CGLIB](https://github.com/cglib/cglib)或者[Javassist](https://www.javassist.org/)这些第三方库实现。

AOP技术看上去比较神秘，但实际上，它本质就是一个动态代理，让我们把一些常用功能如权限检查、日志、事务等，从每个业务方法中剥离出来。

需要特别指出的是，AOP对于解决特定问题，例如事务管理非常有用，这是因为分散在各处的事务代码几乎是完全相同的，并且它们需要的参数（JDBC的Connection）也是固定的。另一些特定问题，如日志，就不那么容易实现，因为日志虽然简单，但打印日志的时候，经常需要捕获局部变量，如果使用AOP实现日志，我们只能输出固定格式的日志，因此，使用AOP时，必须适合特定的场景。



### 1.3.3 装配AOP

#### 1.3.3.1 相关概念

在AOP编程中，我们经常会遇到下面的概念：

- Aspect：切面，即一个横跨多个核心逻辑的功能，或者称之为横切关注点；

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NDUwZGQxZWQ0ZjI3YWRhYWIzMGZhNDEzZTIyZWY1MDZfbVVpd3l3eVlxSHV4c3JkbGV6MWNrR0NHQXZsMHkxT2RfVG9rZW46QzJYY2JYbFJXb0FWR1B4OU90YWNqT096bkdoXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

- Joinpoint：连接点，即定义在应用程序流程的何处插入切面的执行；
- Pointcut：切入点，即一组连接点的集合；
- Weaving：织入，指将切面整合到程序的执行流程中；
- Advice：增强（通知），指特定连接点上执行的动作；
  - 前置通知：在被代理的目标方法前执行
  - 返回通知：在被代理的目标方法成功结束后执行
  - 异常通知：在被代理的目标方法异常结束后执行
  - 后置通知：在被代理的目标方法最终结束后执行
  - 环绕通知：使用try...catch...finally结构围绕整个被代理的目标方法，包括上面四种通知对应的所有位置

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MjQxMzdjOTQ5Y2Y1MmFlZWYyNTlkZDU3NGEwMjg3N2VfOTZERVA1TDA0bHJpMlVmblo3WVcxekNwNU9XQ0xGVG1fVG9rZW46TzR3SWJjOU1Lb1hzRG14c1MwcWM5YkFabmRnXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

- Interceptor：拦截器，是一种实现增强（通知）的方式
  - @Before：先执行拦截代码，再执行目标代码。如果拦截器抛异常，那么目标代码就不执行了；
  - @After：先执行目标代码，再执行拦截器代码。无论目标代码是否抛异常，拦截器代码都会执行；
  - @AfterReturning：和@After不同的是，只有当目标代码正常返回时，才执行拦截器代码；
  - @AfterThrowing：和@After不同的是，只有当目标代码抛出了异常时，才执行拦截器代码；
  - @Around：能完全控制目标代码是否执行，并可以在执行前后、抛异常后执行任意拦截代码，可以说是包含了上面所有功能。
- Target Object：目标对象，即真正执行业务的核心逻辑对象；
- AOP Proxy：AOP代理，是客户端持有的增强后的对象引用。

看完上述术语，是不是感觉对AOP有了进一步的困惑？其实，我们不用关心AOP创造的“术语”，只需要理解AOP本质上只是一种**代理模式**的实现方式，在Spring的容器中实现AOP特别方便。



#### 1.3.3.2 execution表达式

我们以`UserService`和`MailService`为例，这两个属于核心业务逻辑，现在，我们准备给`UserService`的每个业务方法执行前添加日志，给`MailService`的每个业务方法执行前后添加日志，在Spring中，需要以下步骤：

首先，我们通过Maven引入Spring对AOP的支持：

- org.springframework:spring-aspects:6.0.0

上述依赖会自动引入AspectJ，使用AspectJ实现AOP比较方便，因为它的定义比较简单。

然后，我们定义一个`LoggingAspect`：

```Java
@Aspect
@Component
public class LoggingAspect {
    // 声明切入点
    @Pointcut("execution(public * com.SpringMVC.service.MailService.*(..))")
    public void loggingPointcut(){}
    
    // 在执行UserService的每个方法前执行:
    @Before("execution(public * com.SpringMVC.service.UserService.*(..))")
    public void doAccessCheck() {
        System.err.println("[Before] do access check...");
    }

    // 使用@Around注解标明环绕通知方法
    @Around("loggingPointcut()")// 通过在通知方法形参位置声明ProceedingJoinPoint类型的形参，Spring会将这个类型的对象传给我们
    public Object manageTransaction(ProceedingJoinPoint joinPoint) {
        // 获取外界调用目标方法时传入的实参数组
        Object[] args = joinPoint.getArgs();
        // 获取目标方法的签名对象
        Signature signature = joinPoint.getSignature();
        // 获取目标方法的方法名
        String methodName = signature.getName();
        // 声明变量用来存储目标方法的返回值
        Object targetMethodReturnValue = null;
        try {
            // 在目标方法执行前
            System.out.println("[环绕通知 Before] 方法名：" + methodName + "，参数列表：" + "Arrays.asList(args)");
            // 通过ProceedingJoinPoint对象调用目标方法，目标方法的返回值一定要返回给外界调用者
            targetMethodReturnValue = joinPoint.proceed(args);
            // 在目标方法成功返回后
            System.out.println("[环绕通知 AfterReturning] 方法名：" + methodName + "，方法返回值：" + targetMethodReturnValue);
        } catch (Throwable e) { // 在目标方法抛异常后
            System.out.println("[环绕通知 AfterThrowing] 方法名：" + methodName + "，异常：" + e.getClass().getName());
        } finally { // 在目标方法最终结束后
            System.out.println("[环绕通知 After] 方法名：" + methodName);
        }
        return targetMethodReturnValue;
    }
}
```

观察`doAccessCheck()`方法，我们定义了一个`@Before`注解，后面的字符串是告诉AspectJ应该在何处执行该方法，这里写的意思是：执行`UserService`的每个`public`方法前执行`doAccessCheck()`代码。

再观察`doLogging()`方法，我们定义了一个`@Around`注解，它和`@Before`不同，`@Around`可以决定是否执行目标方法，因此，我们在`doLogging()`内部先打印日志，再调用方法，最后打印日志后返回结果。

在`LoggingAspect`类的声明处，除了用`@Component`表示它本身也是一个Bean外，我们再加上`@Aspect`注解，表示它的`@Before`标注的方法需要注入到`UserService`的每个`public`方法执行前，`@Around`标注的方法需要注入到`MailService`的每个`public`方法执行前后。

紧接着，我们需要给`@Configuration`类加上一个`@EnableAspectJAutoProxy`注解：

```Java
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {
    ...
}
```

Spring的IoC容器看到这个注解，就会自动查找带有`@Aspect`的Bean，然后根据每个方法的`@Before`、`@Around`等注解把AOP注入到特定的Bean中。

有些同学会问，`LoggingAspect`定义的方法，是如何注入到其他Bean的呢？

其实AOP的原理非常简单。我们以`LoggingAspect.doAccessCheck()`为例，要把它注入到`UserService`的每个`public`方法中，最简单的方法是编写一个子类，并持有原始实例的引用：

```Java
public UserServiceAopProxy extends UserService {
    private UserService target;
    private LoggingAspect aspect;

    public UserServiceAopProxy(UserService target, LoggingAspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    public User login(String email, String password) {
        // 先执行Aspect的代码:
        aspect.doAccessCheck();
        // 再执行UserService的逻辑:return target.login(email, password);
    }

    public User register(String email, String password, String name) {
        aspect.doAccessCheck();
        return target.register(email, password, name);
    }

    ...
}
```

这些都是Spring容器启动时为我们自动创建的注入了Aspect的子类，它取代了原始的`UserService`（原始的`UserService`实例作为内部变量隐藏在`UserServiceAopProxy`中）。如果我们打印从Spring容器获取的`UserService`实例类型，它类似`UserService$$EnhancerBySpringCGLIB$$1f44e01c`，实际上是Spring使用CGLIB动态创建的子类，但对于调用方来说，感觉不到任何区别。

> **注意：**Spring对接口类型使用JDK动态代理，对普通类使用CGLIB创建子类。如果一个Bean的class是final，Spring将无法为其创建子类。

可见，虽然Spring容器内部实现AOP的逻辑比较复杂（需要使用AspectJ解析注解，并通过CGLIB实现代理类），但我们使用AOP非常简单，一共需要三步：

1. 定义执行方法，并在方法上通过AspectJ的注解告诉Spring应该在何处调用此方法；
2. 标记`@Component`和`@Aspect`；
3. 在`@Configuration`类上标注`@EnableAspectJAutoProxy`。

至于AspectJ的注入语法则比较复杂，请参考[Spring文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pointcuts-examples)。

Spring也提供其他方法来装配AOP，但都没有使用AspectJ注解的方式来得简洁明了，所以我们不再作介绍。



#### 1.3.3.3 注解

使用AspectJ的注解，并配合一个复杂的`execution(* xxx.Xyz.*(..))`语法来定义应该如何装配AOP，在实际项目中，这种写法其实很少使用。假设你写了一个`SecurityAspect`：

```Java
@Aspect
@Component
public class SecurityAspect {
    @Before("execution(public * com.itranswarp.learnjava.service.*.*(..))")
    public void check() {
        if (SecurityContext.getCurrentUser() == null) {
            throw new RuntimeException("check failed");
        }
    }
}
```

基本能实现**无差别全覆盖**，即某个包下面的所有Bean的所有方法都会被这个`check()`方法拦截。

我们在使用AOP时，要注意到虽然Spring容器可以把指定的方法通过AOP规则装配到指定的Bean的指定方法前后，但是，如果自动装配时，因为不恰当的范围，容易导致意想不到的结果，即很多不需要AOP代理的Bean也被自动代理了，并且，后续新增的Bean，如果不清楚现有的AOP装配规则，容易被强迫装配。

使用AOP时，被装配的Bean最好自己能清清楚楚地知道自己被安排了。例如，Spring提供的`@Transactional`就是一个非常好的例子。如果我们自己写的Bean希望在一个数据库事务中被调用，就标注上`@Transactional`：

```Java
@Component
public class UserService {
    // 有事务:
    @Transactional
    public User createUser(String name) {
        ...
    }

    // 无事务:
    public boolean isValidName(String name) {
        ...
    }

    // 有事务:
    @Transactional
    public void updateUser(User user) {
        ...
    }
}
```

或者直接在class级别注解，表示“所有public方法都被安排了”：

```Java
@Component
@Transactional
public class UserService {
    ...
}
```

通过`@Transactional`，某个方法是否启用了事务就一清二楚了。因此，装配AOP的时候，使用注解是最好的方式。

我们以一个实际例子演示如何使用注解实现AOP装配。为了监控应用程序的性能，我们定义一个性能监控的注解：

```Java
@Target(METHOD)
@Retention(RUNTIME)
public @interface MetricTime {
    String value();
}
```

在需要被监控的关键方法上标注该注解：

```Java
@Component
public class UserService {
    // 监控register()方法性能:
    @MetricTime("register")
    public User register(String email, String password, String name) {
        ...
    }
    ...
}
```

然后，我们定义`MetricAspect`：

```Java
@Aspect
@Component
public class MetricAspect {
    @Around("@annotation(metricTime)")
    public Object metric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
        String name = metricTime.value();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long t = System.currentTimeMillis() - start;
            // 写入日志或发送至JMX:
            System.err.println("[Metrics] " + name + ": " + t + "ms");
        }
    }
}
```

注意`metric()`方法标注了`@Around("@annotation(metricTime)")`，它的意思是，符合条件的目标方法是带有`@MetricTime`注解的方法，因为`metric()`方法参数类型是`MetricTime`（注意参数名是`metricTime`不是`MetricTime`），我们通过它获取性能监控的名称。

有了`@MetricTime`注解，再配合`MetricAspect`，任何Bean，只要方法标注了`@MetricTime`注解，就可以自动实现性能监控。运行代码，输出结果如下：

```Plain
Welcome, Bob!
[Metrics] register: 16ms
```



## 1.3 事务

有关事务的知识很重要，不过由于时间的限制，大家课后自行了解吧。



# 2、Spring MVC

大家在第6次授课中，学习了JavaWeb相关的知识。我们知道JavaEE为Web开发提供了Servlet、Filter、Listener、JSP规范，其中Servlet用于实现各种复杂的业务逻辑，JSP负责实现数据的呈现。混合开发模式时，如果我们将所有的业务逻辑、数据处理和页面展示都放在 Servlet 中会导致代码变得非常混乱和难以维护。这时候，MVC（Model - View - Controller）架构模式就应运而生了。不过现在都是前后端分离模式了（后端主要编写REST风格的API向前端返回数据，前端渲染后再返回给客户端），因此我们不必太关注MVC中的View层。



## 2.0 软件架构



### 2.0.1 MVC

一种软件设计模式，将应用程序分为数据模型、视图和控制器三个部分，提高了应用程序的可维护性和可扩展性。

- Model（模型层）：主要负责处理业务逻辑和数据存储
  - 它包含了业务对象（如学生类Student）和业务逻辑（如学生信息的增删改查操作）
  - 可以把模型层看作是应用程序的 “大脑”，它知道如何处理数据以及业务规则。
- View（视图层）：主要负责向用户展示数据，通常是通过 HTML、JSP（Java Server Pages）等页面技术实现
  - 它只关注数据的呈现，不涉及业务逻辑
  - 视图层就像是应用程序的 “脸”，用户通过它来看到系统中的信息。
- Controller（控制器层）：作为模型层和视图层之间的桥梁，它接收用户的请求，根据请求调用相应的模型层方法进行业务处理，然后将处理结果传递给视图层进行展示
  - 可以把控制器层看作是应用程序的 “调度员”。



### 2.0.2 **三层架构**

一种软件架构模式，将应用程序分为三个主要的层次：表述层、业务逻辑层和数据访问层。各层之间采用**接口**相互访问，并通过对象模型的实体类（model）作为数据传递的载体，不同的对象模型实体类一般对应数据库的不同表。

- 各层介绍
  - **表述层**（视图层View）
    - 作为用户与系统交互的接口，负责接收用户输入并展示系统输出。它关注的是用户体验，包括界面的布局、样式以及交互性。
    - 将用户的操作请求转换为业务逻辑层能够理解的形式，并将业务逻辑层返回的结果以合适的方式呈现给用户。
  - **业务逻辑层**（服务层Service）
    - 包含了应用程序的核心业务规则和业务流程。它处理表示层传来的请求，根据业务规则进行数据处理和逻辑判断。
    - 协调和控制数据访问层与表示层之间的数据流动，确保数据在系统中的处理符合业务要求。
  - **数据访问层**（持久层Dao）
    - 负责与数据库或其他数据存储系统进行交互。它主要执行数据的读取、写入、更新和删除操作。
    - 对数据存储进行抽象，使得上层业务逻辑层不需要关心具体的数据存储方式（如数据库类型、文件系统结构等）。
    - 提供数据持久化的功能，确保数据在系统关闭或出现故障后不会丢失。

> 实体类库是数据库表的映射对象，在开发过程中，要建立对象实例，将关系数据库表采用对象实体化的方式表现出来，利用 GET 与 SET 把数据库表中的所有字段映射为系统对象，建立实体类库，进而实现各个结构层的参数传输，服务于其他三层。

- 各层之间的交互关系
  - 表述层与业务逻辑层：表述层向业务逻辑层发送用户操作请求，业务逻辑层接收请求后进行处理，然后将处理结果返回给表示层。
  - 业务逻辑层与数据访问层：业务逻辑层根据业务需求调用数据访问层的方法来获取或更新数据。

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTQxOGM3YjMzM2ZhNjM5MGQ3NjQ2ZTU0YWQzZTY3N2JfVjZBMGV5Y3hMWno4YTZGNHhpa0hSWFZVUFVBWWQ1YkJfVG9rZW46VXAxd2J3RHdNb1ZDYjh4Zmp1amNMcGJkbmhnXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)



## 2.1 简介

我们要讲的Spring MVC是控制器层的框架，其为控制器层搭了一个架子，使我们专注于业务的实现，具体作用如下

1. 简化前端参数接收( 形参列表 )
2. 简化后端数据响应(返回值)
3. 以及其他......

在控制层框架历经Strust、WebWork、Strust2等诸多产品的历代更迭之后，目前业界普遍选择了SpringMVC作为Java EE项目表述层开发的**首选方案**。之所以能做到这一点，是因为SpringMVC具备如下显著优势：

- **Spring 家族原生产品**，与IOC容器等基础设施无缝对接
- 表述层各细分领域需要解决的问题**全方位覆盖**，提供**全面解决方案**
- **代码清新简洁**，大幅度提升开发效率
- 内部组件化程度高，可插拔式组件**即插即用**，想要什么功能配置相应组件即可
- **性能卓著**，尤其适合现代大型、超大型互联网项目要求



### 2.1.1 原理

我们选择SpringMVC的其中一个原因是其与IOC容器等基础设施无缝对接，所以Controller会在Spring IoC容器中被初始化。但Servlet容器由JavaEE服务器提供（如Tomcat），Servlet容器对Spring一无所知，他们之间到底依靠什么进行联系，又是以何种顺序初始化的？

答案是Spring MVC提供了一个DispatcherServlet作为其中的桥梁，其在web.xml中的配置如下

```XML
<?xml version="1.0"?>
<web-app>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.SpringMVC.AppConfig</param-value>
        </init-param>
        <load-on-startup>0</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
```

初始化参数`contextClass`指定使用注解配置的`AnnotationConfigWebApplicationContext`，配置文件的位置参数`contextConfigLocation`指向`AppConfig`的完整类名，最后，把这个Servlet映射到`/*`，即处理所有URL。

上述配置可以看作一个样板配置，有了这个配置，Servlet容器会首先初始化Spring MVC的`DispatcherServlet`，在`DispatcherServlet`启动时，它根据配置`AppConfig`创建了一个类型是`WebApplicationContext`的IoC容器，完成所有Bean的初始化，并将容器绑到`ServletContext`上。

因为`DispatcherServlet`持有IoC容器，能从IoC容器中获取所有`@Controller`的Bean，因此，`DispatcherServlet`接收到所有HTTP请求后，根据Controller方法配置的路径，就可以正确地把请求转发到指定方法，并根据返回的`ModelAndView`决定如何渲染页面。



### 2.1.2 核心组件

Spring MVC与许多其他Web框架一样，是围绕**前端控制器模式**设计的，其中中央 `Servlet`  `DispatcherServlet` 做整体请求处理调度！

除了`DispatcherServlet`SpringMVC还会提供其他特殊的组件协作完成请求处理和响应呈现。

**处理请求流程**如下图：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NTEzYmEzNzU2NjllNzJkOGE3MDBkNzFkNjMyZTllN2JfdFFKYkRFbXNqaURaTnVydHVPV1Bza0dDZ0N0SVFaWW9fVG9rZW46SU1Dd2JwWHZVb3czbWh4Z0R6YWNuRWNQbjBnXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

**涉及组件**

1. DispatcherServlet :  SpringMVC提供，我们需要使用web.xml配置使其生效，它是整个流程处理的核心，所有请求都经过它的处理和分发！[ CEO ]
2. HandlerMapping :  SpringMVC提供，我们需要进行IoC配置使其加入IoC容器方可生效，它内部缓存handler(controller方法)和handler访问路径数据，被DispatcherServlet调用，用于查找路径对应的handler！[秘书]
3. HandlerAdapter : SpringMVC提供，我们需要进行IoC配置使其加入IoC容器方可生效，它可以处理请求参数和处理响应数据数据，每次DispatcherServlet都是通过handlerAdapter间接调用handler，他是handler和DispatcherServlet之间的适配器！[经理]
4. Handler : handler又称处理器，他是Controller类内部的方法简称，是由我们自己定义，用来接收参数，向后调用业务，最终返回响应结果！[打工人]
5. ViewResovler : SpringMVC提供，我们需要进行IoC配置使其加入IoC容器方可生效！视图解析器主要作用简化模版视图页面查找的，但是需要注意，前后端分离项目，后端只返回JSON数据，不返回页面，那就不需要视图解析器！所以，视图解析器，相对其他的组件不是必须的！[财务]



## 2.2 使用



### 2.2.1 快速体验

1. 配置分析

   1. DispatcherServlet：设置处理所有请求
   2. HandlerMapping,HandlerAdapter,Handler：需要加入到IoC容器，供DS调用
   3. Handler自己声明（Controller）需要配置到HandlerMapping中供DS查找

2. 准备项目：SpringMvc-demo

   ![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=YjVhN2NmOWJlZjlmZWNlZDAyNDAxNjIwNmFlNzc2MWRfRE1yd0s1bUdYcEM3Y29JSkp2WU9EOXV4T3F6ajVZTWxfVG9rZW46Q0dhUmJuc21VbzdkSUN4MHdGeGNXbGRKbk9lXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

3. 导入依赖

```XML
<properties>
    <spring.version>6.0.6</spring.version>
    <servlet.api>9.1.0</servlet.api>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<dependencies>
    <!-- springioc相关依赖  -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- web相关依赖  -->
    <!-- 在 pom.xml 中引入 Jakarta EE Web API 的依赖 -->
    <!--
        在 Spring Web MVC 6 中，Servlet API 迁移到了 Jakarta EE API，因此在配置 DispatcherServlet 时需要使用
         Jakarta EE 提供的相应类库和命名空间。错误信息 “‘org.springframework.web.servlet.DispatcherServlet’
         is not assignable to ‘javax.servlet.Servlet,jakarta.servlet.Servlet’” 表明你使用了旧版本的
         Servlet API，没有更新到 Jakarta EE 规范。
    -->
    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-web-api</artifactId>
        <version>${servlet.api}</version>
        <scope>provided</scope>
    </dependency>

    <!-- springwebmvc相关依赖  -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>

</dependencies>
```

4. Controller声明

```Java
// 使用@Controller标记而不是@Component
@Controller
public class HelloController {
    // handler就是controller内部的具体方法
    // 用来向handlerMapping中注册的方法注解
    @RequestMapping(value = "/hello")
    @ResponseBody // 代表向浏览器直接返回数据
    public String hello(){
        System.out.println("HelloController.hello");
        return "hello springmvc!!";
    }
}
```

5. Spring MVC核心组件配置类：声明springmvc涉及组件信息的配置类

```Java
@Configuration // 标注为配置类
@EnableWebMvc
@ComponentScan(basePackages = "com.SpringMVC.controller")
// WebMvcConfigurer：springMvc进行组件配置的规范,配置组件,提供各种方法! 前期可以实现
public class SpringMvcConfig implements WebMvcConfigurer {

    // 可以不添加,springmvc会检查是否配置handlerMapping和handlerAdapter,没有配置默认加载
    @Bean
    public HandlerMapping handlerMapping(){
        return new RequestMappingHandlerMapping();
    }
    @Bean
    public HandlerAdapter handlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }
    
}
```

6. SpringMVC环境搭建

​	对于使用基于 Java 的 Spring 配置的应用程序，建议这样做，如以下示例所示：

```Java
// SpringMVC提供的接口,是替代web.xml的方案,更方便实现完全注解方式ssm处理!
// Springmvc框架会自动检查当前类的实现类,会自动加载 getRootConfigClasses / getServletConfigClasses 提供的配置类
// getServletMappings 返回的地址 设置DispatherServlet对应处理的地址
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定service/mapper层的配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * 指定springmvc的配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMvcConfig.class};
    }

    /**
     * 设置dispatcherServlet的处理路径，替代xml配置
     * 一般情况下为 / 代表处理所有请求!
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

7. 启动测试
   * 注意： tomcat应该是10+版本！方可支持 Jakarta EE API!

下面给出大致的Tomcat运行配置

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MTlmNDM5OTFhZjdhMGJlYTczYWVkNzdmZWNlYWI0NTBfWlZlV2hETHU5MEM0bnFlYWlzbEE5bTBub3FrYlZyaEpfVG9rZW46VVRWT2JIZHN6b0xBMzN4akdMMmNjenl5bkliXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmIzM2JlMDc1MDk0MzBkYjFjM2MxMjYwMTdmMzZiYjlfQUpDVGZlUlFMSGRmNWJTdWJQek9mdldOMHNhWDZlQUxfVG9rZW46RVQweWI0eGJWb3lrN0J4YURTMGNMSG1Dbm9nXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=YTAwYmNkNzNjY2MwYzFlZDA2OGQzMjk5MjQwNWVlNDdfaWtaY0xEQ00zM2k5ekJhelhmNDNCa1U1U2l1cWRrMUtfVG9rZW46WmRrV2J2RVV5b0pMM2x4ZDBsOWNtSnhTbk5oXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)



### 2.2.2 访问路径设置

@RequestMapping注解的作用就是将请求的 URL 地址和处理请求的方式（handler方法）关联起来，建立映射关系。SpringMVC 接收到指定的请求，就会来找到在映射关系中对应的方法来处理这个请求。



#### 2.2.2.1 **精准路径匹配**

在@RequestMapping注解指定 URL 地址时，不使用任何通配符，按照请求地址进行精确匹配。

```Java
@Controller
public class UserController {

    /**
     * 精准设置访问地址 /user/login
     */
    @RequestMapping(value = {"/user/login"})
    @ResponseBody
    public String login(){
        System.out.println("UserController.login");
        return "login success!!";
    }

    /**
     * 精准设置访问地址 /user/register
     */
    @RequestMapping(value = {"/user/register"})
    @ResponseBody
    public String register(){
        System.out.println("UserController.register");
        return "register success!!";
    }
    
}
```



#### **2.2.2.2 模糊路径匹配**

在@RequestMapping注解指定 URL 地址时，通过使用通配符，匹配多个类似的地址。

```Java
@Controller
public class ProductController {

    /**
     *  路径设置为 /product/*  
     *    /* 为单层任意字符串  /product/a  /product/aaa 可以访问此handler  
     *    /product/a/a 不可以
     *  路径设置为 /product/** 
     *   /** 为任意层任意字符串  /product/a  /product/aaa 可以访问此handler  
     *   /product/a/a 也可以访问
     */
    @RequestMapping("/product/*")
    @ResponseBody
    public String show(){
        System.out.println("ProductController.show");
        return "product show!";
    }
}
```

- 单层匹配(/*)：只能匹配URL地址中的一层，如果想准确匹配两层，那么就写“/*/*”以此类推。
- 多层匹配(/**)：可以匹配URL地址中的多层。

> 其中所谓的一层或多层是指一个URL地址字符串被“/”划分出来的各个层次
>
> 这个知识点虽然对于@RequestMapping注解来说实用性不大，但是将来配置拦截器的时候也遵循这个规则。



#### **2.2.2.3 级别**

`@RequestMapping` 注解可以用于类级别和方法级别，它们之间的区别如下：

- 设置到类级别：`@RequestMapping` 注解可以设置在控制器类上，用于映射整个控制器的通用请求路径。这样，如果控制器中的多个方法都需要映射同一请求路径，就不需要在每个方法上都添加映射路径。
- 设置到方法级别：`@RequestMapping` 注解也可以单独设置在控制器方法上，用于更细粒度地映射请求路径和处理方法。当多个方法处理同一个路径的不同操作时，可以使用方法级别的 `@RequestMapping` 注解进行更精细的映射。

```Java
//1.标记到handler方法
@RequestMapping("/user/login")
@RequestMapping("/user/register")
@RequestMapping("/user/logout")

//2.优化标记类+handler方法
//类上
@RequestMapping("/user")
//handler方法上
@RequestMapping("/login")
@RequestMapping("/register")
@RequestMapping("/logout")
```



#### **2.2.2.4 附带请求方式限制**

HTTP 协议定义了八种请求方式，在 SpringMVC 中封装到了下面这个枚举类：

```Java
public enum RequestMethod {
  GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
}
```

默认情况下：@RequestMapping("/logout") 任何请求方式都可以访问！

如果需要特定指定：

```Java
@Controller
public class UserController {

    /**
     * method = RequestMethod.POST 可以指定单个或者多个请求方式!
     * 注意:违背请求方式会出现405异常!
     */
    @ResponseBody
    @RequestMapping(value = {"/user/login"} , method = RequestMethod.POST)
    public String login(){
        System.out.println("UserController.login");
        return "login success!!";
    }

    @ResponseBody
    @RequestMapping(value = {"/user/register"},method = {RequestMethod.POST,RequestMethod.GET})
    public String register(){
        System.out.println("UserController.register");
        return "register success!!";
    }

}
```

**进阶注解**

还有 `@RequestMapping` 的 HTTP 方法特定快捷方式变体

- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`
- `@PatchMapping`

```Java
@RequestMapping(value="/login",method=RequestMethod.GET)
@GetMapping(value="/login")
```

​    注意：进阶注解只能添加到handler方法上，无法添加到类上！



#### 2.2.2.5 **常见配置问题**

​    出现原因：多个 handler 方法映射了同一个地址，导致 SpringMVC 在接收到这个地址的请求时该找哪个 handler 方法处理。

> There is already 'demo03MappingMethodHandler' bean method com.atguigu.mvc.handler.Demo03MappingMethodHandler#empGet() **mapped**.



### 2.2.3 接收数据



#### 2.2.3.1 param和json参数

在 HTTP 请求中，我们可以选择不同的参数类型，如 param 类型和 JSON 类型。下面对这两种参数类型进行区别和对比：

1. 参数编码：  
   1. param 类型的参数会被编码为 ASCII 码
   2. JSON 类型的参数会被编码为 UTF-8
2. 参数顺序：  
   1. param 类型的参数没有顺序限制
   2. JSON 类型的参数是有序的。JSON 采用键值对的形式进行传递，其中键值对是有序排列的
3. 数据类型：  
   1. param 类型的参数仅支持字符串类型、数值类型和布尔类型等简单数据类型
   2. JSON 类型的参数则支持更复杂的数据类型，如数组、对象等
4. 嵌套性:
   1. param 类型的参数不支持嵌套
   2. JSON 类型的参数支持嵌套，可以传递更为复杂的数据结构
5. 可读性：  
   1. param 类型的参数格式比 JSON 类型的参数更加简单、易读
   2. JSON 格式在传递嵌套数据结构时更加清晰易懂。

总的来说，param 类型的参数适用于单一的数据传递，而 JSON 类型的参数则更适用于更复杂的数据结构传递。根据具体的业务需求，需要选择合适的参数类型。在实际开发中，常见的做法是：在 GET 请求中采用 param 类型的参数，而在 POST 请求中采用 JSON 类型的参数传递。



#### 2.2.3.2 param参数接收



##### **2.2.3.2.1 直接接值**

- handler接收参数：只要形参数名和类型与传递参数相同，即可自动接收!

```Java
@Controller
@RequestMapping("param")
public class ParamController {

    /**
     * 前端请求: http://localhost:8080/param/value?name=xx&age=18
     *
     * 可以利用形参列表,直接接收前端传递的param参数!
     *    要求: 参数名 = 形参名
     *          类型相同
     * 出现乱码正常，json接收具体解决！！
     * @return 返回前端数据
     */
    @GetMapping("/value")
    @ResponseBody
    public String setupForm(String name,int age){
        System.out.println("name = " + name + ", age = " + age);
        return name + age;
    }
}
```



##### **2.2.3.2.2 @RequestParam**

- 可以使用 `@RequestParam` 注释将 Servlet 请求参数（即查询参数或表单数据）绑定到控制器中的方法参数
- 使用场景：
  - 指定绑定的请求参数名
  - 要求请求参数必须传递
  - 为请求参数提供默认值

​    基本用法：

```Java
 /**
 * 前端请求: http://localhost:8080/param/data?name=xx&stuAge=18
 * 
 *  使用@RequestParam注解标记handler方法的形参
 *  指定形参对应的请求参数@RequestParam(请求参数名称)
 */
@GetMapping(value="/data")
@ResponseBody
public Object paramForm(@RequestParam("name") String name, 
                        @RequestParam("stuAge") int age){
    System.out.println("name = " + name + ", age = " + age);
    return name+age;
}
```

> 默认情况下，使用此批注的方法参数是必需的，但您可以通过将 `@RequestParam` 批注的 `required` 标志设置为 `false`！如果没有没有设置非必须，也没有传递参数会出现400。将参数设置非必须，并且设置默认值：

```Java
@GetMapping(value="/data")
@ResponseBody
public Object paramForm(@RequestParam("name") String name, 
                        @RequestParam(value = "stuAge",required = false,defaultValue = "18") int age){
    System.out.println("name = " + name + ", age = " + age);
    return name+age;
}
```



##### **2.2.3.2.3 特殊场景接值**

- 一名多值

多选框，提交的数据的时候一个key对应多个值，我们可以使用集合进行接收！

```Java
  /**
   * 前端请求: http://localhost:8080/param/mul?hbs=吃&hbs=喝
   *
   *  一名多值,可以使用集合接收即可!但是需要使用@RequestParam注解指定
   */
  @GetMapping(value="/mul")
  @ResponseBody
  public Object mulForm(@RequestParam List<String> hbs){
      System.out.println("hbs = " + hbs);
      return hbs;
  }
```

- 实体接收

Spring MVC 是 Spring 框架提供的 Web 框架，它允许开发者使用实体对象来接收 HTTP 请求中的参数。通过这种方式，可以在方法内部直接使用对象的属性来访问请求参数，而不需要每个参数都写一遍。下面是一个使用实体对象接收参数的示例：

​      定义一个用于接收参数的实体类：

```Java
public class User {

  private String name;

  private int age = 18;

  // getter 和 setter 略
}
```

在控制器中，使用实体对象接收，示例代码如下：

```Java
@Controller
@RequestMapping("param")
public class ParamController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user) {
        // 在这里可以使用 user 对象的属性来接收请求参数
        System.out.println("user = " + user);
        return "success";
    }
}
```

> 在上述代码中，将请求参数name和age映射到实体类属性上！要求**属性名必须等于参数名**！否则无法映射！



#### 2.2.3.3 路径参数

路径传递参数是一种在 URL 路径中传递参数的方式。在 RESTful 的 Web 应用程序中，经常使用路径传递参数来表示资源的唯一标识符或更复杂的表示方式。而 Spring MVC 框架提供了 `@PathVariable` 注解来处理路径传递参数。

`@PathVariable` 注解允许将 URL 中的占位符映射到控制器方法中的参数。

例如，如果我们想将 `/user/{id}` 路径下的 `{id}` 映射到控制器方法的一个参数中，则可以使用 `@PathVariable` 注解来实现。

下面是一个使用 `@PathVariable` 注解处理路径传递参数的示例：

```Java
 /**
 * 动态路径设计: /user/{动态部分}/{动态部分}   动态部分使用{}包含即可! {}内部动态标识!
 * 形参列表取值: @PathVariable Long id  如果形参名 = {动态标识} 自动赋值!
 *              @PathVariable("动态标识") Long id  如果形参名 != {动态标识} 可以通过指定动态标识赋值!
 *
 * 访问测试:  /param/user/1/root  -> id = 1  uname = root
 */
@GetMapping("/user/{id}/{name}")
@ResponseBody
public String getUser(@PathVariable Long id, 
                      @PathVariable("name") String uname) {
    System.out.println("id = " + id + ", uname = " + uname);
    return "user_detail";
}
```



#### 2.2.3.4 json参数接收

前端传递 JSON 数据时，Spring MVC 框架可以使用 `@RequestBody` 注解来将 JSON 数据转换为 Java 对象。`@RequestBody` 注解表示当前方法参数的值应该从**请求体**中获取，并且需要指定 value 属性来指示请求体应该映射到哪个参数上。其使用方式和示例代码如下：

1. 前端发送 JSON 数据的示例：

```JSON
{
  "name": "张三",
  "age": 18,
  "gender": "男"
}
```

1. 定义一个用于接收 JSON 数据的 Java 类，例如：

```Java
public class Person {
  private String name;
  private int age;
  private String gender;
  // getter 和 setter 略
}
```

1. 在控制器中，使用 `@RequestBody` 注解来接收 JSON 数据，并将其转换为 Java 对象，例如：

```Java
@PostMapping("/person")
@ResponseBody
public String addPerson(@RequestBody Person person) {

  // 在这里可以使用 person 对象来操作 JSON 数据中包含的属性
  return "success";
}
```

​    在上述代码中，`@RequestBody` 注解将请求体中的 JSON 数据映射到 `Person` 类型的 `person` 参数上，并将其作为一个对象来传递给 `addPerson()` 方法进行处理。



#### 2.2.3.5 其他参数

还可以简化接收请求头参数，cookie参数等等，这里不做赘述，大家课下自行了解。



### 2.2.4 响应数据

#### 2.2.4.1 转发和重定向

在 Spring MVC 中，Handler 方法返回值可以实现快速转发，可以使用 `redirect` 或者 `forward` 关键字来实现重定向。

```Java
@RequestMapping("/redirect-demo")
public String redirectDemo() {
    // 重定向到 /demo 路径 
    return "redirect:/demo";
}

@RequestMapping("/forward-demo")
public String forwardDemo() {
    // 转发到 /demo 路径
    return "forward:/demo";
}

//注意： 转发和重定向到项目下资源路径都是相同，都不需要添加项目根路径！填写项目下路径即可！
```

总结：

- 将方法的返回值，设置String类型
- 转发使用forward关键字，重定向使用redirect关键字
- 关键字: /路径
- 注意：如果是项目下的资源，转发和重定向都一样都是项目下路径！都不需要添加项目根路径！



#### 2.2.4.2 返回Json数据



##### 2.2.4.2.1 前置准备

1. 导入jackson依赖

```XML
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>
</dependency>
```

1. 添加json数据转化器：@EnableWebMvc 

```Java
@Configuration
@EnableWebMvc  // json数据处理,必须使用此注解,因为他会加入json处理器
@ComponentScan(basePackages = "com.atguigu.controller") //TODO: 进行controller扫描
//WebMvcConfigurer springMvc进行组件配置的规范,配置组件,提供各种方法! 前期可以实现
public class SpringMvcConfig implements WebMvcConfigurer {

}
```



##### 2.2.4.2.2 @ResponseBody

1. 方法上使用@ResponseBody

​    可以在方法上使用 `@ResponseBody`注解，用于将方法返回的对象序列化为 JSON 或 XML 格式的数据，并发送给客户端。在前后端分离的项目中使用！

​    测试方法：

```Java
@GetMapping("/accounts/{id}")
@ResponseBody
public Object handle() {
  // ...
  return obj;
}
```

具体来说，`@ResponseBody` 注解可以用来标识方法或者方法返回值，表示方法的返回值是要直接返回给客户端的数据，而不是由视图解析器来解析并渲染生成响应体（viewResolver没用）

​    测试方法：

```Java
@RequestMapping(value = "/user/detail", method = RequestMethod.POST)
@ResponseBody
public User getUser(@RequestBody User userParam) {
    System.out.println("userParam = " + userParam);
    User user = new User();
    user.setAge(18);
    user.setName("John");
    // 返回的对象,会使用jackson的序列化工具,转成json返回给前端!
    return user;
}
```

1. 类上使用@ResponseBody

​    如果类中每个方法上都标记了 @ResponseBody 注解，那么这些注解就可以提取到类上。

```Java
@Controller
@RequestMapping("param")
@ResponseBody  //responseBody可以添加到类上,代表默认类中的所有方法都生效!
public class ParamController {
}
```



##### 2.2.4.2.3 @RestController

类上的 @ResponseBody 注解可以和 @Controller 注解合并为 @RestController 注解。所以使用了 @RestController 注解就相当于给类中的每个方法都加了 @ResponseBody 注解。

@RestController源码如下

```Java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface RestController {
 
  /**
   * The value may indicate a suggestion for a logical component name,
   * to be turned into a Spring bean in case of an autodetected component.
   * @return the suggested component name, if any (or empty String otherwise)
   * @since 4.0.1
   */
  @AliasFor(annotation = Controller.class)
  String value() default "";
 
}
```



## 2.3 RESTFul风格



### 2.3.1 简介

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=YWIyMmFlNGEyZmMzYjkzOTViMDI1MzE3ODk4Y2NhNzRfS0dSSTYyWXlqdkY3SldpRnZ5T0Mzam54TlZaMjBOWWFfVG9rZW46WThjaGIwdnBZb1JUNE14YjdjRGNORW00bmdQXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

RESTful（Representational State Transfer）是一种软件架构风格，用于设计网络应用程序和服务之间的通信。它是一种基于标准 HTTP 方法的简单和轻量级的通信协议，广泛应用于现代的Web服务开发。

通过遵循 RESTful 架构的设计原则，可以构建出易于理解、可扩展、松耦合和可重用的 Web 服务。RESTful API 的特点是简单、清晰，并且易于使用和理解，它们使用标准的 HTTP 方法和状态码进行通信，不需要额外的协议和中间件。

总而言之，RESTful 是一种基于 HTTP 和标准化的设计原则的软件架构风格，用于设计和实现可靠、可扩展和易于集成的 Web 服务和应用程序！



### 2.3.2 特点

1. 每一个URI代表1种资源
2. 客户端使用GET、POST、PUT、DELETE 4个表示操作方式的动词对服务端资源进行操作
   1. GET用来获取资源
   2. POST用来新建资源（也可以用于更新资源）
   3. PUT用来更新资源
   4. DELETE用来删除资源
3. 资源的表现形式是XML或者**JSON**
4. 客户端与服务端之间的交互在请求之间是无状态的，从客户端到服务端的每个请求都必须包含理解请求所必需的信息



### 2.3.3 设计规范

1. **HTTP协议请求方式要求**

​    REST 风格主张在项目设计、开发过程中，具体的操作符合**HTTP协议定义的请求方式的语义**

| 操作     | 请求方式 |
| -------- | -------- |
| 查询操作 | GET      |
| 保存操作 | POST     |
| 删除操作 | DELETE   |
| 更新操作 | PUT      |

2. **URI路径风格要求**

-  REST风格下每个资源都应该有一个唯一的标识符
- 资源的标识符应该能明确地说明该资源的信息，同时也应该是可被理解和解释的！
- 使用URL+请求方式确定具体的动作，他也是一种标准的HTTP协议请求！

| 操作 | 传统风格                | REST 风格                              |
| ---- | ----------------------- | -------------------------------------- |
| 保存 | /CRUD/saveEmp           | URI 地址：/CRUD/emp 请求方式：POST     |
| 删除 | /CRUD/removeEmp?empId=2 | URI 地址：/CRUD/emp/2 请求方式：DELETE |
| 更新 | /CRUD/updateEmp         | URI 地址：/CRUD/emp 请求方式：PUT      |
| 查询 | /CRUD/editEmp?empId=2   | URI 地址：/CRUD/emp/2 请求方式：GET    |

- 总结
  - 根据接口的具体动作，选择具体的HTTP协议请求方式
  - 路径设计从原来携带动标识，改成名词，对应资源的唯一标识即可！



## 2.4 其他扩展



### 2.4.1 全局异常处理器



#### 2.4.1.1 异常处理的两种方式

开发过程中是不可避免地会出现各种异常情况的，例如网络连接异常、数据格式异常、空指针异常等等。异常的出现可能导致程序的运行出现问题，甚至直接导致程序崩溃。因此，在开发过程中，合理处理异常、避免异常产生、以及对异常进行有效的调试是非常重要的。

对于异常的处理，一般分为两种方式：

- **编程式异常处理**：是指在代码中显式地编写处理异常的逻辑。它通常涉及到对异常类型的检测及其处理，例如使用 try-catch 块来捕获异常，然后在 catch 块中编写特定的处理代码，或者在 finally 块中执行一些清理操作。在编程式异常处理中，开发人员需要显式地进行异常处理，异常处理代码混杂在业务代码中，导致代码可读性较差。
- **声明式异常处理**：则是将异常处理的逻辑从具体的业务逻辑中分离出来，通过配置等方式进行统一的管理和处理。在声明式异常处理中，开发人员只需要为方法或类标注相应的注解（如 `@Throws` 或 `@ExceptionHandler`），就可以处理特定类型的异常。相较于编程式异常处理，声明式异常处理可以使代码更加简洁、易于维护和扩展。

站在宏观角度来看待声明式事务处理：

  整个项目从架构这个层面设计的异常处理的统一机制和规范。

  一个项目中会包含很多个模块，各个模块需要分工完成。如果张三负责的模块按照 A 方案处理异常，李四负责的模块按照 B 方案处理异常……各个模块处理异常的思路、代码、命名细节都不一样，那么就会让整个项目非常混乱。

  使用声明式异常处理，可以统一项目处理异常思路，项目更加清晰明了！



#### 2.4.1.2 使用

1. 声明异常处理控制器类

异常处理控制类，统一定义异常处理handler方法！

```Java
/**
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * @ControllerAdvice 代表当前类的异常处理controller! 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

}
```

2. 声明异常处理hander方法

异常处理handler方法和普通的handler方法参数接收和响应都一致！

只不过异常处理handler方法要映射异常，发生对应的异常会调用！

普通的handler方法要使用@RequestMapping注解映射路径，发生对应的路径调用！

```Java
/**
 * 异常处理handler 
 * @ExceptionHandler(HttpMessageNotReadableException.class)
 * 该注解标记异常处理Handler,并且指定发生异常调用该方法!
 */
@ExceptionHandler(HttpMessageNotReadableException.class)
public Object handlerJsonDateException(HttpMessageNotReadableException e){
    return null;
}

/**
 * 当发生空指针异常会触发此方法!
 */
@ExceptionHandler(NullPointerException.class)
public Object handlerNullException(NullPointerException e){
    return null;
}

/**
 * 所有异常都会触发此方法!但是如果有具体的异常处理Handler! 
 * 具体异常处理Handler优先级更高!
 * 例如: 发生NullPointerException异常!会触发handlerNullException方法,不会触发handlerException方法!
 */
@ExceptionHandler(Exception.class)
public Object handlerException(Exception e){
    return null;
}
```

3. 配置文件扫描控制器类配置：确保异常处理控制类被扫描

```Java
@Configuration
@EnableWebMvc // json数据处理,必须使用此注解,因为他会加入json处理器
@ComponentScan(basePackages = {"com.lcx.controller","com.lcx.exception"})
public class SpringMvcConfig implements WebMvcConfigurer {
}
```



### 2.4.2 拦截器



#### 2.4.2.1 简介

Springmvc的拦截器 VS javaEE的过滤器：

- 相似点
  - 拦截：必须先把请求拦住，才能执行后续操作
  - 过滤：拦截器或过滤器存在的意义就是对请求进行统一处理
  - 放行：对请求执行了必要操作后，放请求过去，让它访问原本想要访问的资源
- 不同点
  - 工作平台不同
    - 过滤器工作在 Servlet 容器中
    - 拦截器工作在 SpringMVC 的基础上
  - 拦截的范围
    - 过滤器：能够拦截到的最大范围是整个 Web 应用
    - 拦截器：能够拦截到的最大范围是整个 SpringMVC 负责的请求
  - IOC 容器支持
    - 过滤器：想得到 IOC 容器需要调用专门的工具方法，是间接的
    - 拦截器：它自己就在 IOC 容器中，所以可以直接从 IOC 容器中装配组件，也就是可以直接得到 IOC 容器的支持

选择：功能需要如果用 SpringMVC 的拦截器能够实现，就不使用过滤器。



#### 2.4.2.2 使用



1. 创建拦截器类

```Java
public class ProcessInterceptor implements HandlerInterceptor {

    // 在处理请求的目标 handler 方法前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler);
        System.out.println("Process01Interceptor.preHandle");
        // 返回true：放行
        // 返回false：不放行
        return true;
    }
 
    // 在目标 handler方法之后，handler报错不执行!
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler + ", modelAndView = " + modelAndView);
        System.out.println("Process01Interceptor.postHandle");
    }
 
    // 渲染视图之后执行(最后),一定执行!
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler + ", ex = " + ex);
        System.out.println("Process01Interceptor.afterCompletion");
    }
    
}
```

​    拦截器方法拦截位置：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=Nzk0YTkyMDEyZDk0NjQ2ZDU0ODM3ZWM3YjE3NGJmYzRfNmYwNlpPZmxhelJuTDdqejNmaWVvcFJaa3JSeWFHNnJfVG9rZW46UERHNWJSZ0NtbzV0V0x4VndMb2NEbnBHbjZlXzE3MzM1NzcwMzg6MTczMzU4MDYzOF9WNA)

2. 修改配置类添加拦截器

```Java
@Configuration
@EnableWebMvc  //json数据处理,必须使用此注解,因为他会加入json处理器
@ComponentScan(basePackages = {"com.SpringMVC.controller","com.SpringMVC.exception"}) //TODO: 进行controller扫描
//WebMvcConfigurer springMvc进行组件配置的规范,配置组件,提供各种方法! 前期可以实现
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override // 添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        // 将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
        registry.addInterceptor(new ProcessInterceptor());
    }
}
```

1. 默认拦截全部

   ```Java
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new ProcessInterceptor());
   }
   ```

2. 精准配置

   ```Java
   @Override // 添加拦截器
   public void addInterceptors(InterceptorRegistry registry) {
       // 将拦截器添加到Springmvc环境
       // 精准匹配,设置拦截器处理指定请求 路径可以设置一个或者多个,为项目下路径即可
       // addPathPatterns("/common/request/one") 添加拦截路径
       // 也支持 /* 和 /** 模糊路径。 * 任意一层字符串 ** 任意层 任意字符串
       registry.addInterceptor(new ProcessInterceptor()).addPathPatterns("/common/request/one","/common/request/tow");
   }
   ```

3. 排除配置

   ```Java
   //添加拦截器
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       // 排除匹配,排除应该在匹配的范围内排除
       // addPathPatterns("/common/request/one") 添加拦截路径
       // excludePathPatterns("/common/request/tow"); 排除路径,排除应该在拦截的范围内
       registry.addInterceptor(new ProcessInterceptor())
               .addPathPatterns("/common/request/one","/common/request/tow")
               .excludePathPatterns("/common/request/tow");
   }
   ```



* 多个拦截器执行顺序

1. preHandle() 方法：SpringMVC 会把所有拦截器收集到一起，然后按照配置顺序调用各个 preHandle() 方法。
2. postHandle() 方法：SpringMVC 会把所有拦截器收集到一起，然后按照配置相反的顺序调用各个 postHandle() 方法。
3. afterCompletion() 方法：SpringMVC 会把所有拦截器收集到一起，然后按照配置相反的顺序调用各个 afterCompletion() 方法。



### 2.4.3 参数校验

在 Web 应用三层架构体系中，表述层负责接收浏览器提交的数据，业务逻辑层负责数据的处理。为了能够让业务逻辑层基于正确的数据进行处理，我们需要在表述层对数据进行检查，将错误的数据隔绝在业务逻辑层之外。

大家课下自行了解吧。

