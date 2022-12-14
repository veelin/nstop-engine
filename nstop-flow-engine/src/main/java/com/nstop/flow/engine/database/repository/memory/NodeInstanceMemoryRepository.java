package com.nstop.flow.engine.database.repository.memory;

import com.nstop.flow.engine.database.repository.NodeInstanceRepository;
import com.nstop.flow.engine.entity.NodeInstancePO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
