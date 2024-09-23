package org.mybatisnext.config;

import com.google.common.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatisnext.HandlerContext;
import org.mybatisnext.handler.SqlHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Configuration
public class MybatisNextConfig {

    void register(){
        HandlerContext.registerHandler("dept",sql -> {
            return "dept=1";
        });
    }

    @Bean
    SqlSessionFactory sqlSessionFactory() {
        register();
        URL resource = Resources.getResource("mybatisnext-config-test.xml");
        try {
            return new SqlSessionFactoryBuilder().build(resource.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Bean
    DataSource dataSource(SqlSessionFactory sqlSessionFactory ){
        return sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
    }
}
