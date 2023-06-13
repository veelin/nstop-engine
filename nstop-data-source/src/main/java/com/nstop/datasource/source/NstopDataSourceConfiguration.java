package com.nstop.datasource.source;

import com.nstop.datasource.rdb.RdbDataSourceRepository;
import com.nstop.datasource.rdb.RdbSqlSessionRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/12
 */

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class NstopDataSourceConfiguration {


}
