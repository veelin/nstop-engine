package com.didiglobal.turbo.engine.database.adapter;

import com.didiglobal.turbo.engine.common.Constants;
import com.didiglobal.turbo.engine.common.EngineTypeEnum;
import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.database.repository.NodeInstanceRepository;
import com.didiglobal.turbo.engine.database.repository.db.NodeInstanceDbRepository;
import com.didiglobal.turbo.engine.database.repository.memory.NodeInstanceMemoryRepository;
import com.didiglobal.turbo.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NodeInstanceRepositoryAdapter {
    @Resource
    private NodeInstanceDbRepository nodeInstanceDbRepository;
    @Resource
    private NodeInstanceMemoryRepository nodeInstanceMemoryRepository;

    public NodeInstanceRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.ENGINE_TYPE_DATA_KEY);
        return find(engineType);
    }

    public NodeInstanceRepository find(String engineType){
        if (EngineTypeEnum.instant.name().equals(engineType)) {
            return nodeInstanceMemoryRepository;
        }else{
            return nodeInstanceDbRepository;
        }
    }
}
