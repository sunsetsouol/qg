#  JAVA

## 前置知识

### JDK组成

![1673233300864](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673233300864.png)  

### **window命令行窗口常用命令**

| 命令    | 作用                              |
| ------- | --------------------------------- |
| 盘符 C: | 切换到系统的某个盘下              |
| dir     | 查看当前路径下的文件信息          |
| cd      | 切换当前工作目录（tab可以自动补全 |
| cls     | 清屏                              |

### **PATH环境变量**

Path环境变量用于记住程序路径，方便在命令行窗口任意目录启动程序

### IDEA结构

project	-	module	-	package	-	class

项目 		- 		模块 	- 	包				-	类

### 快捷键

| 快捷键                                        | 功能                 |
| --------------------------------------------- | -------------------- |
| main/psvm/sout                                | 快速输入相关代码     |
| Ctrl+D                                        | 赋值当前行到下一行   |
| Ctrl+Y                                        | 删除所在行           |
| Ctrl+ALT+L~~（同crtl+shift+a）~~              | 格式化代码（自动缩进 |
| ALT+Shift+:arrow_up: , ALT+SHIFT+:arrow_down: | 上下移动当前代码     |
| Ctrl+/ , Ctrl+Shift+/                         | 整行注释             |

## 基础语法

### 注释

* //				同c
* /* */			同c
* /**   */   文档注释可以提取到程序说明书中

| 快捷键注释   | 功能     |
| ------------ | -------- |
| ctrl+/       | 单行注释 |
| ctrl+shift+/ | 多行注释 |

### 变量

同c

* 先使用再声明
* 声明后不能储存其他类型的数据
* 有效范围在 {} 中，一个范围不能有同名变量
* 定义的时候可以没有初始值，使用需有初始值

### 变量原理、ASCII、二进制

数据在计算机都是二进制储存

* 计算机最小组成单元：8个二进制位一组，称为一个字节（byte，大B）
* 每个二进制位为一位（bit，小b）1byte=8bit

![1673313867594](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673313867594.png)

字符在计算机中以ASCII编码表形式存储，规定了现代英语，数学字符和西欧字符的数字编号

![1673312989736](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673312989736.png)

**图片数据**

- 图片是无数个像素点组成的

- 每个像素点数据：用0-255\*255\*255表示颜色（红绿蓝三原色）

**声音数据**

* 二进制储存声波

**Java书写二进制、八进制、十六进制数据**

* 二进制以 0B | 0b 开头
* 八进制以 0 开头
* 十六进制以 0x | 0X 开头

进制转换

* 十转二：除二取余
* 二转十：每位代表2的n-1次幂
* 二转八：三个二进制位等于一个八进制位
* 二转十六：四个二进制位等于一个十六进制位

### 数据类型

**<u>分为引用数据类型（如string） 和基本数据类型（四类八种）</u>**

***基本数据类型***

![1673314046597](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673314046597.png)

* 手写的整数和小数默认是int 和double
* 手写的整数在int和long范围之间会报错，可在数字后加 L | l 使其代表long型数据
* 手写小数赋值给float 会报错 double 转化为 float 宽转窄不合法，可在小数后加 F | f使其代表float

***引用数据类型***

string 同c string （函数可能不同

### 关键字、标识符

***关键字***

不能做类名和变量名

![1673314839973](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673314839973.png)

***标识符***

<u>由字符、符号组合成的名称，用于给类，方法，变量等起名字的规矩</u>

**要求和规范**

* 由数字、字母、下划线和$ 等组成
* 不能以数字开头，不能是关键字，区分大小写
* 驼峰命名法 满足标识符规则，全英文有意义，首字母小写其他字母开头大写

### 类型转换

#### 自动类型转换

* 窄变宽，范围小可以**直接**赋值给范围大
* 运算规则与c基本一致，范围小的遇到范围大的自动转换为范围大的，在遇到范围大的数据之前还未转换

![1673315475427](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673315475427.png)

* 表达式的最终结果由最高类型决定
* 表达式中byte、short、char**直接转换成int**参与运算

#### 强制类型转换

将范围大的转换为范围小的，在需要转换的数据前加  **(要转换的数据类型)**

* 强制类型转换**可能造成数据丢失**
* 浮点型转整形直接丢掉整数

### 运算符

\+ \- \* / %

* 整数做除法结果舍弃小数

* %取余可作拆分数字每个位上数字

#### +

可做连接符，能算则算，不能算则连接 （经过overload，可以作连接符

在System.out.println()中+可作不同类型数据的连接符

#### 自增自减

* 自增： ++ 当前值加+，在++i表示i+1并将结果用作计算，i++表示i+1但是用i+1之前的值做计算
* 自减： -- 同自增 

#### 扩展赋值运算符![1673317664002](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673317664002.png)

#### 关系运算符

![1673317860772](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673317860772.png)

* Java中0和非0不能代表true 和false ，true 和false也不代表0和1

#### 逻辑运算符

![1673318070804](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673318070804.png)

* 与 ，或也可写作 && 和 ||  ，&& 和  || 用法跟C一致
* 用&& 时前一个结果为false，后一个语句不执行， & 还会执行下一个语句
* 用 || 是前一个结果为true ，后一个语句不执行， | 还会执行下一个语句

#### 三元运算符/三目运算符

* 语法： 条件表达式？值1 : 值2
* 若条件为true，则返回值1，如果为false，则返回值2

#### 运算符优先级

![1673318706255](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673318706255.png)

### API获取用户键盘输入

* API是 Application Programming Interface ，应用程序变成接口
* 是Java写好的程序，可以直接调用

**获取键盘信息**

1. 需要导包： import java.util.Scanner;
2. new一个Scanner类对象 Scanner 对象名 = new Scanner (System.in)
3. 用类函数录入数据  对象名.nextInt()、  对象名.next()

### 流程控制

#### 分支结构

##### if、 switch case（同c）

![1673320770832](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673320770832.png)

![1673320925807](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673320925807.png)

* switch case条件中只能是byte、short、int、char，JDK5开始支持枚举，JDK7开始支持String，不支持**double、float、long**
* case 的值不能重复，且要明确（不能是变量），注意加break



#### 循环结构

##### for

![1673334645834](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334645834.png)

##### while

![1673334697331](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334697331.png)

用while能做的for都能做，不知道循环次数的用while

##### do while

##### ![1673334824131](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334824131.png)

do while 相较while 一定会执行一次

##### 死循环、break、continue

![1673334900441](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334900441.png)

![1673334974497](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334974497.png)

**break 可以当goto 使用**

#### Random随机数

