package com.nstop.flow.engine.engine;

import com.nstop.flow.engine.common.EngineTypeEnum;
import com.nstop.flow.engine.param.*;
import com.nstop.flow.engine.processor.DefinitionProcessor;
import com.nstop.flow.engine.processor.RuntimeProcessor;
import com.nstop.flow.engine.result.*;

import javax.annotation.Resource;

/**
 * @author linziwei
 * @date 2022/12/7
 */
public class InstantProcessEngine implements ProcessEngine{
    @Resource
    private DefinitionProcessor definitionProcessor;
    @Resource
    private RuntimeProcessor runtimeProcessor;

    @Override
    public CreateFlowResult createFlow(CreateFlowParam createFlowParam) {
        return definitionProcessor.create(createFlowParam);
    }

    @Override
    public UpdateFlowResult updateFlow(UpdateFlowParam updateFlowParam) {
        return definitionProcessor.update(updateFlowParam);
    }

    @Override
    public DeployFlowResult deployFlow(DeployFlowParam deployFlowParam) {
        return definitionProcessor.deploy(deployFlowParam);
    }

    @Override
    public FlowModuleResult getFlowModule(GetFlowModuleParam getFlowModuleParam) {
        return definitionProcessor.getFlowModule(getFlowModuleParam);
    }

    @Override
    public StartProcessResult startProcess(StartProcessParam startProcessParam) {
        return runtimeProcessor.startProcess(startProcessParam);
    }

    @Override
    public CommitTaskResult commitTask(CommitTaskParam commitTaskParam) {
        return runtimeProcessor.commit(commitTaskParam);
    }

    @Override
    public RollbackTaskResult rollbackTask(RollbackTaskParam rollbackTaskParam) {
        return runtimeProcessor.rollback(rollbackTaskParam);
    }

    @Override
    public TerminateResult terminateProcess(String flowInstanceId) {
        return runtimeProcessor.terminateProcess(flowInstanceId);
    }

    @Override
    public NodeInstanceListResult getHistoryUserTaskList(String flowInstanceId) {
        return runtimeProcessor.getHistoryUserTaskList(EngineTypeEnum.instant.name(),flowInstanceId);
    }

    @Override
    public ElementInstanceListResult getHistoryElementList(String flowInstanceId) {
        return runtimeProcessor.getHistoryElementList(EngineTypeEnum.instant.name(),flowInstanceId);
    }

    @Override
    public InstanceDataListResult getInstanceData(String flowInstanceId) {
        return runtimeProcessor.getInstanceData(EngineTypeEnum.instant.name(),flowInstanceId);
    }

    @Override
    public NodeInstanceResult getNodeInstance(String flowInstanceId, String nodeInstanceId) {
        return runtimeProcessor.getNodeInstance(EngineTypeEnum.instant.name(),flowInstanceId, nodeInstanceId);
    }
}
