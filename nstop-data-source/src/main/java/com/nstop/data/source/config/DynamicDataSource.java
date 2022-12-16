package com.nstop.data.source.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.nstop.data.source.context.DataSourceContext;
import com.nstop.data.source.factory.DynamicDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: origindoris
 * @Title: DynamicDataSource
 * @Description:
 * @date: 2022/10/18 14:20
 */
@Slf4j
@Component("dynamicDataSource")
@DependsOn("dynamicDataSourceFactory")
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Value("${spring.datasource.dynamic.datasource.engine.url}")
    private String url;

    @Value("${spring.datasource.dynamic.datasource.engine.username}")
    private String username;

    @Value("${spring.datasource.dynamic.datasource.engine.password}")
    private String password;

    @Value("${spring.datasource.dynamic.datasource.engine.driver-class-name}")
    private String driverClassName;

    @PostConstruct
    public void init() {
        DataSource dataSource = initDefaultDataSource();
        super.setDefaultTargetDataSource(dataSource);
        DynamicDataSourceFactory.setDefaultDataSourceMap(dataSource);
        Map<Object, Object> sourceMap = DynamicDataSourceFactory.getDataSourceMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        super.setTargetDataSources(sourceMap);
        super.afterPropertiesSet();

    }


    private DataSource initDefaultDataSource() {
        try (DruidDataSource druidDataSource = new DruidDataSource()) {
            druidDataSource.setUrl(url);
            druidDataSource.setPassword(password);
            druidDataSource.setUsername(username);
            druidDataSource.setDriverClassName(driverClassName);
            return druidDataSource;
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.getDataSourceKey();
    }

    /**
     * 切换数据源
     *
     * @return
     */
    @Override
    protected DataSource determineTargetDataSource() {
        String lookupKey = (String) determineCurrentLookupKey();
        log.info("使用数据源：{}", lookupKey);
        return DynamicDataSourceFactory.getDataSource(lookupKey);
    }
}
