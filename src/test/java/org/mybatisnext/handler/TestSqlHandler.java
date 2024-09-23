package org.mybatisnext.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatisnext.config.MybatisNextConfig;
import org.mybatisnext.domain.User;
import org.mybatisnext.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;


@JdbcTest
@ContextConfiguration(classes = MybatisNextConfig.class)
public class TestSqlHandler {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp() {
        System.setProperty("javax.xml.accessExternalDTD", "all");
    }

    @SneakyThrows
    @Test
    void testSqlHandler() {
        DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        ClassPathResource script = new ClassPathResource("/handler/initUser.sql");
        populator.addScript(script);
        populator.execute(dataSource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser();
        Assertions.assertNotNull(user);
        Assertions.assertEquals(1, user.getUserId());
    }
}
