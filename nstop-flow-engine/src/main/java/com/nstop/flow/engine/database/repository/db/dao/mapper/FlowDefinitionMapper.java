package com.nstop.flow.engine.database.repository.db.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nstop.flow.engine.entity.FlowDefinitionPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FlowDefinitionMapper extends BaseMapper<FlowDefinitionPO> {

    @Select("SELECT * FROM em_flow_definition WHERE flow_module_id=#{flowModuleId}")
    FlowDefinitionPO selectByFlowModuleId(@Param("flowModuleId") String flowModuleId);

    @Select("SELECT * FROM em_flow_definition WHERE flow_type = #{flowType} and flow_key=#{flowKey}")
    FlowDefinitionPO selectByFlowKey(@Param("flowType") String flowType,@Param("flowKey") String flowKey);
}
