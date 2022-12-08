package com.nstop.flow.engine.database.repository.db;

import com.nstop.flow.engine.database.repository.db.dao.NodeInstanceDAO;
import com.nstop.flow.engine.database.repository.NodeInstanceRepository;
import com.nstop.flow.engine.entity.NodeInstancePO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NodeInstanceDbRepository implements NodeInstanceRepository {

    @Resource
    private NodeInstanceDAO nodeInstanceDAO;

    @Override
    public int insert(NodeInstancePO nodeInstancePO) {
        return nodeInstanceDAO.insert(nodeInstancePO);
    }

    @Override
    public boolean insertOrUpdateList(List<NodeInstancePO> nodeInstanceList) {
        return nodeInstanceDAO.insertOrUpdateList(nodeInstanceList);
    }

    @Override
    public NodeInstancePO selectByNodeInstanceId(String flowInstanceId, String nodeInstanceId) {
        return nodeInstanceDAO.selectByNodeInstanceId(flowInstanceId, nodeInstanceId);
    }

    @Override
    public NodeInstancePO selectBySourceInstanceId(String flowInstanceId, String sourceNodeInstanceId, String nodeKey) {
        return nodeInstanceDAO.selectBySourceInstanceId(flowInstanceId, sourceNodeInstanceId, nodeKey);
    }

    @Override
    public NodeInstancePO selectRecentOne(String flowInstanceId) {
        return nodeInstanceDAO.selectRecentOne(flowInstanceId);
    }

    @Override
    public NodeInstancePO selectRecentActiveOne(String flowInstanceId) {
        return nodeInstanceDAO.selectRecentActiveOne(flowInstanceId);
    }

    @Override
    public NodeInstancePO selectRecentCompletedOne(String flowInstanceId) {
        return nodeInstanceDAO.selectRecentCompletedOne(flowInstanceId);
    }

    @Override
    public NodeInstancePO selectEnabledOne(String flowInstanceId) {
        return nodeInstanceDAO.selectEnabledOne(flowInstanceId);
    }

    @Override
    public List<NodeInstancePO> selectByFlowInstanceId(String flowInstanceId) {
        return nodeInstanceDAO.selectByFlowInstanceId(flowInstanceId);
    }

    @Override
    public List<NodeInstancePO> selectDescByFlowInstanceId(String flowInstanceId) {
        return nodeInstanceDAO.selectDescByFlowInstanceId(flowInstanceId);
    }

    @Override
    public void updateStatus(NodeInstancePO nodeInstancePO, int status) {
        nodeInstanceDAO.updateStatus(nodeInstancePO, status);
    }
}
