# **MySQL**

****

> 关系型数据库：<u>建立在关系模型基础上，由多张相互连接的二维表组成的数据库</u>

[TOC]



## SQL通用语法及分类

- DDl  (Data Definition Language)  :数据定义语言，用来定义数据库对象（数据库、表、字段）
- DML (Data Manipulation Language):数据操作语言，用来对数据库表中的数据进行增删改
- DQL (Data Query Language): 数据查询语言，用来查询数据库中表的记录
- DCL (Data Control Language):数据控制语言，用来创建数据库用户、控制数据库的控制权限

### **DDL(数据定义语言)**

#### ***数据库操作***

* 查询所有数据库： show databases;

* 查询当前数据库： select database();

* 创建数据库  :    create database [if not exits ]  数据库名 [default charset 字符集] [collate 排序规则];

* 删除数据库：  drop database [if exits ] 数据库名;

* 使用数据库 ： use 数据库名;

  *utf8字符集长度为3字节，有些符号占4个字节，推荐使用utf8mb4字符集*

#### ***表操作***

* **查询当前数据库所有表**： show tables;

* **查询表结构 **： desc 表名;

* **查询指定表建表语句 **: show create table 表名;

* **创建表**：create table 表名(

  字段1	字段1类型 [comment '注释'1],

  ...........

  字段n    字段n类型[comment  '注释n']

  )[comment '表注释'];

* **添加字段**： alter table 表名 add 字段名 类型（长度）[comment '注释'] [约束];
  例：`ALTER TABLE emp ADD nickname varchar(20) COMMENT '昵称';`

* **修改数据类型**： alter table 表名 modify  字段名 新数据类型（长度）;

* **修改字段名和字段类型**： alter table 表名 change 旧字段名 新字段名 类型（长度）[comment];

  例：将emp表的nickname字段修改为username，类型为varchar(30)
  `ALTER TABLE emp CHANGE nickname username varchar(30) COMMENT '昵称';`

* **删除字段**：alter table 表名 drop 字段名;

* **修改表名** : alter table 表名 rename to 新表明;

* **删除表**：  drop table [if exists] 表名;

* **删除并重新创建表**: truncate table 表名;



### **DML(数据操作语言)**

#### ***添加数据***

* 指定字段： insert into 表名 (字段名1，字段名2,.....) values (值1，值2，....);
* 全部字段： insert into 表名 values (值1，值2，...);
* 批量添加指定字段：  insert into 表名 (字段名1，字段名2,.....) values (值1，值2，....),(值1，值2，....);
* 批量添加全部字段：  insert into 表名 values (值1，值2，...),(值1，值2，...);
* ***字符串和日期类型数据应包含在引号中，插入数据大小应在字段规定范围内***-

#### ***更新和删除数据***

* 修改数据： update 表名 set 字段名1=值1,字段名2=值2......... [where 条件]；
* 删除数据： delete from 表名 [where 条件]；

### **DQL(数据查询语言)**

#### ***语法***

select 字段列表 from 表名字段 where 条件列表 group by 分组字段列表 having 分组后条件列表 

order by 排序字段列表 limit 分页参数

#### ***基础查询***

* select 字段1，字段2..........   from 表名;
* select * from 表名;
* 设置别名： select 字段1 [as] (可省略) 别名1，字段2 [as] 别名2...from 表名；
* 去除重复记录：select distinct 字段列表 from 表名;

#### ***条件查询***

* select 字段列表 from 表名 where 条件;

|    比较运算符    |                    功能                    |
| :--------------: | :----------------------------------------: |
|        >         |                    大于                    |
|        >=        |                  大于等于                  |
|        <         |                    小于                    |
|        <=        |                  小于等于                  |
|        =         |                    等于                    |
|      <>或!=      |                   不等于                   |
| between....and.. |         在某个范围内（含最大最小）         |
|    ...in(...)    |     表中某字段等于in之后任一值，多选一     |
|   like 占位符    | 模糊匹配（_匹配单个字符，%匹配任意个字符） |
|     is null      |                   是NULL                   |

| 逻辑运算符 |   功能   |
| :--------: | :------: |
|  and或&&   |   并且   |
|  or或\|\|  |   或者   |
|  not或！   | 非，不是 |

* example：

1. `-- 年龄等于30`
2. `select * from employee where age = 30;`
3. `-- 年龄小于30`
4. `select * from employee where age < 30;`
5. `-- 小于等于`
6. `select * from employee where age <= 30;`
7. `-- 没有身份证`
8. `select * from employee where idcard is null or idcard = '';`
9. `-- 有身份证`
10. `select * from employee where idcard;`
11. `select * from employee where idcard is not null;`
12. `-- 不等于`
13. `select * from employee where age != 30;`
14. `-- 年龄在20到30之间`
15. `select * from employee where age between 20 and 30;`
16. `select * from employee where age >= 20 and age <= 30;`
17. `-- 下面语句不报错，但查不到任何信息`
18. `select * from employee where age between 30 and 20;`
19. `-- 性别为女且年龄小于30`
20. `select * from employee where age < 30 and gender = '女';`
21. `-- 年龄等于25或30或35`
22. `select * from employee where age = 25 or age = 30 or age = 35;`
23. `select * from employee where age in (25, 30, 35);`
24. `-- 姓名为两个字`
25. `select * from employee where name like '__';`
26. `-- 身份证最后为X`
27. `select * from employee where idcard like '%X';`

#### ***聚合查询（聚合函数）***

* 常见聚合函数

| 函数  |   功能   |
| :---: | :------: |
| count | 统计数量 |
|  max  |  最大值  |
|  min  |  最小值  |
|  avg  |  平均值  |
|  sum  |   求和   |

* 语法：select 聚合函数 （字段列表） from 表名;
* select count (id) from employee where workaddress ='广东省';

#### ***分组查询***

* select 字段列表 from 表名 [where 条件] group by 分组字段名[having 过滤条件];
* where在分组前过滤，不满足where不参与分组，having在分组后进行过滤：
* example：

1. `-- 根据性别分组，统计男性和女性数量（只显示分组数量，不显示哪个是男哪个是女）`
2. `select count(*) from employee group by gender;`
3. `-- 根据性别分组，统计男性和女性数量`
4. `select gender, count(*) from employee group by gender;`
5. `-- 根据性别分组，统计男性和女性的平均年龄`
6. `select gender, avg(age) from employee group by gender;`
7. `-- 年龄小于45，并根据工作地址分组`
8. `select workaddress, count(*) from employee where age < 45 group by workaddress;`
9. `-- 年龄小于45，并根据工作地址分组，获取员工数量大于等于3的工作地址`
10. `select workaddress, count(*) address_count from employee where age < 45 group by workaddress having address_count >= 3;`

#### ***排序查询***

* select 字段列表 from 表名 order by 字段1 排序方式1，字段2 排序方式2;  (现根据第一字段排序，第一字段相同则根据第二字段排序)
* asc 升序 （默认）， desc 降序
* example:	

1. `-- 根据年龄升序排序`
2. `SELECT * FROM employee ORDER BY age ASC;`
3. `SELECT * FROM employee ORDER BY age;`
4. `-- 两字段排序，根据年龄升序排序，入职时间降序排序`
5. `SELECT * FROM employee ORDER BY age ASC, entrydate DESC;`

#### ***分页查询***

* select 字段列表 from 表名 limit 起始索引，查询记录数：
* example：

1. `-- 查询第一页数据，展示10条`
2. `SELECT * FROM employee LIMIT 0, 10;`
3. `-- 查询第二页`
4. `SELECT * FROM employee LIMIT 10, 10;`

#### *注意事项*：

* 起始索引从0开始，起始索引 = （查询页码 - 1） * 每页显示记录数
* 分页查询是数据库的方言，不同数据库有不同实现，MySQL是LIMIT
* 如果查询的是第一页数据，起始索引可以省略，直接简写 LIMIT 10
* DQL执行顺序： from -> where ->group by ->select -> order by ->limit;

### DCL

**查询用户***

1.use mysql;

select * from user;

***创建用户***

create user '用户名'@'主机名' identified by '密码'；

***修改用户密码***

alter user '用户名'@'主机名' identify with mysql_native_password by '新密码';

***删除用户***

drop user '用户名'@'主机名';

* example:

1. `-- 创建用户test，只能在当前主机localhost访问`
2. `create user 'test'@'localhost' identified by '123456';`
3. `-- 创建用户test，能在任意主机访问`
4. `create user 'test'@'%' identified by '123456';`
5. `create user 'test' identified by '123456';`
6. `-- 修改密码`
7. `alter user 'test'@'localhost' identified with mysql_native_password by '1234';`
8. `-- 删除用户`
9. `drop user 'test'@'localhost';`

