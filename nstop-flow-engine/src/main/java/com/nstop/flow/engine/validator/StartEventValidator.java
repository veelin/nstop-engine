package com.nstop.flow.engine.validator;

import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.model.FlowElement;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StartEventValidator extends ElementValidator {

    protected static final Logger LOGGER = LoggerFactory.getLogger(StartEventValidator.class);
    /**
     * CheckIncoming: check userTask's incoming, warn while incoming is not empty.
     *
     * @param flowElementMap, flowElement
     */
    @Override
    public void checkIncoming(Map<String, FlowElement> flowElementMap, FlowElement flowElement) {
        List<String> incoming = flowElement.getIncoming();

        if (CollectionUtils.isNotEmpty(incoming)) {
            recordElementValidatorException(flowElement, ErrorEnum.ELEMENT_TOO_MUCH_INCOMING);
        }
    }
}
