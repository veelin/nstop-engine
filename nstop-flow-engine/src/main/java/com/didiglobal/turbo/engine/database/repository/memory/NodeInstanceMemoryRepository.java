package com.didiglobal.turbo.engine.database.repository.memory;

import com.didiglobal.turbo.engine.database.repository.NodeInstanceRepository;
import com.didiglobal.turbo.engine.entity.NodeInstancePO;

import java.util.List;

public class NodeInstanceMemoryRepository implements NodeInstanceRepository {


    @Override
    public int insert(NodeInstancePO nodeInstancePO) {
        return 0;
    }

    @Override
    public boolean insertOrUpdateList(List<NodeInstancePO> nodeInstanceList) {
        return false;
    }

    @Override
    public NodeInstancePO selectByNodeInstanceId(String flowInstanceId, String nodeInstanceId) {
        return null;
    }

    @Override
    public NodeInstancePO selectBySourceInstanceId(String flowInstanceId, String sourceNodeInstanceId, String nodeKey) {
        return null;
    }

    @Override
    public NodeInstancePO selectRecentOne(String flowInstanceId) {
        return null;
    }

    @Override
    public NodeInstancePO selectRecentActiveOne(String flowInstanceId) {
        return null;
    }

    @Override
    public NodeInstancePO selectRecentCompletedOne(String flowInstanceId) {
        return null;
    }

    @Override
    public NodeInstancePO selectEnabledOne(String flowInstanceId) {
        return null;
    }

    @Override
    public List<NodeInstancePO> selectByFlowInstanceId(String flowInstanceId) {
        return null;
    }

    @Override
    public List<NodeInstancePO> selectDescByFlowInstanceId(String flowInstanceId) {
        return null;
    }

    @Override
    public void updateStatus(NodeInstancePO nodeInstancePO, int status) {

    }
}
