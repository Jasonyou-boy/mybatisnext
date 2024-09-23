package org.mybatisnext.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatisnext.domain.User;

@Mapper
public interface UserMapper {
    Integer insert(User user);

    User selectUser();

}
