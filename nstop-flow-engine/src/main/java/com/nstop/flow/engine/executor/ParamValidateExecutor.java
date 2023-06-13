package com.nstop.flow.engine.executor;

import com.alibaba.fastjson.JSON;
import com.nstop.flow.engine.bo.NodeInstanceBO;
import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.NodeInstanceStatus;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.ApplicationContextHolder;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author linziwei
 * @date 2023/6/9
 */
@Service
public class ParamValidateExecutor extends ElementExecutor{

    @Override
    protected void doExecute(RuntimeContext runtimeContext) throws ProcessException {
        FlowElement flowElement = runtimeContext.getCurrentNodeModel();

        String beanStr = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.BEAN);
        String methodStr = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.METHOD);
        Map<String, Object> params = JSON.parseObject((String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES.PARAMS));
        String contextName = (String) flowElement.getProperties().get(Constants.ELEMENT_PROPERTIES._contextName);

        Object bean = null;
        if (beanStr.contains("\\.")) {
            try {
                bean = Class.forName(beanStr).newInstance();
            } catch (Exception e) {
                throw new ProcessException(ErrorEnum.PARAM_INVALID);
            }
        } else {
            bean = ApplicationContextHolder.getContext().getBean(beanStr);
        }


        Object result = null;
        if (bean != null) {
            try {
                if (MapUtils.isNotEmpty(params)) {
                    Method method = null;
                    Class c = bean.getClass();
                    Method[] allMethods = c.getDeclaredMethods();
                    method = Arrays.stream(allMethods).filter(m -> (m.getName().equals(methodStr) && m.getParameterTypes().length == params.size())).findFirst().get();
                    if (method == null) {
                        throw new ProcessException(ErrorEnum.PARAM_INVALID, "method or params input error");
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object[] clsParams = new Object[parameterTypes.length];
                    for (int i = 0; i < parameterTypes.length; i++) {
                        Class<?> parameterType = parameterTypes[i];
                        String name = parameterTypes[i].getName();
                        clsParams[i] = JSON.parseObject(String.valueOf(params.get(name)), parameterType);
                    }
                    result = method.invoke(bean, clsParams);
                } else {
                    Method method = bean.getClass().getDeclaredMethod(methodStr);
                    result= method.invoke(bean);
                }

            } catch (ProcessException e){
                throw e;
            } catch (Exception e) {
                throw new ProcessException(ErrorEnum.PARAM_INVALID);
            }
        }

        if (result != null) {
            InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), contextName, result);
        }

//        try {
//            Object execute = GroovyUtil.execute(script, runtimeContext.getInstanceDataMap());
//            if (joinContext) {
//                InstanceDataUtil.putValue(runtimeContext.getInstanceDataMap(), contextName, execute);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


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
