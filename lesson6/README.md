# Java第六节课 Maven、javaWeb

学习某些技术，肯定是我们遇到了某些问题，而这些问题目前手头上没有很好的方案去解决，此时刚好有一种技术可以很好的解决这个问题，这样能够驱动我们愿意去学。所以我们学任何技术之前，需要先了解这种技术能够解决什么问题。带着问题去学习，大家才有兴趣，才能够更快的掌握。

# Maven

我们遇到了什么问题呢？maven能帮我们做什么呢？

## Maven介绍

### Maven出现之前遇到的问题

maven还未出世的时候，我们有很多痛苦的经历。

1. ***痛点1：jar包难以寻找***   

比如我们项目中需要用到fastjson，此时我们会去百度上检索fastjson相关jar包，然后下载下来，放到项目的lib下面，然后加到项目的classpath下面，用着用着发现这个jar的版本太老了，不是我们想要的，然后又重新去找，痛苦啊。

1. ***痛点2：jar包依赖的问题***   

jar包一般都不是独立存在的，一般一些jar也会用到其他的jar，比如spring-aop.jar会用到spring-core.jar，所以你用到一个jar的时候，你必须明确知道这些jar还会依赖于哪些jar，把他们都引入进来，否则项目是无法正常运行的，当项目用到很多jar的时候，我们是很难判断缺少哪些jar的。

1. ***痛点3：jar包版本冲突问题*** 

当我们从网上找到一个jar包来使用的时候，我们是很难判断这个jar依赖的其他jar的版本的，比如a.jar依赖于b.jar，你从网上把b.jar找到了，最后放入项目中，发现b.jar的版本太老了，又得去重新找。

1. ***痛点4：jar不方便管理***   

当我们的项目比较大的时候，我们会将一个大的项目分成很多小的项目，每个小项目由几个开发负责，比如一个电商项目分为：账户相关的项目、订单相关的项目、商品相关的项目，这些项目的结构都是类似的，用到的技术都是一样的：ssm（spring、springmvc、mybatis），然后每个项目都需要把这些jar拷贝一份到自己的项目目录中，最后10个项目只是jar就复制了10份，后来，我们发现项目中有些jar需要升级版本，打算替换一下，此时我们需要依次去替换10个项目，也是相当痛苦。

1. ***痛点5：项目结构五花八门***  

很久之前，我们使用eclipse搭建一个项目的时候，java源码的位置、资源文件的位置、测试文件的位置、静态资源位置、编译之后的class文件位置，都是可以随意放的，这些是由各自公司的架构师搭建项目时定好的，根据他们的经验来定义的，导致每个公司可能项目结构都不太一样。  

1. ***痛点6：项目的生命周期控制方式五花八门***  

一个项目对于开发来说，生命周期是这样的：搭建项目结构、编码、跑测试用例、编译、打包、发布到环境测试、发布到生产环境。其中除了编码之外，大多数时间都是在编译、打包、发布到测试环境，然后测试开始测试，测试提出bug，开发接着修改bug，之后又进行自测、编译、打包、发布到测试环境，多数时间都在重复着跑单元测试、编译、打包、发布的工作。在没有自动化编译的时候，每个过程都需要我们手动去操作，可能有些开发比较优秀，将这些操作写出自动化的脚本来进行了，但是每个人写的自动化的脚本可能都是不一样的，有些用java写，有些人用shell写等等。  

### Maven是什么呢  

来看一下官方解释什么是maven：

**maven是** **apache**软件基金会组织维护的一款自动化构建工具，专注服务于java平台的项目构建和依赖管理。  

maven就是解决上面所有痛点的神器，算是所有开发者的福音。

Maven就是是专门为Java项目打造的管理和构建工具，它的主要功能有：

- 提供了一套标准化的项目结构；
- 提供了一套标准化的构建流程（编译，测试，打包，发布……）；
- 提供了一套依赖管理机制。

### Maven项目结构

一个使用Maven管理的普通的Java项目，它的目录结构默认如下：  

```Plain
a-maven-project //项目文件
├── pom.xml  //maven的xml文件，后续会介绍到
├── src 
│   ├── main
│   │   ├── java //存放Java源码的目录
│   │   └── resources //存放资源文件的目录
│   └── test
│       ├── java //存放测试源码的目录
│       └── resources //存放测试资源的目录
└── target //所有编译、打包生成的文件
```

所有的目录结构都是约定好的标准结构，我们千万不要随意修改目录结构。使用标准结构不需要做任何配置，Maven就可以正常使用。

我们再来看最关键的一个项目描述文件`pom.xml`，它的内容长得像下面：

```XML
<project ...>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.itranswarp.learnjava</groupId>
        <artifactId>hello</artifactId>
        <version>1.0</version>
        <packaging>jar</packaging>
        <properties>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            <maven.compiler.release>17</maven.compiler.release>
        </properties>
        <dependencies>
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-simple</artifactId>
                <version>2.0.16</version>
            </dependency>
        </dependencies>
</project>
```

其中，`groupId`类似于Java的包名，通常是公司或组织名称，`artifactId`类似于Java的类名，通常是项目名称，再加上`version`，一个Maven工程就是由`groupId`，`artifactId`和`version`作为唯一标识。  

我们在引用其他第三方库的时候，也是通过这3个变量确定。例如，依赖`org.slfj4:slf4j-simple:2.0.16`：

```XML
<dependency>
     <groupId>org.slf4j</groupId>
     <artifactId>slf4j-simple</artifactId>
     <version>2.0.16</version>
</dependency>
```

使用`<dependency>`声明一个依赖后，Maven就会自动下载这个依赖包并把它放到classpath中。

另外，注意到`<properties>`定义了一些属性，常用的属性有：

- `project.build.sourceEncoding`：表示项目源码的字符编码，通常应设定为`UTF-8`；
- `maven.compiler.release`：表示使用的JDK版本，例如`21`；
- `maven.compiler.source`：表示Java编译器读取的源码版本；
- `maven.compiler.target`：表示Java编译器编译的Class版本。

从Java 9开始，推荐使用`maven.compiler.release`属性，保证编译时输入的源码和编译输出版本一致。如果源码和输出版本不同，则应该分别设置`maven.compiler.source`和`maven.compiler.target`。

通过`<properties>`定义的属性，就可以固定JDK版本，防止同一个项目的不同的开发者各自使用不同版本的JDK。

## Maven安装配置

### Maven安装

