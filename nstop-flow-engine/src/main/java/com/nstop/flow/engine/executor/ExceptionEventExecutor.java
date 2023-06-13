package com.nstop.flow.engine.executor;

import com.alibaba.fastjson.JSON;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.exception.TurboException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.ApplicationContextHolder;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@Service
public class ExceptionEventExecutor extends ElementExecutor {

    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String _errorCode = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._errorCode);
        String _errorMsg = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._errorMsg);

        int errorCode = StringUtils.isBlank(_errorCode) ? ErrorEnum.SYSTEM_ERROR.getErrNo(): Integer.valueOf(_errorCode);
        InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_CODE, errorCode);
        InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.ERROR_MSG, _errorMsg);
        InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.HAS_ERROR, true);
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
