package com.nstop.flow.engine.executor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.FlowModelUtil;
import com.nstop.flow.engine.util.GroovyUtil;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HttpEndEventExecutor extends ElementExecutor {

    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String _respKeys = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._httpRespKeys);
        if (StringUtils.isBlank(_respKeys)) {
            return;
        }
        List<String> keyList = null;
        if (_respKeys.contains("[")) {
            keyList = JSONArray.parseArray(_respKeys, String.class);
        } else {
            keyList = new ArrayList<>();
            List<String> split = Arrays.stream(_respKeys.split(",")).map(String::trim).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(split)) {
                keyList.addAll(split);
            }
        }

        if (CollectionUtils.isNotEmpty(keyList)) {
            InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.HTTP_RESP_KEYS, keyList);
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
    protected void doRollback(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();
        String nodeName = FlowModelUtil.getElementName(flowElement);
        LOGGER.warn("doRollback: unsupported element type as EndEvent.||flowInstanceId={}||nodeKey={}||nodeName={}||nodeType={}",
                runtimeContext.getFlowInstanceId(), flowElement.getKey(), nodeName, flowElement.getType());
        throw new ProcessException(ErrorEnum.UNSUPPORTED_ELEMENT_TYPE,
                MessageFormat.format(Constants.NODE_INFO_FORMAT, flowElement.getKey(), nodeName, flowElement.getType()));
    }

    @Override
    protected void postRollback(RuntimeContext runtimeContext) throws ProcessException {
        //do nothing
    }

    @Override
    protected RuntimeExecutor getExecuteExecutor(RuntimeContext runtimeContext) throws ProcessException {
        LOGGER.info("getExecuteExecutor: no executor after EndEvent.");
        return null;
    }
}
