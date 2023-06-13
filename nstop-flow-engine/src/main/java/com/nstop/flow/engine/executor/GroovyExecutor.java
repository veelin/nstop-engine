package com.nstop.flow.engine.executor;

import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.config.HookProperties;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.GroovyUtil;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GroovyExecutor extends ElementExecutor {

    /**
     * Update data map: http request to update data map
     * Url: dynamic config
     * Param: one of flowElement's properties
     */
    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String script = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.SCRIPT);
        Boolean joinContext = (Boolean) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._joinContext);
        String contextName = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._contextName);

        try {
            Object execute = GroovyUtil.execute(script, runtimeContext.getInstanceDataMap());
            if (joinContext!= null && joinContext) {
                InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), contextName, execute);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
