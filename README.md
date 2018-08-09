# 分表
===

slice-table是基于城云智慧云平台某个业务需分表场景进行的升级优化

## 构建步骤
```shell

# 1. 下载源码
git clone xxx


# 用IDE导入

```

## 技术栈

- mybatis(3.4.2)
- mysql(服务器Ver 14.14 Distrib 5.7.9, for osx10.9 (x86_64), 驱动6.0.2)
- slf4j + log4j (1.7.21)
- lombok(1.16.12)

## 代码结构

```shell
  ├── LICENSE
  ├── README.md
  ├── pom.xml
  └── src
      ├── main
      │   ├── java
      │   │   └── com
      │   │       └── tuyu
      │   │           ├── dao
      │   │           │   └── UserActionDao.java
      │   │           ├── dto
      │   │           │   └── UserActionDto.java
      │   │           └── po
      │   │               └── UserAction.java
      │   ├── resources
      │   │   ├── config.properties
      │   │   ├── log4j.properties
      │   │   ├── mappers
      │   │   │   └── UserActionMapper.xml
      │   │   ├── mybatis-config.xml
      │   │   └── radio-mac.txt
      │   └── script
      │       └── create_table.sql
      └── test
          └── java
              └── com
                  └── tuyu
                      └── dao
                          ├── RadioMacTest.java
                          └── UserActionDaoTest.java

```

## 分表策略
我可以将用户行为表水平查分为100张表，`user_action_0`,...`user_action_99`,每张表的字段设置都一样，对应的索引要建立在查询业务之上，这里不做讨论，
其次存储引擎选择myisam,因为后面要使用merge存储引擎，最后建立merge表即`user_action`，我在本地测试就没有建立这么多张表，只创建了5张表并插入一千多万的数来做示范：
```
-- total: 10101000
select count(*) from user_action

-- total: 2017951
select count(*) from user_action_0

-- total: 2020653
select count(*) from user_action_1

-- total: 2019868
select count(*) from user_action_2

-- total: 2019883
select count(*) from user_action_3

-- total: 2022645
select count(*) from user_action_4
```

用户行为的模拟数据类似下面这样
```shell
+----+------------+----------+-------------------+-------------------+------------+---------------------+------------+------------+------------+------+----------+--------------+--------+----------+-----------+------------+-------------+--------+---------------------+
| id | date       | group_id | radio_mac         | user              | user_name  | dev_id              | host_ip    | dst_ip     | ip_version | site | tm_type  | serv         | app    | src_port | serv_port | net_action | record_time | result | create_time         |
+----+------------+----------+-------------------+-------------------+------------+---------------------+------------+------------+------------+------+----------+--------------+--------+----------+-----------+------------+-------------+--------+---------------------+
|  6 | 2018-07-19 |       56 | 83:f6:f6:92:e7:d3 | 04:b9:60:5a:c6:0c | 3677996964 | 5417426491785176729 | 1588063025 | 1544644427 | 1523416073 |      | winPhone | P2P流媒体     | 微信    |     1082 |      5193 | 记录       | 07:35:00    |        | 2018-08-08 23:53:43 |
+----+------------+----------+-------------------+-------------------+------------+---------------------+------------+------------+------------+------+----------+--------------+--------+----------+-----------+------------+-------------+--------+---------------------+
```
建表语句类似下面
```sql
-- `user_action_0...4`都和下面的建表语句一样，只是表名变了
create table if not exists `user_action_0` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';

-- merge表
create table if not exists `user_action` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=merge union=(`user_action_0`, `user_action_1`, `user_action_2`, `user_action_3`, `user_action_4`) 
insert_method=last default charset='utf8' comment '用户行为表';
```
这里要做一下说明：就是如何将数据分散保存到这5张表中的，我通过关键属性的值(date, groupId, radioMac,userName)来计算hash值，
然后通过hash值模5得到插入表的序号，最后通过mybatis的sql语句拼接：
```xml
<insert id="add" parameterType="com.tuyu.po.UserAction">
        insert into `user_action_${tableNum}` ( ...) ...
</insert>
```
最后将数据插入到具体的表中

查询的时候直接查询merge表`user_action`
比如某个业务查询场景:
查询某月所有用户行为的记录数
```sql
-- 未加索引查询时间3s多
select count(*) as num from user_action where `date` between '2018-06-01' and '2018-06-31';
```
给表中`date`字段加上索引
```sql
alter table user_action_0 add index `idx_date`(`date`)
alter table user_action_1 add index `idx_date`(`date`);
alter table user_action_2 add index `idx_date`(`date`);
alter table user_action_3 add index `idx_date`(`date`);
alter table user_action_4 add index `idx_date`(`date`);
alter table user_action add index `idx_date`(`date`);
```
执行上面同样的sql执行时间为0.34s左右，看来一千多万的数据中查询某个月的数据，速度也是不错的

如果把上面的sql中的count(*) as num改为*
查询时间变为2.2s左右，我觉的也能接受

优势：
1. 操作简单，不管是插入数据，还是查询数据，都简单，根据hash值映射到具体的表中，然后通过mybatis组装sql语句，查询使用merge表`user_action`
2. 解决了单表过大的问题，将表水平拆分到了多张表中
3. 解决了当前生产环境当中数据库表急剧增长的问题，将表的数量控制在合理的范围内，不然的话，按照现在的策略，当过了3个月左右，就要将之前的表备份出来，然后删掉，以保证数据正常工作
4. 将生产环境中的数据库引擎由innodb改为myisam，对于大数据的查询，全文搜索的有帮助

不足：
1. 测试数据有限，只模拟了10101000条数据，不能和真实环境相提并论，况且数据查询的纬度会更多，估计创建的索引会更多
2. 我一开始没有为表设计索引，后来插入模拟数据之后发现查询缓慢才添加的索引，这样会很慢，以后再建表之前根据业务查询需要，预先建立索引
3. 查询1个月的数据，我看来也有点慢，2s左右，如果在进行group by ,以及排序之类的查询需求，可能会更久
4. 表的数量是固定的100张（我本地测试使用了5张），如果每张表都写到1千万左右，如果需要扩容还得做数据迁移，也是比较费劲的事