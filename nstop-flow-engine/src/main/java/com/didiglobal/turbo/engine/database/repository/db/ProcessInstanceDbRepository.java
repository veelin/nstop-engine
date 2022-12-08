package com.didiglobal.turbo.engine.database.repository.db;

import com.didiglobal.turbo.engine.database.repository.db.dao.ProcessInstanceDAO;
import com.didiglobal.turbo.engine.database.repository.ProcessInstanceRepository;
import com.didiglobal.turbo.engine.entity.FlowInstancePO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessInstanceDbRepository implements ProcessInstanceRepository {

    @Resource
    private ProcessInstanceDAO processInstanceDAO;

    @Override
    public FlowInstancePO selectByFlowInstanceId(String flowInstanceId) {
        return processInstanceDAO.selectByFlowInstanceId(flowInstanceId);
    }

    @Override
    public int insert(FlowInstancePO flowInstancePO) {
        return processInstanceDAO.insert(flowInstancePO);
    }

    @Override
    public void updateStatus(String flowInstanceId, int status) {
        processInstanceDAO.updateStatus(flowInstanceId, status);
    }

    @Override
    public void updateStatus(FlowInstancePO flowInstancePO, int status) {
        processInstanceDAO.updateStatus(flowInstancePO, status);
    }
}
