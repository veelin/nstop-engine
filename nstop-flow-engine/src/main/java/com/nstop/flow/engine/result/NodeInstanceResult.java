package com.nstop.flow.engine.result;

import com.nstop.flow.engine.bo.NodeInstance;
import com.google.common.base.MoreObjects;

public class NodeInstanceResult extends CommonResult {
    private NodeInstance nodeInstance;

    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    public void setNodeInstance(NodeInstance nodeInstance) {
        this.nodeInstance = nodeInstance;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errCode", getCode())
                .add("errMsg", getMessage())
                .add("nodeInstance", nodeInstance)
                .toString();
    }
}
