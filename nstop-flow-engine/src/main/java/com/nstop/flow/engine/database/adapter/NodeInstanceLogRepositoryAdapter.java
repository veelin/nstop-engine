package com.nstop.flow.engine.database.adapter;

import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.EngineTypeEnum;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.database.repository.NodeInstanceLogRepository;
import com.nstop.flow.engine.database.repository.db.NodeInstanceLogDbRepository;
import com.nstop.flow.engine.database.repository.memory.NodeInstanceLogMemoryRepository;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NodeInstanceLogRepositoryAdapter {
    @Resource
    private NodeInstanceLogDbRepository nodeInstanceLogDbRepository;
    @Resource
    private NodeInstanceLogMemoryRepository nodeInstanceLogMemoryRepository;

    public NodeInstanceLogRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.ENGINE_TYPE_DATA_KEY);
        return find(engineType);
    }

    public NodeInstanceLogRepository find(String engineType){
        if (EngineTypeEnum.instant.name().equals(engineType)) {
            return nodeInstanceLogMemoryRepository;
        }else{
            return nodeInstanceLogDbRepository;
        }
    }
}
