# 蓝山Java第十节课（Linux基本操作及项目部署）

## Linux

### Linux简介

Linux 内核最初只是由芬兰人林纳斯·托瓦兹（Linus Torvalds）在赫尔辛基大学上学时出于个人爱好而编写的。

Linux 是一套免费使用和自由传播的类 Unix 操作系统，是一个基于 POSIX 和 UNIX 的多用户、多任务、支持多线程和多 CPU 的操作系统。

Linux 能运行主要的 UNIX 工具软件、应用程序和网络协议。它支持 32 位和 64 位硬件。Linux 继承了 Unix 以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。

#### Linux 的发行版

Linux 的发行版说简单点就是将 Linux 内核与应用软件做一个打包。

![image](https://github.com/user-attachments/assets/b6bb96c6-05c9-4aaf-89e3-a665475314c7)


目前市面上较知名的发行版有：Ubuntu、RedHat、CentOS、Debian、Fedora、SuSE、OpenSUSE、Arch Linux、SolusOS 等。

![image](https://github.com/user-attachments/assets/8e6841f1-2067-4543-8cb0-b8c374ad8b4b)


### Linux 安装

我在这里介绍一下虚拟机安装 Linux 系统，安装步骤比较繁琐，现在其实云服务器挺普遍的，学生在阿里云可以领一段时间的免费服务器，如果自己不想搭建，也可以直接领一台学习用用也可以。

可以跟着下面这个链接的教程来安装，如果需要 iso 文件（我只有ubuntu24和 ubuntu18）或者 vmware的安装包（我的版本是17）可以 找我要，也可以自己下载，vmware可能会需要一个许可证，这个可以在csdn上面搜了直接用，

[教程](https://blog.csdn.net/m0_51913750/article/details/131604868)

### Linux 系统目录结构

登录系统后，在当前命令窗口下输入命令：

```Shell
 ls /
```

你会看到如下图所示:

![image](https://github.com/user-attachments/assets/d485bbc4-bcde-423e-b68d-77fe9843c92c)


树状目录结构：

![image](https://github.com/user-attachments/assets/e7932c5a-ddbf-4376-ae04-556ac72c75bf)

以下是对这些目录的解释（看看就行，反正也记不住）：

- **/****bin**： bin 是 Binaries (二进制文件) 的缩写, 这个目录存放着最经常使用的命令。
- **/boot：** 这里存放的是启动 Linux 时使用的一些核心文件，包括一些连接文件以及镜像文件。
- **/****dev** **：** dev 是 Device(设备) 的缩写, 该目录下存放的是 Linux 的外部设备，在 Linux 中访问设备的方式和访问文件的方式是相同的。
- **/etc：** etc 是 Etcetera(等等) 的缩写,这个目录用来存放所有的系统管理所需要的配置文件和子目录。
- **/home**： 用户的主目录，在 Linux 中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的，如上图中的 alice、bob 和 eve。
- **/lib**： lib 是 Library(库) 的缩写这个目录里存放着系统最基本的动态连接共享库，其作用类似于 Windows 里的 DLL 文件。几乎所有的应用程序都需要用到这些共享库。
- **/lost+found**： 这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件。
- **/media**： linux 系统会自动识别一些设备，例如U盘、光驱等等，当识别后，Linux 会把识别的设备挂载到这个目录下。
- **/mnt**： 系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将光驱挂载在 /mnt/ 上，然后进入该目录就可以查看光驱里的内容了。
- **/****opt**： opt 是 optional(可选) 的缩写，这是给主机额外安装软件所摆放的目录。比如你安装一个ORACLE数据库则就可以放到这个目录下。默认是空的。
- **/****proc**： proc 是 Processes(进程) 的缩写，/proc 是一种伪文件系统（也即虚拟文件系统），存储的是当前内核运行状态的一系列特殊文件，这个目录是一个虚拟的目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息。 这个目录的内容不在硬盘上而是在内存里，我们也可以直接修改里面的某些文件，比如可以通过下面的命令来屏蔽主机的ping命令，使别人无法ping你的机器：
- echo 1 > /proc/sys/net/ipv4/icmp_echo_ignore_all
- **/root**： 该目录为系统管理员，也称作超级权限者的用户主目录。
- **/sbin**： s 就是 Super User 的意思，是 Superuser Binaries (超级用户的二进制文件) 的缩写，这里存放的是系统管理员使用的系统管理程序。
- **/selinux**： 这个目录是 Redhat/CentOS 所特有的目录，Selinux 是一个安全机制，类似于 windows 的防火墙，但是这套机制比较复杂，这个目录就是存放selinux相关的文件的。
- **/srv**： 该目录存放一些服务启动之后需要提取的数据。
- **/sys**：
- 这是 Linux2.6 内核的一个很大的变化。该目录下安装了 2.6 内核中新出现的一个文件系统 sysfs 。
- sysfs 文件系统集成了下面3种文件系统的信息：针对进程信息的 proc 文件系统、针对设备的 devfs 文件系统以及针对伪终端的 devpts 文件系统。
- 该文件系统是内核设备树的一个直观反映。
- 当一个内核对象被创建的时候，对应的文件和目录也在内核对象子系统中被创建。
- **/tmp**： tmp 是 temporary(临时) 的缩写这个目录是用来存放一些临时文件的。
- **/usr**： usr 是 unix system resources(unix 系统资源) 的缩写，这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似于 windows 下的 program files 目录。
- **/usr/bin：** 系统用户使用的应用程序。
- **/usr/sbin：** 超级用户使用的比较高级的管理程序和系统守护程序。
- **/usr/src：** 内核源代码默认的放置目录。
- **/var**： var 是 variable(变量) 的缩写，这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括各种日志文件。
- **/run**： 是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。如果你的系统上有 /var/run 目录，应该让它指向 run。

### Linux 远程登录

Linux 一般作为服务器使用，而服务器一般放在机房，你不可能在机房操作你的 Linux 服务器。

这时我们就需要远程登录到Linux服务器来管理维护系统。

Linux 系统中是通过 ssh 服务实现的远程登录功能，默认 ssh 服务端口号为 22。

Window 系统上 Linux 远程登录客户端有 SecureCRT, Xshell, finalshell 等，本文以 finalshell 为例来登录远程服务器。

finalshell 下载地址: https://www.hostbuf.com/

安装就不介绍了，就跟普通软件一样的。

> 我这里的命令是直接在 finalshell 中演示的，你还没有远程连接成功的时候，需要在虚拟机中安装的 ubuntu 的终端来执行命令

#### 确保 Linux 服务器已启用 SSH

首先，确保你的 Linux 服务器上已经安装并运行了 SSH 服务。对于大多数 Linux 发行版，`OpenSSH` 是默认的 SSH 服务器。您可以使用以下命令来安装它：

```VBScript
sudo apt-get update
sudo apt-get install openssh-server
```

 然后，确保 SSH 服务正在运行：

```Lua
sudo service ssh status
```

![image](https://github.com/user-attachments/assets/463be7f0-8ddf-4ebc-a9d7-e177263139e8)


这样就是成功运行的样子。

#### 获取 Linux 服务器的 IP 地址

在 Linux 服务器上，使用以下命令获取 IP 地址：

```Plain
 ifconfig
```

 记下其中的 IPv4 地址，例如 `192.168.200.131`。

![image](https://github.com/user-attachments/assets/65b3341a-59ae-43b8-85bb-b258e29c698e)


#### 从 finalshell 连接 ubuntu 服务器

打开 finalshell，点击下图箭头指向的图标

![image](https://github.com/user-attachments/assets/2aeb30bc-5932-4fe4-b996-00887beba293)


再点击如下图的图标，再选择ssh连接

![image](https://github.com/user-attachments/assets/7c67074f-5895-4aac-bd8e-e56f25be0eea)


![image](https://github.com/user-attachments/assets/6876b644-fc41-4185-a61c-d0f0c26f5968)


然后会出来一个输入连接信息的窗口，输入完毕后点击确定就可以连接。

![image](https://github.com/user-attachments/assets/c36db3ec-8bbd-4ef5-baef-4c84628138e0)


连接成功之后大概是这样的，成功之后就可以在这里操作虚拟机了

![image](https://github.com/user-attachments/assets/ea4b1b6c-edc7-430f-95ae-3e566373c5ae)


### Linux 文件基本属性

Linux 系统是一种典型的多用户系统，不同的用户处于不同的地位，拥有不同的权限。

为了保护系统的安全性，Linux 系统对不同的用户访问同一文件（包括目录文件）的权限做了不同的规定。

在 Linux 中我们通常使用以下两个命令来修改文件或目录的所属用户与权限：

- chown (change owner) ： 修改所属用户与组。
- chmod (change mode) ： 修改用户的权限。

在 Linux 中我们可以使用 **ll** 或者 **ls** **–l** 命令来显示一个文件的属性以及文件所属的用户和组，如：

```Shell
[root@www /]# ls -l
total 64
dr-xr-xr-x   2 root root 4096 Dec 14  2012 bin
dr-xr-xr-x   4 root root 4096 Apr 19  2012 boot
……
```

实例中，**bin** 文件的第一个属性用 **d** 表示。**d** 在 Linux 中代表该文件是一个目录文件。

在 Linux 中第一个字符代表这个文件是目录、文件或链接文件等等。

- 当为 **d** 则是目录
- 当为 **-** 则是文件；
- 若是 **l** 则表示为链接文档(link file)；
- 若是 **b** 则表示为装置文件里面的可供储存的接口设备(可随机存取装置)；
- 若是 **c** 则表示为装置文件里面的串行端口设备，例如键盘、鼠标(一次性读取装置)。

接下来的字符中，以三个为一组，且均为 **rwx** 的三个参数的组合。其中， **r** 代表可读(read)、 **w** 代表可写(write)、 **x** 代表可执行(execute)。 要注意的是，这三个权限的位置不会改变，如果没有权限，就会出现减号 **-** 而已。

![image](https://github.com/user-attachments/assets/a60e3d4d-a4c2-461a-a01f-03d0e2d73ab1)


每个文件的属性由左边第一部分的 10 个字符来确定（如下图）。

![image](https://github.com/user-attachments/assets/b5f57357-d415-4204-b4e0-b33a89320f4e)

从左至右用 **0-9** 这些数字来表示。

第 **0** 位确定文件类型，第 **1-3** 位确定属主（该文件的所有者）拥有该文件的权限。

第4-6位确定属组（所有者的同组用户）拥有该文件的权限，第7-9位确定其他用户拥有该文件的权限。

其中，第 **1、4、7** 位表示读权限，如果用 **r** 字符表示，则有读权限，如果用 **-** 字符表示，则没有读权限；

第 **2、5、8** 位表示写权限，如果用 **w** 字符表示，则有写权限，如果用 **-** 字符表示没有写权限；第 **3、6、9** 位表示可执行权限，如果用 **x** 字符表示，则有执行权限，如果用 **-** 字符表示，则没有执行权限。

#### Linux文件属主和属组

```Shell
[root@www /]# ls -l
total 64
drwxr-xr-x 2 root  root  4096 Feb 15 14:46 cron
drwxr-xr-x 3 mysql mysql 4096 Apr 21  2014 mysql
……
```

对于文件来说，它都有一个特定的所有者，也就是对该文件具有所有权的用户。

同时，在Linux系统中，用户是按组分类的，一个用户属于一个或多个组。

文件所有者以外的用户又可以分为文件所属组的同组用户和其他用户。

因此，Linux系统按文件所有者、文件所有者同组用户和其他用户来规定了不同的文件访问权限。

在以上实例中，mysql 文件是一个目录文件，属主和属组都为 mysql，属主有可读、可写、可执行的权限；与属主同组的其他用户有可读和可执行的权限；其他用户也有可读和可执行的权限。

对于 root 用户来说，一般情况下，文件的权限对其不起作用。

#### 更改文件属性

1. chgrp：更改文件属组

语法：

```Shell
chgrp [-R] 属组名 文件名
```

参数选项

- -R：递归更改文件属组，就是在更改某个目录文件的属组时，如果加上 **-R** 的参数，那么该目录下的所有文件的属组都会更改。

1. chown：更改文件所有者（owner），也可以同时更改文件所属组。

语法：

```Shell
chown [–R] 所有者 文件名
chown [-R] 所有者:属组名 文件名
```

进入 /root 目录（~）将install.log的拥有者改为bin这个账号：

```Shell
[root@www ~] cd ~
[root@www ~]# chown bin install.log
[root@www ~]# ls -l
-rw-r--r--  1 bin  users 68495 Jun 25 08:53 install.log
```

将install.log的拥有者与群组改回为root：

```Shell
[root@www ~] cd ~
[root@www ~]# chown bin install.log
[root@www ~]# ls -l
-rw-r--r--  1 bin  users 68495 Jun 25 08:53 install.log
```

1. chmod：更改文件9个属性

Linux文件属性有两种设置方法，一种是数字，一种是符号。

Linux 文件的基本权限就有九个，分别是 **owner/group/others(拥有者/组/其他)** 三种身份各有自己的 **read/write/execute** 权限。

先复习一下刚刚上面提到的数据：文件的权限字符为： **-rwxrwxrwx** ， 这九个权限是三个三个一组的！其中，我们可以使用数字来代表各个权限，各权限的分数对照表如下：

- r:4
- w:2
- x:1

每种身份(owner/group/others)各自的三个权限(r/w/x)分数是需要累加的，例如当权限为： **-rwxrwx---** 分数则是：

- owner = rwx = 4+2+1 = 7
- group = rwx = 4+2+1 = 7
- others= --- = 0+0+0 = 0

所以等一下我们设定权限的变更时，该文件的权限数字就是 **770**。变更权限的指令 chmod 的语法是这样的：

```Shell
 chmod [-R] xyz 文件或目录
```

选项与参数：

- **xyz** : 就是刚刚提到的数字类型的权限属性，为 **rwx** 属性数值的相加。
- **-R** : 进行递归(recursive)的持续变更，以及连同次目录下的所有文件都会变更

举例来说，如果要将 **.bashrc** 这个文件所有的权限都设定启用，那么命令如下：

```Shell
[root@www ~]# ls -al .bashrc
-rw-r--r--  1 root root 395 Jul  4 11:45 .bashrc
[root@www ~]# chmod 777 .bashrc
[root@www ~]# ls -al .bashrc
-rwxrwxrwx  1 root root 395 Jul  4 11:45 .bashrc
```

那如果要将权限变成 *-rwxr-xr--* 呢？那么权限的分数就成为 [4+2+1][4+0+1][4+0+0]=754。

### Linux 文件与目录管理

我们知道 Linux 的目录结构为树状结构，最顶级的目录为根目录 **/**。

其他目录通过挂载可以将它们添加到树中，通过解除挂载可以移除它们。

在开始本教程前我们需要先知道什么是绝对路径与相对路径。

- **绝对路径：** 路径的写法，由根目录 **/** 写起，例如： /usr/share/doc 这个目录。
- **相对路径：** 路径的写法，不是由 **/** 写起，例如由 /usr/share/doc 要到 /usr/share/man 底下时，可以写成： **cd ../man** 这就是相对路径的写法。

### 处理目录的常用命令

接下来我们就来看几个常见的处理目录的命令吧：

- ls（英文全拼：list files）: 列出目录及文件名
- cd（英文全拼：change directory）：切换目录
- pwd（英文全拼：print work directory）：显示目前的目录
- mkdir（英文全拼：make directory）：创建一个新的目录
- rmdir（英文全拼：remove directory）：删除一个空的目录
- cp（英文全拼：copy file）: 复制文件或目录
- rm（英文全拼：remove）: 删除文件或目录
- mv（英文全拼：move file）: 移动文件与目录，或修改文件与目录的名称

#### ls (列出目录)

在Linux系统当中， ls 命令可能是最常被运行的。

语法：

```Shell
[root@www ~]# ls [-aAdfFhilnrRSt] 目录名称
[root@www ~]# ls [--color={never,auto,always}] 目录名称
[root@www ~]# ls [--full-time] 目录名称
```

选项与参数：

- -a ：全部的文件，连同隐藏文件( 开头为 . 的文件) 一起列出来(常用)
- -d ：仅列出目录本身，而不是列出目录内的文件数据(常用)
- -l ：长数据串列出，包含文件的属性与权限等等数据；(常用)

将目录下的所有文件列出来(含属性与隐藏档)

```Shell
[root@www ~]# ls -al ~
```

#### cd (切换目录)

cd是Change Directory的缩写，这是用来变换工作目录的命令。

语法：

```Shell
 cd [相对路径或绝对路径]
#使用 mkdir 命令创建 runoob 目录
[root@www ~]# mkdir runoob

#使用绝对路径切换到 runoob 目录
[root@www ~]# cd /root/runoob/

#使用相对路径切换到 runoob 目录
[root@www ~]# cd ./runoob/

# 表示回到自己的家目录，亦即是 /root 这个目录
[root@www runoob]# cd ~

# 表示去到目前的上一级目录，亦即是 /root 的上一级目录的意思；
[root@www ~]# cd ..
```

接下来大家多操作几次应该就可以很好的理解 cd 命令的。

#### pwd (显示目前所在的目录)

pwd 是 **Print Working Directory** 的缩写，也就是显示目前所在目录的命令。

```Shell
[root@www ~]# pwd [-P]
```

选项与参数：

- **-P** ：显示出确实的路径，而非使用链接 (link) 路径。

实例：单纯显示出目前的工作目录：
![image](https://github.com/user-attachments/assets/1ff871be-f2d3-435a-813c-ef8991438359)


#### mkdir (创建新目录)

如果想要创建新的目录的话，那么就使用mkdir (make directory)吧。

语法：

```Shell
mkdir [-mp] 目录名称
```

选项与参数：

- -m ：配置文件的权限喔！直接配置，不需要看默认权限 (umask) 的脸色～
- -p ：帮助你直接将所需要的目录(包含上一级目录)递归创建起来！

实例：请到/tmp底下尝试创建数个新目录看看：

```Shell
[root@www ~]# cd /tmp
[root@www tmp]# mkdir test    <==创建一名为 test 的新目录
[root@www tmp]# mkdir test1/test2/test3/test4
mkdir: cannot create directory `test1/test2/test3/test4': 
No such file or directory       <== 没办法直接创建此目录啊！
[root@www tmp]# mkdir -p test1/test2/test3/test4
```

加了这个 -p 的选项，可以自行帮你创建多层目录！

实例：创建权限为 **rwx--x--x** 的目录。

```Shell
[root@www tmp]# mkdir -m 711 test2
[root@www tmp]# ls -l
drwxr-xr-x  3 root  root 4096 Jul 18 12:50 test
drwxr-xr-x  3 root  root 4096 Jul 18 12:53 test1
drwx--x--x  2 root  root 4096 Jul 18 12:54 test2
```

上面的权限部分，如果没有加上 -m 来强制配置属性，系统会使用默认属性。

如果我们使用 -m ，如上例我们给予 -m 711 来给予新的目录 drwx--x--x 的权限。

#### rmdir (删除空的目录)

语法：

```Shell
rmdir [-p] 目录名称
```

选项与参数：

- **-p ：**从该目录起，一次删除多级空目录

将 mkdir 实例中创建的目录(/tmp 底下)删除掉！

```Shell
[root@www tmp]# ls -l   <==看看有多少目录存在？
drwxr-xr-x  3 root  root 4096 Jul 18 12:50 test
drwxr-xr-x  3 root  root 4096 Jul 18 12:53 test1
drwx--x--x  2 root  root 4096 Jul 18 12:54 test2
[root@www tmp]# rmdir test   <==可直接删除掉，没问题
[root@www tmp]# rmdir test1  <==因为尚有内容，所以无法删除！
rmdir: `test1': Directory not empty
[root@www tmp]# rmdir -p test1/test2/test3/test4
[root@www tmp]# ls -l        <==您看看，底下的输出中test与test1不见了！
drwx--x--x  2 root  root 4096 Jul 18 12:54 test2
```

利用 -p 这个选项，立刻就可以将 test1/test2/test3/test4 一次删除。

不过要注意的是，这个 rmdir 仅能删除空的目录，你可以使用 rm 命令来删除非空目录。

#### cp (复制文件或目录)

cp 即拷贝文件和目录。

语法:

```Shell
[root@www ~]# cp [-adfilprsu] 来源档(source) 目标档(destination)
[root@www ~]# cp [options] source1 source2 source3 .... directory
```

选项与参数：

- **-a：**相当於 -pdr 的意思，至於 pdr 请参考下列说明；(常用)
- **-d：**若来源档为链接档的属性(link file)，则复制链接档属性而非文件本身；
- **-f：**为强制(force)的意思，若目标文件已经存在且无法开启，则移除后再尝试一次；
- **-i：**若目标档(destination)已经存在时，在覆盖时会先询问动作的进行(常用)
- **-l：**进行硬式链接(hard link)的链接档创建，而非复制文件本身；
- **-p：**连同文件的属性一起复制过去，而非使用默认属性(备份常用)；
- **-r：**递归持续复制，用於目录的复制行为；(常用)
- **-s：**复制成为符号链接档 (symbolic link)，亦即『捷径』文件；
- **-u：**若 destination 比 source 旧才升级 destination ！

用 root 身份，将 root 目录下的 .bashrc 复制到 /tmp 下，并命名为 bashrc

```Shell
[root@www ~]# cp ~/.bashrc /tmp/bashrc
[root@www ~]# cp -i ~/.bashrc /tmp/bashrc
cp: overwrite `/tmp/bashrc'? n  <==n不覆盖，y为覆盖
```

#### rm (移除文件或目录)

语法：

```Shell
rm [-fir] 文件或目录
```

选项与参数：

- -f ：就是 force 的意思，忽略不存在的文件，不会出现警告信息；
- -i ：互动模式，在删除前会询问使用者是否动作
- -r ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！

将刚刚在 cp 的实例中创建的 bashrc 删除掉！

```Shell
[root@www tmp]# rm -i bashrc
rm: remove regular file `bashrc'? y
```

如果加上 -i 的选项就会主动询问喔，避免你删除到错误的档名！

#### mv (移动文件与目录，或修改名称)

语法：

```Shell
[root@www ~]# mv [-fiu] source destination
[root@www ~]# mv [options] source1 source2 source3 .... directory
```

选项与参数：

- -f ：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖；
- -i ：若目标文件 (destination) 已经存在时，就会询问是否覆盖！
- -u ：若目标文件已经存在，且 source 比较新，才会升级 (update)

复制一文件，创建一目录，将文件移动到目录中

```Shell
[root@www ~]# cd /tmp
[root@www tmp]# cp ~/.bashrc bashrc
[root@www tmp]# mkdir mvtest
[root@www tmp]# mv bashrc mvtest
```

将某个文件移动到某个目录去，就是这样做！

将刚刚的目录名称更名为 mvtest2

```Shell
[root@www tmp]# mv mvtest mvtest2
```

#### Linux 文件内容查看

Linux系统中使用以下命令来查看文件的内容：

- cat  由第一行开始显示文件内容
- tac  从最后一行开始显示，可以看出 tac 是 cat 的倒着写！
- nl   显示的时候，顺道输出行号！
- more 一页一页的显示文件内容
- less 与 more 类似，但是比 more 更好的是，他可以往前翻页！
- head 只看头几行
- tail 只看尾巴几行

##### cat

由第一行开始显示文件内容

语法：

```Shell
cat [-AbEnTv]
```

选项与参数：

- -A ：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已；
- -b ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
- -E ：将结尾的断行字节 $ 显示出来；
- -n ：列印出行号，连同空白行也会有行号，与 -b 的选项不同；
- -T ：将 [tab] 按键以 ^I 显示出来；
- -v ：列出一些看不出来的特殊字符

检看 /etc/issue 这个文件的内容：

![image](https://github.com/user-attachments/assets/b9fab851-d111-48b2-916b-3ae57682f46c)


##### tac

tac与cat命令刚好相反，文件内容从最后一行开始显示，可以看出 tac 是 cat 的倒着写！

##### nl

显示行号

语法：

```Shell
nl [-bnw] 文件
```

选项与参数：

- -b ：指定行号指定的方式，主要有两种： -b a ：表示不论是否为空行，也同样列出行号(类似 cat -n)； -b t ：如果有空行，空的那一行不要列出行号(默认值)；
- -n ：列出行号表示的方法，主要有三种： -n ln ：行号在荧幕的最左方显示； -n rn ：行号在自己栏位的最右方显示，且不加 0 ； -n rz ：行号在自己栏位的最右方显示，且加 0 ；
- -w ：行号栏位的占用的位数。

实例一：用 nl 列出 /etc/issue 的内容

![image](https://github.com/user-attachments/assets/d24b8f83-d10f-4073-93c9-e38b54f45466)


##### more

一页一页翻动,你的光标会在箭头处等待你的命令

![image](https://github.com/user-attachments/assets/1cfc8272-b31f-4140-8658-7fbe50c2d706)


在 more 这个程序的运行过程中，你有几个按键可以按的：

- 空白键 (space)：代表向下翻一页；
- Enter         ：代表向下翻『一行』；
- /字串         ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字；
- :f            ：立刻显示出档名以及目前显示的行数；
- q             ：代表立刻离开 more ，不再显示该文件内容。
- b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用。

##### less

less运行时可以输入的命令有：

- 空白键    ：向下翻动一页；
- [pagedown]：向下翻动一页；
- [pageup]  ：向上翻动一页；
- /字串     ：向下搜寻『字串』的功能；
- ?字串     ：向上搜寻『字串』的功能；
- n         ：重复前一个搜寻 (与 / 或 ? 有关！)
- N         ：反向的重复前一个搜寻 (与 / 或 ? 有关！)
- q         ：离开 less 这个程序；

### Linux vi/vim

vim是Linux系统内置的「文本编辑器」，用于查看或编辑文件的内容，学会使用vim编辑器，将在Linux终端中畅通无阻。

1. **三种模式**

vim编辑器有三种模式：命令模式、编辑模式、末行模式。

- 「命令模式」：可以进行删除、复制、粘贴等快捷操作。
- 「编辑模式」：可以编辑文件内容。
- 「末行模式」：可以通过命令操作文件，比如搜索、保存、退出等。

1. **三种打开方式**

注意：使用vim打开文件时，尽量使用 Tab 键补全文件名，如果文件名不存在，则会创建一个新的文件。

1）打开指定文件：

```Shell
vim 文件路径
[root ]# vim /test/a.txt
```

提示：输入`:wq` 退出文件。

2）打开文件时，将光标移动到「指定行」

```Shell
vim +行数 文件路径
[root ]# vim +3 /test/a.txt
```

打开文件时，光标停在了第三行（默认停在首行） 注意：加号 + 是语法格式，不要漏掉

3）打开文件时，「高亮」显示关键字

```Shell
vim +/关键字 文件路径
[root ]# vim +/root /test/a.txt
```

打开文件时，字符串 root 已高亮显示

![image](https://github.com/user-attachments/assets/9df707e0-843b-47b7-bd8c-a6a71fa83027)


1. **命令模式**

打开文件后，默认就是命令模式，可以进行光标移动、复制粘贴、搜索替换等操作。

注意：命令模式中，敲击的键盘会被识别为命令，而不是输入内容。

- **移动光标**

| 指令                  | 作用                 |
| --------------------- | -------------------- |
| h 或 左方向键（←）    | 光标向左移动一个字符 |
| l 或 右方向键（→）    | 光标向右移动一个字符 |
| k 或 上方向键（↑）    | 光标向上移动一个字符 |
| j 或 下方向键（↓）    | 光标向下移动一个字符 |
| 0 或 Ctrl+6 或 home键 | 光标移动到行首       |
| Ctrl+4 或 end键       | 光标移动到行尾       |
| Ctrl + f 或 pg up键   | 【屏幕】向下移动一页 |
| Ctrl + b 或 pg dn键   | 【屏幕】向上移动一页 |
| gg                    | 移动到文件的第一行   |
| G                     | 移动到文件的最后一行 |
| nG                    | 移动到文件的第n行    |

- **搜索替换**

| 指令                 | 作用                                               |
| -------------------- | -------------------------------------------------- |
| /字符串 + 回车       | 向下搜索指定字符串                                 |
| n                    | 继续向下搜索下一个字符串                           |
| ?字符串 + 回车       | 向上搜索指定字符串                                 |
| N                    | 继续向上搜索下一个字符串                           |
| :%s/word1/word2/g    | 将文件中的word1替换为word2                         |
| :%s/word1/word2/gc   | 将文件中的word1替换为word2，但替换前需要用户确认！ |
| :1,10s/word1/word2/g | 将第1行到第10行的word1替换为word2                  |

- 复制粘贴删除

| 指令        | 作用               |
| ----------- | ------------------ |
| x           | 删除后一个字符     |
| X           | 删除前一个字符     |
| dd          | 剪切一行           |
| 数字dd      | 剪切多行           |
| yy          | 复制一行           |
| 数字yy      | 复制多行           |
| p           | 粘贴到下一行       |
| P           | 粘贴到上一行       |
| u           | 撤回操作           |
| Ctrl + r    | 撤回刚才的撤回操作 |
| .（小数点） | 重复刚才的操作     |

 提示：

1） vim中的「数字键」代表重复次数，可以配合其他指令简化操作，比如：

-  10↓或10j可一次向下移动10行。
-  2p可以一次粘贴2行
-  2dd可以一次剪切2行
-  以此类推…

1. **编辑模式**

命令模式中按下 i 键进入编辑模式，也就是vim打开文件后按下 i 键进入编辑模式。

| 指令  | 作用                                                         |
| ----- | ------------------------------------------------------------ |
| i     | 进入输入模式，进入后显示 – INSTER–                           |
| o     | 进入输入模式，在光标下一行插入新行，进入后显示 – INSTER –    |
| R     | 进入取代模式，输入的值会取代光标所在的内容，进入后显示 – REPLACE – |
| esc键 | 退出编辑模式（输入模式）                                     |

提示：

1）输入模式和取代模式都是编辑模式，屏幕左下角出现 – INSTER – 或 – REPLACE –字样，表示进入了编辑模式，这时才可以输入内容。

![image](https://github.com/user-attachments/assets/fde7ef8a-b1fc-4fbf-ba58-71b6cd844691)


2）按下esc键，确认下右下角 – INSTER – 或 – REPLACE – 字样消失后，再进行其他操作，以免误操。

3）想要「撤回」编辑模式下的操作，需要先退出编辑模式，再按 u 键

1. **末行模式**

英文状态的 `:`键 进入末行模式

命令模式 或 vim打开文件时，按下 `:` 键，左下角出现 ：时，即表示末行模式。

![image](https://github.com/user-attachments/assets/f32d638d-6287-47b9-8578-71938873b86d)


| 指令            | 作用                                               |
| --------------- | -------------------------------------------------- |
| :w              | 保存                                               |
| :q              | 退出                                               |
| :wq             | 保存后退出                                         |
| :q!             | 不保存，强制退出                                   |
| :set nu         | 显示行号                                           |
| :set nonu       | 取消 显示行号                                      |
| :w [文件名]     | 将编辑后的内容保存到另一个文件中（另存为）         |
| :2,5 w [文件名] | 将第2到5行的内容保存到另一个文件中（指定行另存为） |
| :r [文件名]     | 输入另一个文件的内容，到光标的下一行               |

### Linux apt 命令

apt（Advanced Packaging Tool）是一个在 Debian 和 Ubuntu 中的 Shell 前端软件包管理器。

apt 命令提供了查找、安装、升级、删除某一个、一组甚至全部软件包的命令，而且命令简洁而又好记。

apt 命令执行需要超级管理员权限(root)。

#### apt 语法

  apt [options] [command] [package ...]

- **options：**可选，选项包括 -h（帮助），-y（当安装过程提示选择全部为"yes"），-q（不显示安装的过程）等等。
- **command：**要进行的操作。
- **package**：安装的包名。

#### apt 常用命令

- 列出所有可更新的软件清单命令：**sudo** **apt** **update**
- 升级软件包：**sudo** **apt** **upgrade**
- 列出可更新的软件包及版本信息：**apt** **list --upgradable**
- 升级软件包，升级前先删除需要更新软件包：**sudo** **apt** **full-upgrade**
- 安装指定的软件命令：**sudo** **apt** **install** **<package_name>**
- 安装多个软件包：**sudo** **apt** **install** **<package_1> <package_2> <package_3>**
- 更新指定的软件命令：**sudo** **apt** **update <package_name>**
- 显示软件包具体信息,例如：版本号，安装大小，依赖关系等等：**sudo** **apt** **show <package_name>**
- 删除软件包命令：**sudo** **apt** **remove <package_name>**
- 清理不再使用的依赖和库文件: **sudo** **apt** **autoremove**
- 移除软件包及配置文件: **sudo** **apt** **purge <package_name>**
- 查找软件包命令： **sudo** **apt** **search <keyword>**
- 列出所有已安装的包：**apt** **list --installed**
- 列出所有已安装的包的版本信息：**apt** **list --all-versions**

## Docker

### Docker 架构

Docker 架构是基于客户端-服务器模式的，其中包括多个关键组件，确保容器化应用的高效构建、管理和运行。

Docker 的架构设计使得开发者能够轻松地将应用程序与其所有依赖封装在一个可移植的容器中，并在不同的环境中一致地运行。

Docker 使用客户端-服务器 (C/S) 架构模式，使用远程 API 来管理和创建 Docker 容器。

Docker 容器通过 Docker 镜像来创建。

容器与镜像的关系类似于面向对象编程中的对象与类。

![image](https://github.com/user-attachments/assets/059424c0-fd0c-4db3-96be-8a123c4162dd)


- `镜像`：一个镜像代表一个应用环境，他是一个只读的文件，如 MySQL 镜像、Tomcat 镜像、Nginx 镜像等
- `容器`：镜像每次运行之后就是产生一个容器，就是正在运行的镜像，特点就是可读可写
- `仓库`：用来存放镜像的位置，类似于 Maven 仓库，也是镜像下载和上传的位置
- `dockerFile`Docker 生成镜像配置文件，用来书写自定义镜像的一些配置
- `tar`：一个对镜像打包的文件，日后可以还原成镜像

#### Docker 架构示意图

![image](https://github.com/user-attachments/assets/69debe41-41ba-4dd3-9fb3-23b5482ec705)


#### Docker 架构的工作流程

- **构建镜像**：使用 `Dockerfile` 创建镜像。
- **推送镜像到注册表**：将镜像上传到 Docker Hub 或私有注册表中。
- **拉取镜像**：通过 `docker pull` 从注册表中拉取镜像。
- **运行容器**：使用镜像创建并启动容器。
- **管理容器**：使用 Docker 客户端命令管理正在运行的容器（例如查看日志、停止容器、查看资源使用情况等）。
- **网络与存储**：容器之间通过 Docker 网络连接，数据通过 Docker 卷或绑定挂载进行持久化。

### Ubuntu Docker 安装(如果安装不成功就在网上自己找一个教程来装)

#### 安装

在测试或开发环境中 Docker 官方为了简化安装流程，提供了一套便捷的安装脚本，CentOS 系统上可以使用这套脚本安装。另外可以通过 `--mirror` 选项使用国内源进行安装。

执行这个命令后，脚本就会自动的将一切准备工作做好，并且把 Docker 的稳定(stable)版本安装在系统中。

```Bash
curl -fsSL get.docker.com -o get-docker.sh
sudo sh get-docker.sh --mirror Aliyun
```

- 启动 Docker

```Bash
sudo systemctl enable dockersudo systemctl start docker
```

- 创建 Docker 用户组

```Bash
sudo groupadd docker
```

- 将当前用户加入 Docker 组

```Bash
sudo usermod -aG docker $USER
```

- 测试 Docker 是否安装

```Bash
docker --version
```

#### 其他安装教程

[菜鸟安装教程](https://www.runoob.com/docker/ubuntu-docker-install.html)

[配置镜像加速](https://www.runoob.com/docker/docker-mirror-acceleration.html)

### **容器和****虚拟机**

#### **docker运行 常用命令**

```Plain
：--name:指定容器的名字
-d：后台运行容器并返回容器id，也即启动守护式容器（后台运行）
-it：为容器重新分配一个伪输入终端，通常与-i使用
-P（大写）随机的端口映射，
-p(小写)：指定端口映射
docker exec -it:进入容器内部，打开终端命令,退出不会杀死容器
docker atach -it同上，退出会杀死容器
docker inspect  可以观看容器实例信息
docker run -it -privileged=true -v /mydocker/u:/tmp/u:ro --name u2 ubuntu     ro:read only,容器只可读，不可写
docker run it -privileged=true --volumes-from 实例名称 --name 名字 ubuntu      --volumes-from表示继承某一个容器的配   
docker images: 查看镜像
docker rm: 删除容器实例
docker rmi：删除镜像
```

#### **示例：使用docker部署安装****mysql**

```Plain
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf.d:/etc/mysql/conf.d \
-e MYSQL_ROOT_PASSWORD=2825097536 \
-d mysql:5.7
```

**Docker镜像加载原理(浅了解)：** docker的镜像实际上由一层一层的文件系统组成，这种层级的文件系统UnionFS。bootfs(boot file system)主要包含bootloader和kernel,.bootloader主要是引导加载kernel,Linux刚启动时会加载bootfs文件系统，在Docker镜像的最底层是引导文件系统bootfs。这一层与我们典型的Linux/Unix系统是一样的，包含boot加载器和内核。当boot加载成之后整个内核就都在内存中了，此时内存的使用权已由bootfs转交给内核，此时系统也会卸载bootfs。rootfs(root file system),在bootfs.之上.包含的就是典型Linux系统中的/dev,proc,bin,Ietc等标准目录和文件。rootfs就是各不不同的操作系统发行版，比如ubuntu,Cehtos等等**。**

读不懂吗？反正我也读不懂。。。能用就行了

**docker 发布到镜像仓库**

阿里云或者docker官网创建好仓库，想GitHub一样提交代码就好了

```Plain
docker操作阿里云常用命令
$ docker login --username=feiwoscun registry.cn-hangzhou.aliyuncs.com
$ docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/atluobin/luobin:[镜像版本号]
$ docker push registry.cn-hangzhou.aliyuncs.com/atluobin/luobin:[镜像版本号]
```

注意/atluobin/luobin，这个实例里面的镜像版本不可以重复，重复会把原始的镜像覆盖掉

#### **docker容器数据卷**

Docker挂载主机目录访问如果出现cannot open directory:Permission denied 解决办法：在挂载目录后多加一个-privileged=true参数即可 如果是CentOS7安全模块会比之前系统版本加强，不安全的会先禁止，所以目录挂载的情况被默认为不安全的行为，在SELinux里面挂载目录被禁止掉了，如果要开启，我们一般使用-privileged=true命令，扩大容器的权限解决挂载目录没有权限的问题，也即使用该参数，container内的root拥有真正的root权限，否则，container内的root只是外部的一个普通用户权限 。

```Plain
docker run -d -p5000:5000 -v /mydata/config:/data/config
```

-v:就是容器数据卷，可以做到多个实例都享用同一份文件

将运用与运行的环境打包镜像，run后形成容器实例运行，但是我们对数据的要求希望是持久化的 Docker容器产生的数据，如果不备份，那么当容器实例别除后，容器内的数据自然也就没有了。 为了能保存数据在docker中我们使用卷。 特点： 1：数据卷可在容器之间共享或重用数腿 2：卷中的更改可以直接实时生效，爽， 3:数据卷中的更改不会包含在镜像的更新中 4:数据卷的生命周期一直持续到没有容器使用它为止

### **docker network网络**

| 网络模式 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| host     | 容器将不会虚拟出自己的网卡，配置自己的iP等，而是便用宿主机的P和端口。 |
| none     | 容器有独立的Network namespace,但并没有对其进行任何网络设置，如分配veth pair和网桥连接，IP等。 |
| containe | 新创建的容器不会创建自己的网卡和配置自己的iP,而是和一个指定的容器共享P、端口范围等。 |
| bridge   | 为每一个容器分配，设置ip等，并将容器连接到一个docker0，虚拟网桥，默认该模式 |

### **编写dockerfile文件**

Dockerfile 是用于构建 Docker 镜像的文本文件，其中包含了一系列指令，每个指令对应一个镜像层的操作。通过编写 Dockerfile，可以定义应用程序的环境、依赖关系和运行时配置。

简单的 Dockerfile 的例子：

```Bash
# 使用官方的基础镜像，这里以Alpine Linux为例
FROM alpine:latest
# 设置镜像的作者信息
LABEL maintainer="your-name <your-email@example.com>"
# 在容器内创建一个新目录
WORKDIR /app
# 将当前目录下的所有文件复制到容器的 /app 目录中
COPY . .
# 安装应用所需的依赖
RUN apk --no-cache add python3
# 设置环境变量
ENV APP_PORT=8080
# 声明容器将要监听的端口
EXPOSE $APP_PORT
# 定义容器启动时执行的命令
CMD ["python3", "app.py"]
```

- `FROM`：指定基础镜像，即构建新镜像所基于的镜像。在本例中，使用了最新版本(latest)的 Alpine Linux。
- `LABEL`：设置镜像的元数据，如作者信息、版本等。
- `WORKDIR`：设置工作目录，即后续指令的执行目录。
- `COPY`：将本地文件复制到容器内。在这里，将当前目录下的所有文件复制到容器的 `/app` 目录中。
- `RUN`：在镜像构建过程中执行命令。在这里，使用 `apk` 包管理器安装了 Python3。
- `ENV`：设置环境变量。
- `EXPOSE`：声明容器将要监听的端口。这并不会自动映射端口，只是用于标记容器中应用程序可能使用的端口。
- `CMD`：定义容器启动时执行的默认命令。

#### **Dockerfile 中常用的一些****指令**

| 指令       | 用途                                                         | 示例                                                         |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| FROM       | 指定基础镜像，构建新镜像的起点                               | FROM ubuntu:20.04                                            |
| LABEL      | 设置镜像的元数据，如作者、版本、描述等                       | LABEL maintainer="your-name <[your-email@example.com](mailto:your-email@example.com)>" |
| RUN        | 在构建过程中执行命令，用于安装软件、更新系统等操作           | RUN apt-get update && apt-get install -y python3             |
| WORKDIR    | 设置工作目录，即后续指令的执行目录                           | WORKDIR /app                                                 |
| USER       | 指定容器中运行的用户                                         | USER nobody                                                  |
| ENV        | 设置环境变量，在构建过程中配置容器的环境                     | ENV MY_PATH /usr/mytest                                      |
| VOLUME     | 声明容器内的挂载点，用于持久化数据                           | VOLUME ["/data"]                                             |
| ADD        | 将宿主机目录下的文件拷贝进镜像，同时会自动处理 URL 和解压 tar 压缩包 | ADD ./files /app                                             |
| COPY       | 类似于 ADD，将宿主机目录下的文件拷贝进镜像，但不会自动处理 URL 和解压 tar | COPY ./files /app                                            |
| EXPOSE     | 声明容器将要监听的端口，供其他容器或主机连接                 | EXPOSE 80                                                    |
| CMD        | 定义容器启动时执行的默认命令，可被覆盖                       | CMD ["nginx", "-g", "daemon off;"]                           |
| ENTRYPOINT | 类似于 CMD，但不会被覆盖。定义容器的主要运行命令，CMD 提供默认参数 | ENTRYPOINT ["nginx", "-g", "daemon off;"]                    |

### **使用docker-compose部署编排**

只使用dockerfile进行创建镜像会有很多的问题，比如当项目部署的时候有redis，mysql这样的镜像启动顺序，或者说容器间的ip地址发生了变化，映射出错，要么生产ip写死，要么通过服务调用。

使用docker-compose可以做到集中管理，一套带走

### **安装docker-compose**

```Plain
//下载
sudo curl -L "https://github.com/docker/compose/releases/download/v2.14.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
 //授权   
sudo chmod +x usr/local/bin/docker-compose
    
```

**[docker-compose安装](https://www.runoob.com/docker/docker-compose.html)**

### **编写docker-compose.yml文件**

#### **什么是docker-compose.yml文件？**

Docker Compose 是一个用于定义和运行多容器 Docker 应用程序的工具。它通过一个单独的 `docker-compose.yml` 文件来配置应用的服务、网络和卷。下面是一个简单的 `docker-compose.yml` 文件的例子，以说明其基本结构和一些常见配置项：

```YAML
version: '3'  # Docker Compose 文件版本

services:  # 定义应用的服务
  web:  # 服务名称
    image: nginx:latest  # 使用的镜像
    ports:  # 定义端口映射
      - "8080:80"  # 将容器的 80 端口映射到主机的 8080 端口
    volumes:  # 定义卷挂载
      - ./web-content:/usr/share/nginx/html  # 将主机上的 ./web-content 目录挂载到容器的 /usr/share/nginx/html 目录
    networks:  # 定义网络
      - frontend
    environment:  # 设置环境变量
      - NGINX_VERSION=latest

  db:
    image: mysql:5.7
    environment:
      - MYSQL_ROOT_PASSWORD=root_password
      - MYSQL_DATABASE=my_database
      - MYSQL_USER=user
      - MYSQL_PASSWORD=password
    volumes:
      - db-data:/var/lib/mysql
    networks:
      - frontend
      - backend

networks:  # 定义网络
  frontend:
  backend:

volumes:  # 定义卷
  db-data:
  web-content:
```

- `version`：指定 Docker Compose 文件的版本。目前常用的版本是3。
- `services`：定义应用的各个服务，每个服务包含一个或多个容器。
- `image`：指定服务所使用的镜像。
- `ports`：定义端口映射，将容器内的端口映射到主机上的端口。
- `volumes`：定义卷挂载，将主机上的目录或卷挂载到容器内。
- `networks`：定义服务使用的网络，以及服务之间的连接关系。
- `environment`：设置容器的环境变量。
- `volumes`：定义卷，用于持久化数据。

#### **docker-compose常用命令**

| 指令      | 说明                             |
| --------- | -------------------------------- |
| -h        | 查看帮助                         |
| up        | 启动所有的docker-compose服务     |
| up -d     | 启动所有的服务并后台运行         |
| down      | 停止并删除容器，网络，卷，镜像   |
| exec      | 进入容器的实例内部               |
| ps        | 展示当前编排过的运行的所有的容器 |
| top       | 展示当前编排过的容器进程         |
| logs      | 查看容器输出日志                 |
| config    | 检查配置                         |
| config -q | 检查配置，输出问题               |
| restart   | 重启服务                         |
| start     | 启动服务                         |
| stop      | 停止服务                         |

一个docker-compose模板

```YAML
version: 'xxx' # 版本号
 
services: # 固定写死，代表有几个服务实例
microService: # 自定义服务名
 image: zzyy_docker:1.6 #镜像名字:版本号
 container_name: ms01
 volumes: # 容器数据卷
         - /app/microService:/data
 network: # docker网络配置
         - atguigu_net
 depends_on: # 依赖
         - redis
         - mysql

# docker run -p 6379:6379 --name redis608 --privileged=true -v /app/redis/redis.conf:/etc/redis/redis.conf -v /app/redis/data:/data -d redis:6.0.8 redis-server /etc/redis/redis.conf
redis:
 images: redis:6.0.8
 ports: # 端口映射
         - "6379:6379"
 volumes:
         - /app/redis/redis.conf:/etc/redis/redis.conf
         - /app/redis/data:/data
 networks:
         - atguigu_net
 command: redis-server /etc/redis/redis.conf # 命令

# docker run -p 3306:3306 --name mysql5.7 --privileged=true -v /app/mysql/db:/var/lib/mysql -v /app/mysql/mysql_config:/etc/mysql -v /app/mysql/init:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
mysql:
 image: mysql:5.7
 environment: # 环境配置
   - MYSQL_ROOT_PASSWORD: "123456"
   - MYSQL_DATABASE: "db"
   - MYSQL_USER: "zzyy"
   - MYSQL_PASSWORD: "zzyy123"
 ports:
   - "3306:3306"
 volumes:
   - /app/mysql/db:/var/lib/mysql
   - /app/mysql/mysql_config:/etc/mysql
   - /app/mysql/init:/docker-entrypoint-initdb.d
 network:
   - auguigu_net
 command: --default-authentication-plugin=mysql_native_password # 解决外部无法访问

# docker network create atguigu_net
network:
 atguigu_net
```

编写好之后，使用`docker-compose up -d` 可以一次性的把需要的容器给创建好，就省去了很多麻烦。

### **实战: docker-compose部署springboot项目**

我们就来尝试一下使用docker-compose部署一个简单的springboot项目

> 为了方便我把相关文件放在了/static下

#### **Dockerfile**

```Bash
#依赖jdk17环境
FROM khipu/openjdk17-alpine

#对外暴露8081
EXPOSE 8081
#复制server-1.0-SNAPSHOT到docker容器中并命名为app.jar
ADD springboot_test-1.0-SNAPSHOT.jar app.jar
#执行命令
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

#### **docker-compose.yml**

```YAML
version: '3'
services:
  springboot:
    image: springboot:latest
    build: .
    ports:
      - 8081:8081
    volumes:
      - /usr/photo:/usr/photo # 实际上在本次示例中部署的项目上并没有什么用,可写可不写
    depends_on:
      - mysql
  mysql:
    image: mysql:8.0.20
    container_name: mysql
    restart: always
    ports:
      - 3306:3306
    volumes:
      - dbdata:/var/lib/mysql
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql  # 将你的 SQL 文件挂载到容器内的初始化目录
    environment:
      MYSQL_DATABASE: webserver
      MYSQL_ROOT_USER: root
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_ROOT_HOST: '%'
      TZ: Asia/Shanghai
    command: ["--init-file", "/docker-entrypoint-initdb.d/init.sql"]  # 执行初始化 SQL 文件
volumes:
  dbdata:
  imagedata:
```

然后我们直接`docker-compose up`就行啦!
