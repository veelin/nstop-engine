package com.didiglobal.turbo.engine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum FlowModuleEnum {

    DEFAULT(FlowDefinitionStatus.DEFAULT, FlowDeploymentStatus.DEFAULT, FlowModuleStatus.DEFAULT),
    INIT(FlowDefinitionStatus.INIT, null, FlowModuleStatus.EDITING),
    EDITING(FlowDefinitionStatus.EDITING, null, FlowModuleStatus.EDITING),
    DEPLOYED(null, FlowDeploymentStatus.DEPLOYED, FlowModuleStatus.EDITING),
    DISABLED(FlowDefinitionStatus.DISABLED, FlowDeploymentStatus.DISABLED, FlowModuleStatus.DISABLED);

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowModuleEnum.class);

    private Integer defineStatus;
    private Integer deployStatus;
    private Integer flowModuleStatus;

    FlowModuleEnum(Integer defineStatus, Integer deployStatus, Integer flowModuleStatus) {
        this.defineStatus = defineStatus;
        this.deployStatus = deployStatus;
        this.flowModuleStatus = flowModuleStatus;
    }

    public Integer getDefineStatus() {
        return defineStatus;
    }

    public void setDefineStatus(Integer defineStatus) {
        this.defineStatus = defineStatus;
    }

    public Integer getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(Integer deployStatus) {
        this.deployStatus = deployStatus;
    }

    public Integer getFlowModuleStatus() {
        return flowModuleStatus;
    }

    public void setFlowModuleStatus(Integer flowModuleStatus) {
        this.flowModuleStatus = flowModuleStatus;
    }

    public static Integer getStatusByDefinitionStatus(Integer defineStatus) {
        for (FlowModuleEnum flowModuleEnum : FlowModuleEnum.values()) {
            if (flowModuleEnum.getDefineStatus().equals(defineStatus)) {
                return flowModuleEnum.getFlowModuleStatus();
            }
        }
        LOGGER.warn("unknown defineStatus.||defineStatus={}", defineStatus);
        return null;
    }

    public static Integer getStatusByDeploymentStatus(Integer deployStatus) {
        for (FlowModuleEnum flowModuleEnum : FlowModuleEnum.values()) {
            if (flowModuleEnum.getDefineStatus().equals(deployStatus)) {
                return flowModuleEnum.getFlowModuleStatus();
            }
        }
        LOGGER.warn("unknown deployStatus.||deployStatus={}", deployStatus);
        return null;
    }
}



