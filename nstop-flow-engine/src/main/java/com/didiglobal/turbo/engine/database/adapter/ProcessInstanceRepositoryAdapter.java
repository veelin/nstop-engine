package com.didiglobal.turbo.engine.database.adapter;

import com.didiglobal.turbo.engine.common.Constants;
import com.didiglobal.turbo.engine.common.EngineTypeEnum;
import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.database.repository.ProcessInstanceRepository;
import com.didiglobal.turbo.engine.database.repository.db.ProcessInstanceDbRepository;
import com.didiglobal.turbo.engine.database.repository.memory.ProcessInstanceMemoryRepository;
import com.didiglobal.turbo.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessInstanceRepositoryAdapter {
    @Resource
    private ProcessInstanceDbRepository processInstanceDbRepository;
    @Resource
    private ProcessInstanceMemoryRepository processInstanceMemoryRepository;

    public ProcessInstanceRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.ENGINE_TYPE_DATA_KEY);
        return find(engineType);
    }

    public ProcessInstanceRepository find(String engineType){
        if (EngineTypeEnum.instant.name().equals(engineType)) {
            return processInstanceMemoryRepository;
        }else{
            return processInstanceDbRepository;
        }
    }
}
