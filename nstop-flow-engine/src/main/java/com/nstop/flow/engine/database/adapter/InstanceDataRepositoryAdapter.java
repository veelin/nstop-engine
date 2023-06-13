package com.nstop.flow.engine.database.adapter;


import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.EngineTypeEnum;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.database.repository.InstanceDataRepository;
import com.nstop.flow.engine.database.repository.db.InstanceDataDbRepository;
import com.nstop.flow.engine.database.repository.memory.InstanceDataMemoryRepository;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InstanceDataRepositoryAdapter {

    @Resource
    private InstanceDataMemoryRepository instanceDataMemoryRepository;
    @Resource
    private InstanceDataDbRepository instanceDataDbRepository;

    public InstanceDataRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.ENGINE_TYPE_DATA_KEY);
        return find(engineType);
    }

    public InstanceDataRepository find(String engineType){
        if (EngineTypeEnum.instant.name().equals(engineType)) {
            return instanceDataMemoryRepository;
        }else{
            return instanceDataDbRepository;
        }
    }
}
