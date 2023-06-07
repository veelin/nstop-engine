package com.nstop.datasource.source;

import com.nstop.datasource.constant.DataSourceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author linziwei
 * @date 2023/6/6
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfiguration {

    private final DataSourceProperties dataSourceProperties;


    public DataSourceConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


//    @Bean
//    public List<DataSourceProperties.DataSourceProperty> mysql(){
//        return dataSourceProperties.getDataSourcePropertyList().get(DataSourceTypeEnum.mysql.name());
//    }
}
