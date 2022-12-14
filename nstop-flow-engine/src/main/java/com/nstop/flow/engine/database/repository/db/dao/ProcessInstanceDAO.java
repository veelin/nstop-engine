package com.nstop.flow.engine.database.repository.db.dao;

import com.nstop.flow.engine.database.repository.db.dao.mapper.ProcessInstanceMapper;
import com.nstop.flow.engine.entity.FlowInstancePO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProcessInstanceDAO extends BaseDAO<ProcessInstanceMapper, FlowInstancePO> {

    public FlowInstancePO selectByFlowInstanceId(String flowInstanceId) {
        return baseMapper.selectByFlowInstanceId(flowInstanceId);
    }

    /**
     * insert flowInstancePO
     *
     * @param flowInstancePO
     * @return -1 while insert failed
     */
    public int insert(FlowInstancePO flowInstancePO) {
        try {
            return baseMapper.insert(flowInstancePO);
        } catch (Exception e) {
            // TODO: 2020/2/1 clear reentrant exception log
            LOGGER.error("insert exception.||flowInstancePO={}", flowInstancePO, e);
        }
        return -1;
    }

    public void updateStatus(String flowInstanceId, int status) {
        FlowInstancePO flowInstancePO = baseMapper.selectByFlowInstanceId(flowInstanceId);
        updateStatus(flowInstancePO, status);
    }

    public void updateStatus(FlowInstancePO flowInstancePO, int status) {
        flowInstancePO.setStatus(status);
        flowInstancePO.setModifyTime(new Date());
        baseMapper.updateStatus(flowInstancePO);
    }
}
