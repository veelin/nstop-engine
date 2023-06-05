package com.nstop.flow.engine.database.repository.db.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nstop.flow.engine.entity.FlowDeploymentPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FlowDeploymentMapper extends BaseMapper<FlowDeploymentPO> {

    @Select("SELECT * FROM em_flow_deployment WHERE flow_deploy_id=#{flowDeployId}")
    FlowDeploymentPO selectByDeployId(@Param("flowDeployId") String flowDeployId);

    @Select("SELECT * FROM em_flow_deployment WHERE flow_module_id=#{flowModuleId} ORDER BY id DESC LIMIT 1")
    FlowDeploymentPO selectByModuleId(@Param("flowModuleId") String flowModuleId);

    @Select("SELECT * FROM em_flow_deployment WHERE flow_type=#{flowType}  <if test='flowKey != null'> and flow_key=#{flowKey} </if> ORDER BY id DESC LIMIT 1")
    FlowDeploymentPO selectByFlowType(@Param("flowType") String flowType, @Param("flowKey") String flowKey);
}
