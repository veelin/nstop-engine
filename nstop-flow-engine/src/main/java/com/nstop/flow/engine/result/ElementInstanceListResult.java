package com.nstop.flow.engine.result;

import com.nstop.flow.engine.bo.ElementInstance;
import com.nstop.flow.engine.common.ErrorEnum;
import com.google.common.base.MoreObjects;

import java.util.List;

public class ElementInstanceListResult extends CommonResult {
    private List<ElementInstance> elementInstanceList;

    public ElementInstanceListResult(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public List<ElementInstance> getElementInstanceList() {
        return elementInstanceList;
    }

    public void setElementInstanceList(List<ElementInstance> elementInstanceList) {
        this.elementInstanceList = elementInstanceList;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .add("elementInstanceList", elementInstanceList)
                .toString();
    }
}
