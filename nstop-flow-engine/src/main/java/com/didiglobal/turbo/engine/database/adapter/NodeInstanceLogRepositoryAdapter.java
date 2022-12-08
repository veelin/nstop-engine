package com.didiglobal.turbo.engine.database.adapter;

import com.didiglobal.turbo.engine.common.Constants;
import com.didiglobal.turbo.engine.common.EngineTypeEnum;
import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.database.repository.InstanceDataRepository;
import com.didiglobal.turbo.engine.database.repository.NodeInstanceLogRepository;
import com.didiglobal.turbo.engine.database.repository.db.NodeInstanceLogDbRepository;
import com.didiglobal.turbo.engine.database.repository.memory.NodeInstanceLogMemoryRepository;
import com.didiglobal.turbo.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NodeInstanceLogRepositoryAdapter {
    @Resource
    private NodeInstanceLogDbRepository nodeInstanceLogDbRepository;
    @Resource
    private NodeInstanceLogMemoryRepository nodeInstanceLogMemoryRepository;

    public NodeInstanceLogRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.ENGINE_TYPE_DATA_KEY);
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
