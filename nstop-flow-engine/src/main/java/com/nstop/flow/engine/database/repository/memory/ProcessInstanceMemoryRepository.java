package com.nstop.flow.engine.database.repository.memory;

import com.nstop.flow.engine.database.repository.ProcessInstanceRepository;
import com.nstop.flow.engine.entity.FlowInstancePO;
import org.springframework.stereotype.Service;

@Service
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
