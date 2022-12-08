package com.nstop.flow.engine.database.repository;

import com.nstop.flow.engine.entity.FlowInstancePO;

public interface ProcessInstanceRepository {

    FlowInstancePO selectByFlowInstanceId(String flowInstanceId);

    /**
     * insert flowInstancePO
     *
     * @param flowInstancePO
     * @return -1 while insert failed
     */
    int insert(FlowInstancePO flowInstancePO);
    void updateStatus(String flowInstanceId, int status);
    void updateStatus(FlowInstancePO flowInstancePO, int status);
}
