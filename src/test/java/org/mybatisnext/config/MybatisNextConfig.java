package org.mybatisnext.config;

import com.google.common.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatisnext.HandlerContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

@Configuration
public class MybatisNextConfig {

    void register() {
        //示例:根据当前用户Id生成条件
        int nowUserId = 1;
        HandlerContext.registerHandler("user", sql -> {
            return "user_id=" + nowUserId;
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

}
