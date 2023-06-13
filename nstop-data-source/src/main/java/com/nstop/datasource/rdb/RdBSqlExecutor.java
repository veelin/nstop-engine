package com.nstop.datasource.rdb;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.freakchick.orange.SqlMeta;
import com.github.freakchick.orange.engine.DynamicSqlEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/7
 */
@Component
@Slf4j
public class RdBSqlExecutor {

    @Autowired
    private RdbSqlSessionRepository rdbSqlSessionRepository;

    public Map<String, Object> selectOne(String dsName, String sql, Map<String, Object> params) {
        sql = convertSql(sql);
        Map<String, Object> retMap = null;
        SqlSessionTemplate sqlSessionTemplate = rdbSqlSessionRepository.getTemplateByName(dsName);
        SqlSession sqlSession = getSqlSession(sqlSessionTemplate);
        PreparedStatement pst = null;

        DynamicSqlEngine engine = new DynamicSqlEngine();
        SqlMeta sqlMeta = engine.parse(sql, params);
        String sqlTf = sqlMeta.getSql();
        List<Object> paramTf = sqlMeta.getJdbcParamValues();
        try {
            pst = sqlSession.getConnection().prepareStatement(sqlTf);
            if (CollectionUtils.isNotEmpty(paramTf)) {
                for (int i = 0; i < paramTf.size(); i++) {
                    pst.setObject(i + 1, paramTf.get(i));
                }
            }
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            if (resultSet.next()) {
                retMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    retMap.put(md.getColumnName(i), resultSet.getObject(i));
                }
            }

        } catch (SQLException e) {
            log.error("RdBSqlExecutor.selectOne error!", e);
            throw new RuntimeException(e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(sqlSession, sqlSessionTemplate);
        }

        return retMap;
    }

    public List<Map<String, Object>> selectList(String dsName, String sql, Map<String, Object> params) {
        sql = convertSql(sql);
        List<Map<String, Object>> retList = new ArrayList();
        SqlSessionTemplate sqlSessionTemplate = rdbSqlSessionRepository.getTemplateByName(dsName);
        SqlSession sqlSession = getSqlSession(sqlSessionTemplate);
        PreparedStatement pst = null;
        DynamicSqlEngine engine = new DynamicSqlEngine();
        SqlMeta sqlMeta = engine.parse(sql, params);
        String sqlTf = sqlMeta.getSql();
        List<Object> paramTf = sqlMeta.getJdbcParamValues();
        try {
            pst = sqlSession.getConnection().prepareStatement(sqlTf);
            if (CollectionUtils.isNotEmpty(paramTf)) {
                for (int i = 0; i < paramTf.size(); i++) {
                    pst.setObject(i + 1, paramTf.get(i));
                }
            }
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                retList.add(rowData);
            }

        } catch (SQLException e) {
            log.error("RdBSqlExecutor.selectList error!", e);
            throw new RuntimeException(e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(sqlSession, sqlSessionTemplate);
        }

        return retList;
    }

    public long count(String dsName, String sql, Map<String, Object> params) {
        sql = convertSql(sql);
        String from = sql.substring(sql.indexOf("from"), sql.length());
        sql = "select count(1) " + from;
        int limitIndex = sql.indexOf("limit");
        if (limitIndex > -1) {
            sql = sql.substring(limitIndex, sql.length());
        }
        List<Map<String, Object>> retList = new ArrayList();
        SqlSessionTemplate sqlSessionTemplate = rdbSqlSessionRepository.getTemplateByName(dsName);
        SqlSession sqlSession = getSqlSession(sqlSessionTemplate);
        PreparedStatement pst = null;
        DynamicSqlEngine engine = new DynamicSqlEngine();
        SqlMeta sqlMeta = engine.parse(sql, params);
        String sqlTf = sqlMeta.getSql();
        List<Object> paramTf = sqlMeta.getJdbcParamValues();
        try {
            pst = sqlSession.getConnection().prepareStatement(sqlTf);
            if (CollectionUtils.isNotEmpty(paramTf)) {
                for (int i = 0; i < paramTf.size(); i++) {
                    pst.setObject(i + 1, paramTf.get(i));
                }
            }
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            log.error("RdBSqlExecutor.selectList error!", e);
            throw new RuntimeException(e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(sqlSession, sqlSessionTemplate);
        }
    }

    public int insertOrUpdate(String dsName, String sql, Map<String, Object> params) {
        sql = convertSql(sql);
        SqlSessionTemplate sqlSessionTemplate = rdbSqlSessionRepository.getTemplateByName(dsName);
        SqlSession sqlSession = getSqlSession(sqlSessionTemplate);
        PreparedStatement pst = null;
        DynamicSqlEngine engine = new DynamicSqlEngine();
        SqlMeta sqlMeta = engine.parse(sql, params);
        String sqlTf = sqlMeta.getSql();
        List<Object> paramTf = sqlMeta.getJdbcParamValues();
        try {
            pst = sqlSession.getConnection().prepareStatement(sqlTf);
            if (CollectionUtils.isNotEmpty(paramTf)) {
                for (int i = 0; i < paramTf.size(); i++) {
                    pst.setObject(i + 1, paramTf.get(i));
                }
            }
            int count = pst.executeUpdate();
            return count;

        } catch (SQLException e) {
            log.error("RdBSqlExecutor.insertOrUpdate error!", e);
            throw new RuntimeException(e);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(sqlSession, sqlSessionTemplate);
        }
    }

    public SqlSession getSqlSession(SqlSessionTemplate sqlSessionTemplate) {
        return SqlSessionUtils.getSqlSession(sqlSessionTemplate.getSqlSessionFactory(),
                sqlSessionTemplate.getExecutorType(), sqlSessionTemplate.getPersistenceExceptionTranslator());
    }

    public void closeSqlSession(SqlSession session, SqlSessionTemplate sqlSessionTemplate) {
        SqlSessionUtils.closeSqlSession(session, sqlSessionTemplate.getSqlSessionFactory());
    }

    private String convertSql(String script) {
        script = script.replaceAll("\n", " ");
        return script;
    }
}

