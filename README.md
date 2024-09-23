# MybatisNext

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

MybatisNext是对Mybatis的SQL映射功能的增强。通过MybatisNext，我们能够使用Java语言更精确、灵活地控制Mapper映射。

> [!TIP]
> 我们拥有一把精确的手术刀，能够对指定的SQL片段进行编辑或者修改。对SQL的控制权又再次返回到JAVA程序中而不仅仅是XML。 我们不妨做出天马行空的想象，或许未来可以结合ABAC权限模型，对数据权限进行控制，而不是将数据权限与业务紧耦合。

## 主要功能

* **参数注入**：通过运行时代码向Mapper中动态地添加参数，而不是硬编码到Mapper接口方法上。
* **自定义处理器**：使用自定义处理器，能够对SQL片段进行处理，可以在租户模式、数据权限、数据脱敏等场景使用。



## 如何使用

> [!CAUTION]
> 正在努力开发中，请勿在生产环境中使用。期待你的加入
### Maven

```xml

<dependency>
    <groupId>com.mybatisnext</groupId>
    <artifactId>xxxx</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 使用示例




