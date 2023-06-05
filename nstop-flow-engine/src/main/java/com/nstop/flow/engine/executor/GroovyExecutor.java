package com.nstop.flow.engine.executor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nstop.flow.engine.bo.HookInfoResponse;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.InstanceDataType;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.config.HookProperties;
import com.nstop.flow.engine.database.repository.InstanceDataRepository;
import com.nstop.flow.engine.entity.InstanceDataPO;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.FlowModelUtil;
import com.nstop.flow.engine.util.GroovyUtil;
import com.nstop.flow.engine.util.HttpUtil;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GroovyExecutor extends ElementExecutor {

    private static final String HOOK_URL_NAME = "infoHook";
    private static final String PARAM_FLOW_INSTANCE_ID = "flowInstanceId";
    private static final String PARAM_DATA_LIST = "dataList";

    @Resource
    private HookProperties hookProperties;

    /**
     * Update data map: http request to update data map
     * Url: dynamic config
     * Param: one of flowElement's properties
     */
    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String script = (String) flowElement.getProperties().get("script");
        Boolean joinContext = (Boolean) flowElement.getProperties().get("_joinContext");
        String contextName = (String) flowElement.getProperties().get("_contextName");

        try {
            Object execute = GroovyUtil.execute(script, runtimeContext.getInstanceDataMap());
            if (joinContext) {
                InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), contextName, execute);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        //merge data to current dataMap
//        JSONObject dataMap = runtimeContext.getInstanceDataMap();
//        String script = flowElement.getProperties().get("script");
//        String script = dataMap.getString("script");

//
//        dataMap.putAll(hookInfoValueMap);

        //save data
//        if (MapUtils.isNotEmpty(dataMap)) {
//            String instanceDataId = saveInstanceDataPO(runtimeContext);
//            runtimeContext.setInstanceDataId(instanceDataId);
//        }
    }

    private String saveInstanceDataPO(RuntimeContext runtimeContext) {
//        String instanceDataId = genId();
//        InstanceDataPO instanceDataPO = buildHookInstanceData(instanceDataId, runtimeContext);
//        InstanceDataRepository instanceDataRepository = instanceDataRepositoryAdapter.find(runtimeContext);
//        instanceDataRepository.insert(instanceDataPO);
//        return instanceDataId;
        return null;
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
