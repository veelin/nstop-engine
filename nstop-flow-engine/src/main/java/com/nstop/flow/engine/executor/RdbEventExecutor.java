package com.nstop.flow.engine.executor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nstop.datasource.rdb.RdBSqlExecutor;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.*;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.InstanceDataUtil;
import com.nstop.flow.engine.util.StringFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RdbEventExecutor extends ElementExecutor {

    @Autowired
    private RdBSqlExecutor rdBSqlExecutor;

    /**
     * Update data map: http request to update data map
     * Url: dynamic config
     * Param: one of flowElement's properties
     */
    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String dsName = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.DS_NAME);
        String sqlType = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.SQL_TYPE);
        String script = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.SQL_SCRIPT);
        Object pageIndex =  flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.SQL_PAGE_INDEX);
        Object pageSize =  flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.SQL_PAGE_SIZE);

        String contextName = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._contextName);
        RdbSqlTypeEnum rdbSqlTypeEnum = RdbSqlTypeEnum.valueOf(sqlType);
        Object result = null;
        switch (rdbSqlTypeEnum) {
            case selectOne:
                result = rdBSqlExecutor.selectOne(dsName, script, runtimeContext.getInstanceDataMap());
                break;
            case selectList:
                result = rdBSqlExecutor.selectList(dsName, script, runtimeContext.getInstanceDataMap());
                break;
            case insert:
            case update:
                result = rdBSqlExecutor.insertOrUpdate(dsName, script, runtimeContext.getInstanceDataMap());
                break;
            case page:
                result = selectPage(dsName, script, runtimeContext.getInstanceDataMap(),pageIndex, pageSize);
                break;

        }

        InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), contextName, result);
    }


    public Page selectPage(String dsName, String sql, Map<String, Object> params, Object page, Object pageSize) throws ProcessException {
        if (page == null || pageSize == null) {
            throw new ProcessException(ErrorEnum.PARAM_INVALID, "page param error!");
        }
        Integer pageI = Integer.valueOf( StringFormatUtil.format((String)page, params));
        Integer pageSizeI = Integer.valueOf( StringFormatUtil.format((String)pageSize, params));
        String sqlPage = sql + " limit " + (pageI - 1) * pageSizeI + "," + pageSizeI;
        List<Map<String, Object>> maps = rdBSqlExecutor.selectList(dsName, sqlPage, params);
        long cnt = rdBSqlExecutor.count(dsName, sql, params);
        Page ret = new Page(pageI, pageSizeI, cnt);
        ret.setRecords(maps);
        return ret;
    }

    @Override
    protected void postExecute(RuntimeContext runtimeContext) throws ProcessException {
        NodeInstanceBO currentNodeInstance = runtimeContext.getCurrentNodeInstance();
        currentNodeInstance.setInstanceDataId(runtimeContext.getInstanceDataId());
        currentNodeInstance.setStatus(NodeInstanceStatus.COMPLETED);
        runtimeContext.getNodeInstanceList().add(currentNodeInstance);
    }

    /**
     * Calculate unique outgoing
     * Expression: one of flowElement's properties
     * Input: data map
     *
     * @return
     * @throws Exception
     */
    @Override
    protected RuntimeExecutor getExecuteExecutor(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement nextNode = calculateNextNode(runtimeContext.getCurrentNodeModel(),
                runtimeContext.getFlowElementMap(), runtimeContext.getInstanceDataMap());

        runtimeContext.setCurrentNodeModel(nextNode);
        return executorFactory.getElementExecutor(nextNode);
    }
}
