package com.nstop.flow.engine.validator;

import com.nstop.flow.engine.common.ErrorEnum;
import com.nstop.flow.engine.exception.DefinitionException;
import com.nstop.flow.engine.model.FlowElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SequenceFlowValidator extends ElementValidator {

    @Override
    public void checkIncoming(Map<String, FlowElement> flowElementMap, FlowElement flowElement) throws DefinitionException {
        super.checkIncoming(flowElementMap, flowElement);

        List<String> incomingList = flowElement.getIncoming();
        if (incomingList.size() >1) {
            throwElementValidatorException(flowElement, ErrorEnum.ELEMENT_TOO_MUCH_INCOMING);
        }
    }

    @Override
    public void checkOutgoing(Map<String, FlowElement> flowElementMap, FlowElement flowElement) throws DefinitionException {
        super.checkOutgoing(flowElementMap, flowElement);

        List<String> outgoingList = flowElement.getOutgoing();
        if (outgoingList.size() > 1) {
            throwElementValidatorException(flowElement, ErrorEnum.ELEMENT_TOO_MUCH_OUTGOING);
        }
    }
}
