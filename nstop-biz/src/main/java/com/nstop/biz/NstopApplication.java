package com.nstop.biz;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = {"com.nstop.biz", "com.nstop.flow.engine","com.nstop.data.source"})
@MapperScan(basePackages = {"com.nstop"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.nstop.data.source")
@EntityScan("com.nstop.data.source")
@EnableJpaAuditing
public class NstopApplication {

    public static void main(String[] args) {
        SpringApplication.run(NstopApplication.class, args);
    }

}