**在程序中获得随机数**

1. 导包：import java.util.Random;
2. new一个Random类对象： Random r = new Random();
3. 调用类函数 int number = r.nextInt( n )

* n是一个int型参数，可以生成0-n-1之间的随机数，不包含n

### 数组

**静态数组的定义：**

* 完整格式：数据类型[] 数组名 = new 数据类型 [] {元素1，......}；

* 简化格式：数据类型[] 数组名 = {元素1 ，....};

数组是引用类型~~（不同于c++的引用，更像是c++的指针）~~，储存数组的地址

兼容c的写法  数据类型 数组名[] = {元素};

**数组的访问：**

数组名称[索引]

**获得数组长度**

数组名 . length 	~~(c++：数组名.size() ,Java不带括号)~~ 

遍历数组时要保证数组不为null且length大于0

**动态数组定义：**

数据类型 [] 数组名 =  new 数据类型 [长度] 

后赋值 数组名[0]=值;.......

**动态跟静态不能混用**

**~~（感觉跟动态没什么关系，跟c相比静态就是不带长度，动态就是不能赋值）~~**

**各类型默认值**

![1673406619804](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673406619804.png)

#### 冒泡排序

![1673407244651](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673407244651.png)

![1673408246038](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673408246038.png)

### 内存

![1673408112184](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673408112184.png)

![1673408085113](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673408085113.png)

## 方法（函数）

![1673410137978](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673410137978.png)

![1673410124425](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673410124425.png)

### 方法的调用过程

- 方法在没有被调用的时候，在方法区的字节码文件中存放
- 方法被调用的时候，需要进入到栈内存中运行

### 参数传递机制

* 实参：在方法内部定义的变量
* 形参：在定义方法时，（）中声明的参数
* java参数传递机制：值传递，在传输实参给形参时，不是传输实参本身，而是传输实参存储的值

引用类型参数~~（本质就是指针）~~传递传递的是地址，所以在方法中对引用类型参数进行修改会影响对象本身

### 方法重载（overload）

同一个类中，多个方法**名称相同，但是形参不同（个数、类型、顺序）**就是重载方法，在调用时会根据参数不同决定调用的方法

### return

可单独使用，立即**跳出当前方法**，可放在任何方法

## OOP

类：是对象共同特征的描述

对象：是真实存在的具体实力

在java和其他oop语言中，必须先设计类，才能创建对象并使用

### 封装

OOP三大特征：封装、继承、多态性

封装的原则：对象代表什么，就封装对应的数据，并为数据提供相应的行为（外界不能直接访问到成员变量，只能借助类所提供的方法接触

private：private修饰的成员只能在当前类访问

用public将访问对象的方法暴露

### 类的设计

public class 类名{

​	成员变量

​	成员方法

​	构造器

​	代码块

​	内部类

}

**对象的创建**： 类名 对象名=new 类名()

**对象信息的访问**： 对象.成员变量;	对象.成员方法()

**注意事项：**一个java文件只能有一个public的类，成员变量一般无需初始化

#### 内存机制

对象是new出来的所以存放在堆内存中，new出来的对象储存的是对象在堆内存中的地址，每个对象的成员变量也存放在堆内存中

**Java区别与c++**： c++是 改造不够的oop语言，可以做两个对象的赋值，但是java这样的oop语言在对象变量的赋值**不是**拿一个对象给另一个对象**赋值**，而是让**两个对象的管理者共同管理**一个对象

#### 构造器（构造函数）

构造器是与类名同名的方法，在创造对象时自动调用

调用构造器得到对象： 类 变量名称 = new 构造器：

无参构造器（默认存在，可以重写）：初始化对象成员变脸采用默认值

有参数构造器：在初始化对象的时候可以接受参数为对象赋值

#### this

跟c++相同，代表当前**对象**的地址

Java中没有指针概念，所以用 **this.对象** 使用this指针

#### JavaBean（实体类）

* 成员变量要用private修饰

* 提供成员变量对应的 setxxx()/getxxx()方法

* 必须有一个无参构造器，有参构造器可写可不写

![1673486017428](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673486017428.png)



#### static

static是**静态**的意思，可以修饰成员变量和成员方法（声明的变量会放在静态变量区）

static修饰成员变量表示该成员在内存中只储存一份，可以被共享访问（可以通过类名访问，也可以通过对象访问，推荐用类名访问）

![](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113090144089.png)

![image-20230113091219147](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113091219147.png)

**注意事项**

* 静态方法只能访问静态的成员，不可以**直接**访问实例成员
* 实例方法可以访问静态的成员，也可以访问实例成员
* 静态方法不可以出现this关键字

##### 工具类

类中都是一些静态方法，每个方法都是以完成一个公用的功能为目的，这个类给系统开发人员共同使用

用实例方法做需要创建对象调用且创建对象只是为了调用方法，会浪费内存

工具类无需构造对象，建议工具类的构造器私有

##### 代码块

![image-20230113100513623](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113100513623.png)

静态代码块可以保证只执行一次，可以做数据的准备

##### 单例模式

解决一个问题最优解称为设计模式

单例模式可以保证系统中，应用该模式的这个类永远只有一个实例，即一个类只能创建一个对象

###### **饿汉单例模式**

在类获取对象的时候，对象已经提前创建好了

**设计步骤**

定义一个类，把构造器私有，定义一个静态变量存储一个对象

###### 懒汉单例模式

真正需要对象的时候，才去创建一个对象

**设计步骤**

定义一个类，把构造器私有，定义一个静态变量存储一个对象，提供一个返回单例对象的方法

![image-20230113104348964](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113104348964.png)

### 继承

定义：通过extends将一个类和另一个类建立起父子关系

* 父类又称基类或超类   子类又称为派生类

**子类继承父类之后，就可以使用父类公共的属性和方法**

好处： 提高代码复用性

继承设计规范

* 子类们的共性属性放在父类定义，子类独有的定义在自身

#### 特点

![image-20230113110209820](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113110209820.png)

* 子类不能继承父类构造器（但可以使用父类构造器）

* 子类可以继承父类的**私有成员**，但不能**直接**访问

* 子类可以跟父类**共享静态成员**
* java只支持单继承，不支持多继承，但支持多层继承

* 所有类都继承于Object类

#### 成员变量、成员方法的访问

![image-20230113111526384](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113111526384.png)

this可以强制访问子类成员，supper可以强制访问父类成员（前提是必须存在）

#### 方法重写（override）

子类出现跟父类一模一样的方法声明，就称子类这个方法是重写的方法

可以在重写后的方法上加 @Override判断作为重写是否正确的校验注解

