package com.nstop.flow.engine.validator;

import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.exception.DefinitionException;
import com.nstop.flow.engine.exception.ProcessException;
import com.nstop.flow.engine.model.FlowModel;
import com.nstop.flow.engine.util.FlowModelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ModelValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelValidator.class);

    @Resource
    private FlowModelValidator flowModelValidator;

    public void validate(String flowModelStr) throws DefinitionException, ProcessException {
        if (StringUtils.isBlank(flowModelStr)) {
            LOGGER.warn("message={}", ErrorEnum.MODEL_EMPTY.getErrMsg());
            throw new DefinitionException(ErrorEnum.MODEL_EMPTY);
        }

        FlowModel flowModel = FlowModelUtil.parseModelFromString(flowModelStr);
        if (flowModel == null || CollectionUtils.isEmpty(flowModel.getFlowElementList())) {
            LOGGER.warn("message={}||flowModelStr={}", ErrorEnum.MODEL_EMPTY.getErrMsg(), flowModelStr);
            throw new DefinitionException(ErrorEnum.MODEL_EMPTY);
        }
        flowModelValidator.validate(flowModel);
    }
}
