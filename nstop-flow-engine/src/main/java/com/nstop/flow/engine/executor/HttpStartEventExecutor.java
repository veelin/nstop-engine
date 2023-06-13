package com.nstop.flow.engine.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HttpStartEventExecutor extends ElementExecutor {

    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        Map<String, Object> paramMap = JSON.parseObject((String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.PARAMS), Map.class);
        String requiredStr = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._requiredList);

        if (MapUtils.isEmpty(paramMap)) {
            return;
        }
        if (StringUtils.isBlank(requiredStr)) {
            return;
        }
        List<String> requiredList = null;
        if (requiredStr.contains("[")) {
            requiredList = JSONArray.parseArray(requiredStr, String.class);
        } else {
            requiredList = new ArrayList<>();
            List<String> split = Arrays.stream(requiredStr.split(",")).map(String::trim).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(requiredList)) {
                requiredList.addAll(split);
            }
        }

        if (CollectionUtils.isNotEmpty(requiredList)) {
            for (String key : requiredList) {
                Object val = paramMap.get(key);
                if (val == null) {
                    throw new ProcessException(ErrorEnum.PARAM_INVALID, key + " is required");
                }
                if (val instanceof String) {
                    String valStr = (String) val;
                    if (StringUtils.isBlank(valStr)) {
                        throw new ProcessException(ErrorEnum.PARAM_INVALID, key + " is empty");
                    }
                }
                if (val instanceof List) {
                    List valList = (List) val;
                    if (CollectionUtils.isEmpty(valList)) {
                        throw new ProcessException(ErrorEnum.PARAM_INVALID, key + " is empty");
                    }
                }
            }

        }


    }

    @Override
    protected void postExecute(RuntimeContext runtimeContext) throws ProcessException {
        NodeInstanceBO currentNodeInstance = runtimeContext.getCurrentNodeInstance();
        currentNodeInstance.setInstanceDataId(runtimeContext.getInstanceDataId());
        currentNodeInstance.setStatus(NodeInstanceStatus.COMPLETED);
        runtimeContext.getNodeInstanceList().add(currentNodeInstance);
    }

    @Override
    protected void preRollback(RuntimeContext runtimeContext) throws ProcessException {
        runtimeContext.setCurrentNodeInstance(runtimeContext.getSuspendNodeInstance());
        runtimeContext.setNodeInstanceList(Collections.emptyList());

        LOGGER.warn("postRollback: reset runtimeContext.||flowInstanceId={}||nodeKey={}||nodeType={}",
                runtimeContext.getFlowInstanceId(), runtimeContext.getCurrentNodeModel().getKey(), runtimeContext.getCurrentNodeModel().getType());
        throw new ProcessException(ErrorEnum.NO_USER_TASK_TO_ROLLBACK, "It's a startEvent.");
    }

    @Override
    protected void postRollback(RuntimeContext runtimeContext) throws ProcessException {
        //do nothing
    }
}
