package com.nstop.data.source.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson2.JSONObject;
import com.nstop.common.exception.DataSourceException;
import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.model.Source;
import com.nstop.data.source.util.DruidUtil;
import com.nstop.data.source.util.SourcePropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: origindoris
 * @Title: DynamicDataSourceFactory
 * @Description: 数据源工厂
 * @date: 2022/10/18 14:28
 */
@Slf4j
@Component("dynamicDataSourceFactory")
public class DynamicDataSourceFactory {


    private static Map<String, DataSource> dataSourceMap =  new ConcurrentHashMap<>(1);


    public static List<DataSource> list(){
       return new ArrayList<>(dataSourceMap.values());
    }

    public static void removeDataSource(String key){
        dataSourceMap.remove(key);
    }

    public static DataSource getDataSource(String key) {
        if (StringUtils.isBlank(key)) {
            return dataSourceMap.get(DataSourceConstant.DEFAULT_DATA_SOURCE_KEY);
        }
        DataSource dataSource = dataSourceMap.get(key);
        if (dataSource == null) {
            return dataSourceMap.get(DataSourceConstant.DEFAULT_DATA_SOURCE_KEY);
        }
        return dataSource;
    }



    public static Map<String, DataSource> getDataSourceMap(){
        return dataSourceMap;
    }

    public static void setDefaultDataSourceMap(DataSource dataSource){
        dataSourceMap.put(DataSourceConstant.DEFAULT_DATA_SOURCE_KEY, dataSource);
    }


    public static DataSource getDefaultDataSource() {
        return dataSourceMap.get(DataSourceConstant.DEFAULT_DATA_SOURCE_KEY);
    }

    public static void addDataSource(Source source) throws DataSourceException {
        JSONObject sourceProperty = source.getSourceProperty();
        DruidDataSource druidDataSource = DruidUtil.getDruidDataSource(SourcePropertyUtil.convert(sourceProperty));
        if (druidDataSource == null) {
            return;
        }
        String sourceCode = source.getSourceCode();
        dataSourceMap.put(sourceCode, druidDataSource);
    }



}