要安装Maven，可以从 [maven官网](https://maven.apache.org/download.cgi) 下载最新的Maven 3.9.x，然后在本地解压，设置几个环境变量：

```Plain
M2_HOME=/path/to/maven-3.9.x  //你的maven安装目录
Path=$%2_HOME%/bin       
```

Windows把  `M2_HOME=/path/to/maven-3.9.x` 添加到环境变量中 ，再把`%M2_HOME%\bin`添加到系统Path变量中。我使用的是 `MAVEN_HOME`作为maven安装目录的变量名，这个可以自己定义，没有强制要求，不过最好见名知义，方便后续的管理。

![image](https://github.com/user-attachments/assets/7424a6b5-bc75-4710-8070-65ea973fdcbe)


![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MmU0OGY2MDIzNTRhMTI1NzY2MzA4OTE4MmM5YTI3MzhfVDdzUDhtajU0a2htazNZZ29qT0c1REtmcGpjSGpYTDZfVG9rZW46QUZvUGJjcDQ2b3FLNW14M1hZU2NYczZGbmRiXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

然后，打开命令行窗口，输入`mvn -version`，应该看到Maven的版本信息：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NDczNzI2OWQ3YmE3ZjM4OTk2ZTI1MmY3MTlmOGM0ZmZfMEZNSlJ3aG5zM2k0UGdSVGo3REdRbE80UEhPOThSem1fVG9rZW46RkVzS2JyUGpTb2pvNDZ4RW1YU2M0QXZubmFlXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

到这里maven的安装就完成了。

> 如果使用了 IntelliJ IDEA ，可以不用去额外下载 Maven，直接使用 IDEA 中自带的 Maven 插件即可。IntelliJ IDEA 中自带的 Maven 插件在 `\ideaIU-2019.2.4.win\plugins\maven\lib\maven3`

### Maven配置

实际上，没有特殊需求的话，安装好之后直接就可以用了。一般来说，还是需要稍微配置一下，比如中央仓库的问题。默认使用 Maven 自己的中央仓库，使用起来网速比较慢，这个时候，可以通过修改配置文件，将仓库改成国内的镜像仓库，国内仓库使用较多的是阿里巴巴的仓库。

#### 仓库类型

| 仓库类型 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 本地仓库 | 就是你自己电脑上的仓库，每个人电脑上都有一个仓库，默认位置在 `当前用户名\.m2\repository` |
| 私服仓库 | 一般来说是公司内部搭建的 Maven 私服，处于局域网中，访问速度较快，这个仓库中存放的 jar 一般就是公司内部自己开发的 jar |
| 中央仓库 | 有 Apache 团队来维护，包含了大部分的 jar，早期不包含 Oracle 数据库驱动，从 2019 年 8 月开始，包含了 Oracle 驱动 |

现在存在 3 个仓库，那么 jar 包如何查找呢？

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=M2NiNDE5YzIzZWJmZDIyMDQyODlmYmUxYTFlMjE3MDRfWnlaM3YzdEVzVW1aN0g3UkY3dDlFZTVrb0pYckRzSEdfVG9rZW46Tzlyd2JKakZObzA4VWZ4czE1emN2VTIzbkdjXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

#### 本地仓库配置

本地仓库默认位置在 `当前用户名\.m2\repository`，这个位置可以自定义，但是不建议大家自定义这个地址，有几个原因：

1. 虽然所有的本地的 jar 都放在这个仓库中，但是并不会占用很大的空间。
2. 默认的位置比较隐蔽，不容易碰到

技术上来说，当然是可以自定义本地仓库位置的，在 conf/settings.xml 中自定义本地仓库位置：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MmMwNmUwNzRmMjVkMTRmOGE1NTI1YmYwZWU5ZjgyNjhfYXBtTHFRSVhSNURla2JNRURHRTBiQ0pwb3dCbVFZVnNfVG9rZW46SzJXZGJFcmV4b291YzF4aTdpTGNrNGNobndnXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

#### 远程镜像配置

由于默认的中央仓库下载较慢，因此，也可以将远程仓库地址改为阿里巴巴的仓库地址：

```XML
<mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

这段配置，加在 settings.xml 中的 mirrors 节点中：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MDUxNTUwNTc4MTUyMmMwNzBmN2ZjY2E4MzU0YWI4NTFfcXJMa1lpSUw2RTB2cFpnSk1JMFNqMVFRQlpqcXBtbTVfVG9rZW46Q0VZOWJGSEMzb1FDeXd4U2FnNmNBTEMzbktoXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

## Maven常见命令

Maven 中有一些常见的命令，如果使用 Eclipse 需要手动敲命令，如果使用 IDEA 的话，可以不用命令，直接点点点就可以了。

| 常用命令    | 中文含义 | 说明                                         |
| ----------- | -------- | -------------------------------------------- |
| mvn clean   | 清理     | 这个命令可以用来清理已经编译好的文件         |
| mvn compile | 编译     | 将 Java 代码编译成 Class 文件                |
| mvn test    | 测试     | 项目测试                                     |
| mvn package | 打包     | 根据用户的配置，将项目打成 jar 包或者 war 包 |
| mvn install | 安装     | 手动向本地仓库安装一个 jar                   |
| mvn deploy  | 上传     | 将 jar 上传到私服                            |

这里需要注意的是，这些命令都不是独立运行的，它有一个顺序。举个简单例子：

我想将 jar 上传到私服，那么就要构建 jar，就需要执行 package 命令，要打包，当然也需要测试，那就要走 mvn test 命令，要测试就要先编译.....，因此，最终所有的命令都会执行一遍。不过，开发者也可以手动配置不执行某一个命令，这就是跳过。一般来是，除了测试，其他步骤都不建议跳过。

当然，如果开发者使用了 IDEA ，这些命令不用手动敲，点一下就行：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDE5M2RkODc4M2JiNzNkNzA0Y2Y0MjliMjc1ODg4NTZfeWVXcFgxUmxhbWg0R2lSclgzVjQxMW9pWEdhTXhhRE9fVG9rZW46VkdUb2JMVllpb2ZiV2Z4TGNJRWM4bWJKbkRiXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

###  **通过命令来构建项目**

可以直接通过命令来构建一个 Maven 项目，不过在实际开发中，一般使用 IDEA 就可以直接创建 Maven 项目了,就不具体介绍这个命令了,有兴趣的可以下来了解。

创建命令：

```Verilog
mvn archetype:generate -DgroupId=org.javaboy -DartifactId=firstapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## IDEA **中使用 Maven**

IDEA 安装完成后，就可以直接使用 Maven 了。

### Maven相关配置

IDEA 中，Maven 的配置在 `File->Settings->Build,Execution,Deployment->Build Tools->Maven`:

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmNmYmUzN2JkYjhhMzU4OTE2NTdlZDY1ZDUxMjkyNjlfMW54VGlQcjN2N0FjOVd0MWVzVW1acVN6ZENTanpTMmtfVG9rZW46QkhlTWI1ZWlCb0FvV0Z4cDNBaWM0bDZSbnVlXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

### IDEA 创建Maven工程

这个比较简单，就不在这里具体介绍了可以参考[教程](https://developer.aliyun.com/article/1106119?spm=5176.26934562.main.1.7c62775cBgO5hq)来创建，也可以自己试着来创建。

## Maven的生命周期和插件

### 什么是生命周期

maven的生命周期是为了对所有的构建过程进行抽象和统一。这个生命周期包含了项目的清理、初始化、编译、测试、打包、集成测试、验证、部署和站点生成几乎所有构建步骤。Maven 提供了三种内置的生命周期：

### 清理生命周期**（Clean Lifecycle）**

清理生命周期用于清理项目构建过程中生成的文件。它包括以下阶段：

- `pre-clean`：在实际清理之前执行所需的任何操作。
- `clean`：删除之前构建生成的所有文件。
- `post-clean`：执行清理后的所需操作。

### 默认生命周期**（Default Lifecycle）**

默认生命周期是 Maven 中最常用的生命周期，它涵盖了从项目编译到部署的整个过程。默认生命周期包括以下阶段：

- `validate`：验证项目是否正确，所有必要的信息是否可用。
- `compile`：编译项目的源代码。
- `test`：使用合适的单元测试框架测试编译后的代码。
- `package`：将编译后的代码打包成可分发的格式，如 JAR。
- `verify`：运行任何检查以验证包是否有效并符合质量标准。
- `install`：将包安装到本地仓库，供其他项目使用。
- `deploy`：将最终的包复制到远程仓库，供其他开发者和项目使用。

### 站点生命周期**（Site Lifecycle）**

站点生命周期用于生成项目的站点文档。它包括以下阶段：

- `pre-site`：在实际生成站点文档之前执行所需的任何操作。
- `site`：生成项目的站点文档。
- `post-site`：执行生成站点文档后的所需操作，并准备部署站点。
- `site-deploy`：将生成的站点文档部署到指定的 Web 服务器。

### 什么是 Maven 插件

Maven 插件是 Maven 的扩展机制，允许开发者通过插件来扩展 Maven 的功能。每个插件包含一个或多个目标（Goal），每个目标对应一个具体的任务。例如，`maven-compiler-plugin` 的 `compile` 目标是编译 Java 源代码。

### 插件的类型

Maven 插件主要分为两类：

- 构建插件（Build Plugins）：
  - 这些插件在构建生命周期中执行，绑定到特定的构建阶段。
  - 例如，`maven-compiler-plugin` 用于编译 Java 代码，`maven-surefire-plugin` 用于运行单元测试。
- 报告插件（Reporting Plugins）：
  - 这些插件用于生成项目报告，通常在 `site` 生命周期中执行。
  - 例如，`maven-javadoc-plugin` 用于生成 Java 文档，`maven-project-info-reports-plugin` 用于生成项目信息报告。

### 常用插件

- maven-compiler-plugin：功能：编译 Java 源代码。 默认绑定：绑定到 `compile` 和 `test-compile` 阶段。
- maven-surefire-plugin：功能：运行单元测试。 默认绑定：绑定到 `test` 阶段。
- maven-jar-plugin：功能：创建 JAR 文件。默认绑定：绑定到 `package` 阶段。
- maven-assembly-plugin：功能：创建包含依赖项的发布包。

### 插件的配置

插件的配置通常在 `pom.xml` 文件中进行。以下是一些常见的配置元素：

- groupId、artifactId、version：用于标识插件。
- configuration：用于配置插件的具体参数。
- executions：用于定义插件的执行任务，包括绑定到特定的构建阶段和目标。

## Maven 坐标和本地仓库

Maven 是一个强大的构建工具，其核心功能依赖于**坐标（Coordinates）和仓库（Repository）**的概念。以下是对 Maven 坐标和本地仓库的详细说明：

### Maven 坐标

Maven 使用坐标来唯一标识项目中的每个依赖项和插件。坐标由以下几个元素组成：

- groupId：
  - 定义项目所属的组织或团体，通常使用反向域名格式。例如，`org.apache.maven.plugins` 表示 Maven 插件的组织。
- artifactId：
  - 定义项目的名称或模块名称。例如，`maven-compiler-plugin` 表示 Maven 编译器插件。
- version：
  - 定义项目的版本号。例如，`3.8.1` 表示插件的版本。
- packaging（可选）：
  - 定义项目的打包类型，如 `jar`、`war`、`pom` 等。默认值为 `jar`。
- classifier（可选）：
  - 用于区分具有相同 `groupId`、`artifactId` 和 `version` 的不同构建产物。例如，`javadoc` 或 `sources`。

示例坐标：

```XML
<dependency>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
</dependency>
```

### 本地仓库

Maven 本地仓库是存储所有项目依赖项和构建产物的目录。当 Maven 构建项目时，它会从远程仓库下载依赖项并将其存储在本地仓库中，以便后续构建可以快速访问这些依赖项。

#### 本地仓库的位置

- 默认位置：

  - Windows: `C:\Users\<用户名>\.m2\repository`
  - macOS/Linux: `/Users/<用户名>/.m2/repository` 或 `/home/<用户名>/.m2/repository`

- 自定义位置：

  - 可以通过修改 Maven 的配置文件 `settings.xml` 来更改本地仓库的位置。`settings.xml` 文件位于 `~/.m2/settings.xml`。

  - 示例配置：

  - ```XML
    <settings>
      <localRepository>/path/to/custom/local/repo</localRepository>
    </settings>
    ```

#### 本地仓库的结构

本地仓库中的目录结构遵循以下模式：

```Plain
<groupId>/<artifactId>/<version>/<artifactId>-<version>.<packaging>
```

示例：

```Plain
org/apache/maven/plugins/maven-compiler-plugin/3.8.1/maven-compiler-plugin-3.8.1.jar
```

#### 本地仓库的作用

- 缓存依赖项：
  - 当 Maven 构建项目时，它会首先检查本地仓库中是否存在所需的依赖项。如果存在，则直接使用本地副本；如果不存在，则从远程仓库下载并存储在本地仓库中
- 存储构建产物：
  - Maven 也会将项目的构建产物（如 JAR、WAR 文件）存储在本地仓库中，以便其他项目可以引用这些产物
- 版本管理：
  - 本地仓库中的每个依赖项和构建产物都以其坐标作为标识，确保版本的一致性和可追溯性

1. 远程仓库

除了本地仓库，Maven 还可以从远程仓库下载依赖项。常见的远程仓库包括：

- Maven Central Repository：
  - 官方 Maven 中央仓库，包含大量开源项目的依赖项。地址：https://repo.maven.apache.org/maven2
- 私有仓库：
  - 企业或组织可以搭建自己的私有 Maven 仓库，用于存储内部项目的依赖项和构建产物

## Maven的依赖管理

Maven 的依赖管理是其核心功能之一，它简化了项目依赖项的管理和构建过程。通过 Maven 的依赖管理机制，开发者可以轻松地声明项目所需的库和框架，Maven 会自动处理这些依赖项的下载、版本冲突解决以及传递依赖的管理。以下是关于 Maven 依赖管理的详细说明：

### 依赖管理概述

Maven 的依赖管理主要通过 `pom.xml` 文件中的 `<dependencies>` 元素来声明和管理项目所需的依赖项。每个依赖项由一组坐标（groupId、artifactId、version）唯一标识，Maven 根据这些坐标从本地仓库或远程仓库中查找并下载所需的库。

### 依赖声明

在 `pom.xml` 中，依赖项通常以如下形式声明：

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.20</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>
```

### 依赖坐标

每个依赖项由以下坐标唯一标识：

- groupId：定义项目所属的组织或团体。
- artifactId：定义项目的名称或模块名称。
- version：定义项目的版本号。
- scope（可选）：定义依赖项的作用范围，如 `compile`、`test`、`runtime`、`provided` 等。

1. 依赖的作用范围（Scope）

Maven 提供了多种依赖作用范围，用于控制依赖项在项目构建过程中的可见性和使用范围。常见的作用范围包括：

- compile（默认）：
  - 依赖项在所有构建阶段都可用，包括编译、测试和运行时。
- test：
  - 依赖项仅在测试编译和测试运行时可用。例如，`junit` 依赖项通常使用 `test` 范围。
- provided：
  - 依赖项在编译和测试时需要，但在运行时由容器或环境提供。例如，`servlet-api` 依赖项通常使用 `provided` 范围。
- runtime：
  - 依赖项在运行时需要，但在编译时不需要。例如，JDBC 驱动通常使用 `runtime` 范围。

### 示例：

```XML
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

1. 传递依赖

Maven 支持传递依赖，即一个依赖项本身可能依赖于其他库，Maven 会自动解析并下载这些传递依赖。例如，如果项目依赖于 `spring-core`，而 `spring-core` 依赖于 `commons-logging`，Maven 会自动下载 `commons-logging` 作为传递依赖。

### 依赖冲突

当多个传递依赖引入不同版本的同一个库时，会产生依赖冲突。Maven 使用以下策略解决依赖冲突：

- 最近优先策略（Nearest First）：

  - Maven 会选择离项目最近的依赖版本。例如，如果项目直接依赖 `A` 的 `1.0` 版本，而 `A` 传递依赖 `B` 的 `2.0` 版本，而项目直接依赖 `B` 的 `1.0` 版本，则 Maven 会选择 `B` 的 `1.0` 版本。

- 排除依赖（Exclusions）：

  - 开发者可以通过在 `pom.xml` 中使用 `<exclusions>` 元素来排除特定的传递依赖。例如：

  - ```XML
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.20</version>
        <exclusions>
            <exclusion>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    ```

1. 依赖管理的高级功能

### 依赖管理（Dependency Management）

Maven 提供了 `<dependencyManagement>` 元素，用于集中管理项目中的依赖版本。这在多模块项目中特别有用，可以统一管理所有模块的依赖版本。

示例：

```XML
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.20</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

在子模块中，可以省略版本号：

```XML
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
</dependency>
```

### 依赖范围（Scope）的高级用法

- import：
  - 用于导入依赖管理信息，通常用于继承父 POM 中的依赖管理。
- system：
  - 依赖项不在 Maven 仓库中，需要通过 `systemPath` 显式指定路径。

## Maven 中的父子工程

在 Maven 中，父子工程（Multi-Module Project）是一种常见的项目结构，用于管理多个相关的子模块（Modules）。这种结构特别适用于大型项目或需要将项目拆分为多个独立模块的场景。通过父子工程，开发者可以集中管理依赖项、插件和构建配置，从而提高项目的可维护性和一致性。

### 什么是父子工程？

父子工程是指一个父项目（Parent Project）包含多个子模块（Child Modules）。父项目负责管理子模块的公共配置，如依赖项、插件版本、构建配置等，而每个子模块则负责具体的业务逻辑或功能实现。

### 父子工程的优点

- 集中管理依赖项和插件：
  - 父项目可以统一管理所有子模块的依赖项和插件版本，避免重复配置和版本冲突。
- 简化构建过程：
  - 通过父项目，可以一次性构建所有子模块，简化构建过程。
- 提高项目可维护性：
  - 集中管理配置和依赖项，减少重复代码和配置，提高项目的可维护性。

1. 父子工程的配置

### 父项目的 `pom.xml`

父项目的 `pom.xml` 文件需要使用 `<packaging>pom</packaging>`，并通过 `<modules>` 元素声明所有子模块。

示例：

```XML
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>parent-project</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>

    <modules>
        <module>module-a</module>
        <module>module-b</module>
        <module>module-c</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.3.20</version>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>3.12.0</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 子模块的 `pom.xml`

每个子模块的 `pom.xml` 文件需要继承父项目，并声明自身的依赖项和插件配置。

示例（module-a/pom.xml）：

```XML
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <artifactId>module-a</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
    </dependencies>
</project>
```

### 继承与依赖管理

- 继承：
  - 子模块通过 `<parent>` 元素继承父项目的配置，包括 `groupId`、`version`、依赖管理、插件管理等。
- 依赖管理：
  - 父项目可以在 `<dependencyManagement>` 中集中管理依赖项的版本，子模块只需声明 `groupId` 和 `artifactId`，无需指定版本号。

1. 构建父子工程

### 构建所有子模块

在父项目的根目录下运行以下命令，可以一次性构建所有子模块：

```Bash
mvn clean install
```

### 构建单个子模块

如果只需要构建某个特定的子模块，可以在子模块的目录下运行：

```Bash
mvn clean install
```

### 父子工程的目录结构

一个典型的父子工程目录结构如下：

```Plain
parent-project/
├── pom.xml
├── module-a/
│   └── pom.xml
├── module-b/
│   └── pom.xml
└── module-c/
    └── pom.xml
```

## maven的打包方式(了解)

Maven 提供了多种打包方式，主要包括以下几种：

### maven-jar-plugin

- 用途: 这是 Maven 默认的打包插件，用于创建标准的 Java JAR 文件。
- 特点:
  - 适用于简单的项目。
  - 不包含项目依赖的 JAR 包，用户需要手动添加依赖到类路径中

### maven-assembly-plugin

- 用途: 用于创建自定义的打包结构，可以将多个文件或依赖打包成一个归档文件。
- 特点:
  - 支持多种打包格式，如 ZIP、TAR、GZIP 等。
  - 适用于需要自定义目录结构和包含非代码资源的项目，如配置文件、脚本等
- 使用场景: 适合大数据项目或需要将多个资源打包在一起的项目。

### maven-shade-plugin

- 用途: 用于创建可执行的 JAR 文件（也称为 fat JAR），其中包含所有项目依赖。
- 特点:
  - 可以对依赖的 JAR 包进行重命名，以避免类冲突。
  - 适用于需要将所有依赖打包到一个可执行文件中的项目
- 优点: 生成一个独立的可执行 JAR 文件，无需用户手动添加依赖。

### maven-dependency-plugin

- 用途: 用于处理项目依赖，如复制依赖到指定目录。
- 特点:
  - 常与 `maven-jar-plugin` 一起使用，以管理项目依赖。
  - 适用于需要将依赖与项目代码分开管理的项目

### 其他打包方式

- Spring Boot Maven Plugin: 适用于 Spring Boot 项目，可以将项目打包为可执行的 JAR 或 WAR 文件，并自动配置 Spring Boot 特性。
- Tomcat Maven Plugin: 用于将项目打包为包含嵌入式 Tomcat 服务器的 WAR 文件，适用于 Web 应用

### 选择合适的打包方式

- 简单项目: 使用 `maven-jar-plugin` 即可。
- 需要包含所有依赖: 选择 `maven-shade-plugin`。
- 自定义打包结构: 使用 `maven-assembly-plugin`。
- Spring Boot 项目: 推荐使用 `Spring Boot Maven Plugin`。

### 示例配置

以下是使用 `maven-shade-plugin` 的一个简单示例配置：

```XML
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.4</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>com.example.Main</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

通过上述配置，执行 `mvn package` 后将生成一个包含所有依赖的可执行 JAR 文件。

#### 插件基本信息

- groupId: `org.apache.maven.plugins` 这是一个常用的 Maven 插件组 ID，表示该插件由 Apache Maven 官方提供。
- artifactId: `maven-shade-plugin` 这是插件的名称，表示这是一个用于创建包含所有依赖的 "shaded" JAR 文件的插件。
- version: `3.2.4` 这是插件的版本号，表示使用该插件的 3.2.4 版本。

#### 插件执行配置

- executions: 这个标签用于定义插件的执行行为。插件可以定义多个 execution，每个 execution 可以在不同的构建阶段执行不同的目标。
- execution: 定义一个具体的执行块。
  - phase: `package` 表示这个 execution 会在 Maven 构建生命周期的 `package` 阶段执行。`package` 阶段是构建生命周期中的一个阶段，用于将编译后的代码打包成可分发的格式（如 JAR、WAR 等）。
  - goals: 定义在 `package` 阶段执行的目标。
    - goal: `shade` 这是一个目标，表示使用 `maven-shade-plugin` 的 `shade` 功能来打包项目。`shade` 目标会将项目及其所有依赖打包成一个可执行的 JAR 文件。

#### 插件配置

- configuration: 这是插件的具体配置部分，用于定制插件的行为。
  - transformers: 这是 `shade` 插件的一个配置选项，用于对 JAR 文件进行转换。
    - transformer: 定义一个具体的转换器。
      - implementation: `org.apache.maven.plugins.shade.resource.ManifestResourceTransformer` 这是一个具体的转换器实现，用于修改 JAR 文件的 `MANIFEST.MF` 文件。
      - mainClass: `com.example.Main` 这是 `ManifestResourceTransformer` 的一个配置选项，用于指定生成的可执行 JAR 文件的入口类。执行这个 JAR 文件时，`com.example.Main` 类的 `main` 方法将被调用。

这些打包方式可以根据项目的具体需求进行选择和配置，以实现最佳的构建和部署效果。

# JavaWeb

## Web基础

今天我们访问网站，使用App时，都是基于Web这种Browser/Server模式，简称BS架构，它的特点是，客户端只需要浏览器，应用程序的逻辑和数据都存储在服务器端。浏览器只需要请求服务器，获取Web页面，并把Web页面展示给用户即可。

Web页面具有极强的交互性。由于Web页面是用HTML编写的，而HTML具备超强的表现力，并且，服务器端升级后，客户端无需任何部署就可以使用到新的版本，因此，BS架构升级非常容易。

### HTTP协议

HTTP（超文本传输协议，HyperText Transfer Protocol）是用于在客户端和服务器之间传输超文本（如HTML文档）的应用层协议。它是Web的基础协议之一，负责客户端与服务器之间的通信。

#### 什么是HTTP？

HTTP是一种用于在客户端（如浏览器）和服务器之间传输超文本（如HTML文档）的协议。它是无连接、无状态的协议，这意味着每次请求都是独立的，服务器不会保留之前的请求信息。

#### HTTP的特点

- 无连接性（Connectionless）：每次请求完成后，连接会关闭，服务器不会保留连接信息。
- 无状态性（Stateless）：每个请求都是独立的，服务器不会保留客户端的状态信息。
- 媒体独立性（Media Independence）：HTTP可以传输任何类型的数据，数据的类型由Content-Type标头指定。
- 可扩展性（Extensibility）：通过HTTP标头可以添加新功能。

#### HTTP的工作原理

HTTP协议基于请求-响应模型（Request-Response Model）。客户端发送请求，服务器处理请求并返回响应。

##### 客户端与服务器

- 客户端（Client）：通常是Web浏览器，负责发送HTTP请求。
- 服务器（Server）：负责接收HTTP请求并返回HTTP响应。

##### 请求-响应流程

1. 1.客户端发送HTTP请求到服务器。
2. 2.服务器接收请求并处理。
3. 3.服务器返回HTTP响应给客户端。
4. HTTP请求

HTTP请求由请求行（Request Line）、请求头（Request Headers）和请求体（Request Body）组成。

##### 请求行

请求行包含请求方法、请求的URL和协议版本。例如：

```Plain
GET /index.html HTTP/1.1
```

##### 请求头

请求头提供附加信息，如主机名、用户代理、Accept类型等。例如：

```Plain
Host: www.example.com
User-Agent: Mozilla/5.0
Accept: text/html
```

##### 请求体

请求体用于发送数据，如表单数据。例如：

```Plain
name=John+Doe&age=30
```

#### HTTP响应

HTTP响应由状态行（Status Line）、响应头（Response Headers）和响应体（Response Body）组成。

##### 状态行

状态行包含协议版本、状态码和状态消息。例如：

```Plain
HTTP/1.1 200 OK
```

##### 响应头

响应头提供附加信息，如内容类型、服务器类型、Content-Length等。例如：

```Plain
Content-Type: text/html; charset=UTF-8
Content-Length: 1274
Server: Apache/2.4.1 (Unix)
```

##### 响应体

响应体包含服务器返回的实际内容，如HTML文档、图片等。

#### 常见HTTP方法

- GET：请求获取资源。请求参数附加在URL后面。
- POST：向服务器提交数据。请求参数在请求体中。
- PUT：上传资源到服务器。
- DELETE：删除服务器上的资源。
- HEAD：获取资源的元数据，不返回具体内容。

#### 常见HTTP状态码

- 200 OK：请求成功。
- 301 Moved Permanently：资源已永久移动到新位置。
- 302 Found：资源临时移动到新位置。
- 400 Bad Request：客户端请求有语法错误。
- 401 Unauthorized：请求未经授权。
- 403 Forbidden：服务器拒绝执行请求。
- 404 Not Found：请求的资源不存在。
- 500 Internal Server Error：服务器内部错误。
- 503 Service Unavailable：服务器暂时无法处理请求。

1. HTTP与HTTPS

#### HTTPS

HTTPS（HTTP Secure）是HTTP的安全版本，通过SSL/TLS协议对数据进行加密和身份验证。HTTPS使用443端口，而HTTP使用80端口。

#### HTTPS的优势

- 数据加密：防止数据被窃听和篡改。
- 身份验证：确保客户端与服务器之间的通信安全。
- 数据完整性：防止数据被篡改。

## Tomcat

（这里简单介绍一下tomcat，不做详细的讲解，可以自己下来再了解一下tomcat的一些细节）

Apache Tomcat 是Java Servlet、JavaServer Pages （JSP）、Java表达式语言和Java的WebSocket技术的一个开源实现 ,通常我们将Tomcat称为Web容器或者Servlet容器 。

我们写的java项目可以部署到 tomcat 服务器中运行。

tomcat各版本和相应规范的映射关系（可以在[官网](https://tomcat.apache.org/whichversion.html)查看详细信息）：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjBlM2U5ODk1MTYwYTYxNzY2MDM2MDJkNWJlZTk5MzBfWFlzalhhR2cwWlBHSTdmU3N4dURiZHRyS2FJMU85VmZfVG9rZW46Q1Nnc2IzcWpBb2RUZUp4Q0NVMWNCSVNlbmpmXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

下载地址：

```Plain
https://tomcat.apache.org/download-90.cgi
```

下载到本地并解压：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MTVlMmJiNTExOTYxMzE1YWY5N2MxN2ZjMWQ2NjI4MDdfcHdESnNnWDRQT0JSN0l0S0FQVnpua3hQd1dqMXMzdXdfVG9rZW46WEszb2JwWnFNb2kzeEN4TUp1eWNSNGtqbnVkXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

进入主目录：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=YTAyMTIyYTVhZGFmNGIxODQ1ODQyYmFjOTk4NWViZDdfR1pzdGF6b2Q4MFBqRnhQc0pRR0lwOUdXejR3Y2hGU2NfVG9rZW46Qm5DYmIyN3hxb0VnaTF4eW9MNGNnWkpyblRlXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

### tomcat目录介绍(了解一下，不需要记住，需要的是时候再查文档)

#### bin

启动，关闭和其他脚本。这些 .sh文件（对于Unix系统）是这些.bat文件的功能副本（对于Windows系统）。由于Win32命令行缺少某些功能，因此此处包含一些其他文件。

比如说：windows下启动tomcat用的是startup.bat，另外Linux环境中使用的是startup.sh。对应还有相应的shutdown关闭脚本。

#### conf

tomcat的配置文件和相关的DTD。这里最重要的文件是server.xml。它是容器的主要配置文件。

`catalina.policy`：tomcat：安全策略文件，控制JVM相关权限，具体可以参考java.security.Permission。

`catalina.properties`：tomcat Catalina 行为控制配置文件，比如：Common ClassLoader。

`logging.properties`：tomcat日志配置文件。里面的日志采用的是JDK Logging。

`server.xml`：tomcat server配置文件(对于我开发人员来说是非常重要)。

`context.xml`：全局context配置文件，监视并加载资源文件，当监视的文件发生发生变化时，自动加载 。

`tomcat-user.xml`：tomcat角色配置文件。

`web.xml`：Servlet标准的web.xml部署文件，tomcat默认实现部分配置 入内：

- org.apache.catalina.servlets.DefaultServlet。
- org.apache.jasper.servlet.JspServlet

#### logs

日志文件默认位于此处。

`localhost`有用，当你们的tomcat启动不了的时候，多看这个文件。比如：

- NoClassDefFoundError
- ClassNotFoundException

`access`最没用。

`catalina.{date}` 主要是控制台输出，全部日志都在这里面。

#### webapps

这是您的webapp所在的位置。其实这里面这几个都是一个项目。

简化web部署的方式。在线上环境中我们的应用是不会放在这里的。最好的办法就是外置。

#### lib

tomcat存放共用的类库。比如：

- ecj-4.17.jar: eclipse Java编译器
- jasper.jar：JSP编译器。

#### work

存放tomcat运行时编译后的文件，比如JSP编译后的文件 。

#### temp

存放运行时产生的临时文件。

### 启动tomcat

我们在windows下的就直接启动bin目录下的startup.bat，对应Linux环境中我们使用的是startup.sh。

双击就能启动了。控制台会输8080端口，然后我们访问：

```Plain
http://localhost:8080/
```

页面展示：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MjcxMGUyODhjODVmZTNmY2M0MGJhYTQ0YTYxYzI4YzFfU01hWlJsTzdrUXV0RmNkNDZqWkc4Y2lzWVU2ZGZDc21fVG9rZW46SEM3MWJGbENrbzMwWTR4Vjg3TmN4eHZjblpmXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

这就代表着我们的tomcat启动成功了。

此时，`http://localhost:8080/`请求到的是ROOT目录。

比如：我们还可以`http://localhost:8080/manager`

## Servlet

### 什么是Servlet

​    

​    Servlet（Server Applet），全称Java Servlet，未有中文译文。是用Java编写的服务器端程序。其主要功能在于交互式地浏览和修改数据，生成动态Web内容。狭义的Servlet是指Java语言实现的一个接口，广义的Servlet是指任何实现了这个Servlet接口的类，一般情况下，人们将Servlet理解为后者。

​    Servlet运行于支持Java的应用服务器中。从实现上讲，Servlet可以响应任何类型的请求，但绝大多数情况下Servlet只用来扩展基于HTTP协议的Web服务器。

### Servlet的工作模式

- 客户端发送请求至服务器
- 服务器启动并调用Servlet，Servlet根据客户端请求生成响应内容并将其传给服务器
- 服务器将响应返回客户端

### Servlet API 概

​    Servlet API 包含以下4个Java包：

1. javax.servlet   其中包含定义servlet和servlet容器之间契约的类和接口。
2. javax.servlet.http   其中包含定义HTTP Servlet 和Servlet容器之间的关系。
3. javax.servlet.annotation   其中包含标注servlet，Filter,Listener的标注。它还为被标注元件定义元数据
4. javax.servlet.descriptor，其中包含提供程序化登录Web应用程序的配置信息的类型。

### Servlet 的使用方法

​    Servlet技术的核心是Servlet，**它是所有Servlet类必须直接或者间接实现的一个接口**。在编写实现Servlet的Servlet类时，直接实现它。在扩展实现这个这个接口的类时，间接实现它。

### Servlet 的工作原理

​    Servlet接口定义了Servlet与servlet容器之间的契约。这个契约是：Servlet容器将Servlet类载入内存，并产生Servlet实例和调用它具体的方法。但是要注意的是，在一个应用程序中，每种Servlet类型只能有一个实例。

​    用户请求致使Servlet容器调用Servlet的Service（）方法，并传入一个ServletRequest对象和一个ServletResponse对象。ServletRequest对象和ServletResponse对象都是由Servlet容器（例如TomCat）封装好的，并不需要程序员去实现，程序员可以直接使用这两个对象。

​    ServletRequest中封装了当前的Http请求，因此，开发人员不必解析和操作原始的Http数据。ServletResponse表示当前用户的Http响应，程序员只需直接操作ServletResponse对象就能把响应轻松的发回给用户。

​    对于每一个应用程序，Servlet容器还会创建一个ServletContext对象。这个对象中封装了上下文（应用程序）的环境详情。每个应用程序只有一个ServletContext。每个Servlet对象也都有一个封装Servlet配置的ServletConfig对象。

### Servlet 接口中定义的方法

​    Servlet 接口中定义了的方法方法。

```Java
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}
```

### Servlet 的生命周期

​    

*Servlet* *生命周期可被定义为从创建直到毁灭的整个过程。以下是 Servlet 遵循的过程：*

- Servlet 初始化后调用 **init ()** 方法。
- Servlet 调用 **service()** 方法来处理客户端的请求。
- Servlet 销毁前调用 **destroy()** 方法。
- 最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。

*现在让我们详细讨论生命周期的方法。*

#### init() 方法

init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，在后续每次用户请求时不再调用。因此，它是用于一次性初始化，就像 Applet 的 init 方法一样。

Servlet 创建于用户第一次调用对应于该 Servlet 的 URL 时，但是您也可以指定 Servlet 在服务器第一次启动时被加载。

当用户调用一个 Servlet 时，就会创建一个 Servlet 实例，每一个用户请求都会产生一个新的线程，适当的时候移交给 doGet 或 doPost 方法。init() 方法简单地创建或加载一些数据，这些数据将被用于 Servlet 的整个生命周期。

init 方法的定义如下：

```Java
public void init() throws ServletException {
  // 初始化代码...
}
```

#### service() 方法

service() 方法是执行实际任务的主要方法。Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut，doDelete 等方法。

下面是该方法的特征：

```Java
public void service(ServletRequest request, 
                    ServletResponse response) 
      throws ServletException, IOException{
}
```

service() 方法由容器调用，service 方法在适当的时候调用 doGet、doPost、doPut、doDelete 等方法。所以，您不用对 service() 方法做任何动作，您只需要根据来自客户端的请求类型来重写 doGet() 或 doPost() 即可。

doGet() 和 doPost() 方法是每次服务请求中最常用的方法。下面是这两种方法的特征。

#### doGet() 方法

GET 请求来自于一个 URL 的正常请求，或者来自于一个未指定 METHOD 的 HTML 表单，它由 doGet() 方法处理。

```Java
public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

#### doPost() 方法

POST 请求来自于一个特别指定了 METHOD 为 POST 的 HTML 表单，它由 doPost() 方法处理。

```Java
public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

#### destroy() 方法

destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

在调用 destroy() 方法之后，servlet 对象被标记为垃圾回收。destroy 方法定义如下所示：

```Java
  public void destroy() {
    // 终止化代码...
  }
```

#### 架构图

下图显示了一个典型的 Servlet 生命周期方案。

- 第一个到达服务器的 HTTP 请求被委派到 Servlet 容器。
- Servlet 容器在调用 service() 方法之前加载 Servlet。
- 然后 Servlet 容器处理由多个线程产生的多个请求，每个线程执行一个单一的 Servlet 实例的 service() 方法。

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=MmU0OGJjNjg3Y2M4OWZiNWQxY2U1MDJmZTlkYWMwMDhfV0RGTlV5d0I2cXFYbXFLZXFLUmd5Sk1OYXNqQVBYSUNfVG9rZW46UEpGTmI5OG01b1Y2Q2J4clN0Z2NORW5ibjFlXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

### Servlet实例

引入的Servlet API如下：

```XML
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>5.0.0</version>
    <scope>provided</scope>
</dependency>
```

注意到`<scope>`指定为`provided`，表示编译时使用，但不会打包到`.war`文件中，因为运行期Web服务器本身已经提供了Servlet API相关的jar包。

要务必注意`servlet-api`的版本。4.0及之前的`servlet-api`由Oracle官方维护，引入的依赖项是`javax.servlet:javax.servlet-api`，编写代码时引入的包名为：

```Java
import javax.servlet.*;
```

而5.0及以后的`servlet-api`由Eclipse开源社区维护，引入的依赖项是`jakarta.servlet:jakarta.servlet-api`，编写代码时引入的包名为：

```Java
import jakarta.servlet.*;
```

教程采用最新的`jakarta.servlet:5.0.0`版本，但对于很多仅支持Servlet 4.0版本的框架来说，例如Spring 5，我们就只能使用`javax.servlet:4.0.0`版本，这一点针对不同项目要特别注意。

 注意

引入不同的Servlet API版本，编写代码时导入的相关API的包名是不同的。

整个工程结构如下：

```Plain
web-servlet-hello/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── itranswarp/
        │           └── learnjava/
        │               └── servlet/
        │                   └── HelloServlet.java
        ├── resources/
        └── webapp/
```

目录`webapp`目前为空，如果我们需要存放一些资源文件，则需要放入该目录。有的同学可能会问，`webapp`目录下是否需要一个`/WEB-INF/web.xml`配置文件？这个配置文件是低版本Servlet必须的，但是高版本Servlet已不再需要，所以无需该配置文件，这里我就不来演示这个了，但是还是会简单介绍一下。

我们来实现一个最简单的Servlet：

```Java
// WebServlet注解表示这是一个Servlet，并映射到地址/:
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // 设置响应类型:
        resp.setContentType("text/html");
        // 获取输出流:
        PrintWriter pw = resp.getWriter();
        // 写入响应:
        pw.write("<h1>Hello, world!</h1>");
        // 最后不要忘记flush强制输出:
        pw.flush();
    }
}
```

​     一个Servlet总是继承自`HttpServlet`，然后覆写`doGet()`或`doPost()`方法。注意到`doGet()`方法传入了`HttpServletRequest`和`HttpServletResponse`两个对象，分别代表HTTP请求和响应。我们使用Servlet API时，并不直接与底层TCP交互，也不需要解析HTTP协议，因为`HttpServletRequest`和`HttpServletResponse`就已经封装好了请求和响应。以发送响应为例，我们只需要设置正确的响应类型，然后获取`PrintWriter`，写入响应即可。

​     **这里使用的是****`HttpServlet`** **而不是** **`Servlet`** **，大家可以思考一下为什么这样做，也可以查查资料，上课的时候我也会讲一下。**

 

**写完之后可以尝试一下自己运行测试，可以选择在** **[idea 里面集成 tomcat](https://blog.csdn.net/Wxy971122/article/details/123508532)** **直接运行，也可以打包之后部署在下载的 tomcat 里面运行，tomcat的那一部分我没讲部署，部署其实熟悉了很简单，不过没有部署过的话，第一次查资料会有点困难，希望大家可以自己来尝试着部署一下写的项目，学会自己查资料学习，我们后期很多时候都需要查资料来完成一些任务，希望大家可以现在就锻炼一下查找资料的能力。**

### Servlet 进阶

一个Web App就是由一个或多个Servlet组成的，每个Servlet通过注解说明自己能处理的路径。例如：

```Java
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    ...
}
```

上述`HelloServlet`能处理`/hello`这个路径的请求。

<mark>早期的Servlet需要在web.xml中配置映射路径，但最新Servlet版本只需要通过注解就可以完成映射。</mark>

因为浏览器发送请求的时候，还会有请求方法（HTTP Method）：即`GET`、`POST`、`PUT`等不同类型的请求。因此，要处理`GET`请求，我们要覆写`doGet()`方法：

```Java
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ...
    }
}
```

类似的，要处理`POST`请求，就需要覆写`doPost()`方法。

如果没有覆写`doPost()`方法，那么`HelloServlet`能不能处理`POST /hello`请求呢？

我们查看一下`HttpServlet`的`doPost()`方法就一目了然了：它会直接返回405或400错误。因此，一个Servlet如果映射到`/hello`，那么所有请求方法都会由这个Servlet处理，至于能不能返回200成功响应，要看有没有覆写对应的请求方法。

一个Webapp完全可以有多个Servlet，分别映射不同的路径。例如：

```Java
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    ...
}

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    ...
}

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    ...
}
```

浏览器发出的HTTP请求总是由Web Server先接收，然后，根据Servlet配置的映射，不同的路径转发到不同的Servlet：

```Plain
               ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
               │            /hello    ┌───────────────┐│
                          ┌──────────▶│ HelloServlet  │
               │          │           └───────────────┘│
┌───────┐    ┌──────────┐ │ /signin   ┌───────────────┐
│Browser│───▶│Dispatcher│─┼──────────▶│ SignInServlet ││
└───────┘    └──────────┘ │           └───────────────┘
               │          │ /         ┌───────────────┐│
                          └──────────▶│ IndexServlet  │
               │                      └───────────────┘│
                              Web Server
               └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
```

这种根据路径转发的功能我们一般称为 dispatch 。映射到`/`的`IndexServlet`比较特殊，它实际上会接收所有未匹配的路径，相当于`/*`，因为Dispatcher的逻辑可以用伪代码实现如下：

```Java
String path = ...
if (path.equals("/hello")) {
    dispatchTo(helloServlet);
} else if (path.equals("/signin")) {
    dispatchTo(signinServlet);
} else {// 所有未匹配的路径均转发到"/"
    dispatchTo(indexServlet);
}
```

所以我们在浏览器输入一个`http://localhost:8080/abc`也会看到`IndexServlet`生成的页面。

#### HttpServletRequest

`HttpServletRequest`封装了一个HTTP请求，它实际上是从`ServletRequest`继承而来。最早设计Servlet时，设计者希望Servlet不仅能处理HTTP，也能处理类似SMTP等其他协议，因此，单独抽出了`ServletRequest`接口，但实际上除了HTTP外，并没有其他协议会用Servlet处理，所以这是一个过度设计。

我们通过`HttpServletRequest`提供的接口方法可以拿到HTTP请求的几乎全部信息，常用的方法有：

- getMethod()：返回请求方法，例如，`"GET"`，`"POST"`；
- getRequestURI()：返回请求路径，但不包括请求参数，例如，`"/hello"`；
- getQueryString()：返回请求参数，例如，`"name=Bob&a=1&b=2"`；
- getParameter(name)：返回请求参数，GET请求从URL读取参数，POST请求从Body中读取参数；
- getContentType()：获取请求Body的类型，例如，`"application/x-www-form-urlencoded"`；
- getContextPath()：获取当前Webapp挂载的路径，对于ROOT来说，总是返回空字符串`""`；
- getCookies()：返回请求携带的所有Cookie；
- getHeader(name)：获取指定的Header，对Header名称不区分大小写；
- getHeaderNames()：返回所有Header名称；
- getInputStream()：如果该请求带有HTTP Body，该方法将打开一个输入流用于读取Body；
- getReader()：和getInputStream()类似，但打开的是Reader；
- getRemoteAddr()：返回客户端的IP地址；
- getScheme()：返回协议类型，例如，`"http"`，`"https"`；

此外，`HttpServletRequest`还有两个方法：`setAttribute()`和`getAttribute()`，可以给当前`HttpServletRequest`对象附加多个Key-Value，相当于把`HttpServletRequest`当作一个`Map<String, Object>`使用。

调用`HttpServletRequest`的方法时，注意务必阅读接口方法的文档说明，因为有的方法会返回`null`，例如`getQueryString()`的文档就写了：

```Plain
... This method returns null if the URL does not have a query string...
```

#### HttpServletResponse

`HttpServletResponse`封装了一个HTTP响应。由于HTTP响应必须先发送Header，再发送Body，所以，操作`HttpServletResponse`对象时，必须先调用设置Header的方法，最后调用发送Body的方法。

常用的设置Header的方法有：

- setStatus(sc)：设置响应代码，默认是`200`；
- setContentType(type)：设置Body的类型，例如，`"text/html"`；
- setCharacterEncoding(charset)：设置字符编码，例如，`"UTF-8"`；
- setHeader(name, value)：设置一个Header的值；
- addCookie(cookie)：给响应添加一个Cookie；
- addHeader(name, value)：给响应添加一个Header，因为HTTP协议允许有多个相同的Header；

写入响应时，需要通过`getOutputStream()`获取写入流，或者通过`getWriter()`获取字符流，二者只能获取其中一个。

写入响应前，无需设置`setContentLength()`，因为底层服务器会根据写入的字节数自动设置，如果写入的数据量很小，实际上会先写入缓冲区，如果写入的数据量很大，服务器会自动采用Chunked编码让浏览器能识别数据结束符而不需要设置Content-Length头。

但是，写入完毕后调用`flush()`却是必须的，因为大部分Web服务器都基于HTTP/1.1协议，会复用TCP连接。如果没有调用`flush()`，将导致缓冲区的内容无法及时发送到客户端。此外，写入完毕后千万不要调用`close()`，原因同样是因为会复用TCP连接，如果关闭写入流，将关闭TCP连接，使得Web服务器无法复用此TCP连接。

<mark>写入完毕后对输出流调用flush()而不是close()方法！</mark>

有了`HttpServletRequest`和`HttpServletResponse`这两个高级接口，我们就不需要直接处理HTTP协议。注意到具体的实现类是由各服务器提供的，而我们编写的Web应用程序只关心接口方法，并不需要关心具体实现的子类。

#### Servlet多线程模型

一个Servlet类在服务器中只有一个实例，但对于每个HTTP请求，Web服务器会使用多线程执行请求。因此，一个Servlet的`doGet()`、`doPost()`等处理请求的方法是多线程并发执行的。如果Servlet中定义了字段，要注意多线程并发访问的问题：

```Java
public class HelloServlet extends HttpServlet {
    private Map<String, String> map = new ConcurrentHashMap<>();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 注意读写map字段是多线程并发的:
        this.map.put(key, value);
    }
}
```

对于每个请求，Web服务器会创建唯一的`HttpServletRequest`和`HttpServletResponse`实例，因此，`HttpServletRequest`和`HttpServletResponse`实例只有在当前处理线程中有效，它们总是局部变量，不存在多线程共享的问题。

#### 小结

一个Webapp中的多个Servlet依靠路径映射来处理不同的请求；

映射为`/`的Servlet可处理所有“未匹配”的请求；

如何处理请求取决于Servlet覆写的对应方法；

Web服务器通过多线程处理HTTP请求，一个Servlet的处理方法可以由多线程并发执行。

### 重定向和转发

#### Redirect

重定向是指当浏览器请求一个URL时，服务器返回一个重定向指令，告诉浏览器地址已经变了，麻烦使用新的URL再重新发送新请求。

例如，我们已经编写了一个能处理`/hello`的`HelloServlet`，如果收到的路径为`/hi`，希望能重定向到`/hello`，可以再编写一个`RedirectServlet`：

```Java
@WebServlet(urlPatterns = "/hi")
public class RedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 构造重定向的路径:
        String name = req.getParameter("name");
        String redirectToUrl = "/hello"(name == null ? "" : "?name="name);
        // 发送重定向响应:
        resp.sendRedirect(redirectToUrl);
    }
}
```

如果浏览器发送`GET /hi`请求，`RedirectServlet`将处理此请求。由于`RedirectServlet`在内部又发送了重定向响应，因此，浏览器会收到如下响应：

```Plain
HTTP/1.1 302 Found
Location: /hello
```

当浏览器收到302响应后，它会立刻根据`Location`的指示发送一个新的`GET /hello`请求，这个过程就是重定向：

```Plain
┌───────┐   GET /hi     ┌───────────────┐
│Browser│ ────────────▶ │RedirectServlet│
│       │ ◀──────────── │               │
└───────┘   302         └───────────────┘
┌───────┐  GET /hello   ┌───────────────┐
│Browser│ ────────────▶ │ HelloServlet  │
│       │ ◀──────────── │               │
└───────┘   200 <html>  └───────────────┘
```

观察Chrome浏览器的网络请求，可以看到两次HTTP请求：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=Yjg1ZTQ3ZTljNTliOTJhZmJmNjQ1MTMyNGQ2Njg4OGNfYXQ1Vk1EUFhkU2xESkt2eHhBU1lhYlhtMzF1dzVZWGhfVG9rZW46SEltdWJxaDVJb2xpaWF4U2tKVmN5ZGdmbnNlXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

并且浏览器的地址栏路径自动更新为`/hello`。

重定向有两种：

一种是302响应，称为临时重定向，一种是301响应，称为永久重定向。两者的区别是，如果服务器发送301永久重定向响应，浏览器会缓存`/hi`到`/hello`这个重定向的关联，下次请求`/hi`的时候，浏览器就直接发送`/hello`请求了。

重定向有什么作用？重定向的目的是当Web应用升级后，如果请求路径发生了变化，可以将原来的路径重定向到新路径，从而避免浏览器请求原路径找不到资源。

`HttpServletResponse`提供了快捷的`redirect()`方法实现302重定向。如果要实现301永久重定向，可以这么写：

```Java
resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY); // 301
resp.setHeader("Location", "/hello");
```

#### Forward

Forward是指内部转发。当一个Servlet处理请求的时候，它可以决定自己不继续处理，而是转发给另一个Servlet处理。

例如，我们已经编写了一个能处理`/hello`的`HelloServlet`，继续编写一个能处理`/morning`的`ForwardServlet`：

```Java
@WebServlet(urlPatterns = "/morning")
public class ForwardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/hello").forward(req, resp);
    }
}
```

`ForwardServlet`在收到请求后，它并不自己发送响应，而是把请求和响应都转发给路径为`/hello`的Servlet，即下面的代码：

```Java
req.getRequestDispatcher("/hello").forward(req, resp);
```

后续请求的处理实际上是由`HelloServlet`完成的。这种处理方式称为转发（Forward），我们用流程图画出来如下：

```Plain
                          ┌────────────────────────┐
                          │      ┌───────────────┐ │
                          │ ────▶│ForwardServlet │ │
┌───────┐  GET /morning   │      └───────────────┘ │
│Browser│ ──────────────▶ │              │         │
│       │ ◀────────────── │              ▼         │
└───────┘    200 <html>   │      ┌───────────────┐ │
                          │ ◀────│ HelloServlet  │ │
                          │      └───────────────┘ │
                          │       Web Server       │
                          └────────────────────────┘
```

转发和重定向的区别在于，转发是在Web服务器内部完成的，对浏览器来说，它只发出了一个HTTP请求：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTViMTZmMDBkYzRjMzM0YzBhOTkwYWQ4ZTFhMDMzODZfUFk4aE9FaFZUWVk4ZXlkODRkSmc1anNlamR2a2U0MXhfVG9rZW46SE04bWJ0U3hBb0hxUUV4UjM0Q2N6MVEzblVsXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

注意到使用转发的时候，浏览器的地址栏路径仍然是`/morning`，浏览器并不知道该请求在Web服务器内部实际上做了一次转发。

### Session和Cookie

​      在Web应用程序中，我们经常要跟踪用户身份。当一个用户登录成功后，如果他继续访问其他页面，Web程序如何才能识别出该用户身份？

​      因为HTTP协议是一个无状态协议，即Web应用程序无法区分收到的两个HTTP请求是否是同一个浏览器发出的。为了跟踪用户状态，服务器可以向浏览器分配一个唯一ID，并以Cookie的形式发送到浏览器，浏览器在后续访问时总是附带此Cookie，这样，服务器就可以识别用户身份。

#### Session

我们把这种基于唯一ID识别用户身份的机制称为Session。每个用户第一次访问服务器后，会自动获得一个Session ID。如果用户在一段时间内没有访问服务器，那么Session会自动失效，下次即使带着上次分配的Session ID访问，服务器也认为这是一个新用户，会分配新的Session ID。

JavaEE的Servlet机制内建了对Session的支持。我们以登录为例，当一个用户登录成功后，我们就可以把这个用户的名字放入一个`HttpSession`对象，以便后续访问其他页面的时候，能直接从`HttpSession`取出用户名：

```Java
@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    // 模拟一个数据库:
    private Map<String, String> users = Map.of("bob", "bob123", "alice", "alice123", "tom", "tomcat");
    // GET请求时显示登录页:
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");PrintWriter pw = resp.getWriter();
        pw.write("<h1>Sign In</h1>");
        pw.write("<form action=\"/signin\" method=\"post\">");
        pw.write("<p>Username: <input name=\"username\"></p>");
        pw.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        pw.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        pw.write("</form>");
        pw.flush();
    }
    // POST请求时处理用户登录:
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String expectedPassword = users.get(name.toLowerCase());
        if (expectedPassword != null && expectedPassword.equals(password)) {// 登录成功:
                req.getSession().setAttribute("user", name);
                resp.sendRedirect("/"); 
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
    }
}
```

上述`SignInServlet`在判断用户登录成功后，立刻将用户名放入当前`HttpSession`中：

```Java
HttpSession session = req.getSession();
session.setAttribute("user", name);
```

在`IndexServlet`中，可以从`HttpSession`取出用户名：

```Java
@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 从HttpSession获取当前用户名:
    String user = (String) req.getSession().getAttribute("user");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Welcome, "(user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            // 未登录，显示登录链接:
            pw.write("<p><a href=\"/signin\">Sign In</a></p>");
        } else {
            // 已登录，显示登出链接:
            pw.write("<p><a href=\"/signout\">Sign Out</a></p>");
        }
        pw.flush();
    }
}
```

如果用户已登录，可以通过访问`/signout`登出。登出逻辑就是从`HttpSession`中移除用户相关信息：

```Java
@WebServlet(urlPatterns = "/signout")public class SignOutServlet extends HttpServlet {protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {// 从HttpSession移除用户名:
        req.getSession().removeAttribute("user");
        resp.sendRedirect("/");
    }
}
```

对于Web应用程序来说，我们总是通过`HttpSession`这个高级接口访问当前Session。如果要深入理解Session原理，可以认为Web服务器在内存中自动维护了一个ID到`HttpSession`的映射表，我们可以用下图表示：

```Plain
           ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
           │      ┌───────────────┐                │
             ┌───▶│ IndexServlet  │◀──────────┐
           │ │    └───────────────┘           ▼    │
┌───────┐    │    ┌───────────────┐      ┌────────┐
│Browser│──┼─┼───▶│ SignInServlet │◀────▶│Sessions││
└───────┘    │    └───────────────┘      └────────┘
           │ │    ┌───────────────┐           ▲    │
             └───▶│SignOutServlet │◀──────────┘
           │      └───────────────┘                │
           └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
```

而服务器识别Session的关键就是依靠一个名为`JSESSIONID`的Cookie。在Servlet中第一次调用`req.getSession()`时，Servlet容器自动创建一个Session ID，然后通过一个名为`JSESSIONID`的Cookie发送给浏览器：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=M2Y1OTg1OGM0Y2ZiNTJjYTgxYTMwZDFmYjU3MjJhODJfdTk1RXFvS25vTXlqYVA4ODNyWkNJcTVmRXBEZ2t0SG9fVG9rZW46WWdGN2JsVmRlbzdudHd4Y1psUWNqc2hpbkJjXzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

这里要注意的几点是：

- `JSESSIONID`是由Servlet容器自动创建的，目的是维护一个浏览器会话，它和我们的登录逻辑没有关系；
- 登录和登出的业务逻辑是我们自己根据`HttpSession`是否存在一个`"user"`的Key判断的，登出后，Session ID并不会改变；
- 即使没有登录功能，仍然可以使用`HttpSession`追踪用户，例如，放入一些用户配置信息等。

除了使用Cookie机制可以实现Session外，还可以通过隐藏表单、URL末尾附加ID来追踪Session。这些机制很少使用，最常用的Session机制仍然是Cookie。

使用Session时，由于服务器把所有用户的Session都存储在内存中，如果遇到内存不足的情况，就需要把部分不活动的Session序列化到磁盘上，这会大大降低服务器的运行效率，因此，放入Session的对象要小，通常我们放入一个简单的`User`对象就足够了：

```Java
public class User {
    public long id; 
    // 唯一标识
    public String email;
    public String name;
}
```

在使用多台服务器构成集群时，使用Session会遇到一些额外的问题。通常，多台服务器集群使用反向代理作为网站入口：

```Plain
                                     ┌────────────┐
                                ┌───▶│Web Server 1│
                                │    └────────────┘
┌───────┐     ┌─────────────┐   │    ┌────────────┐
│Browser│────▶│Reverse Proxy│───┼───▶│Web Server 2│
└───────┘     └─────────────┘   │    └────────────┘
                                │    ┌────────────┐
                                └───▶│Web Server 3│
                                     └────────────┘
```

如果多台Web Server采用无状态集群，那么反向代理总是以轮询方式将请求依次转发给每台Web Server，这会造成一个用户在Web Server 1存储的Session信息，在Web Server 2和3上并不存在，即从Web Server 1登录后，如果后续请求被转发到Web Server 2或3，那么用户看到的仍然是未登录状态。

要解决这个问题，方案一是在所有Web Server之间进行Session复制，但这样会严重消耗网络带宽，并且，每个Web Server的内存均存储所有用户的Session，内存使用率很低。

另一个方案是采用粘滞会话（Sticky Session）机制，即反向代理在转发请求的时候，总是根据JSESSIONID的值判断，相同的JSESSIONID总是转发到固定的Web Server，但这需要反向代理的支持。

无论采用何种方案，使用Session机制，会使得Web Server的集群很难扩展，因此，Session适用于中小型Web应用程序。对于大型Web应用程序来说，通常需要避免使用Session机制。

#### Cookie

实际上，Servlet提供的`HttpSession`本质上就是通过一个名为`JSESSIONID`的Cookie来跟踪用户会话的。除了这个名称外，其他名称的Cookie我们可以任意使用。

如果我们想要设置一个Cookie，例如，记录用户选择的语言，可以编写一个`LanguageServlet`：

```Java
@WebServlet(urlPatterns = "/pref")
public class LanguageServlet extends HttpServlet {
    private static final Set<String> LANGUAGES = Set.of("en", "zh");
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (LANGUAGES.contains(lang)) {
            // 创建一个新的Cookie:
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围:
            cookie.setPath("/");// 该Cookie有效期:
            cookie.setMaxAge(8640000); // 8640000秒=100天// 将该Cookie添加到响应:
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/");
    }
}
```

创建一个新Cookie时，除了指定名称和值以外，通常需要设置`setPath("/")`，浏览器根据此前缀决定是否发送Cookie。如果一个Cookie调用了`setPath("/user/")`，那么浏览器只有在请求以`/user/`开头的路径时才会附加此Cookie。通过`setMaxAge()`设置Cookie的有效期，单位为秒，最后通过`resp.addCookie()`把它添加到响应。

如果访问的是https网页，还需要调用`setSecure(true)`，否则浏览器不会发送该Cookie。

因此，务必注意：浏览器在请求某个URL时，是否携带指定的Cookie，取决于Cookie是否满足以下所有要求：

- URL前缀是设置Cookie时的Path；
- Cookie在有效期内；
- Cookie设置了secure时必须以https访问。

我们可以在浏览器看到服务器发送的Cookie：

![img](https://lanshanteam.feishu.cn/space/api/box/stream/download/asynccode/?code=NmIxMmZkY2E0NGNhOGEwYjM5MjhmYTJkMGY5MDJhYTJfdE5TRldUbXpKeDR3Q3BLTWNEODBYVGEwRVhaTTVXQ2pfVG9rZW46VVNtNGJ5ckdrbzRybWZ4SzZDQ2N6V2NqbkV4XzE3MzE4MjYxODg6MTczMTgyOTc4OF9WNA)

如果我们要读取Cookie，例如，在`IndexServlet`中，读取名为`lang`的Cookie以获取用户设置的语言，可以写一个方法如下：

```Java
private String parseLanguageFromCookie(HttpServletRequest req) {
    // 获取请求附带的所有Cookie:
    Cookie[] cookies = req.getCookies();
    // 如果获取到Cookie:
    if (cookies != null) {
        // 循环每个Cookie:
        for (Cookie cookie : cookies) {
        // 如果Cookie名称为lang:
        if (cookie.getName().equals("lang")) {
        // 返回Cookie的值:
            return cookie.getValue();
            }
        }
    }
    // 返回默认值:
    return "en";
}
```

可见，读取Cookie主要依靠遍历`HttpServletRequest`附带的所有Cookie。

## Filter 过滤器

在一个比较复杂的Web应用程序中，通常都有很多URL映射，对应的，也会有多个Servlet来处理URL。

我们考察这样一个论坛应用程序：

```Plain
            ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
               /             ┌──────────────┐
            │ ┌─────────────▶│ IndexServlet │ │
              │              └──────────────┘
            │ │/signin       ┌──────────────┐ │
              ├─────────────▶│SignInServlet │
            │ │              └──────────────┘ │
              │/signout      ┌──────────────┐
┌───────┐   │ ├─────────────▶│SignOutServlet│ │
│Browser├─────┤              └──────────────┘
└───────┘   │ │/user/profile ┌──────────────┐ │
              ├─────────────▶│ProfileServlet│
            │ │              └──────────────┘ │
              │/user/post    ┌──────────────┐
            │ ├─────────────▶│ PostServlet  │ │
              │              └──────────────┘
            │ │/user/reply   ┌──────────────┐ │
              └─────────────▶│ ReplyServlet │
            │                └──────────────┘ │
             ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
```

各个Servlet设计功能如下：

- IndexServlet：浏览帖子；
- SignInServlet：登录；
- SignOutServlet：登出；
- ProfileServlet：修改用户资料；
- PostServlet：发帖；
- ReplyServlet：回复。

其中，ProfileServlet、PostServlet和ReplyServlet都需要用户登录后才能操作，否则，应当直接跳转到登录页面。

我们可以直接把判断登录的逻辑写到这3个Servlet中，但是，同样的逻辑重复3次没有必要，并且，如果后续继续加Servlet并且也需要验证登录时，还需要继续重复这个检查逻辑。

为了把一些公用逻辑从各个Servlet中抽离出来，JavaEE的Servlet规范还提供了一种Filter组件，即过滤器，它的作用是，在HTTP请求到达Servlet之前，可以被一个或多个Filter预处理，类似打印日志、登录检查等逻辑，完全可以放到Filter中。

例如，我们编写一个最简单的EncodingFilter，它强制把输入和输出的编码设置为UTF-8：

```Java
@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        System.out.println("EncodingFilter:doFilter");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
```

编写Filter时，必须实现`Filter`接口，在`doFilter()`方法内部，要继续处理请求，必须调用`chain.doFilter()`。最后，用`@WebFilter`注解标注该Filter需要过滤的URL。这里的`/*`表示所有路径。

添加了Filter之后，整个请求的处理架构如下：

```Plain
            ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
                                   /             ┌──────────────┐
            │                     ┌─────────────▶│ IndexServlet │ │
                                  │              └──────────────┘
            │                     │/signin       ┌──────────────┐ │
                                  ├─────────────▶│SignInServlet │
            │                     │              └──────────────┘ │
                                  │/signout      ┌──────────────┐
┌───────┐   │   ┌──────────────┐  ├─────────────▶│SignOutServlet│ │
│Browser│──────▶│EncodingFilter├──┤              └──────────────┘
└───────┘   │   └──────────────┘  │/user/profile ┌──────────────┐ │
                                  ├─────────────▶│ProfileServlet│
            │                     │              └──────────────┘ │
                                  │/user/post    ┌──────────────┐
            │                     ├─────────────▶│ PostServlet  │ │
                                  │              └──────────────┘
            │                     │/user/reply   ┌──────────────┐ │
                                  └─────────────▶│ ReplyServlet │
            │                                    └──────────────┘ │
             ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
```

还可以继续添加其他Filter，例如LogFilter：

```Java
@WebFilter("/*")
public class LogFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        System.out.println("LogFilter: process "((HttpServletRequest) request).getRequestURI());
        chain.doFilter(request, response);
     }
}
```

多个Filter会组成一个链，每个请求都被链上的Filter依次处理：

```Plain
                                        ┌────────┐
                                     ┌─▶│ServletA│
                                     │  └────────┘
    ┌──────────────┐    ┌─────────┐  │  ┌────────┐
───▶│EncodingFilter│───▶│LogFilter│──┼─▶│ServletB│
    └──────────────┘    └─────────┘  │  └────────┘
                                     │  ┌────────┐
                                     └─▶│ServletC│
                                        └────────┘
```

有些细心的同学会问，有多个Filter的时候，Filter的顺序如何指定？多个Filter按不同顺序处理会造成处理结果不同吗？

答案是Filter的顺序确实对处理的结果有影响。但遗憾的是，Servlet规范并没有对`@WebFilter`注解标注的Filter规定顺序。如果一定要给每个Filter指定顺序，就必须在`web.xml`文件中对这些Filter再配置一遍，Filter的顺序就是**Filter** 在`web.xml`中 `filter-mapping` 元素的配置顺序。

注意到上述两个Filter的过滤路径都是`/*`，即它们会对所有请求进行过滤。也可以编写只对特定路径进行过滤的Filter，例如`AuthFilter`：

```Java
@WebFilter("/user/*")
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        System.out.println("AuthFilter: check authentication");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("user") == null) {
        // 未登录，自动跳转到登录页:
            System.out.println("AuthFilter: not signin!");
            resp.sendRedirect("/signin");
        } else {// 已登录，继续处理:
            chain.doFilter(request, response);
        }
    }
}
```

注意到`AuthFilter`只过滤以`/user/`开头的路径，因此：

- 如果一个请求路径类似`/user/profile`，那么它会被上述3个Filter依次处理；
- 如果一个请求路径类似`/test`，那么它会被上述2个Filter依次处理（不会被AuthFilter处理）。

再注意观察`AuthFilter`，当用户没有登录时，在`AuthFilter`内部，直接调用`resp.sendRedirect()`发送重定向，且没有调用`chain.doFilter()`，因此，当用户没有登录时，请求到达`AuthFilter`后，不再继续处理，即后续的Filter和任何Servlet都没有机会处理该请求了。

可见，Filter可以有针对性地拦截或者放行HTTP请求。

如果一个Filter在当前请求中生效，但什么都没有做：

```Java
@WebFilter("/*")
public class MyFilter implements Filter {
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
    // TODO
    }
}
```

那么，用户将看到一个空白页，因为请求没有继续处理，默认响应是200+空白输出。

<mark>如果Filter要使请求继续被处理，就一定要调用chain.doFilter()！</mark>

如果我们使用MVC模式，即一个统一的`DispatcherServlet`入口，加上多个Controller，这种模式下Filter仍然是正常工作的。例如，一个处理`/user/*`的Filter实际上作用于那些处理`/user/`开头的Controller方法之前。

MVC模式如果有时间的话上课的时候简单介绍一下，大家可以提前自己了解一下。

## Listener

### Listener介绍

除了Servlet和Filter外，JavaEE的Servlet规范还提供了第三种组件：Listener。

Listener顾名思义就是监听器，有好几种Listener，其中最常用的是`ServletContextListener`，我们编写一个实现了`ServletContextListener`接口的类如下：

```Java
@WebListener
public class AppListener implements ServletContextListener {
    // 在此初始化WebApp,例如打开数据库连接池等:
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("WebApp initialized.");
    }
    // 在此清理WebApp,例如关闭数据库连接池等:
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("WebApp destroyed.");
    }
}
```

任何标注为`@WebListener`，且实现了特定接口的类会被Web服务器自动初始化。上述`AppListener`实现了`ServletContextListener`接口，它会在整个Web应用程序初始化完成后，以及Web应用程序关闭后获得回调通知。我们可以把初始化数据库连接池等工作放到`contextInitialized()`回调方法中，把清理资源的工作放到`contextDestroyed()`回调方法中，因为Web服务器保证在`contextInitialized()`执行后，才会接受用户的HTTP请求。

很多第三方Web框架都会通过一个`ServletContextListener`接口初始化自己。

除了`ServletContextListener`外，还有几种Listener：

- HttpSessionListener：监听HttpSession的创建和销毁事件；
- ServletRequestListener：监听ServletRequest请求的创建和销毁事件；
- ServletRequestAttributeListener：监听ServletRequest请求的属性变化事件（即调用`ServletRequest.setAttribute()`方法）；
- ServletContextAttributeListener：监听ServletContext的属性变化事件（即调用`ServletContext.setAttribute()`方法）；

### ServletContext

一个Web服务器可以运行一个或多个WebApp，对于每个WebApp，Web服务器都会为其创建一个全局唯一的`ServletContext`实例，我们在`AppListener`里面编写的两个回调方法实际上对应的就是`ServletContext`实例的创建和销毁：

```Java
public void contextInitialized(ServletContextEvent sce) {
    System.out.println("WebApp initialized: ServletContext = "sce.getServletContext());
}
```

`ServletRequest`、`HttpSession`等很多对象也提供`getServletContext()`方法获取到同一个`ServletContext`实例。`ServletContext`实例最大的作用就是设置和共享全局信息。

此外，`ServletContext`还提供了动态添加Servlet、Filter、Listener等功能，它允许应用程序在运行期间动态添加一个组件，虽然这个功能不是很常用。

# 作业

## 搭建一个简单的 Java Web 应用实现以下功能

要求如下：

1. **实现登录功能**

- 创建一个 Servlet（例如 `LoginServlet`）来处理登录请求。
- 在 `LoginServlet` 中，验证用户输入的用户名和密码（例如，用户名为 `admin`，密码为 `password`）。
- 如果验证成功，显示欢迎信息（如欢迎xx用户使用等）；
- 如果验证失败，返回登录页面并显示错误信息（如 密码错误、用户不存在等）。
- 使用session或者cookie来实现会话保存

1. **使用Filter过滤器实现登录校验**

- 思考需要用什么来判断用户是否登录
- 未登录怎么处理
- 是否是所有接口都需要拦截

1. **实现文件上传功能（能实现图片的上传回显更好）**

- 创建一个 Servlet（例如 `UploadServlet`）来处理文件上传请求。
- 在 `UploadServlet` 中，接收上传的文件并保存到服务器的指定目录。
- 上传成功后，显示上传成功的信息，并显示上传的文件列表。

> 作业还是有点难度的，大家应该是需要在网上查资料才能做出来。

**作业提交邮箱： ** **huangyuhuai@lanshan.email**

**这次的作业提交希望 附有截图，提交** **github地址** 

**关于git也是一个程序员的基本技能（虽然我也还不会呢），希望大家抽时间可以学习一下**
