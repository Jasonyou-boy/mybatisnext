# MybatisNext

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

MybatisNext是对Mybatis的SQL语句映射的增强。通过MybatisNext，我们能够使用Java语言更精确、灵活地控制Mapper映射。

## 主要功能

* **参数注入**：通过运行时代码向Mapper中动态地添加参数，而不是硬编码到Mapper接口方法上。
* **自定义处理器**：使用自定义处理器，能够对片段SQL进行处理，可以在租户模式、数据权限、数据脱敏等场景使用。

## 如何使用

### Maven

```xml

<dependency>
    <groupId>com.mybatisnext</groupId>
    <artifactId>xxxx</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 使用示例




