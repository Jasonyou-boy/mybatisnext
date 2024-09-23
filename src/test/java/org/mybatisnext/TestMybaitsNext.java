package org.mybatisnext;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mybatisnext.config.MybatisNextConfig;
import org.mybatisnext.domain.User;
import org.mybatisnext.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;

@JdbcTest
@ContextConfiguration(classes = MybatisNextConfig.class)
public class TestMybaitsNext {

    @Autowired
    DataSource db;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @SneakyThrows
    @Test
    @Sql(scripts = "handler/initUser.sql")
    void testInsert() {
        Connection connection = db.getConnection();
        SqlSession sqlSession = sqlSessionFactory.openSession(connection);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.select(1);

    }
}
