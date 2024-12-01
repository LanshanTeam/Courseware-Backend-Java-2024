# MySQL、Redis

## 

讲到MySQL,它是一种**关系型数据库**（基于关系模型的数据库，它通过表格的形式存储和管理数据

可以自己私下去看一下**《数据库原理》**，讲的一些关系型数据库共有的东西



以下是我们了解关系型数据库的一些必知必会的新概念：

**数据库**（Database）可以理解是一种特殊格式的数据**文件**

- 数据以一种**特殊组织形式**的二进制格式存储在数据库中

**数据库管理系统**（Database Management System）是对数据库进行管理的一组**软件**系统

结构化查询语言（Struct Query Language，**SQL**），是一种允许我们在**关系型数据库系统**上查询和操作数据的语言

开发者通过**SQL -> 数据库管理系统 -> 操作系统 -> 数据库（文件）**

关键术语：

- 表：也称关系表，表是数据库中存储数据的组织形式

- 字段：即表中的列，也称为属性

- 记录：即表中的行，一行数据称为一条记录

- NULL值：null表示

  不存在

  的值，或

  暂时未知

  的值

  - null不是空字符串
  - null与其他值进行运算，结果总为null

- 键：

  - 超键：能唯一标识记录的属性集

  - 候选键：属于超键，是不含多余属性的超键，是最小的超键

  - 主键：属于候选键，能唯一标识记录的一个字段或者多个字段的组合

    - 主键是唯一的
    - **主键不能为nul**l

  - 唯一键：一个或多个字段的集合，唯一标识数据库表中的数据

    - 防止列中的复制值
    - 列中只能有一个null
    - 一个表中可以有多个唯一键

  - 外键：建立与其他表的联系，一个表中的某些字段是另外表的主键或唯一键，这些字段称为外键

    - 外键可以为null

    - 外键也可以是本表的原有字段

      

前面我们说到要用SQL去操作关系型数据库

## SQL：

详细操作可以看一下**《必知必会SQL》**

我们常听说的后端工程师是CRUD工程师，是跟数据打交道的

那么CRUD是什么呢？

> **CRUD即Create(新增)、Read(读取)、(Update)更新、（Delete)删除**

SQL语句用法很多，时间有限，我们今天只讲最常用的增删改查操作

1. 登录mysql

   ```
   mysql -uroot -p
   ```

   

2. 查看所有数据库

   ```
   show databases
   ```

   

3. 创建我们这节课的数据库lesson5

   ```
   create lesson5
   ```

   

4. 使用某个库

   ```
   use 库名
   ```

   

5. 查看库的所有表

   ```
   show tables from 数据库
   ```

   

6. 创建表student

   ```
   -- 创建一张学生表
   drop table if exist student;
   create table student (
      id int,
      sn int comment '学号',
      name varchar(20) comment '姓名',
      qq_mail varchar(20) comment 'QQ邮箱'
   );
    
   -- 单行数据+全列插入  插入两条记录，value_list 数量必须和定义表的列的数量及顺序一致
   insert into student values (100, 10000, '唐三藏', NULL);
   insert into student values (101, 10001, '孙悟空', '11111');
    
   -- 多行数据+指定列插入  插入两条记录，value_list 数量必须和指定列数量及顺序一致
   insert into student (id, sn, name) values
    (102, 20001, '曹孟德'),
    (103, 20002, '孙仲谋');
   ```

7. 查看表student

   ```
   SELECT * FROM student;
   ```

好了前戏做完了，我们来对这张表做CRUD：

1. **查询`student`表中的数据**（SELECT）:

   sql

   ```sql
   SELECT * FROM student;
   ```

   或者查询特定的列：

   sql

   ```sql
   SELECT id, name, qq_email FROM student;
   ```

2. **插入数据到`student`表中**（INSERT）:

   sql

   ```sql
   INSERT INTO student (id, name, qq_email) VALUES (1, 'John Doe', 53425234523@qq.com);
   ```

3. **更新`student`表中的数据**（UPDATE）:

   sql

   ```sql
   UPDATE student SET qq_email = 53425234523@qq.com WHERE id = 1;
   ```

4. **删除`student`表中的数据**（DELETE）:

   sql

   ```sql
   DELETE FROM student WHERE id 
   ```





## MySQL:

mysql 、redis这些都是工具，我们需要学会的是怎么玩它，玩的顺心

在我们日常项目开发中，会用到ORM框架去操作数据库

常见的ORM框架：Mybatis（最重要，最常用，大多公司里会采用的）、MybatisPlus（开发便捷，不宜维护，个人项目开发可用）

贴个链接，想看自己看 https://blog.csdn.net/2302_80742310/article/details/139234954?spm=1001.2014.3001.5502



但会用也不够，还要懂得其中的原理





















## Redis:
