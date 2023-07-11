package com.nstop.datasource.source;

import com.nstop.datasource.rdb.RdbDataSourceRepository;
import com.nstop.datasource.rdb.RdbSqlSessionRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/6
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@AutoConfigureBefore(RdbSqlSessionRepository.class)
@AutoConfigureAfter(NstopMybatisConfiguration.class)
public class DataSourceConfiguration {


    private final DataSourceProperties dataSourceProperties;


    @Autowired
    private RdbDataSourceRepository rdbDataSourceRepository;
    @Autowired
    private RdbSqlSessionRepository rdbSqlSessionRepository;

    public DataSourceConfiguration(DataSourceProperties dataSourceProperties) throws Exception {
        this.dataSourceProperties = dataSourceProperties;
    }
    @PostConstruct
    public void init() throws Exception {
        initDataSource(rdbDataSourceRepository);
        initSqlSessionTemplate(rdbDataSourceRepository, rdbSqlSessionRepository);
    }

    private void initDataSource(RdbDataSourceRepository dataSourceRepository){

        Map<String, Map<String, DataSourceProperties.DataSourceProperty>> datasource = this.dataSourceProperties.getDatasource();
        if (CollectionUtils.isEmpty(datasource)) {
            return;
        }

        for (Map.Entry<String, Map<String, DataSourceProperties.DataSourceProperty>> entry : datasource.entrySet()) {
            String dsType = entry.getKey();
            Map<String, DataSourceProperties.DataSourceProperty> dsList = entry.getValue();
            if (CollectionUtils.isEmpty(dsList)) {
                continue;
            }
            if ("rdb".equals(dsType)) {
                for (Map.Entry<String, DataSourceProperties.DataSourceProperty> dsEntry : dsList.entrySet()){
                    String name = dsEntry.getKey();
                    DataSourceProperties.DataSourceProperty property = dsEntry.getValue();

                    DataSource dataSource = DataSourceBuilder.create()
                            .type(property.getType())
                            .url(property.getUrl())
                            .driverClassName(property.getDriverClassName())
                            .username(property.getUsername())
                            .password(property.getPassword())
                            .build();
                    dataSourceRepository.saveDataSource(name, dataSource);

                }
            }
        }
    }

    private void initSqlSessionTemplate(RdbDataSourceRepository rdbDataSourceRepository, RdbSqlSessionRepository rdbSqlSessionRepository) throws Exception {
            Map<String, DataSource> allDatasource = rdbDataSourceRepository.getAllDatasource();
            if (!CollectionUtils.isEmpty(allDatasource)) {
                for (Map.Entry<String, DataSource> dataSourceEntry : allDatasource.entrySet()) {
                    rdbSqlSessionRepository.initSingleSqlSessionTemplate(dataSourceEntry.getKey(), dataSourceEntry.getValue());
                }
            }
    }

    @Bean(name = "nstopSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate (RdbSqlSessionRepository rdbSqlSessionRepository){
        SqlSessionTemplate nstop = rdbSqlSessionRepository.getTemplateByName("sys");
        if (nstop == null) {
            throw new RuntimeException("not found sys data source configuration");
        }
        return nstop;
    }

    @Bean(name = "nstopSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory (RdbSqlSessionRepository rdbSqlSessionRepository){
        SqlSessionFactory nstop = rdbSqlSessionRepository.getFactoryByName("sys");
        if (nstop == null) {
            throw new RuntimeException("not found sys data source configuration");
        }
        return nstop;
    }

}
