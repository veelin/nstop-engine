package com.nstop.biz;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.nstop.biz", "com.nstop.flow.engine", "com.nstop.datasource"})
@MapperScan(basePackages = {"com.nstop"}, sqlSessionTemplateRef= "nstopSqlSessionTemplate")
public class NstopApplication {

    public static void main(String[] args) {
        SpringApplication.run(NstopApplication.class, args);
    }

}