<u>主机名可以用 % 通配</u>

#### 常用权限

|        权限        |        说明        |
| :----------------: | :----------------: |
| all,all privileges |      所有权限      |
|       select       |      查询数据      |
|       insert       |      插入数据      |
|       update       |      修改数据      |
|       delete       |      删除数据      |
|       alter        |       修改表       |
|        drop        | 删除数据库/表/视图 |
|       create       |   创建数据库/表    |

查询权限： show grants for '用户名'@'主机名';

授予权限： grant 权限列表 on 数据库名，表名 to '用户名'@'主机名';

撤销权限：  revoke 权限列表 on 数据库名，表名 from '用户名'@'主机名';

<u>多个权限用逗号分隔，授权时，数据库名和表名可用* 进行通配，代表所有</u>

## 函数

> 字符串函数
>
> 数值函数
>
> 日期函数
>
> 流程函数

### 字符串函数

|           函数           |                         功能                          |
| :----------------------: | :---------------------------------------------------: |
|    concat(s1,s2..,sn)    |       字符串拼接，将s1，s2...sn拼接成一个字符串       |
|        lower(str)        |                 将字符串全部转为小写                  |
|        upper(str)        |                 将字符串全部转为大写                  |
|     lpad(str,n,pad)      | 左填充，用字符串pad对str的左边进行填充，达到n个字符串 |
|     rpad(str,n,pad)      | 右填充，用字符串pad对str的右边进行填充，达到n个字符串 |
|        trim(str)         |              去掉字符串头部和尾部的空格               |
| substring(str,start,len) |    返回从字符串str从start位置起的len个长度的字符串    |
|    replace(s1,s2,s3)     |          替换字符串，将s1中的s2全部替换成s3           |

* example：

1. `-- 拼接`
2. `SELECT CONCAT('Hello', 'World');`
3. `-- 小写`
4. `SELECT LOWER('Hello');`
5. `-- 大写`
6. `SELECT UPPER('Hello');`
7. `-- 左填充`
8. `SELECT LPAD('01', 5, '-');`
9. `-- 右填充`
10. `SELECT RPAD('01', 5, '-');`
11. `-- 去除空格`
12. `SELECT TRIM(' Hello World ');`
13. `-- 切片（起始索引为1）`
14. `SELECT SUBSTRING('Hello World', 1, 5);`

### 数值函数

|    函数    |               功能               |
| :--------: | :------------------------------: |
|  ceil(x)   |             向上取整             |
|  floor(x)  |             向下取整             |
|  mod(x,y)  |             返回x%y              |
|   rand()   |        返回0~1内的随机数         |
| round(x,y) | 求参数x的四舍五入值，保留y位小数 |

### 日期函数

|               函数                |                           功能                            |
| :-------------------------------: | :-------------------------------------------------------: |
|             curdate()             |                       返回当前日期                        |
|             curtime()             |                       返回当前时间                        |
|               now()               |                    返回当前日期和时间                     |
|            year(date)             |                    获取指定date的年份                     |
|            month(date)            |                    获取指定date的月份                     |
|             day(date)             |                    获取指定date的日期                     |
| date_add(date,interval expr type) |     返回一个日期/时间值加上一个时间间隔expr后的时间值     |
|       datediff(date1,date2)       | 返回初始时间date1和结束时间date2之间的天数（date1-date2） |

* example：

1. `-- DATE_ADD`
2. `SELECT DATE_ADD(NOW(), INTERVAL 70 YEAR);`

### 流程函数

|                           函数                           |                           功能                            |
| :------------------------------------------------------: | :-------------------------------------------------------: |
|                      if(value,t,f)                       |            如果value为true，则返回t，否则返回f            |
|                  ifnull(value1,value2)                   |      如果value1不为null，返回value1，否则返回value2       |
|    case when [val1] then [res1]....else [default] end    |      如果value1为true，返回res1，。。否则返回default      |
| case [expr] when [val1] then [res1]...else [default] end | 如果expr的值等于value1，则返回res1，。。。否则返回default |

* example

1. `select`
2. `    name,`
3. `    (case when age > 30 then '中年' else '青年' end)`
4. `from employee;`
5. `select`
6. `    name,`
7. `    (case workaddress when '北京市' then '一线城市' when '上海市' then '一线城市' else '二线城市' end) as '工作地址'`
8. `from employee;`

## 约束

|   约束   |                           描述                           |     关键字     |
| :------: | :------------------------------------------------------: | :------------: |
| 非空约束 |                限制该字段的数据不能为null                |    not null    |
| 唯一约束 |          保证该字段的所有数据都是唯一、不重复的          |     unique     |
| 主键约束 |         主键是一行数据的唯一表示，要求非空且唯一         |  primary key   |
| 默认约束 |      保存数据时，如果未指定该字段的值，则采用默认值      |    default     |
| 检查约束 |                  保证字段值满足某一条件                  |     check      |
| 外键约束 | 用来让两张图的数据之间建立连接，保证数据的一致性和完整性 |  foreign key   |
| 自动增长 |                       数据自动增长                       | auto_increment |

* example:

1. `create table user(`
2. `    id int primary key auto_increment,`
3. `    name varchar(10) not null unique,`
4. `    age int check(age > 0 and age < 120),`
5. `    status char(1) default '1',`
6. `    gender char(1)`
7. `);`

### 外键约束

* example

1. `CREATE TABLE 表名(`
2. `    字段名 字段类型,`
3. `    ...`
4. `    [CONSTRAINT] [外键名称] FOREIGN KEY(外键字段名) REFERENCES 主表(主表列名)`
5. `);`
6. `ALTER TABLE 表名 ADD CONSTRAINT 外键名称 FOREIGN KEY (外键字段名) REFERENCES 主表(主表列名);`
7. ``
8. `-- 例子`
9. `alter table emp add constraint fk_emp_dept_id foreign key(dept_id) references dept(id);`

删除外键：alter table 表名 drop foreign key 外键名;

### 删除/更新行为

| 行为        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| no action   | 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除/更新（与RESTRICT一致） |
| restrict    | 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除/更新（与NO ACTION一致） |
| cascade     | 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除/更新（与NO ACTION一致） |
| set null    | 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则设置子表中该外键值为null（要求该外键允许为null） |
| set default | 父表有变更时，子表将外键设为一个默认值（Innodb不支持）       |

alter table 表名 add constraint 外键名称 foreign key（外键字段）references 主表名（主表字段名）on update 行为 on delete 行为;

## 多表关系

* 一对多
* 多对多
* 一对一

### 查询

select * from employee ,dept;

消除无效笛卡尔积：

select * from employee, dept where employee,.dept=dept.id;

### 内连接查询

***隐式内查询***

select 字段列表 from 表1，表2，where 条件...;

***显式内查询***

select 字段列表 from 表1 [inner] join 表2 on 连接条件..;

<u>显式性能比隐式高</u>

* example

1. `-- 查询员工姓名，及关联的部门的名称`
2. `-- 隐式`
3. `select e.name, d.name from employee as e, dept as d where e.dept = d.id;`
4. `-- 显式`
5. `select e.name, d.name from employee as e inner join dept as d on e.dept = d.id;`

### 外连接查询

***左外连接***

查询左表所有数据及两表交集部分数据

* select 字段列表 from 表1 left [outer] join 表2 on 条件;（相当于查询表1的所有数据，包含表1和表2交集的部分数据）

***右外连接***

查询右表所有数据及两表交集部分数据

* select 字段列表 from 表1 right [outer] join 表2 on 条件;

* example:

1. `-- 左`
2. `select e.*, d.name from employee as e left outer join dept as d on e.dept = d.id;`
3. `select d.name, e.* from dept d left outer join emp e on e.dept = d.id;  -- 这条语句与下面的语句效果一样`
4. `-- 右`
5. `select d.name, e.* from employee as e right outer join dept as d on e.dept = d.id;`

***自连接查询***

当前表与自身的连接查询，自连接必须使用表别名

select 字段列表 from 表A 别名A join 表A 别名B on 条件...;

* example：

1. `-- 查询员工及其所属领导的名字`
2. `select a.name, b.name from employee a, employee b where a.manager = b.id;`
3. `-- 没有领导的也查询出来`
4. `select a.name, b.name from employee a left join employee b on a.manager = b.id;`

***联合查询***

把多次查询结果合并，形成新的查询集

select 字段列表 from 表A

union [all]

select 字段列表 from 表B

