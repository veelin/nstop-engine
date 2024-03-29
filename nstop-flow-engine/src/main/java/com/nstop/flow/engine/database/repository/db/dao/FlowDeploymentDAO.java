package com.nstop.flow.engine.database.repository.db.dao;

import com.nstop.flow.engine.database.repository.db.dao.mapper.FlowDeploymentMapper;
import com.nstop.flow.engine.entity.FlowDeploymentPO;
import org.springframework.stereotype.Service;

@Service
public class FlowDeploymentDAO extends BaseDAO<FlowDeploymentMapper, FlowDeploymentPO> {

    /**
     * Insert: insert flowDeploymentPO, return -1 while insert failed.
     *
     * @param  flowDeploymentPO
     * @return
     */
    public int insert(FlowDeploymentPO flowDeploymentPO) {
        try {
            return baseMapper.insert(flowDeploymentPO);
        } catch (Exception e) {
            LOGGER.error("insert exception.||flowDeploymentPO={}", flowDeploymentPO, e);
        }
        return -1;
    }

    public FlowDeploymentPO selectByDeployId(String flowDeployId) {
        return baseMapper.selectByDeployId(flowDeployId);
    }

    /**
     * SelectRecentByFlowModuleId : query recent flowDeploymentPO by flowModuleId.
     *
     * @param  flowModuleId
     * @return flowDeploymentPO
     */
    public FlowDeploymentPO selectRecentByFlowModuleId(String flowModuleId) {
        return baseMapper.selectByModuleId(flowModuleId);
    }

    public FlowDeploymentPO selectRecentByFlowType(String flowType) {
        return baseMapper.selectByFlowType(flowType);
    }

    public FlowDeploymentPO selectRecentByFlowKey(String flowType, String flowKey) {
        return baseMapper.selectByFlowKey(flowType, flowKey);
    }
}
