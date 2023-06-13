package com.nstop.datasource.source;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linziwei
 * @date 2023/6/12
 */
@Configuration
@AutoConfigureBefore(DataSourceConfiguration.class)
public class NstopMybatisConfiguration {
    @Bean()
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration nstopMybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }
}
