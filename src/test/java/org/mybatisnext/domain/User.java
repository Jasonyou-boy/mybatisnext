package org.mybatisnext.domain;

import lombok.Data;

@Data
public class User {
    Integer userId;
    String userName;
    String sex;
    Integer age;
    Integer dept;
}
