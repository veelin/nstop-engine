package com.didiglobal.turbo.engine.database.repository.memory;

import com.didiglobal.turbo.engine.database.repository.NodeInstanceLogRepository;
import com.didiglobal.turbo.engine.entity.NodeInstanceLogPO;

import java.util.List;

public class NodeInstanceLogMemoryRepository implements NodeInstanceLogRepository {

    @Override
    public int insert(NodeInstanceLogPO nodeInstanceLogPO) {
        return 0;
    }

    @Override
    public boolean insertList(List<NodeInstanceLogPO> nodeInstanceLogList) {
        return false;
    }
}
