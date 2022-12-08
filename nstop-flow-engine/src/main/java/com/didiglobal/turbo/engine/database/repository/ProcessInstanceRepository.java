package com.didiglobal.turbo.engine.database.repository;

import com.didiglobal.turbo.engine.entity.FlowInstancePO;

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
