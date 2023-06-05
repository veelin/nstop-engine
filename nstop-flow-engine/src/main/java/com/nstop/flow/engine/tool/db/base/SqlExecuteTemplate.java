package com.nstop.flow.engine.tool.db.base;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/5
 */
public interface SqlExecuteTemplate {

    <T> T selectOne(String var1, Object var2);

    <E> List<E> selectList(String var1, Object var2);

    int insert(String var1, Object var2);

    int update(String var1, Object var2);

    int delete(String var1, Object var2);

}
