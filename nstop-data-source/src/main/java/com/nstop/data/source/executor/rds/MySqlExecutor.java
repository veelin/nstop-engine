package com.nstop.data.source.executor.rds;

import com.nstop.common.exception.NStopException;
import com.nstop.data.source.constant.DataSourceConstant;
import com.nstop.data.source.executor.RdbExecutor;
import com.nstop.data.source.model.Source;
import com.nstop.data.source.service.DataSourceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: origindoris
 * @Title: MySqlExecutor
 * @Description:
 * @date: 2022/10/18 14:12
 */
@Component(DataSourceConstant.MYSQL)
public class MySqlExecutor implements RdbExecutor {

    private final DataSourceService dataSourceService;

    public MySqlExecutor(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @Override
    public List<Map> query(Source source, String sql) throws NStopException {
        return dataSourceService.execNativeQuery(sql, source.getSourceCode());
    }

}
