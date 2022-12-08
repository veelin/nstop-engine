package com.didiglobal.turbo.engine.database.adapter;


import com.didiglobal.turbo.engine.common.Constants;
import com.didiglobal.turbo.engine.common.EngineTypeEnum;
import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.database.repository.InstanceDataRepository;
import com.didiglobal.turbo.engine.database.repository.db.InstanceDataDbRepository;
import com.didiglobal.turbo.engine.database.repository.memory.InstanceDataMemoryRepository;
import com.didiglobal.turbo.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InstanceDataRepositoryAdapter {

    @Resource
    private InstanceDataMemoryRepository instanceDataMemoryRepository;
    @Resource
    private InstanceDataDbRepository instanceDataDbRepository;

    public InstanceDataRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.ENGINE_TYPE_DATA_KEY);
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