![image-20230113112218062](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113112218062.png)

#### 子类构造器

* 子类所有构造器默认先访问父类无参构造器，再执行自己
  * 子类在初始化的时候可能会使用到父类中的数据，如果父类没有完成初始化，子类无法使用父类数据
  * 子类初始化之前，一定要调用父类构造器先完成父类数据空间的初始化
  * 子类构造器第一行语句默认是supper()，不写也存在

#### 子类构造器访问父类有参构造器

supper(参数)

如果父类没有无参构造器，只有有参构造器会报错，因为子类默认调用父类无参构造器，可以书写无参构造器

this可以调用兄弟构造器（同一个类中overload的构造器）

* this(...) 和 supper()都只能放在构造器的第一行，所以不能同时存在同一构造器中（因为this()会调用supper() ）

![image-20230113113722421](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113113722421.png)

### 语法

#### 包

包是用来分门别类管理不同类的，类似于文件夹，建包利于程序管理和维护

建包语法：package 公司域名倒写.技术名称

建包语句必须在第一行，一般idea会帮助创建

**导包**

* 相同包下的类可以直接访问，不同包下的类必须导包才可以使用
* import 包名.类名
* 如果这个类中使用不同包下相同的类名，此时默认只能导入一个类的包，另一个类需要带包名访问

#### 权限修饰符

用来控制一个成员能被访问的范围

![image-20230113125749431](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113125749431.png)

#### final

* final是最终的一i是，可以修饰类、方法、变量
* 修饰类：表明该类是最终类，不能被继承
* 修饰方法：表明该方法时最终方法，不能被重写
* 修饰变量：表示该变量第一次赋值后，不能再次被赋值
  * 修饰基本类型，那么储存的数据值不能发生改变
  * 修改引用类型，那么储存的地址值不能发生改变，但是地址指向的内容可变

#### 常量

常量是使用了public static final修饰的成员变量，必须有初始值，执行过程中其值不能被改变

* 常量可以用于做系统配置信息，方便程序的维护，同时提高可读性
* 命名规范：全大写用下划线连接

常量在编译过程会把使用常量的地方替换成真实的字面量（跟宏定义差不多）

#### 枚举

修饰符 enum 枚举名称{

​				多列枚举类实例的名称

}

![image-20230113144457593](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113144457593.png)

通过枚举名.对象引用

### 抽象类

abstract是抽象的意思，可以修饰类和方法	

![image-20230113145025557](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113145025557.png)

* 抽象类是不完整的设计图，当父类知道子类一定要完成某些行为，但是每个子类该行为的实现又不同，于是该父类就把该行为定义成抽象方法的像是，具体实现交给子类完成，此时这个类可以声明为抽象类

* 一个类如果继承了抽象类，那么必须重写玩抽象类的全部抽象方法，否则这个类也必须定义成抽象类

模板方法建议用final修饰，因为模板方法是给子类直接用的，不是让子类重写的，用final可以防止子类重写模板方法，这样更安全

### 接口

**interface**

接口体现规范思想，规范默认都是公开的，所以接口中常量的public static final可以省略不写，抽象方法的public abstract可以省略不写

![image-20230115180244906](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115180244906.png)

#### 实现

接口是用来被实现的，实现接口的类叫做实现类，implement

![image-20230113152735287](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113152735287.png)

#### 接口多继承

一个接口可以继承多个接口类，整合多个接口为同一个接口，便于子类实现

#### 接口新增方法

**JDK8开始新增的方法：**

* 默认方法：default 修饰，实现类对象调用
* 静态方法：static修饰，必须用当前接口名调用
* 私有方法：private修饰，jdk9开始才有，只能在接口内部被调用
* 都默认被public修饰

![image-20230113155544014](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113155544014.png)

### 多态

什么是多态：同类型对象，执行同一个行为，会表现处不同的行为特征

多态的常见形式：

父类类型  对象名称 = new 子类构造器

接口		对象名称 = new 实现类的构造器

多态中成员访问特点：

* 方法调用，编译看左边，运行看右边

* 变量调用，编译看左边，运行看左边 （多态侧重行为多态）

多态的前提：

* 有继承/实现关系，有父类引用指向子类对象，有方法重写~~（upcast~~

优势：

* 多态下右边对象可以实现解耦合，便于扩展和维护

问题：

* 多态下不能使用子类特有功能
* 解决：强制类型转换

#### 强制类型转换（从父到子）

* 解决多态下劣势，可以调用子类独有的功能
  * 如果转型后类型和对象真是类型不是同一种类型，那么转换时会出现ClassCastException

java在强制类型转换前可以使用instanceof判断当前对象的真实类型，再进行强制转换

![image-20230113162820779](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113162820779.png)

## 内部类

内部类就是定义再一个类里面的类，里面的类可以理解为寄生，为不累可以理解为宿主

### 内部类的使用场景：

![image-20230113164306759](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113164306759.png)

### 静态内部类

有static修饰，属于外部类本身

他的特点和使用与普通类是完全一样的，类有的成分他都有，只是再别的类的位置里

![image-20230113170344219](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113170344219.png)

静态内部类可以直接访问外部类的静态成员，不可以直接访问外部类的实例成员，实例成员只能通过对象访问

### 成员内部类

无static修饰，属于外部类对象

JDK16之前，成员内部类不能定义静态成员

![image-20230113173255533](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113173255533.png)

成员内部类可以直接访问外部类的静态成员，也可以直接访问外部类的实例成员，因为必须现有外部类，才能有内部类对象

* 在成员内部类中访问外部类对象：外部类名.this.xxx

### 局部内部类

局部内部类放在方法、代码块、构造器等执行体中

### 匿名内部类

本质上是一个没有名字的内部类，定义在方法中、代码块中

作用：方便创建子类对象，最终目的为了简化代码编写

![image-20230113174736062](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230113174736062.png)

* 匿名内部类是一个没有名字的内部类

* 匿名内部类写出来就会产生一个匿名内部类的对象
* 匿名内部类的对象类型相当于是当前new的那个类型的子类型
* 匿名内部类可以作为方法的实际参数进行传输

## String

String类可以用于储存字符串，同时String类提供很多操作字符串功能

String被称为不可变字符串类型，对象在创建后不能被修改 +=指向的的是新创建的字符串对象，对象没有改变，而是指向新对象

String在sout时做了优化，会直接输出String所指向的内容

### String对象的创建

1. 直接定义  String 变量名 = "xxxxx";
2. 通过构造器创建

