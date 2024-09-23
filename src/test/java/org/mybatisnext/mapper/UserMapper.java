package org.mybatisnext.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatisnext.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer insert(User user);

    List<User> select(@Param("userId") Integer userId);

}
