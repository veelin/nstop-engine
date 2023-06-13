package com.nstop.datasource.rdb;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/7
 */
@Component
public class RdbSqlSessionRepository {
    private static Map<String, SqlSessionTemplate> sqlSessionTemplateMap = new HashMap<>();
    private static Map<String, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();

    public SqlSessionTemplate getTemplateByName(String name){
        return sqlSessionTemplateMap.get(name);
    }

    public SqlSessionFactory getFactoryByName(String name){
        return sqlSessionFactoryMap.get(name);
    }


    public void initSingleSqlSessionTemplate(String name, DataSource dataSource, Configuration configuration) throws Exception {


        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration);
        SqlSessionFactory sqlSessionFactory = bean.getObject();
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        sqlSessionTemplateMap.put(name, sqlSessionTemplate);
        sqlSessionFactoryMap.put(name, sqlSessionFactory);
    }

}
