package com.nstop.flow.engine.database.repository.memory;

import com.nstop.flow.engine.database.repository.NodeInstanceLogRepository;
import com.nstop.flow.engine.entity.NodeInstanceLogPO;

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
