package com.nstop.flow.engine.database.adapter;

import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.EngineTypeEnum;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.database.repository.NodeInstanceRepository;
import com.nstop.flow.engine.database.repository.db.NodeInstanceDbRepository;
import com.nstop.flow.engine.database.repository.memory.NodeInstanceMemoryRepository;
import com.nstop.flow.engine.util.InstanceDataUtil;
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
