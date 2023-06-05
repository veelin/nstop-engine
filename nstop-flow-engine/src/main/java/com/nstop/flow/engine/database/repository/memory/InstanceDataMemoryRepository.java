package com.nstop.flow.engine.database.repository.memory;

import com.nstop.flow.engine.database.repository.InstanceDataRepository;
import com.nstop.flow.engine.entity.InstanceDataPO;
import org.springframework.stereotype.Service;

@Service
public class InstanceDataMemoryRepository implements InstanceDataRepository {

    @Override
    public InstanceDataPO select(String flowInstanceId, String instanceDataId) {
        return null;
    }

    @Override
    public InstanceDataPO selectRecentOne(String flowInstanceId) {
        return null;
    }

    @Override
    public int insert(InstanceDataPO instanceDataPO) {
        return 1;
    }
}