| 构造器                         | 说明                                   |
| ------------------------------ | -------------------------------------- |
| public String()                | 创建一个空白字符串对象，不包含任何内容 |
| public String(String original) | 根据传入的字符串内容来创建字符串对象   |
| public String(char[] chs)      | 根据字字符数组的内容来创建字符串对象   |
| public String(byte[] chs)      | 根据字节数组内容来创建字符串对象       |

* 两种方法的区别
  * 以""方式给出的字符串对象，在字符串常量池中储存，且相同内容只会存储一份
  * 构造器new出来的对象，没new一次会产生一个新对象，放在堆内存

![1673487425868](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673487425868.png)

变量运算结果会放在堆内存，如果是确定的字符运算会优化

### String类常用的API

**字符串比较**

| 方法名                                                 | 说明                                                         |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| public boolean equals (Object anObject)                | 将此字符串与指定对象进行比较，只关心字符内容是否一致         |
| public boolean equalsIgnoreCase (String anotherString) | 将此字符串与指定对象进行比较，忽略大小写比较字符串，只关心字符内容是否一致 |

**其他常用API**

| 方法名                                                       | 说明                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------ |
| public int length()                                          | 返回此字符串的长度                                     |
| public char charAt(int index)                                | 获取某个索引位置的字符                                 |
| public char[] toCharArray()                                  | 将当前字符串转换成字符数组返回                         |
| public String substring(int beginIndex, int endIndex)        | 根据开始和结束索引进行截取，得到新字符串（包前不包后） |
| public String substring(int beginIndex)                      | 从传入的索引处截取到末尾，得到新字符串                 |
| public String replace(CharSequence target,CharSequence replacement) | 使用新值，将字符串中旧值替换，得到新字符串             |
| public String[] split(String regex)                          | 根据regex切割字符串，得到字符串数组返回                |
| public boolean contains(CharSequence s)                      | 判断是否包含s                                          |
| public boolean startsWiths(String preifx)                    | 判断是否以prefix开头                                   |

## ArrayList

代表集合类，集合是一种容器，跟数组类似，但是集合大小不固定，提供更丰富的API

构造器：public ArrayList()

创建对象 ArrayList list=new ArrayList()

添加元素 

| 方法名                                | 说明                           |
| ------------------------------------- | ------------------------------ |
| Public boolean add(E e)               | 将指定的元素追加到集合末尾     |
| public void add(int index, E element) | 在集合中指定的位置插入指定元素 |

### 泛类约束

ArrayList < E > 可以在编译阶段约束集合对象只能操作某种数据类型

* example : 

ArrayList< String >:	只能操作字符串类型元素

ArrayList< Integer >: 只能操作整数类型元素

* 集合中只能存储引用类型，不支持基本数据类型

### 常用API

| 方法名称                          | 说明                                 |
| --------------------------------- | ------------------------------------ |
| public E get(int index)           | 返回指定索引处元素                   |
| public int size()                 | 返回集合中的元素个数                 |
| public E remove(int index)        | 删除指定索引处元素，返回被删除元素   |
| public boolean remove(object o)   | 删除指定元素，返回删除是否成功       |
| public E set(int index,E element) | 修改指定索引处元素，返回被修改的元素 |

## 常用API

API(Application Programming interface)应用程序编程接口

### Object

一个类要么默认继承了Object类，要么简洁继承了Object类，一切子类都可以直接使用Object类的方法

| 方法名                          | 说明                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| public String toString()        | 默认是返回当前对象在堆内存中的地址信息：类的全限名@内存地址  |
| public Boolean equals(Object o) | 默认是比较当前对象与另一个对象的地址是否相同，相同返回true，不同返回false |

* toString 存在的意义：被重写，以便返回对象的内容信息
* equals 存在的意义：被重写，子类自己定制规则（String类已经重写了equals）

Objects类和Object还是继承关系，Objects是JDK1.7开始有的，二者equals比较方法一样，但是前者对空指针调用作了处理，更加安全

### Objects

| 方法名                                          | 说明                                                         |
| ----------------------------------------------- | ------------------------------------------------------------ |
| public static boolean equals(Object a,Object b) | 比较两个对象的，底层会先进行非空判断，从而可以避免空指针异常，再进行equals比较 |
| public static boolean isNuull(object obj)       | 判断变量是否为null，为null返回true                           |

源码分析

public static boolean equals(Object a, Object b){

​	return (a==b) || (a!=null && a.equals(b));

}

### StringBuilder

StringBuilder是一个可变字符串类，可以看作对象容器，可以提高字符串操作效率，如拼接、修改

构造器

| 名称                             | 说明                                           |
| -------------------------------- | ---------------------------------------------- |
| public StringBuilder()           | 创建一个空白的可变的字符串对象，不包含任何内容 |
| public StringBuilder(String str) | 创建一个指定字符串内容的可变字符串对象         |

StringBuilder常用方法

![image-20230114123400450](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114123400450.png)

### Math

包含执行基本数字运算方法，没有构造器，属于工具类，直接通过类名访问

![image-20230114130045034](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114130045034.png)

### System

System的功能是通用的，都是直接调用类名即可，不能被实例化

![image-20230114130441769](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114130441769.png)

### BigDecimal

解决浮点型运算精度失真的问题

将double转成BigDecimal进行运算存在精度损失风险，可以先转化成String在转化为BigDecimal

