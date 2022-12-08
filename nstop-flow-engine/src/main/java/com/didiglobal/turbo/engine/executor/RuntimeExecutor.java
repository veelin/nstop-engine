package com.didiglobal.turbo.engine.executor;


import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.database.adapter.InstanceDataRepositoryAdapter;
import com.didiglobal.turbo.engine.database.adapter.NodeInstanceLogRepositoryAdapter;
import com.didiglobal.turbo.engine.database.adapter.NodeInstanceRepositoryAdapter;
import com.didiglobal.turbo.engine.exception.ProcessException;
import com.didiglobal.turbo.engine.util.IdGenerator;
import com.didiglobal.turbo.engine.util.StrongUuidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public abstract class RuntimeExecutor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RuntimeExecutor.class);

    @Resource
    protected ExecutorFactory executorFactory;

    @Resource
    protected InstanceDataRepositoryAdapter instanceDataRepositoryAdapter;

    @Resource
    protected NodeInstanceRepositoryAdapter nodeInstanceRepositoryAdapter;

    @Resource
    protected NodeInstanceLogRepositoryAdapter nodeInstanceLogRepositoryAdapter;

    private static final IdGenerator idGenerator = new StrongUuidGenerator();


    protected String genId() {
        return idGenerator.getNextId();
    }

    public abstract void execute(RuntimeContext runtimeContext) throws ProcessException;

    public abstract void commit(RuntimeContext runtimeContext) throws ProcessException;

    public abstract void rollback(RuntimeContext runtimeContext) throws ProcessException;

    protected abstract boolean isCompleted(RuntimeContext runtimeContext) throws ProcessException;

    protected abstract RuntimeExecutor getExecuteExecutor(RuntimeContext runtimeContext) throws ProcessException;

    protected abstract RuntimeExecutor getRollbackExecutor(RuntimeContext runtimeContext) throws ProcessException;
}
