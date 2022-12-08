package com.didiglobal.turbo.engine.database.repository;

import com.didiglobal.turbo.engine.entity.NodeInstanceLogPO;

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
