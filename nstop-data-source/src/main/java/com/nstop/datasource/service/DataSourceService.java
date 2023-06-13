package com.nstop.datasource.service;

import com.nstop.datasource.rdb.RdbDataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linziwei
 * @date 2023/6/8
 */
@Service
public class DataSourceService {

    @Autowired
    private RdbDataSourceRepository rdbDataSourceRepository;
    public List<String> getRdbDataSourceName(){
        Map<String, DataSource> allDatasource = rdbDataSourceRepository.getAllDatasource();
        List<String> collect = allDatasource.keySet().stream().collect(Collectors.toList());
        collect.sort(Comparator.comparing(String::hashCode));
        return collect;
    }

}