***<u>union all 会有重复的结果，union 不会,联合查询比使用or效率高</u>***

### 子查询

SQL语句中嵌套select语句

* select * from t1 where column1=(select column1 from t2);

**子查询外部的语句可以是 INSERT / UPDATE / DELETE / SELECT 的任何一个**

根据子查询结果可以分为：

- 标量子查询（子查询结果为单个值）
- 列子查询（子查询结果为一列）
- 行子查询（子查询结果为一行）
- 表子查询（子查询结果为多行多列）

根据子查询位置可分为：

- WHERE 之后
- FROM 之后

- SELECT 之后

#### 标量子查询

子查询返回的结果是单个值（数字、字符串、日期等）。
常用操作符： < > > >= < <=

* example:

1. `-- 查询销售部所有员工`
2. `select id from dept where name = '销售部';`
3. `-- 根据销售部部门ID，查询员工信息`
4. `select * from employee where dept = 4;`
5. `-- 合并（子查询）`
6. `select * from employee where dept = (select id from dept where name = '销售部');`
7. `-- 查询xxx入职之后的员工信息`
8. `select * from employee where entrydate > (select entrydate from employee where name = 'xxx');`

#### 列子查询

返回的结果是一列（可以是多行）。

常用操作符：

| 操作符 |                  描述                  |
| :----: | :------------------------------------: |
|   IN   |       在指定的集合范围内，多选一       |
| NOT IN |          不在指定的集合范围内          |
|  ANY   |  子查询返回列表中，有任意一个满足即可  |
|  SOME  | 与ANY等同，使用SOME的地方都可以使用ANY |
|  ALL   |    子查询返回列表的所有值都必须满足    |

* example:

1. `-- 查询销售部所有员工`
2. `select id from dept where name = '销售部';`
3. `-- 根据销售部部门ID，查询员工信息`
4. `select * from employee where dept = 4;`
5. `-- 合并（子查询）`
6. `select * from employee where dept = (select id from dept where name = '销售部');`
7. ``
8. `-- 查询xxx入职之后的员工信息`
9. `select * from employee where entrydate > (select entrydate from employee where name = 'xxx');`

#### 行子查询

返回结果是一行（可以是多列）

* example

1. `-- 查询与xxx的薪资及直属领导相同的员工信息`
2. `select * from employee where (salary, manager) = (12500, 1);`
3. `select * from employee where (salary, manager) = (select salary, manager from employee where name = 'xxx');`

#### 表子查询

返回结果是多行多列

* example

1. `-- 查询与xxx1，xxx2的职位和薪资相同的员工`
2. `select * from employee where (job, salary) in (select job, salary from employee where name = 'xxx1' or name = 'xxx2');`
3. `-- 查询入职日期是2006-01-01之后的员工，及其部门信息`
4. `select e.*, d.* from (select * from employee where entrydate > '2006-01-01') as e left join dept as d on e.dept = d.id;`

## 事务

事务是一组操作的集合，事务会把所有操作作为一个整体一起向系统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败。

* 查看事务提交方式

select @@autocommit;

* 设置事务提交方式，1为自动提交，0为手动提交，该设置只对当前会话有效

set @@autocommit =0;

* 开启事务

start transaction 或 begin transaction

* 提交事务

commit

* 回滚事务

roll back

* example:

1. `start transaction;`
2. `select * from account where name = '张三';`
3. `update account set money = money - 1000 where name = '张三';`
4. `update account set money = money + 1000 where name = '李四';`
5. `commit;`

### 四大特性

- 原子性(Atomicity)：事务是不可分割的最小操作但愿，要么全部成功，要么全部失败
- 一致性(Consistency)：事务完成时，必须使所有数据都保持一致状态
- 隔离性(Isolation)：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行
- 持久性(Durability)：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的

### 并发事务

|    问题    |                             描述                             |
| :--------: | :----------------------------------------------------------: |
|    脏读    |             一个事务读到另一个事务还没提交的数据             |
| 不可重复读 |       一个事务先后读取同一条记录，但两次读取的数据不同       |
|    幻读    | 一个事务按照条件查询数据时，没有对应的数据行，但是再插入数据时，又发现这行数据已经存在 |

### 并发事务的隔离级别

|       隔离级别        | 脏读 | 不可重复读 | 幻读 |
| :-------------------: | :--: | :--------: | :--: |
|   Read uncommitted    |  √   |     √      |  √   |
|    Read committed     |  ×   |     √      |  √   |
| Repeatable Read(默认) |  ×   |     ×      |  √   |
|     Serializable      |  ×   |     ×      |  ×   |

- √表示在当前隔离级别下该问题会出现
- Serializable 性能最低；Read uncommitted 性能最高，数据安全性最差

查看事务隔离级别：

select @@transaction_isolation ;

设置事务的隔离级别:

set [session | global] transaction isolation level {read uncommitted| read committed| repeatable read| serializable};
SESSION 是会话级别，表示只针对当前会话有效，GLOBAL 表示对所有会话有效



## 存储引擎

MySQL体系结构

