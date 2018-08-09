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
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── tuyu
    │   │           ├── dao
    │   │           │   └── UserActionDao.java
    │   │           └── po
    │   │               └── UserAction.java
    │   └── resources
    │       ├── config.properties
    │       ├── log4j.properties
    │       ├── mappers
    │       │   └── UserActionMapper.xml
    │       └── mybatis-config.xml
    └── test
        └── java
            └── com
                └── tuyu
                    └── dao
                        └── UserActionDaoTest.java

```