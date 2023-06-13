package com.nstop.datasource.source;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/6
 */
@Data
@ConfigurationProperties(prefix = "nstop-engine")
public class DataSourceProperties {

    private Map<String, Map<String, DataSourceProperty>> datasource;
//

    @Data
    public static class DataSourceProperty {
        private Class<DataSource> type;
        private String username;
        private String password;
        private String driverClassName;
        private String url;
    }

}
