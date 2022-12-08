package com.didiglobal.turbo.engine.database.repository.memory;

import com.didiglobal.turbo.engine.database.repository.InstanceDataRepository;
import com.didiglobal.turbo.engine.entity.InstanceDataPO;

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
        return 0;
    }
}
