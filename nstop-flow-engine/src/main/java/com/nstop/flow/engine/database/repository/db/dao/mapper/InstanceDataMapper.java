package com.nstop.flow.engine.database.repository.db.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nstop.flow.engine.entity.InstanceDataPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface InstanceDataMapper extends BaseMapper<InstanceDataPO> {

    @Select("SELECT * FROM ei_instance_data WHERE instance_data_id=#{instanceDataId}")
    InstanceDataPO select(@Param("flowInstanceId") String flowInstanceId,
                          @Param("instanceDataId") String instanceDataId);

    @Select("SELECT * FROM ei_instance_data WHERE flow_instance_id=#{flowInstanceId} ORDER BY id DESC LIMIT 1")
    InstanceDataPO selectRecentOne(@Param("flowInstanceId") String flowInstanceId);
}
