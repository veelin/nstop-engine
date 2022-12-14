package com.nstop.flow.engine.database.repository;

import com.nstop.flow.engine.entity.InstanceDataPO;

public interface InstanceDataRepository {
    InstanceDataPO select(String flowInstanceId, String instanceDataId);

    /**
     * select recent InstanceData order by id desc
     *
     * @param flowInstanceId
     * @return
     */
    InstanceDataPO selectRecentOne(String flowInstanceId);

    /**
     * insert instanceDataPO
     *
     * @param instanceDataPO
     * @return -1 while insert failed
     */
    int insert(InstanceDataPO instanceDataPO);
}
