package com.didiglobal.turbo.engine.database.repository.db;

import com.didiglobal.turbo.engine.database.repository.db.dao.InstanceDataDAO;
import com.didiglobal.turbo.engine.database.repository.InstanceDataRepository;
import com.didiglobal.turbo.engine.entity.InstanceDataPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InstanceDataDbRepository implements InstanceDataRepository {

    @Resource
    private InstanceDataDAO instanceDataDAO;

    public InstanceDataPO select(String flowInstanceId, String instanceDataId){
        return instanceDataDAO.select(flowInstanceId, instanceDataId);
    }

    /**
     * select recent InstanceData order by id desc
     *
     * @param flowInstanceId
     * @return
     */
    public InstanceDataPO selectRecentOne(String flowInstanceId){
        return instanceDataDAO.selectRecentOne(flowInstanceId);
    }

    /**
     * insert instanceDataPO
     *
     * @param instanceDataPO
     * @return -1 while insert failed
     */
    public int insert(InstanceDataPO instanceDataPO){
        return instanceDataDAO.insert(instanceDataPO);
    }
}
