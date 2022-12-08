package com.nstop.flow.engine.database.repository;

import com.nstop.flow.engine.entity.NodeInstanceLogPO;

import java.util.List;

public interface NodeInstanceLogRepository {

    /**
     * insert nodeInstanceLogPO
     *
     * @param nodeInstanceLogPO
     * @return -1 while insert failed
     */
    int insert(NodeInstanceLogPO nodeInstanceLogPO);

    /**
     * nodeInstanceLogList batch insert
     *
     * @param nodeInstanceLogList
     * @return
     */
    boolean insertList(List<NodeInstanceLogPO> nodeInstanceLogList);
}
