package com.nstop.flow.engine.database.repository.db;

import com.nstop.flow.engine.database.repository.db.dao.NodeInstanceLogDAO;
import com.nstop.flow.engine.database.repository.NodeInstanceLogRepository;
import com.nstop.flow.engine.entity.NodeInstanceLogPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NodeInstanceLogDbRepository implements NodeInstanceLogRepository {

    @Resource
    private NodeInstanceLogDAO nodeInstanceLogDAO;

    /**
     * insert nodeInstanceLogPO
     *
     * @param nodeInstanceLogPO
     * @return -1 while insert failed
     */
    public int insert(NodeInstanceLogPO nodeInstanceLogPO){
        return nodeInstanceLogDAO.insert(nodeInstanceLogPO);
    }

    /**
     * nodeInstanceLogList batch insert
     *
     * @param nodeInstanceLogList
     * @return
     */
    public boolean insertList(List<NodeInstanceLogPO> nodeInstanceLogList){
        return nodeInstanceLogDAO.insertList(nodeInstanceLogList);
    }
}
