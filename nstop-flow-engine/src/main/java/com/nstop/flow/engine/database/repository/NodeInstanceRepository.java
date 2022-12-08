package com.nstop.flow.engine.database.repository;

import com.nstop.flow.engine.entity.NodeInstancePO;

import java.util.List;

public interface NodeInstanceRepository {

    /**
     * insert nodeInstancePO
     *
     * @param nodeInstancePO
     * @return -1 while insert failed
     */
    int insert(NodeInstancePO nodeInstancePO);

    /**
     * when nodeInstancePO's id is null, batch insert.
     * when nodeInstancePO's id is not null, update it status.
     *
     * @param nodeInstanceList
     * @return
     */
    // TODO: 2020/1/14 post handle while failed: retry 5 times
    boolean insertOrUpdateList(List<NodeInstancePO> nodeInstanceList);

    NodeInstancePO selectByNodeInstanceId(String flowInstanceId, String nodeInstanceId);

    NodeInstancePO selectBySourceInstanceId(String flowInstanceId, String sourceNodeInstanceId, String nodeKey);

    /**
     * select recent nodeInstancePO order by id desc
     * @param flowInstanceId
     * @return
     */
    NodeInstancePO selectRecentOne(String flowInstanceId);

    /**
     * select recent active nodeInstancePO order by id desc
     * @param flowInstanceId
     * @return
     */
    NodeInstancePO selectRecentActiveOne(String flowInstanceId);

    /**
     * select recent completed nodeInstancePO order by id desc
     * @param flowInstanceId
     * @return
     */
    NodeInstancePO selectRecentCompletedOne(String flowInstanceId);

    /**
     * select recent active nodeInstancePO order by id desc
     * If it doesn't exist, select recent completed nodeInstancePO order by id desc
     *
     * @param flowInstanceId
     * @return
     */
    NodeInstancePO selectEnabledOne(String flowInstanceId);

    List<NodeInstancePO> selectByFlowInstanceId(String flowInstanceId);

    /**
     * select nodeInstancePOList order by id desc
     *
     * @param flowInstanceId
     * @return
     */
    List<NodeInstancePO> selectDescByFlowInstanceId(String flowInstanceId);

    /**
     * update nodeInstancePO status by nodeInstanceId
     * @param nodeInstancePO
     * @param status
     */
    void updateStatus(NodeInstancePO nodeInstancePO, int status);
}
