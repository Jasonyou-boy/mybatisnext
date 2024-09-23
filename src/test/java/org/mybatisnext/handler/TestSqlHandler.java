package org.mybatisnext.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.tools.Script;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatisnext.HandlerContext;
import org.mybatisnext.config.MybatisNextConfig;
import org.mybatisnext.domain.User;
import org.mybatisnext.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


@JdbcTest
@ContextConfiguration(classes = MybatisNextConfig.class)
public class TestSqlHandler {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp(){
        System.setProperty("javax.xml.accessExternalDTD", "all");
    }

    @SneakyThrows
    @Test
    void testId(){
        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        ClassPathResource script = new ClassPathResource("/handler/initUser.sql");
        populator.addScript(script);
        populator.execute(dataSource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.select(1);
        Assertions.assertNotNull(users);
        users.forEach(user->{
               Assertions.assertEquals(1,user.getDept());
        });
    }
}
