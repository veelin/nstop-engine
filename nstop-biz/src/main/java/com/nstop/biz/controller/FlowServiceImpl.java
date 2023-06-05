package com.nstop.biz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nstop.biz.controller.enums.FlowModuleStatusEnum;
import com.nstop.biz.controller.pojo.request.*;
import com.nstop.biz.controller.pojo.response.FlowModuleListResponse;
import com.nstop.biz.controller.pojo.response.FlowModuleResponse;
import com.nstop.flow.engine.common.FlowDeploymentStatus;
import com.nstop.flow.engine.database.repository.db.dao.FlowDefinitionDAO;
import com.nstop.flow.engine.database.repository.db.dao.FlowDeploymentDAO;
import com.nstop.flow.engine.engine.TurboProcessEngine;
import com.nstop.flow.engine.entity.FlowDefinitionPO;
import com.nstop.flow.engine.entity.FlowDeploymentPO;
import com.nstop.flow.engine.result.CreateFlowResult;
import com.nstop.flow.engine.result.DeployFlowResult;
import com.nstop.flow.engine.result.FlowModuleResult;
import com.nstop.flow.engine.result.UpdateFlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: james zhangxiao
 * @Date: 4/11/22
 * @Description: 流程处理类
 */
@Service
public class FlowServiceImpl {

    @Resource
    private TurboProcessEngine turboProcessEngine;

    @Resource
    private FlowDefinitionDAO flowDefinitionDAO;

    @Resource
    private FlowDeploymentDAO flowDeploymentDAO;

    /**
     * 创建流程
     *
     * @param createFlowParam 创建流程基础信息
     * @return 创建流程结果
     */
    public CreateFlowResult createFlow(CreateFlowRequest createFlowParam) {
        return turboProcessEngine.createFlow(createFlowParam);
    }

    /**
     * 保存流程模型数据
     *
     * @param updateFlowRequest 保存流程模型请求参数
     * @return 保存流程模型结果
     */
    public UpdateFlowResult updateFlow(UpdateFlowRequest updateFlowRequest) {
        return turboProcessEngine.updateFlow(updateFlowRequest);
    }

    /**
     * 发布流程
     *
     * @param deployFlowRequest 发布流程请求参数
     * @return 发布流程结果
     */
    public DeployFlowResult deployFlow(DeployFlowRequest deployFlowRequest) {
        return turboProcessEngine.deployFlow(deployFlowRequest);
    }

    /**
     * 获取单个流程
     *
     * @param getFlowModuleRequest 查询单个流程参数
     * @return 查询单个流程结果
     */
    public FlowModuleResult getFlowModule(GetFlowModuleRequest getFlowModuleRequest) {
        return turboProcessEngine.getFlowModule(getFlowModuleRequest);
    }

    /**
     * 查询流程列表
     *
     * @param getFlowModuleListRequest 查询流程列表参数
     * @return 查询流程结果
     */
    public FlowModuleListResponse getFlowModuleList(GetFlowModuleListRequest getFlowModuleListRequest) {

        Page<FlowDefinitionPO> page = buildGetFlowModuleListPage(getFlowModuleListRequest);
        QueryWrapper<FlowDefinitionPO> queryWrapper = buildGetFlowModuleListQueryWrapper(getFlowModuleListRequest);
        IPage<FlowDefinitionPO> pageRes = flowDefinitionDAO.page(page, queryWrapper);
        List<FlowModuleResponse> flowModuleList = new ArrayList<>();
        for (FlowDefinitionPO flowDefinitionPO : pageRes.getRecords()) {
            FlowModuleResponse getFlowModuleResponse = new FlowModuleResponse();
            BeanUtils.copyProperties(flowDefinitionPO, getFlowModuleResponse);
            QueryWrapper<FlowDeploymentPO> flowDeployQuery = buildCountFlowDeployQueryWrapper(flowDefinitionPO.getFlowModuleId());
            long count = flowDeploymentDAO.count(flowDeployQuery);
            if (count >= 1) {
                //4 已发布
                getFlowModuleResponse.setStatus(FlowModuleStatusEnum.PUBLISHED.getValue());
            }
            flowModuleList.add(getFlowModuleResponse);
        }
        FlowModuleListResponse flowModuleListResponse = new FlowModuleListResponse();
        flowModuleListResponse.setFlowModuleList(flowModuleList);
        BeanUtils.copyProperties(pageRes, flowModuleListResponse);
        return flowModuleListResponse;
    }

    private Page<FlowDefinitionPO> buildGetFlowModuleListPage(GetFlowModuleListRequest getFlowModuleListRequest) {
        Page<FlowDefinitionPO> page = new Page<>();
        if (getFlowModuleListRequest.getSize() != null && getFlowModuleListRequest.getCurrent() != null) {
            page.setCurrent(getFlowModuleListRequest.getCurrent());
            page.setSize(getFlowModuleListRequest.getSize());
        }
        return page;
    }

    private QueryWrapper<FlowDefinitionPO> buildGetFlowModuleListQueryWrapper(GetFlowModuleListRequest getFlowModuleListRequest) {
        QueryWrapper<FlowDefinitionPO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowModuleId())) {
            queryWrapper.eq("flow_module_id", getFlowModuleListRequest.getFlowModuleId());
        }
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowName())) {
            queryWrapper.like("flow_name", getFlowModuleListRequest.getFlowName());
        }
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowDeployId())) {
            queryWrapper.eq("flow_module_id", getFlowModuleListRequest.getFlowDeployId());
        }
        queryWrapper.orderByDesc("modify_time");
        return queryWrapper;
    }

    private QueryWrapper<FlowDeploymentPO> buildCountFlowDeployQueryWrapper(String flowModuleId) {
        QueryWrapper<FlowDeploymentPO> flowDeployQuery = new QueryWrapper<>();
        flowDeployQuery.eq("flow_module_id", flowModuleId);
        flowDeployQuery.eq("status", FlowDeploymentStatus.DEPLOYED);
        return flowDeployQuery;
    }

}
