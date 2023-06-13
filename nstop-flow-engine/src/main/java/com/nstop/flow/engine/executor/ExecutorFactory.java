package com.nstop.flow.engine.executor;

import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.common.FlowElementType;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowElement;
import com.nstop.flow.engine.util.FlowModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;

@Service
public class ExecutorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorFactory.class);

    @Resource
    private StartEventExecutor startEventExecutor;

    @Resource
    private EndEventExecutor endEventExecutor;

    @Resource
    private SequenceFlowExecutor sequenceFlowExecutor;

    @Resource
    private UserTaskExecutor userTaskExecutor;

    @Resource
    private ExclusiveGatewayExecutor exclusiveGatewayExecutor;
    @Resource
    private GroovyExecutor groovyExecutor;

    @Resource
    private HttpStartEventExecutor httpStartEventExecutor;

    @Resource
    private HttpEndEventExecutor httpEndEventExecutor;

    @Resource
    private JavaInvokerExecutor javaInvokerExecutor;
    @Resource
    private RdbEventExecutor rdbEventExecutor;
    @Resource
    private ExceptionEventExecutor exceptionEventExecutor;

    public ElementExecutor getElementExecutor(FlowElement flowElement) throws ProcessException {
        String elementType = flowElement.getType();
        ElementExecutor elementExecutor = getElementExecutor(elementType);

        if (elementExecutor == null) {
            LOGGER.warn("getElementExecutor failed: unsupported elementType.|elementType={}", elementType);
            throw new ProcessException(ErrorEnum.UNSUPPORTED_ELEMENT_TYPE,
                    MessageFormat.format(Constants.NODE_INFO_FORMAT, flowElement.getKey(),
                            FlowModelUtil.getElementName(flowElement), elementType));
        }

        return elementExecutor;
    }

    private ElementExecutor getElementExecutor(String elementType) {
        switch (elementType) {
            case FlowElementType.START_EVENT:
                return startEventExecutor;
            case FlowElementType.END_EVENT:
                return endEventExecutor;
            case FlowElementType.SEQUENCE_FLOW:
                return sequenceFlowExecutor;
            case FlowElementType.USER_TASK:
                return userTaskExecutor;
            case FlowElementType.EXCLUSIVE_GATEWAY:
                return exclusiveGatewayExecutor;
            case FlowElementType.GROOVY:
                return groovyExecutor;
            case FlowElementType.HTTP_START_EVENT:
                return httpStartEventExecutor;
            case FlowElementType.HTTP_END_EVENT:
                return httpEndEventExecutor;
            case FlowElementType.JAVA_INVOKER:
                return javaInvokerExecutor;
            case FlowElementType.RDB:
                return rdbEventExecutor;
            case FlowElementType.EXCEPTION:
                return exceptionEventExecutor;
            default:
                return null;
        }
    }
}
