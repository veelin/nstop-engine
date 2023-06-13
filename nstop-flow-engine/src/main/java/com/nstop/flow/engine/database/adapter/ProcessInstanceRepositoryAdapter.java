package com.nstop.flow.engine.database.adapter;

import com.nstop.flow.engine.common.Constants;
import com.nstop.flow.engine.common.EngineTypeEnum;
import com.nstop.flow.engine.common.RuntimeContext;
import com.nstop.flow.engine.database.repository.ProcessInstanceRepository;
import com.nstop.flow.engine.database.repository.db.ProcessInstanceDbRepository;
import com.nstop.flow.engine.database.repository.memory.ProcessInstanceMemoryRepository;
import com.nstop.flow.engine.util.InstanceDataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessInstanceRepositoryAdapter {
    @Resource
    private ProcessInstanceDbRepository processInstanceDbRepository;
    @Resource
    private ProcessInstanceMemoryRepository processInstanceMemoryRepository;

    public ProcessInstanceRepository find(RuntimeContext context){
        String engineType = InstanceDataUtil.getStringValue(context.getInstanceDataMap(), Constants.SYSTEM_CONTEXT_PROPERTIES.ENGINE_TYPE_DATA_KEY);
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