![结构图](https://dhc.pythonanywhere.com/media/editor/MySQL%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84_20220315034329549927.png)

![层级描述](https://dhc.pythonanywhere.com/media/editor/MySQL%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84%E5%B1%82%E7%BA%A7%E5%90%AB%E4%B9%89_20220315034359342837.png)

存储引擎就是存储数据、建立索引、更新/查询数据等技术的实现方式。存储引擎是基于表而不是基于库的，所以存储引擎也可以被称为表引擎。
默认存储引擎是InnoDB。

* `-- 查询建表语句`
* `show create table account;`
* `-- 建表时指定存储引擎`
* `CREATE TABLE 表名(`
* `    ...`
* `) ENGINE=INNODB;`
* `-- 查看当前数据库支持的存储引擎`
* `show engines`

#### InnoDB

InnoDB 是一种兼顾高可靠性和高性能的通用存储引擎，在 MySQL 5.5 之后，InnoDB 是默认的 MySQL 引擎。

特点

- DML 操作遵循 ACID 模型，支持**事务**
- **行级锁**，提高并发访问性能
- 支持**外键**约束，保证数据的完整性和正确性

文件：

​	xx.ibd: xxx代表表名，InnoDB 引擎的每张表都会对应这样一个表空间文件，存储该表的表结构（frm、sdi）、数据和索引。

***参数  innodb_file_per_table：***

决定多张表共享一个表空间还是每张表对应一个表空间

语法：show variables like 'innodb_file_per_table';

从ibd文件中提取表结构数据： 

在cmd下执行   ibd2sdi  xxx.ibd

InnoDB存储结构

![InnoDB逻辑存储结构](https://dhc.pythonanywhere.com/media/editor/%E9%80%BB%E8%BE%91%E5%AD%98%E5%82%A8%E7%BB%93%E6%9E%84_20220316030616590001.png)

#### MyISAM

MyISAM是MySQL早期的默认存储引擎

特点：

- 不支持事务，不支持外键
- 支持表锁，不支持行锁
- 访问速度快

文件：

- xxx.sdi: 存储表结构信息
- xxx.MYD: 存储数据
- xxx.MYI: 存储索引

#### Memory

Memory引擎的表数据是存储在内存中的，受硬件、断电问题影响，只能作为临时表或缓存使用

特点：

- 存放在内存中，速度快
- hash索引（默认）

文件：

- xxx.sdi: 存储表结构信息

***存储引擎特点***

|     特点     |       InnoDB        | MyISAM | Memory |
| :----------: | :-----------------: | :----: | :----: |
|   存储限制   |        64TB         |   有   |   有   |
|   事务安全   |        支持         |   -    |   -    |
|    锁机制    |        行锁         |  表锁  |  表锁  |
|  B+tree索引  |        支持         |  支持  |  支持  |
|   Hash索引   |          -          |   -    |  支持  |
|   全文索引   | 支持（5.6版本之后） |  支持  |   -    |
|   空间使用   |         高          |   低   |  N/A   |
|   内存使用   |         高          |   低   |  中等  |
| 批量插入速度 |         低          |   高   |   高   |
|   支持外键   |        支持         |   -    |   -    |

***存储引擎的选择***

在选择存储引擎时，应该根据应用系统的特点选择合适的存储引擎。对于复杂的应用系统，还可以根据实际情况选择多种存储引擎进行组合。

- InnoDB: 如果应用对事物的完整性有比较高的要求，在并发条件下要求数据的一致性，数据操作除了插入和查询之外，还包含很多的更新、删除操作，则 InnoDB 是比较合适的选择
- MyISAM: 如果应用是以读操作和插入操作为主，只有很少的更新和删除操作，并且对事务的完整性、并发性要求不高，那这个存储引擎是非常合适的。
- Memory: 将所有数据保存在内存中，访问速度快，通常用于临时表及缓存。Memory 的缺陷是对表的大小有限制，太大的表无法缓存在内存中，而且无法保障数据的安全性

电商中的足迹和评论适合使用 MyISAM 引擎，缓存适合使用 Memory 引擎

## 索引

索引是帮助 MySQL **高效获取数据**的**数据结构（有序）**。在数据之外，数据库系统还维护着满足特定查找算法的数据结构，这些数据结构以某种方式引用（指向）数据，这样就可以在这些数据结构上实现高级查询算法，这种数据结构就是索引。

优缺点：

优点：

- 提高数据检索效率，降低数据库的IO成本
- 通过索引列对数据进行排序，降低数据排序的成本，降低CPU的消耗

缺点：

- 索引列也是要占用空间的
- 索引大大提高了查询效率，但降低了更新的速度，比如 INSERT、UPDATE、DELETE

### 索引结构

|      索引结构       |                             描述                             |
| :-----------------: | :----------------------------------------------------------: |
|       B+Tree        |          最常见的索引类型，大部分引擎都支持B+树索引          |
|        Hash         | 底层数据结构是用哈希表实现，只有精确匹配索引列的查询才有效，不支持范围查询 |
|  R-Tree(空间索引)   | 空间索引是 MyISAM 引擎的一个特殊索引类型，主要用于地理空间数据类型，通常使用较少 |
| Full-Text(全文索引) | 是一种通过建立倒排索引，快速匹配文档的方式，类似于 Lucene, Solr, ES |

|    索引    |    InnoDB     | MyISAM | Memory |
| :--------: | :-----------: | :----: | :----: |
| B+Tree索引 |     支持      |  支持  |  支持  |
|  Hash索引  |    不支持     | 不支持 |  支持  |
| R-Tree索引 |    不支持     |  支持  | 不支持 |
| Full-text  | 5.6版本后支持 |  支持  | 不支持 |

#### B-Tree

二叉树缺点：顺序插入时，会形成一个链表查询性能大大降低，大数据量情况下，层级较深，检索速度慢

红黑树：红黑树也存在大数据量情况下，层级较深，检索速度慢的问题

为解决上述问题，使用B-Tree结构

B-Tree (多路平衡查找树) 以一棵最大度数（max-degree，指一个节点的子节点个数）为5（5阶）的 b-tree 为例（每个节点最多存储4个key，5个指针）

![B-Tree结构](https://dhc.pythonanywhere.com/media/editor/B-Tree%E7%BB%93%E6%9E%84_20220316163813441163.png)

[演示地址](<https://www.cs.usfca.edu/~galles/visualization/BTree.html>)



#### B+Tree

结构图

![B+Tree结构图](https://dhc.pythonanywhere.com/media/editor/B+Tree%E7%BB%93%E6%9E%84%E5%9B%BE_20220316170700591277.png)

[演示地址](<https://www.cs.usfca.edu/~galles/visualization/BPlusTree.html>)

与B-Tree区别

- 所有的数据都会出现在叶子节点
- 叶子节点形成一个单向链表

MySQL 索引数据结构对经典的 B+Tree 进行了优化。在原 B+Tree 的基础上，增加一个指向相邻叶子节点的链表指针，就形成了带有顺序指针的 B+Tree，提高区间访问的性能。

![MySQL B+Tree 结构图](https://dhc.pythonanywhere.com/media/editor/%E7%BB%93%E6%9E%84%E5%9B%BE_20220316171730865611.png)

#### Hash

哈希索引就是采用一定的hash算法，将键值换算成新的hash值，映射到对应的槽位上，然后存储在hash表中
如果两个（或多个）键值，映射到一个相同的槽位上，他们就产生了hash冲突（也称为hash碰撞），可以通过链表来解决

特点：

- Hash索引只能用于对等比较（=、in），不支持范围查询（betwwn、>、<、…）
- 无法利用索引完成排序操作
- 查询效率高，通常只需要一次检索就可以了，效率通常要高于 B+Tree 索引

存储引擎支持：

- Memory
- InnoDB: 具有自适应hash功能，hash索引是存储引擎根据 B+Tree 索引在指定条件下自动构建的

#### 面试题

1. 为什么 InnoDB 存储引擎选择使用 B+Tree 索引结构？

- 相对于二叉树，层级更少，搜索效率高
- 对于 B-Tree，无论是叶子节点还是非叶子节点，都会保存数据，这样导致一页中存储的键值减少，指针也跟着减少，要同样保存大量数据，只能增加树的高度，导致性能降低
- 相对于 Hash 索引，B+Tree 支持范围匹配及排序操作

### 索引分类

| 分类     | 含义                                                 | 特点                     | 关键字   |
| :------- | :--------------------------------------------------- | :----------------------- | :------- |
| 主键索引 | 针对于表中主键创建的索引                             | 默认自动创建，只能有一个 | PRIMARY  |
| 唯一索引 | 避免同一个表中某数据列中的值重复                     | 可以有多个               | UNIQUE   |
| 常规索引 | 快速定位特定数据                                     | 可以有多个               |          |
| 全文索引 | 全文索引查找的是文本中的关键词，而不是比较索引中的值 | 可以有多个               | FULLTEXT |

在 InnoDB 存储引擎中，根据索引的存储形式，又可以分为以下两种：

| 分类                      | 含义                                                       | 特点                 |
| :------------------------ | :--------------------------------------------------------- | :------------------- |
| 聚集索引(Clustered Index) | 将数据存储与索引放一块，索引结构的叶子节点保存了行数据     | 必须有，而且只有一个 |
| 二级索引(Secondary Index) | 将数据与索引分开存储，索引结构的叶子节点关联的是对应的主键 | 可以存在多个         |

![大致原理](https://dhc.pythonanywhere.com/media/editor/%E5%8E%9F%E7%90%86%E5%9B%BE_20220318194454880073.png)

![演示图](https://dhc.pythonanywhere.com/media/editor/%E6%BC%94%E7%A4%BA%E5%9B%BE_20220319215403721066.png)

聚集索引选取规则：

- 如果存在主键，主键索引就是聚集索引
- 如果不存在主键，将使用第一个唯一(UNIQUE)索引作为聚集索引
- 如果表没有主键或没有合适的唯一索引，则 InnoDB 会自动生成一个 rowid 作为隐藏的聚集索引

#### 思考

1. 以下 SQL 语句，哪个执行效率高？为什么？

```
select * from user where id = 10;
select * from user where name = 'Arm';
-- 备注：id为主键，name字段创建的有索引
```

答：第一条语句，因为第二条需要回表查询，相当于两个步骤。

2. InnoDB 主键索引的 B+Tree 高度为多少？

答：假设一行数据大小为1k，一页中可以存储16行这样的数据。InnoDB 的指针占用6个字节的空间，主键假设为bigint，占用字节数为8.
可得公式：`n * 8 + (n + 1) * 6 = 16 * 1024`，其中 8 表示 bigint 占用的字节数，n 表示当前节点存储的key的数量，(n + 1) 表示指针数量（比key多一个）。算出n约为1170。

如果树的高度为2，那么他能存储的数据量大概为：`1171 * 16 = 18736`；
如果树的高度为3，那么他能存储的数据量大概为：`1171 * 1171 * 16 = 21939856`。

另外，如果有成千上万的数据，那么就要考虑分表，涉及运维篇知识。

#### 语法

* 创建索引

create [unique | fulltext] index 索引名（一般为index_name） on 表名 (字符段,..); 如果不加索引类型参数，则创建的是常规索引

* 查看索引

show index from 表名;

* 删除索引

drop index 索引名(一般为index_name) on 表名；

* example:

1. `-- name字段为姓名字段，该字段的值可能会重复，为该字段创建索引`
2. `create index idx_user_name on tb_user(name);`
3. `-- phone手机号字段的值非空，且唯一，为该字段创建唯一索引`
4. `create unique index idx_user_phone on tb_user (phone);`
5. `-- 为profession, age, status创建联合索引`
6. `create index idx_user_pro_age_stat on tb_user(profession, age, status);`
7. `-- 为email建立合适的索引来提升查询效率`
8. `create index idx_user_email on tb_user(email);`
9. `-- 删除索引`
10. `drop index idx_user_email on tb_user;`

### 性能分析

#### 查看执行频次

show [session | global] status 命令查看数据库各操作的访问次数

查看当前数据库的 insert,update,delete,select访问频率

show global | session status like 'com___';   _代表一个字符

#### 慢查询日志

慢查询日志记录了所有执行时间超过指定参数（long_query_time，单位：秒，默认10秒）的所有SQL语句的日志。
MySQL的慢查询日志默认没有开启，需要在MySQL的配置文件（/etc/my.cnf）中配置如下信息：

1. `# 开启慢查询日志开关`
2. `slow_query_log=1`
3. `# 设置慢查询日志的时间为2秒，SQL语句执行时间超过2秒，就会视为慢查询，记录慢查询日志`
4. `long_query_time=2`

更改后记得重启MySQL服务

慢查询日志文件位置：/var/lib/mysql/localhost-slow.log

查看慢查询日志开关状态：
`show variables like 'slow_query_log';`

#### profile

通过 have_profiling 参数，能看到当前 MySQL 是否支持 profile 操作：

select @@have_profiling;  (如果是yes则表示支持profile操作)

查看profiling是否打开：

select @@profiling;

profiling默认关闭，打开profiling：

set profiling=1;

查看所有语句耗时：

show profiles;

查看指定query_id 的SQL语句各个阶段耗时;

show profile for query  query_id;

查看指定query_id 的SQL语句CPU的使用情况;

show profile cpu for query query_id;

#### explain

explain 或者desc命令获取MySQL如何执行select的信息

语法：

直接在select 语句前加上关键字 explain/desc

explain各字段含义：

- id：select 查询的序列号，表示查询中执行 select 子句或者操作表的顺序（id相同，执行顺序从上到下；id不同，值越大越先执行）
- select_type：表示 SELECT 的类型，常见取值有 SIMPLE（简单表，即不适用表连接或者子查询）、PRIMARY（主查询，即外层的查询）、UNION（UNION中的第二个或者后面的查询语句）、SUBQUERY（SELECT/WHERE之后包含了子查询）等
- type：表示连接类型，性能由好到差的连接类型为 NULL、system、const、eq_ref、ref、range、index、all
- possible_key：可能应用在这张表上的索引，一个或多个
- Key：实际使用的索引，如果为 NULL，则没有使用索引
- Key_len：表示索引中使用的字节数，该值为索引字段最大可能长度，并非实际使用长度，在不损失精确性的前提下，长度越短越好
- rows：MySQL认为必须要执行的行数，在InnoDB引擎的表中，是一个估计值，可能并不总是准确的
- filtered：表示返回结果的行数占需读取行数的百分比，filtered的值越大越好

### 使用规则

#### 最左前缀法则

* 如果索引关联了多列（联合索引），要遵守最左前缀法则，最左前缀法则指的是查询从索引的最左列开始，并且不跳过索引中的列
* 如果跳跃某一列，索引将部分失效（后面的字段索引失效）
* 联合索引中，出现范围查询（<, >），范围查询右侧的列索引失效。可以用>=或者<=来规避索引失效问题。

#### 索引失效情况

1. 在索引列上进行运算操作，索引将失效。如：`explain select * from tb_user where substring(phone, 10, 2) = '15';`
2. 字符串类型字段使用时，不加引号，索引将失效。如：`explain select * from tb_user where phone = 17799990015;`，此处phone的值没有加引号
3. 模糊查询中，如果仅仅是尾部模糊匹配，索引不会是失效；如果是头部模糊匹配，索引失效。如：`explain select * from tb_user where profession like '%工程';`，前后都有 % 也会失效。
4. 用 or 分割开的条件，如果 or 其中一个条件的列没有索引，那么涉及的索引都不会被用到。
5. 如果 MySQL 评估使用索引比全表更慢，则不使用索引。

#### SQL提示

是优化数据库的一个重要手段，简单来说，就是在SQL语句中加入一些人为的提示来达到优化操作的目的。

分为use index，ignore index和force index三种

* example：

使用索引：
`explain select * from tb_user use index(idx_user_pro) where profession="软件工程";`
不使用哪个索引：
`explain select * from tb_user ignore index(idx_user_pro) where profession="软件工程";`
必须使用哪个索引：
`explain select * from tb_user force index(idx_user_pro) where profession="软件工程";`

* use 是建议，实际使用哪个索引 MySQL 还会自己权衡运行速度去更改，force就是无论如何都强制使用该索引。

#### 覆盖查询&回表查询

尽量使用覆盖索引（查询使用了索引，并且需要返回的列，在该索引中已经全部能找到），减少 select *。

如果在聚集索引中直接能找到对应的行，则直接返回行数据，只需要一次查询，哪怕是select *；如果在辅助索引中找聚集索引，如`select id, name from xxx where name='xxx';`，也只需要通过辅助索引(name)查找到对应的id，返回name和name索引对应的id即可，只需要一次查询；如果是通过辅助索引查找其他字段，则需要回表查询，如`select id, name, gender from xxx where name='xxx';`

所以尽量不要用`select *`，容易出现回表查询，降低效率，除非有联合索引包含了所有字段

explain 中 extra 字段含义：
`using index condition`：查找使用了索引，但是需要回表查询数据
`using where; using index;`：查找使用了索引，但是需要的数据都在索引列中能找到，所以不需要回表查询

***面试题***：

一张表，有四个字段（id, username, password, status），由于数据量大，需要对以下SQL语句进行优化，该如何进行才是最优方案：
`select id, username, password from tb_user where username='itcast';`

解：给username和password字段建立联合索引，则不需要回表查询，直接覆盖索引

#### 前缀索引

当字段类型为字符串（varchar, text等）时，有时候需要索引很长的字符串，这会让索引变得很大，查询时，浪费大量的磁盘IO，影响查询效率，此时可以只降字符串的一部分前缀，建立索引，这样可以大大节约索引空间，从而提高索引效率。

语法：create index  索引名(idx_xxx)  on 表名(字段名(n));

前缀长度：可以根据索引的选择性来决定，而选择性是指不重复的索引值（基数）和数据表的记录总数的比值，索引选择性越高则查询效率越高，唯一索引的选择性是1，这是最好的索引选择性，性能也是最好的。

求选择性公式：

select count(distinct email) / count(*) from tb_user;

select count(distinct substring(email, 1, 5)) / count(*) from tb_user;

#### 单列索引&联合索引

单列索引：即一个索引只包含单个列
联合索引：即一个索引包含了多个列
在业务场景中，如果存在多个查询条件，考虑针对于查询字段建立索引时，建议建立联合索引，而非单列索引。

- 多条件联合查询时，MySQL优化器会评估哪个字段的索引效率更高，会选择该索引完成本次查询

#### 设计原则

1. 针对于数据量较大，且查询比较频繁的表建立索引
2. 针对于常作为查询条件（where）、排序（order by）、分组（group by）操作的字段建立索引
3. 尽量选择区分度高的列作为索引，尽量建立唯一索引，区分度越高，使用索引的效率越高
4. 如果是字符串类型的字段，字段长度较长，可以针对于字段的特点，建立前缀索引
5. 尽量使用联合索引，减少单列索引，查询时，联合索引很多时候可以覆盖索引，节省存储空间，避免回表，提高查询效率
6. 要控制索引的数量，索引并不是多多益善，索引越多，维护索引结构的代价就越大，会影响增删改的效率
7. 如果索引列不能存储NULL值，请在创建表时使用NOT NULL约束它。当优化器知道每列是否包含NULL值时，它可以更好地确定哪个索引最有效地用于查询

## SQL优化

### 插入数据

普通插入：insert into 

1. 采用批量插入（一次插入的数据不建议超过1000条）
2. 手动提交事务
3. 主键顺序插入

大批量插入：

如果一次性需要插入大批量数据，使用insert语句插入性能较低，此时可以使用MySQL数据库提供的load指令插入。

1. `客户端连接服务端时，加上参数 --local-infile（这一行在bash/cmd界面输入）`

   `mysql --local-infile -u root -p`

2. ` 设置全局参数local_infile为1，开启从本地加载文件导入数据的开关`

   `set global local_infile = 1;`

   `select @@local_infile;`

3. `执行load指令将准备好的数据，加载到表结构中`

   `load data local infile '/root/sql1.log' into table 'tb_user' fields terminated by ',' lines terminated by '\n';`

### 主键优化

数据组织方式：在InnoDB存储引擎中，表数据都是根据主键顺序组织存放的，这种存储方式的表称为索引组织表（Index organized table, IOT）

页分裂：页可以为空，也可以填充一半，也可以填充100%，每个页包含了2-N行数据（如果一行数据过大，会行溢出），根据主键排列，将前一页的后半数据和被插入数据放进新页并插入。
页合并：当删除一行记录时，实际上记录并没有被物理删除，只是记录被标记（flaged）为删除并且它的空间变得允许被其他记录声明使用。当页中删除的记录到达 MERGE_THRESHOLD（默认为页的50%），InnoDB会开始寻找最靠近的页（前后）看看是否可以将这两个页合并以优化空间使用

主键设计原则：

- 满足业务需求的情况下，尽量降低主键的长度
- 插入数据时，尽量选择顺序插入，选择使用 AUTO_INCREMENT 自增主键
- 尽量不要使用 UUID 做主键或者是其他的自然主键，如身份证号
- 业务操作时，避免对主键的修改

### order by优化

1. Using filesort：通过表的索引或全表扫描，读取满足条件的数据行，然后在排序缓冲区 sort buffer 中完成排序操作，所有不是通过索引直接返回排序结果的排序都叫 FileSort 排序
2. Using index：通过有序索引顺序扫描直接返回有序数据，这种情况即为 using index，不需要额外排序，操作效率高

* 如果order by字段全部使用升序排序或者降序排序，则都会走索引，但是如果一个字段升序排序，另一个字段降序排序，则不会走索引，explain的extra信息显示的是`Using index, Using filesort`，如果要优化掉Using filesort，则需要另外再创建一个索引，如：`create index idx_user_age_phone_ad on tb_user(age asc, phone desc);`，此时使用`select id, age, phone from tb_user order by age asc, phone desc;`会全部走索引

总结

- 根据排序字段建立合适的索引，多字段排序时，也遵循最左前缀法则
- 尽量使用覆盖索引
- 多字段排序，一个升序一个降序，此时需要注意联合索引在创建时的规则（ASC/DESC）
- 如果不可避免出现filesort，大数据量排序时，可以适当增大排序缓冲区大小 sort_buffer_size（默认256k）

### group by优化

- 在分组操作时，可以通过索引来提高效率
- 分组操作时，索引的使用也是满足最左前缀法则的

如索引为`idx_user_pro_age_stat`，则句式可以是`select ... where profession order by age`，这样也符合最左前缀法则

### limit优化

常见的问题如`limit 2000000, 10`，此时需要 MySQL 排序前2000000条记录，但仅仅返回2000000 - 2000010的记录，其他记录丢弃，查询排序的代价非常大。
优化方案：一般分页查询时，通过创建覆盖索引能够比较好地提高性能，可以通过<u>覆盖索引加子查询形式</u>进行优化

* example:

1. `-- 此语句耗时很长`

   `select * from tb_sku limit 9000000, 10;`

2. `-- 通过覆盖索引加快速度，直接通过主键索引进行排序及查询`

   `select id from tb_sku order by id limit 9000000, 10;`

3. `-- 下面的语句是错误的，因为 MySQL 不支持 in 里面使用 limit`

   `-- select * from tb_sku where id in (select id from tb_sku order by id limit 9000000, 10);`

4. `-- 通过连表查询即可实现第一句的效果，并且能达到第二句的速度`

   `select * from tb_sku as s, (select id from tb_sku order by id limit 9000000, 10) as a where s.id = a.id;`

### count优化

MyISAM 引擎把一个表的总行数存在了磁盘上，因此执行 count(*) 的时候会直接返回这个数，效率很高（前提是不适用where）；

InnoDB 在执行 count(*) 时，需要把数据一行一行地从引擎里面读出来，然后累计计数。
优化方案：自己计数，如创建key-value表存储在内存或硬盘，或者是用redis

各种用法的性能：

- count(主键)：InnoDB引擎会遍历整张表，把每行的主键id值都取出来，返回给服务层，服务层拿到主键后，直接按行进行累加（主键不可能为空）
- count(字段)：没有not null约束的话，InnoDB引擎会遍历整张表把每一行的字段值都取出来，返回给服务层，服务层判断是否为null，不为null，计数累加；有not null约束的话，InnoDB引擎会遍历整张表把每一行的字段值都取出来，返回给服务层，直接按行进行累加
- count(1)：InnoDB 引擎遍历整张表，但不取值。服务层对于返回的每一层，放一个数字 1 进去，直接按行进行累加
- count(*)：InnoDB 引擎并不会把全部字段取出来，而是专门做了优化，不取值，服务层直接按行进行累加

按效率排序：count(字段) < count(主键) < count(1) < count(*)，

所以尽量使用 count(*)

### update优化

InnoDB 的行锁是针对<u>索引</u>加的锁，不是针对记录加的锁，并且该索引不能失效，否则会从行锁升级为表锁。

如以下两条语句：
`update student set no = '123' where id = 1;`，这句由于id有主键索引，所以只会锁这一行；
`update student set no = '123' where name = 'test';`，这句由于name没有索引，所以会把整张表都锁住进行数据更新，解决方法是给name字段添加索引

## 视图

视图（View）是一种虚拟存在的表。视图中的数据并不在数据库中实际存在，行和列数据来自定义视图的查询中使用的表，并且是在使用视图时动态生成的。
通俗的讲，视图只保存了查询的SQL逻辑，不保存查询结果。所以我们在创建视图的时候，主要的工作就落在创建这条SQL查询语句上。

### 创建视图

create [or replace] view 视图名称 (列名列表) as select 语句 [with [cascaded | local] check option]

* example

create or replace view stu_v  as select id,name from student where id<=10;

### 查询视图

查看创建视图语句： show create view 视图名称;

查看视图数据： select * from 视图名称;

### 修改视图

方式一：create or replace view 视图名称 (列名列表)  as select 语句[with [cascaded | local] check option]

方式二： alter view 视图名称(列名列表) as select语句 [with [cascaded |local] check option]

### 删除视图

drop view [if exists] 视图名称 

### 视图检查选项

当使用with check option 子句创建视图时，MySQL会通过视图检查正在更改的每个行，例如插入，更新，删除，以使其符合视图的定义。MySQL允许基于另一个视图创建视图，它还会检查依赖视图中的规则以保持一致性。为了确定检查的范围，mysql提供了两个选项：cascaded 和 local ，默认值为 cascaded

#### csacaded

级联，选择该选项除了会检查创建视图时的条件，还会检查依赖视图的条件

#### local

本地的条件也会检查，还会向上检查。在向上找的时候，就要看是否上面开了检查选项，如果没开就不检查。

#### 区别

cascaded 不管上面开没开检查选项都会进行检查，local只会检查上面开了检查选项的

### 更新及作用

#### 更新

要使视图可更新，视图中的行与基础表中的行之间必须存在一对一的关系。如果视图包含以下任何一项，则该视图不可更新

1. 聚合函数或窗口函数 ( SUM()、MIN()、MAX()、COUNT() 等 )
2. DISTINCT
3. GROUP BY
4. HAVING
5. UNION 或者UNION ALL

* example

用了聚合函数，插入会失败。 

create view stu_v_count   as select count(*) from student;

insert into  stu_v_count   values(10);   插入失败

#### 作用

* 视图不仅可以简化用户对数据的理解，也可以简化他们的操作。那些被经常使用的查询可以被定义为视图，从而使得用户不必为以后的操作每次指定全部的条件。

* 安全： 数据库可以授权，但不能授权到数据库特定行和特定的列上。通过视图用户只能查询和修改他们所能见到的数据
* 数据独立 视图可帮助用户屏蔽真实表结构变化带来的影响。

## 存储过程

存储过程是事先经过编译并存储在数据库中的一段SQL 语句的集合， 存储过程思想上很简单，就是数据库SQL 语言层面的代码封装与重用。

### 特点

1. 封装
2. 复用
3. 可以接收参数，也可以返回数据减少网络交互，效率提升

### 基本语法

#### 创建

create procedure 存储过程名称([参数列表])

begin

​			SQL语句

end;

*  在命令行中，执行创建存储过程的SQL时，需要通过关键字delimiter 指定SQL语句的结束符。delimiter $ ，则 $ 符作为结束符,完成创建后在用delimiter改回 ; 作为结束符

#### 调用

call 名称([参数])

#### 查看

查询指定数据库的存储过程及状态信息

* select * from information_schema.ROUTINES where ROUTINES_SCHEMA="数据库名"

查询某个存储过程的定义

* show create procedure

#### 删除

drop procedure [if exists] 存储过程名称

### 变量

#### 系统变量

是MySQL服务器提供，不是用户自定义的，分为全局变量(global)和会话变量(session)

##### 查看系统变量

* show [session | global] variables;	查看所有系统变量
* show [session | global] variables like '...';	可以通过模糊搜索查看变量
* select @@[session | global] 系统变量名；	查看指定变量的值

##### 设置系统变量

* set [session |global] 系统变量名=值;
* set @@[session | global] 系统变量名=值;

#### 用户定义变量

是用户根据需要自己定义的变量，用户变量不用提前声明，在用的时候直接用“@变量名”使用就可以，作用域为当前连接

##### 赋值

set @ 变量名 = 值[,@变量名=值...];

set @ 变量名  := 值[,@变量名=值...];

select @变量名 :=值[,@变量名:=值...];

select 字段名 into @变量名 from 表名;

##### 使用

select @变量名;

#### 局部变量

是根据需要定义的在局部生效的变量，访问前需要declare声明，可用作存储过程内的局部变量和输入参数，范围在声明的begin......end块

##### 声明

declare 变量名 变量类型 [default ..]; 初始参数

##### 赋值

set 变量名=值;

set 变量名:=值;

select 字段名 Into 变量名 from 表名 ..;

### if判断

语法 

if 条件1 then

  .........

elseif 条件2 then

...........

else

.................

end if;

### 参数

| 类型  | 含义                                             |
| ----- | ------------------------------------------------ |
| in    | 该类参数作为输入，也就是需要调用时传入值（默认） |
| out   | 该类参数作为输出，也就是该参数可以作为返回值     |
| inout | 既可以作为输入参数，也可以作为输出参数           |

用法

create procedure 存储过程名称([in | out | inout 参数名  参数类型])

begin

​			SQL语句

end;

### case

语法一

case  case_value

​		when value1 then statement1

​		[when value2 then statement2]

​		[else statement]

end case;

语法二

case

​			when condition1 then statement1

​			[when condition2 then statement]

​			[else statement]

end case;

### while

while循环时有条件的循环控制语句~~（跟c的while类似~~

语法：

while 条件 do

​			SQL逻辑

end while;

### repeat

repeat时有条件的循环控制语句，满足条件时退出~~（跟c的do while类似，条件相反~~

repeat 

​			SQL逻辑

​			until 条件	（满足条件退出循环

end repeat;

### loop

loop实现简单的循环，不在SQL逻辑中增加退出条件为死循环，可以配合两个语句使用

* leave： 配合循环使用，退出循环	~~（break~~
* iterate： 必须用在循环中，作用是跳出当前循环	~~（continue~~

[begin_label(循环名)] loop

​			SQL逻辑

end loop [end_label(循环名)]

leave label（循环名）~~相当于break~~

iterate label（循环名）~~相当于continue~~

### 游标与条件处理程序（handler）	

#### 游标

游标（CURSOR）是用来存储查询结果集的数据类型，在存储过程和函数中可以使用游标对结果集进行循环的处理~~（迭代器）~~

* 声明游标

declare 游标名称 cursor for 查询语句（要先声明普通变量再声明游标

* 打开游标

open 游标名称

* 获取游标记录

fetch 游标名称 into 变量[，变量]

* 关闭游标

close 游标名称

![1673187658379](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673187658379.png)

问题：无法停止while循环

#### 条件处理程序

定义在流程控制结构执行过程中遇到的问题

declare handler_action handler for condition_value [,condition_value..] statement (handler_action后执行的语句)

***handler_action***

* continue 继续执行当前程序
* exit 中止执行当前程序

***condition_value***

* sqlstate 状态码
* sqlwarning 所有01开头的sqlstate
* not found 所有02开头的sqlstate
* sqlexception 所有没有被sqlwarining或not found捕获的sqlstate简写

![1673188377981](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673188377981.png)

### 存储函数（可以用存储过程替代

有返回值的存储过程

create function 函数名称（[参数])

return 参数类型 [characteristic..]

begin

​		SQL语句

​		return ...;

end;

***characteristic***

* deterministic:相同输入的参数总是产生相同的结果
* no sql ：不包含sql语句
* reads sql data： 包含读取数据的语句，但不包含写入数据的语句

## 触发器

触发器是与表有关的数据库对象，指在insert/update/delete之前或之后，触发并执行触发器中定义的SQL语句集合。

行级触发（比如说 一条语句影响了 5 行 则会被触发 5 次），不支持语句级触发（比如说 一条语句影响了 5 行 则会被触发 1 次）。

| 触发器类型 | NEW 和 OLD                                           |
| ---------- | ---------------------------------------------------- |
| INSERT     | NEW 表示将要或者已经新增的数据                       |
| UPDATE     | OLD表示修改之前的数据，NEW表示将要或已经修改后的数据 |
| DELETE     | OLD表示将要或者已经删除的数据                        |

语法

* 创建

create trigger 触发器名

before/after  insert/update/delete  on 表名 for each row

begin

​		事务

end;

* 查看

show triggers;

* 删除

drop trigger [数据库名].触发器名		如果没有指定数据库名，默认当前数据库

## 锁

分类：全局锁、表级锁、行级锁

### 全局锁

对整个数据库实例加锁，变为只读状态，DML、DDL语句阻塞

应用场景：做全库备份

* 上锁：

flush tables with read lock;

* 备份（借助mysqldump做数据备份

mysqldump -u用户名（root ） -p用户密码     备份的数据库名 > 存放位置;

可以加上参数 --single-transaction 完成不加锁的一致性数据备份

mysqldump --single-tracsaction -u用户名（root ） -p用户密码     备份的数据库名 > 存放位置;

* 释放全局锁

unlock tables;

### 表级锁

#### 表锁

1.表共享读锁（read lock

2.表独占写锁（write lock

* 加锁

lock tables 表名 ... read/write

* 释放锁

  unlock tables	（或者客户端断开连接

  

#### 元数据锁（MDL

MDL加锁过程是系统自动控制，无需显式使用，在访问一张表的时候会自动加上。MDL锁主要作用是维护表元数据的数据一致性

| 对应SQL                                           | 锁类型                               | 说明              |
| ------------------------------------------------- | ------------------------------------ | ----------------- |
| lock tables xxx read/write                        | share_read_only/shared_no_read_write |                   |
| select 、 select ... lock in share mode           | share_read                           |                   |
| insert 、 update 、delete 、 select ...for update | shared_write                         | 与shared_read兼容 |
| alter table...                                    | exclusive                            | 与其他MDL互斥     |

* 查询元数据锁

select object_type,object_schema,object_name,lock_type,lock_duration from performance_schema.metadata_locks;

#### 意向锁

意向共享锁（IS）；与表锁共享锁兼容，与表锁排他锁互斥

意向排他锁（IX）：与表锁共享锁及排他锁都互斥，意向锁之间不会互斥

查看意向锁及行锁情况：

select object_schema,object_name,index_name,lock_type,lock_mode,lock_data from performance_schema_locks;

### 行级锁

每次操作锁住对应行数据，应用在InnoDB存储引擎中

* 行锁：锁定单个行记录的锁，防止其他事务对此update和delete，在RC和RR隔离级别下支持

* 间隙锁：锁定索引记录间隙，确保间隙不变，防止其他事务在间隙insert，产生幻读（不包括端点），在RR隔离级别下支持
* 临键锁：行锁和间隙锁组合，同时锁住数据和GAP，在RR隔离级别下支持

#### 行锁(Record Lock)

* 共享锁（S）：允许一个事务去读一行，阻止其他事务获得相同数据集的排他锁
* 排他锁（X）：允许获取排他锁的事务更新数据，阻止其他事务获得相同数据集的共享锁和排他锁

针对唯一索引进行检索自动优化为行锁

若InnoDB的行锁是针对索引家的锁，不通过索引条件检索数据，那么InnoDB将对表中的所有记录加锁，此时**<u>升级为表锁</u>**

| SQL                            | 行锁类型 | 说明                                     |
| ------------------------------ | -------- | ---------------------------------------- |
| select....  lock in share mode | 共享锁   | 需要手动在select之后加lock in share mode |
| select..... for updare         | 排他锁   | 需要手动在select之后加for update         |



#### 间隙锁(Gap Lock)/临键锁(Next-Key Lock)

默认情况下，InnoDB在RR事务隔离级别下运行使用next-key锁进行搜索和索引扫描以防止幻读

间隙锁唯一目的是防止其他事务插入间隙，间隙锁可以共存

* 索引上的等值查询（唯一索引），给不存在的记录加锁时优化为间隙锁（所著所在间隙）
* 索引上的等值查询（普通索引），向右遍历时最后一个值不满足查询条件时，next-key lock 退化为间隙锁（锁住查询行前的间隙和查询行后的间隙及查询行）
* 索引上的范围查询（唯一索引）会访问不满足条件的第一个值为止（锁住所在行到无穷的间隙）

## InnoDB存储引擎

### 逻辑存储结构

![1673326390492](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673326390492.png)

### 架构

**InnoDB架构图**

![1673326561374](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673326561374.png)

**左侧为内存结构，右侧为磁盘结构**

#### 内存结构

![1673326762056](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673326762056.png)

![1673327369569](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673327369569.png)

![1673327816924](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673327816924.png)

![1673327862963](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673327862963.png)

![1673328057718](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673328057718.png)

#### 磁盘结构

![1673334369355](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673334369355.png)

![1673336399958](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673336399958.png)	![1673336531108](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673336531108.png)

#### 后台线程

将InnoDB存储引擎缓冲池数据刷新到磁盘文件

![1673336839255](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673336839255.png)

![1673336975102](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673336975102.png)

### 事务

#### redo log

![1673338362182](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673338362182.png)

对数据进行处理时数据从磁盘结构到缓冲池中，修改后的脏页传输到redo log buffer，在传输到redo log file中，如果脏页传输失败则通过redo log 恢复，如果成功一段时间后redo log file被清理

#### undo log

![1673338801219](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673338801219.png)

事务失败用undo log中语句回滚

### MVCC（多版本并发控制）

![1673354347718](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673354347718.png)

![1673354721856](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673354721856.png)

![1673354988788](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673354988788.png)

![1673355231169](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673355231169.png)

![1673356950192](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673356950192.png)

RC隔离级别下MVCC快照读的流程

![1673357301393](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673357301393.png)	

RR隔离级别下MVCC第一次快照读跟RC隔离级别下一致，接下来的快照读套用第一次结果

![1673357520419](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673357520419.png)

## MySQL管理

### 系统数据库

![1673442235462](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\1673442235462.png)

### 常用工具

#### mysql

指MySQL客户端工具而非MySQL服务

* 语法

mysql [options] [database]

* 选项

1. -u : user=name      指定用户名
2. -p : password[=name]   指定密码
3. -h : host=name       指定服务器IP或域名
4. -p : port=port           指定连接端口
5. -e  : execute=name    执行sql语句并退出（可以在MySQL客户端执行sql语句而不用连接MySQL数据库再执行，处理脚本方便）

* example：

mysql -uroot -p123456 数据库名 -e "select * from stu";

#### mysqladmin

是一个执行管理操作的客户端程序，可以用来检查服务器配置、当前状态、创建并删除数据库

* 通过帮助文档查看选项

mysqladmin --help;

* example: 

mysqladmin -uroot -p12456 drop  'test01';

#### mysqlbinlog

查看服务器生成的二进制日志文件

* 语法

mysqlbinlog [options] log-file1,log-file2.....;

* 选项
  * -d : database=name  		指定数据库名称，只列出指定数据库相关操作
  * -o : offset=n              忽略日志中前n行命令
  * -r : result-file=name   将输出的文本格式日志输出到指定文件
  * -s : short-form        显示简单格式，省略掉一些信息
  * --start datatime=date1 --stop-datatime=date2  指定日期间隔内的所有日志
  * --start position=pos1 --stop-position=pos2   指定位置间隔内的所有日志

#### mysqlshow

对象查找工具，用来查找存在哪些数据库、数据库中的表、表中的列或索引

* 语法：mysql [options] [数据库名[表名[列名]]]

* 选项：
  *  --count  显示数据库及表的统计信息（数据库、表可不指定）
  * -i   显示指定数据库或指定表的状态信息

* example 

mysqlshow -uroot -p123456  da01  --count  

#### mysqldump

用来备份数据库或在不同数据库之间进行数据迁移，备份内容包含创建表及插入表的sql语句

* 语法：
  *  mysqldump [options] 数据库名[表名]
  *  mysqldump [options] --database/ -B db1[db2 db3...]
  *  mysqldump [options]  --all-databases/-A
    * 后面加  >文件名   指定备份到的文件

* 连接选项
  * -u : user=name   指定用户名
  * -p : password[=name]   指定密码
  * -h : host=name     指定服务器ip或域名
  * -p : port=..              指定连接端口

* 输出选项
  * --add-drop-database   在每个数据库创建语句前加上drop database 语句
  * -add drop-table  在每个表创建语句前加上drop table语句；默认开启，不开启（--skip-add-drop-tabe)
  * -n : no-create-db  不包含数据库的创建语句
  * -t : no-create-info  不包含数据表的创建语句
  * -d : no-data         不包含数据
  * -T : tab=name      自动生成两个文件，一个sql文件，创建表结构语句，一个txt文件，数据文件

#### mysqlimport/source

mysqlimport是客户端数据导入工具，用来导入mysqldumo 加-T参数后导出的文本文件

* 语法：mysqlimport [options] db_name textfile1 [textfile2..]
* example: mysqlimport -uroot -p123456  test  /xxx.txt

如果需要导入sql文件，可以使用mysql 中的source指令

* 语法： source /root/xxxx.sql

## 日志

### 错误日志

错误日志记录了当mysql启动或停止时，以及服务器在运行过程中发生任何严重错误时的相关信息

当数据库出现任何故障导致无法正常使用时，建议先查看此日志

该日志默认开启，存放/var/log/，默认日志名为mysqld.log

* 查看日志位置： show variables like '%log_error%'

### 二进制日志

二进制日志记录了所有DDl语句和DML语句，但不包括数据查询语句

作用：

* 灾难时的数据恢复
* MySQL的主从复制

二进制日志默认时开的，涉及参数： show variables like '%log_bin'

二进制日志的具体格式及特点

![image-20230114160356789](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114160356789.png)

二进制日志是以二进制方式存储的，不能直接读取，需要通过mysqlbinlog查看

![image-20230114160953686](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114160953686.png)

**日志删除**

![image-20230114161552422](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114161552422.png)

![image-20230114161801193](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114161801193.png)

### 查询日志

查询日志中记录了客户端的所有操作语句，默认情况下查询日志是未开启的，开启查询日志可以配置 general_log , general_log_file

![image-20230114162056398](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114162056398.png)

![image-20230114162214563](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114162214563.png)

### 慢查询日志

![image-20230114162621550](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114162621550.png)

![image-20230114163157085](C:\Users\28246\AppData\Roaming\Typora\typora-user-images\image-20230114163157085.png)

## 主从复制

主从复制是指将主数据库的DDL和DML操作通过二进制日志传到从库服务器中，然后在从库上对这些日志重新执行，从而使从库和主库的数据保持同步

![image-20230114163600613](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114163600613.png)

增删改在主库，查询在从库，实现读写分离

### 原理

![image-20230114164048112](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114164048112.png)

### 搭建

服务器准备

![image-20230114165627644](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114165627644.png)

准备好两台服务器之后在两台服务器中安装MySQL并完成初始化工作

**主库配置**

* 修改配置文件 /etc/my.cnf

![image-20230114170117691](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114170117691.png)

![image-20230114170608766](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114170608766.png)

**从库配置**

* 修改配置文件
* 可以配置超级管理员只读  :    super-read-only=1;

![image-20230114172033701](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114172033701.png)

![image-20230114172335217](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114172335217.png)

![image-20230114172709535](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114172709535.png)

## 分库分表

![image-20230114173500254](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114173500254.png)

### 垂直拆分

#### 垂直分库

以表为依据，根据业务将不同表拆分到不同库中

特点

* 每个库的表结构都不一样
* 每个库的数据也不一样
* 所有库的并集使全量数据

#### 垂直分表

以字段为依据，根据字段属性将不同字段拆分到不同表中

特点

* 每个表的结构都不一样
* 每个表的数据也不一样，一般通过一列（主键/外键）关联
* 所有表的并集是全量数据

![image-20230114175337853](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114175337853.png)

### 水平拆分

#### 水平分库

以字段为依据，按照一定策略，将一个库的数据拆分到多个库中

特点

* 每个库的表结构都一样
* 每个库的数据都不一样
* 所有库的并集是全量数据

#### 水平分表

以字段为依据	，按照一定策略，将一个表的数据拆分到多个表中

特点

* 每个表的表结构都一样
* 每个表的数据都不一样
* 所有表的并集是全量数据

![image-20230114175924730](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114175924730.png)

### 实现技术

![image-20230114180353152](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114180353152.png)

### Mycat

Mycat是开源的、活跃的、基于java语言编写的MySQL**数据库中间件**，可以像使用MySQL一样使用Mycat

![image-20230114215637048](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114215637048.png)

Mycat概述

![image-20230115103748924](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230115103748924.png)







## 读写分离

![image-20230114181412199](https://souln.oss-cn-guangzhou.aliyuncs.com/java/image-20230114181412199.png)
