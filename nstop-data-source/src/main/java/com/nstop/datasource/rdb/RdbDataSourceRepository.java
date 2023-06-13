package com.nstop.datasource.rdb;

import com.alibaba.druid.util.StringUtils;
import com.nstop.datasource.source.DataSourceConfiguration;
import com.nstop.datasource.source.DataSourceProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/6
 */
@Component
public class RdbDataSourceRepository {

    private static Map<String, DataSource> DATA_SOURCE_MAP = new HashMap<>();


    public void saveDataSource(String name, DataSource ds){
        if (StringUtils.isEmpty(name) || ds == null) {
            return;
        }
        DATA_SOURCE_MAP.put(name, ds);
    }

    public DataSource selectDataSource(String name){
        return DATA_SOURCE_MAP.get(name);
    }

    public Map<String, DataSource> getAllDatasource(){
        return DATA_SOURCE_MAP;
    }

}