![image-20230114133817330](https://souln.oss-cn -guangzhou.aliyuncs.com/java/image-20230114133817330.png)

![image-20230114133153691](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114133153691.png)

BigDecima对象名.doubleValue()可以用BigDecima对象生成double对象 

BigDecima divide结果如果不精确会报错，可以进行四舍五入

![image-20230114134422860](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114134422860.png)

## 日期和时间

### Date

Date类的对象在Java中代表的是当前所在系统的此刻日期时间

| 构造器                 | 说明                                           |
| ---------------------- | ---------------------------------------------- |
| public Date()          | 创建一个Date对象，代表的是系统当前此刻日期时间 |
| public Date(long time) | 把时间毫秒值转换成Date日期对象                 |



| Date方法                       | 说明                                         |
| ------------------------------ | -------------------------------------------- |
| public long getTime()          | 获取时间对象的毫秒值                         |
| public void setTime(long time) | 设置日期对象的时间为当前时间毫秒值对应的时间 |

### SimpleDateFormat

可以对Date对象或时间毫秒值格式化成我们喜欢的时间形式

也可以把字符串的时间形式解析成日期对象

| 构造器                                  | 说明                                     |
| --------------------------------------- | ---------------------------------------- |
| public SimpleDateFormat()               | 构造一个SimpleDateFormat，使用默认格式   |
| public SimpleDateFormat(String pattern) | 构造一个SimpleDateFormat，使用指定的格式 |

| 格式化方法                              | 说明                            |
| --------------------------------------- | ------------------------------- |
| public final String format(Date date)   | 将日期格式化成日期/时间字符串   |
| public final String format(Object time) | 将时间毫秒值化成日期/时间字符串 |



![image-20230114204949892](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114204949892.png)

EEE代表星期几	a会根据时间取上午还是下午

把SimpleDateFormat解析字符串时间成为日期对象

| 解析方法                         | 说明                                 |
| -------------------------------- | ------------------------------------ |
| public Date parse(String source) | 从给定字符串的开始解析文本以生成日期 |

### Calendar

Calendar代表了系统此刻日期对应的日历对象

Calender是一个抽象类，不能直接创建对象

创建对象方法

public static Calendar getInstance()

常用方法

![image-20230114210828294](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114210828294.png)

### LocalDate、LocalTime、LocalDateTime

![image-20230114223232186](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114223232186.png)

![image-20230114224646450](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114224646450.png)

### 	Instant

![image-20230115153905080](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115153905080.png)

### DateTimeFormatter

![image-20230115154033622](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115154033622.png)

### Period

![image-20230115154526119](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115154526119.png)

### Duration

![image-20230115154641361](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115154641361.png)

## 包装类

![image-20230115155255315](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115155255315.png)

![image-20230115155343797](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115155343797.png)

可以用包装类的valueof代替parsexxx

## 正则表达式

![image-20230115164936570](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115164936570.png)

![image-20230115171701884](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115171701884.png)

## Arrays类

![](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115171911829.png)

**Array.toString返回数组内容并转化为字符串 [x.....]**

![image-20230115173224323](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115173224323.png)

![image-20230115174420328](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115174420328.png)

![image-20230115174719594](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115174719594.png) 

## Lambda表达式

![image-20230115175914520](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115175914520.png)

![image-20230115181334375](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115181334375.png)

**省略规则**

![image-20230115181756076](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115181756076.png)

## 泛型

### 泛型定义

![image-20230116123429796](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116123429796.png)

![image-20230116123506326](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116123506326.png)

### 泛型类

![image-20230116123616610](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116123616610.png)

![image-20230116124023570](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116124023570.png)

### 泛型方法

![image-20230116124357672](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116124357672.png)

### 泛型接口

![image-20230116124811338](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116124811338.png)

### 泛型通配符、上下限

通配符：  ？

* ？可以在使用泛型的时候代表一切类型

虽然两个子类都继承父类，但是两个子类的ArrayList没有关系 ，所以需要一个父类的ArrayList的时候不能传两个子类的ArrayList

这时候可以用通配符 ？ ，表示可以传递一切类型，再用泛型的上下限指定类型

泛型的上下限

![image-20230116125542912](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116125542912.png)

在通配符后加extends或super表示传入类型的ArrayList只能是某个类型的子类或父类

## 可变参数

![image-20230117122356878](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\image-20230117122356878.png)



## Collection

集合和数组一样都是容器，但是集合大小不固定，启动后可以动态变化，类型	也可以选择不固定，适合做元素的增删操作

* 集合只能存储引用类型数据，基本类型可以使用包装类

![image-20230115183956372](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115183956372.png)

### collection体系

![image-20230115184717117](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115184717117.png)

![image-20230115185219905](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115185219905.png)

### collection常用API

Collection是单列集合的祖宗接口，他的功能是全部单列集合都可以继承使用的

![image-20230115205307509](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115205307509.png)

### Collect的遍历

#### 迭代器

迭代器在java中的代表是Iterator，迭代器是集合的专用遍历方式

![image-20230115211157503](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115211157503.png)

![image-20230115213922914](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115213922914.png)

迭代器如果取元素越界会出现NoSuchElementException异常

#### foreach/增强for循环

![image-20230115214447719](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115214447719.png)

修改无意义，不会影响元素的值

#### Lambda表达式遍历

![image-20230115221221027](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115221221027.png)

### 	List

![image-20230116112805143](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116112805143.png)

![image-20230116112816742](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116112816742.png)

#### ArrayList

![image-20230116120049602](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116120049602.png)

#### LinkedList

![image-20230116115833347](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116115833347.png)

**first和last的特性可以将LinkedList当栈或队列使用，栈可以用push和pop代替addFirst和removeFirst**

#### List的遍历删除

![image-20230116123007555](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116123007555.png)

### Set

![image-20230116131825373](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116131825373.png)

#### HashSet底层原理

HashSet集合底层采取哈希表存储的数据

哈希表是一种对于增删改查数据性能都较好的结构

哈希表的组成

* JDK8前使用数组+链表组成
* JDK8就使用数组+链表+红黑树组成
* ![image-20230116132508631](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116132508631.png)

![image-20230116132908657](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116132908657.png)

JDK8后链表元素大于8自动转换成红黑树

如果希望Set集合认为两个内容一样的对象是重复的，必须重写对象的hashcode()和equals()方法

#### LinkedHashSet

![image-20230116134255227](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116134255227.png)

#### TreeSet

![image-20230116134339322](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230116134339322.png)

​	要使用TreeSet存储自定义类型，需要制定排序规则

方式一：让自定义的类实现Comparable接口重写里面的compareTo方法来制定比较规则

方式二：TreeSet集合有参数构造器，可以设置comparator接口对应的比较器对象来制定比较规则

* 如果两者同时存在默认使用集合自带的比较器排序
* 如果返回0会认为两个对象相等，会自动去掉一个



### Collections工具类

![image-20230117123127090](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117123127090.png)

![image-20230117123208315](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117123208315.png)

## Map集合

Map集合是双列集合，每个元素包含两个数据

格式：key=value（键值对元素，不支持基本元素）

### Map体系

![image-20230117123753031](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117123753031.png)

![image-20230117131851774](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117131851774.png)

### Map的API

![image-20230117132505244](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117132505244.png)

合并其他Map集合：putAll

map1.putAll(map2)

### Map的遍历方式

#### 键找值

先获取Map集合的全部键的Set集合，遍历Set集合，然后通过键一区对应值

涉及到的API

![image-20230117133622234](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117133622234.png)

#### 值找键

先把Map集合转换成Set集合，Set集合中每个元素都是键值对实体类型了，遍历Set集合，然后提取键以及提取值

涉及到的API

![image-20230117134041707](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117134041707.png)

![image-20230117134715279](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117134715279.png)

#### Lambda表达式

![image-20230117134752878](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117134752878.png)

### HashMap

![image-20230117140038408](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117140038408.png)

### LinkedHashMap

![image-20230117140856183](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117140856183.png)

### TreeMap

![image-20230117141022083](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117141022083.png)

不重复是指只要排序大小相同就会去重

![image-20230117141430051](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117141430051.png)

## 不可变集合

不可变集合就是不可被修改的集合

![image-20230117145815550](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117145815550.png)

## Stream流

### 概述

用于简化集合和数组操作的API

![image-20230117164602454](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117164602454.png)

Stream流的核心

![image-20230117164626245](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117164626245.png)

![image-20230117164809616](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117164809616.png)

### Stream流的获取

![image-20230117165321961](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117165321961.png)

数组获取stream流： Arrays.stream()

### Stream流常用API

![image-20230117170927747](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117170927747.png)



![image-20230117171001749](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117171001749.png)

### 收集Stream流

把Stream流操作后的结果转回到集合或数组中

![image-20230117173523793](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117173523793.png)

## 异常

异常时程序在编译或者执行的过程中出现的问题，语法错误不算在异常体系中

### 异常体系

![image-20230117173901302](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117173901302.png)

![image-20230117174339852](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117174339852.png)

### 常见运行时异常

![image-20230117174837982](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117174837982.png)

### 编译时异常

![image-20230117175225573](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117175225573.png)

### 异常的默认处理机制

![image-20230117175437875](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117175437875.png)

![image-20230117175534761](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117175534761.png)

### 异常处理方式一

![image-20230117180026848](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117180026848.png)

### 异常处理方式二

![image-20230117180516642](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117180516642.png)

### 异常处理方式三

方法直接将异常throws给调用这，调用者收到异常后捕获处理

建议在最外层调用捕获异常处理

### 自定义异常

![image-20230117182754589](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117182754589.png)

## 日志

日志技术的优势

* 可以将系统执行的信息选择性的记录到指定的位置（控制台、文件、数据库中）
* 可以随时以开关的形式控制是否记录日志，无需修改源代码 

![image-20230117211842154](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117211842154.png)

**日志的框架大多是接口，提供给实现框架去设计的**

![image-20230117212216421](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117212216421.png)

### Logback日志框架和使用

![image-20230117212612282](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117212612282.png)	

![image-20230117214947128](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117214947128.png)

### 输出位置、格式设置

![image-20230117220316438](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117220316438.png)

### 日志级别设置

可以通过设置日志的输出级别来控制哪些日志信息输出或不输出

![image-20230117220825857](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230117220825857.png)

## File、流

目前是在内存中存储数据来处理、修改、运算的，不能长久保存

硬盘中数据的形式是文件，文件是数据的载体，可以永久存储数据

* File类可以定位文件进行删除、获取文本本身信息等操作，但是不能读写文件内容
* IO流技术可以对硬盘中的文件进行读写

### FIle类概述

File类在包java.io.File下，代表操作系统的文件对象（文件、文件夹）

#### File类创建对象

| 方法名称                                | 说明                                               |
| --------------------------------------- | -------------------------------------------------- |
| public File(String pathname)            | 根据文件路径创建文件对象                           |
| public File(String parent,String child) | 从父路径名字符串和子路径名字符串创建文件对象       |
| public File(File parent,String chid)    | 根据父路径对应文件对象和子路径名字符串创建文件对象 |

File对象可以定位文件或文件夹，封装的对象是路径名，可以存在也可以不存在

**绝对路径和相对路径**

* 绝对路径：从盘符开始（File file1=new File("D：\\\\......")
* 相对路径：不带盘符，默认直接到当前工程下目录寻找（File  file2=new File("模块名\\\\.....")

#### File类常用API

![image-20230118100155956](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230118100155956.png)

![image-20230118100547838](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230118100547838.png)

#### File的遍历

![image-20230118103618685](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230118103618685.png)

### 字符集

计算机给人类字符进行编号存储，这套编号规则就是字符集

* ASCII使用1个字节存储一个字符，一个字节是8位，总共可以表示128个字符信息，对于英文、数字来说够用
* GBK：window系统默认的码表，兼容ASCII表，也包含几万个汉字，并支持繁体汉字和日韩文字（一个中文以两个字节方式储存，但不包含世界上所有国家的文字）
* Unicode码表，又称统一码，是计算机科学领域的意向业界字符编码标准（容纳世界上大多数国家所有常见文字和符号）
  * 先用过UTF-8，UTF-16-和UTF-32的编码再存储到计算机，最常见的是UTF-8
  * UTF-8编码后的中文一般以三个字节的形式储存
  * 兼容ASCII编码表
  * 编码前后字符集一致，否则出现中文乱码

#### String编码

byte[] 数组名 = String名.getBytes()

String 变量名 = new String(bytes)

### IO流

IO流也成为输入输出流，用来读写数据

I表示intput，输入流，读数据到内存

O表示output，输出流，写数据到磁盘

按流的方向分为输入流和输出流

按数据最小单位分为字符流和字节流

![image-20230118120655110](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\image-20230118120655110.png)

#### 文件字节输入流

以内存为基准，把磁盘文件中的数据以字节形式读取到内存

FileInputStream(File file)

FileInputStream(String pathname)

**方法名称**

* read()	每次读取一个字节返回，没有可读的返回-1（性能不好，不能读中文）

* read( byte[] buffer) 	每次读取一个字节数组返回，没有可读返回-1

可以在循环中用read(byte[] buffer) 读取，但是要判断读取多少个字节（最后一组数据如果没有装满则上一组最后的字节内容还会存在）但是在读取中文的时候输出可能还是会出现乱码

* 可以自己定义一个字节数组与文件的大小一样大，然后使用读取字节数组的方法一次性读取完成
* 官方提供了readAllBytes()  API可以直接将当前字节输入流文件对象的字节装到一个字节数组返回

#### 文件字节输出流

文件字节输出流，写字节读数据到磁盘文件

FileOutputStream(File file)

FileOutputStream(File file, boolean append)		为true表示追加不覆盖

FileOutputStream(String filepath)

FileOutputStream(String filepath, boolean append)

**API**

public void write(int a)										\r\n.getBytes()表示换行

public void write(byte[] buffer)						配合String.getBytes()使用

public void write(byte[] buffer,int pos, int len)

**刷新和关闭流**

flush()	刷新流，还可以继续写数据（不刷新可能没更新）

close()	关闭流，释放资源，但是关闭前会刷新，一旦关闭就不能继续使用

#### 资源释放方式

##### try catch finally

finally:在异常处理时提供finally块来执行所有清楚操作，比如IO流中的释放资源

被finally控制的语句最终一定会执行，除非JVM退出

##### try with resource

try后面用括号放置资源对象，用完会自动关闭，自动调用close，出现异常也会关闭

![image-20230118144115511](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\image-20230118144115511.png)

try后面放置的资源都是实现了Closeable/Autoloseable接口的类对象

#### 文件字符输入流

文件字符输入流，一个一个字符读取到内存

FileReader(File file)

FileReader(String pathname)

**API**

public int read()

public int read(char[] buffer)

#### 文件字符输出流

文件字符输出流，写字符数据到磁盘文件

FileWriter(File file)

FileWriter(File file, boolean append)			append为true代表追加数据

FileWriter(String filepath)

FileWriter(String filepath,boolean append)

**API**

write(int c)

write(char[] cbuf)

write(char[] cbuf,int off,int len)

write(String str)

write(String str,int pos,int len)

**刷新和关闭流**

flush()	刷新流，还可以继续写数据（不刷新可能没更新）

close()	关闭流，释放资源，但是关闭前会刷新，一旦关闭就不能继续使用

字节流适合做一切文件数据的拷贝（音视频，文本），但是不适合读取中文内容输出，字符流适合做文本文件操作

### 缓冲流

缓冲流也称高校流或者高级流，之前的字节流成为原始流

作用：缓冲流自带缓冲区（8k），可以提高原始字节流、字符流读写数据的性能

* 字节缓冲输入流(BufferedInputStream)

* 字节缓冲输出流(BufferedOutputStream)

把低级字节输入输出流包装成高级的缓冲字节输入输出流从而提高性能

* 字符缓冲输入流(BufferedReader)（多了按照行读取数据的功能）
* 字符缓冲输出流(BufferedWriter)（多了换行功能）

public String readLine()	读取一行数据，无行可读返回null

public void newLine()		换行操作

### 转换流

解决字符编码不同读取乱码的问题

#### 字符输入转换流（InputStreamReader）

可以把原始字节流按照指定编码转换成字符输入流

InputStreamReader(InputStream  is) 

InputStreamReader(InputStream  is,String charset)	（可以把原始的字节流按照指定编码转换成字符输入流，这样字符流中的字符就不乱码了）

#### 字符输出转换流(OutputStreamWrite)

可以把字节输出流按照指定编码转换成字符输出流

OutputStreamWriter(OutputStream os)

OutputStreamWriter(OutputStream os,String charset)	（可以把原始的字节输出流按照指定编码转换成字符输出流了）

### 序列化对象

#### 对象序列化

以内存为基准，把内存中的对象存储到磁盘文件中去，称为对象序列化

使用到的流时对象字节输出流：ObjectOutputStream

* ObjectOutputStream(OutputStream out)

序列化方法

* public final void writeObject(Object obj)	把对象写出去到对象序列化流的文件中

#### 对象反序列化

以内存为基准，把存储到磁盘文件中去的对象数据恢复成内存中的对象，称为对象反序列化

使用到的流时对象字节输入流：ObjectInputStream

* ObjectInputStream(InputStream out)

序列化方法

* public Object readObject()	把存储到磁盘文件中去的对象数据恢复成内存中的对象返回

---

* 对象如果要序列化，必须实现serializable序列化接口
* 成员变量加transient不参与序列化
* 申明序列化的版本号：private static final long serialVersionUID=1；（每次修改版本号也要改，序列化和反序列化版本号要一致）

### 打印流

可以实现方便、高效的打印数据到文件中去，可以实现打印什么数据就是什么数据，一般指PrintStream，PrintWriter两个类

#### PrintStream

字节输出流

* PrintStream(OutputStream os)

* PrintStream(File f)

* PrintStream(String filepath)

**API**

* public void print(Xxx xxx)	打印任意类型数据

#### PrintWriter

字符输出流

* PrintWriter(OutputStream os)

* PrintWriter(Writer w)

* PrintWriter(File f)

* PrintWriter(String filepath)

**API**

* public void print(Xxx  xx)

***

* 二者打印数据功能上是一模一样的，都是使用方便，性能高效
* PrintStream继承字节输出流OutputStream，支持写字节数据的方法
* PrintWriter继承字符输出流Writer，支持写字符数据出去

#### 输出语句的重定向

属于打印流的一种应用，可以把输出语句的打印位置改到文件

* 输出语句的重定向

PrintStream ps=new PrintStream("文件地址")

System.setOut(ps)

* 重定向后System.out会把内容打印到文件中

### Properties

是一个Map集合，但是不当集合用

Properties代表的是一个属性文件，可以把自己对象中的键值对信息存入到一个属性文件中去

* 属性文件：后缀是.properties结尾的文件，里面的内容都是key=value，后续做系统配置信息

![image-20230118185416758](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230118185416758.png)

### IO框架

commons-io是apache提供的有关IO操作的类库，可以提高IO功能开发效率

commons-io工具包提供了很多有关io操作的类，两个主要的类：FileUtils，IOUtils

FileUtils方法

| 方法名                                                  | 说明                     |
| ------------------------------------------------------- | ------------------------ |
| String readFileToString(File file, String encoding)     | 读取文件数据，返回字符串 |
| void copyFile(File srcFile,File destFile)               | 复制文件                 |
| void copyDirectiryToDirectory(File srcDir,File destDir) | 复制文件夹               |

## 线程（Thread）

线程是程序内部的一条执行路径，main方法的执行就是单独的执行路径

如果程序中只有一条执行路程，那么这个程序就是单线程程序

多线程是指软硬件上实现多条执行流程的技术

### 多线程的创建

#### 继承Thread类

1. 定义一个子类MyThread继承线程类java.lang.Thread，重写run()方法
2. 创建MyThread类的对象
3. 调用线程对象的start()方法启动线程（启动后还是执行run()方法）

* 优点：编码简单
* 缺点：线程类已经继承Thread，无法继承其他了类，不利于扩展

| 构造器                                     | 说明                                         |
| ------------------------------------------ | -------------------------------------------- |
| public Thread(String name)                 | 可以为当前线程指定名称                       |
| public Thread(Runnable target)             | 封装Runnable对象称为线程对象                 |
| public Thread(Runnable target,String name) | 封装RUnnable对象称为线程对象，并指定路程名称 |

为什么不调用run方法二十调用start

<u>直接调用run方法会当成普通方法执行，此时相当于还是单线程执行，只有调用start方法才是启动一个新的线程执行</u>

要把主线程放在子线程之后，如果放在子线程之前，主线程一直是先跑完，相当于单线程的效果

#### 实现Runnable接口

**方案一：**

1. 定义一个线程任务类MyRunnable实现Runnable接口，重写run()方法
2. 创建MyRunnable任务对象
3. 把MyRunnable任务对象交给Thread处理
4. 调用线程对象的start()方法启动线程

* 优点：线程任务类只是实现接口，可以继续继承类和实现接口，可扩展性强
* 缺点：编程多一层对象包装，如果线程有执行结果是不可以直接返回

**方案二**

1. 创建Runnable的匿名内部类对象
2. 交给Thread处理
3. 调用线程对象的start()启动线程

#### 实现Callable接口（JDK5.0新增）

前两种线程创建方式重写的run方法都不能直接返回结果，不适合需要返回线程执行结果的业务场景

可以利用Callable（指定返回的泛型）、FutureTask接口实现

1. 得到任务对象
   1. 定义类实现Callable接口，重写call方法，封装要做的事情
   2. 用FutureTask把Callable对象封装成线程任务对象

2. 把线程任务对象交给Thread处理
3. 调用Thread的start方法启动线程，执行任务
4. 线程执行完毕后，通过FutureTask的get方法去获取任务执行的结果

* 优点：线程任务类只是实现接口，可以继续继承类和实现接口，扩展性强，可以在线程执行完毕后去获取线程执行的结果
* 缺点：编码复杂一点

### 线程常用方法

String getName()						获得当前线程名称

void setName(String name)	改线程名

**构造器**

public Thread(String name)

public Thread(Runnable target)

public Thread(Runnable target, String name)

**hread类线程休眠方法**

public static void sleep(long time)	让线程休眠指定时间

### 线程安全

多个线程同时操作同一个共享资源，修改共享资源时可能会出现业务安全问题

### 线程同步

核心思想：加锁，把共享资源上锁，每次只能一个线程进入访问完毕后解锁，然后其他线程才能进来

#### 同步代码块

作用：把出现线程安全的核心**代码**上锁

***

synchronized(同步锁对象){

​		操作共享资源的代码（核心代码）

}

***

锁对象的规范：建议使用共享资源作为锁对象，实例方法建议使用this作为锁对象，静态方法建议使用字节码（类名.class）对象做锁对象

#### 同步方法

作用：把出现线程安全的核心**方法**上锁

***

修饰符 synchronized 返回值类型 方法名称(形参列表){

​			操作共享资源的代码

}

***

同步方法底层也是隐式锁对象，不过范围是整个方法，如果方法是实例方法，同步方法默认用this作为锁对象，如果是静态方法，同步方法默认用类名.class作锁对象

#### Lock锁

为了更清晰的表达如何加锁和释放锁，JDK5后提供锁对象Lock

Lock是接口不能实例化，使用实现类ReentrantLock来构建锁对象

public ReentrantLock()			获得Lock锁的实现类对象

***

Lock的API

void lock()									获得锁

void unlock()								释放锁

***

可以加final修饰锁，确保不会被修改

解锁要放在try finally 里， 否则如果出错就打不开锁了

### 线程通信

线程通信就是线程间相互发送数据，线程通信通常通过共享一个数据的方式实现

线程间会根据共享数据的情况决定自己该怎么做每一集通知其他线程该怎么做

线程通信的前提：线程通信通常是在多个线程操作同一个共享资源的时候进行通信，且要保证线程安全

Object类的等待和唤醒方法，用同步锁对象调用

void wait()				让当前线程等待并释放所占锁，知道另一个线程调用notify()或notifyAll()

void notify()			唤醒正在等待的单个线程

void notifyAll()		唤醒正在等待的所有线程

### 线程池

#### 概述

可以复用线程的技术

固定几个线程，有任务时等前面线程先解决完再执行后面任务

线程池的接口：ExecutorService

得到线程池对象

- 使用ExecutorService实现类ThreadPoolExecutor创建一个线程池对象
- 使用Executors调用方法返回不同特点的线程池对象



![image-20230119173750835](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230119173750835.png)

**新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程才会创建临时线程**

**核心线程和临时线程都在忙，任务队列也满了，新的任务过来的时候开始拒绝任务**

![image-20230119180006460](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230119180006460.png)

Runnable通过线程池的execute方法执行线程

Callable通过线程池的submit方法执行线程并返回未来对象

#### Executors工具类

![image-20230119193925914](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230119193925914.png)

2、3允许请求的任务队列长度是Interger.MAX_VALUE，可能会出现内存溢出现象；1、4创建线程最大数量是Interger.MAX_VALUE，线程数可能随任务1：1增长，也可能出现内存溢出现象

### 并发、并行

正在运行的程序就是一个独立的进程，线程是属于进程的，多个线程是并发与并行同时进行的

#### 并发

* CPU同时处理线程的数量有限
* CPU会轮询为系统的每个线程服务，由于CPU切换的速度很快，给我们的感觉这些线程在同时执行，这是并发

#### 并行

* 在同一个时刻，同时又多个线程被CPU处理并执行

### 线程的生命周期

线程的状态：就是线程从生到死的过程，以及中间经历各种状态及状态切换

| 线程状态                | 描述                                                         |
| ----------------------- | ------------------------------------------------------------ |
| NEW(新建)               | 线程刚被创建，但是并未启动。                                 |
| Runnable(可运行)        | 线程已经调用了start()等待CPU调度                             |
| Blocked(锁阻塞)         | 线程在执行的时候未竞争到锁对象，则该线程进入Blocked状态；。  |
| Waiting(无限等待)       | 一个线程进入Waiting状态，另一个线程调用notify或者notifyAll方法才能够唤醒 |
| Timed Waiting(计时等待) | 同waiting状态，有几个方法有超时参数，调用他们将进入Timed Waiting状态。带有超时参数的常用方法有Thread.sleep 、Object.wait。 |
| Teminated(被终止)       | 因为run方法正常退出而死亡，或者因为没有捕获的异常终止了run方法而死亡。 |



## 定时器

控制任务延时调用，或者周期调用技术

实现方式：Timer、ScheduledExecutorService

### Timer

构造器:public Timer()

方法： public void schedule(TimerTask task,long delay,long period)

***

问题：

* Timer是单线程，处理多个任务按照顺序执行，存在延时与设置计时器的时间有出入
* 可能因为其中的某个任务的异常使Timer线程死掉，影响后续任务执行

### ScheduledExecutorService

ScheduledExecutorServicen内部为线程池，某个任务的执行情况不会影响其他任务的执行

public static ScheduleExecutorService newScheduledThreadPool(int corePoolsize)		获得线程池对象

public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)	周期调度方法

