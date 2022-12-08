package com.didiglobal.turbo.engine.database.repository.memory;

import com.didiglobal.turbo.engine.database.repository.ProcessInstanceRepository;
import com.didiglobal.turbo.engine.entity.FlowInstancePO;

public class ProcessInstanceMemoryRepository implements ProcessInstanceRepository {

    @Override
    public FlowInstancePO selectByFlowInstanceId(String flowInstanceId) {
        return null;
    }

    @Override
    public int insert(FlowInstancePO flowInstancePO) {
        return 0;
    }

    @Override
    public void updateStatus(String flowInstanceId, int status) {

    }

    @Override
    public void updateStatus(FlowInstancePO flowInstancePO, int status) {

    }
}
