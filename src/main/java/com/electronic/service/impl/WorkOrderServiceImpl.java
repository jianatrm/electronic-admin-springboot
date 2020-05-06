package com.electronic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.electronic.base.BaseResponse;
import com.electronic.base.PageResult;
import com.electronic.base.SessionUser;
import com.electronic.base.VO.WorkCarbonVO;
import com.electronic.base.VO.WorkNodeVO;
import com.electronic.base.VO.WorkOrderVO;
import com.electronic.contants.BusinessConstants;
import com.electronic.contants.NodeConstants;
import com.electronic.contants.UserConstants;
import com.electronic.contants.WorkOrderConstants;
import com.electronic.dao.mapper.bo.*;
import com.electronic.dao.mapper.interfaces.*;
import com.electronic.service.WorkOrderService;
import com.electronic.utils.SessionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private WorkNodeMapper nodeMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private DeptElectronicDocMapper deptElectronicDocMapper;

    @Autowired
    private ElectronicDocMapper electronicDocMapper;

    @Autowired
    private WorkCarbonMapper workCarbonMapper;

    @Override
    public WorkOrder selectWorkOrder(WorkOrderVO workOrderVO) throws Exception {
        WorkOrderExample WorkOrderExample = new WorkOrderExample();
        WorkOrderExample.Criteria criteria = WorkOrderExample.createCriteria();
        if (workOrderVO.getWorkOrderId() > 0) {
            criteria.andWorkOrderIdEqualTo(workOrderVO.getWorkOrderId());
        }
        List<WorkOrder> WorkOrders = workOrderMapper.selectByExample(WorkOrderExample);
        return CollectionUtils.isEmpty(WorkOrders) ? null : WorkOrders.get(0);
    }

    @Override
    public Integer addWorkOrder(WorkOrderVO workOrderVO) throws Exception {
        List<WorkNode> nodeList = JSONObject.parseArray(workOrderVO.getWorkNodeList(), WorkNode.class);
        WorkOrder workOrder = new WorkOrder();
        BeanUtils.copyProperties(workOrderVO, workOrder);
        workOrder.setWorkOrderStatus(WorkOrderConstants.APPROVAL);
        workOrder.setCurrentNode("1");
        workOrder.setNodeCount(nodeList.size());
        workOrder.setCreateTime(new Date());
        workOrder.setOperateTime(new Date());
        workOrder.setWorkOrderCode(DateFormatUtils.format(new Date(), "yyyyMMDDhhmmss"));
        workOrder.setWorkOrderName(workOrderVO.getWorkOrderName());
        workOrder.setWorkInfo(workOrderVO.getWorkInfo());
        int insertSelective = workOrderMapper.insertSelective(workOrder);


        for (int i = 0; i < nodeList.size(); i++) {
            WorkNode node = new WorkNode();
            BeanUtils.copyProperties(nodeList.get(i), node);
            if (i == 0){
                node.setNodeOperateStatus(NodeConstants.TO_OPERATE);
            }else {
                node.setNodeOperateStatus(NodeConstants.NO_OPERATE);
            }
            node.setNodeOperateTime(new Date());
            node.setWorkOrderId(workOrder.getWorkOrderId());
            nodeMapper.insert(node);
        }

        return insertSelective;
    }

    @Override
    public Integer updateWorkOrder(WorkOrderVO workOrderVO) throws Exception {
        WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(workOrderVO.getWorkOrderId());
//        workOrder.setCurrentWorkNode();
//        user.setOperateTime(new Date());
        int updateByPrimaryKeySelective = workOrderMapper.updateByPrimaryKeySelective(workOrder);
        return updateByPrimaryKeySelective;
    }

    @Override
    public BaseResponse<PageResult<WorkOrderVO>> queryWorkOrder(WorkOrderVO workOrderVO) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
        PageResult<WorkOrderVO> pageResult = new PageResult<>();
        WorkOrderExample WorkOrderExample = new WorkOrderExample();
        WorkOrderExample.Criteria criteria = WorkOrderExample.createCriteria();
        criteria.andOrganizerEqualTo(workOrderVO.getOrganizer());
        if (workOrderVO.getWorkOrderStatus()>0){
            criteria.andWorkOrderStatusNotEqualTo(workOrderVO.getWorkOrderStatus());
        }
        WorkOrderExample.setOrderByClause("operate_time desc");
        PageHelper.startPage(workOrderVO.getPageNum(), workOrderVO.getPageSize());
        List<WorkOrder> workOrders = workOrderMapper.selectByExample(WorkOrderExample);
        PageInfo pageInfo = new PageInfo(workOrders);
        List<WorkOrderVO> list = new ArrayList<>();
        for (WorkOrder workOrder : workOrders) {
            WorkOrderVO work = new WorkOrderVO();
            BeanUtils.copyProperties(workOrder, work);
            work.setWorkOrderStatusDesc(WorkOrderConstants.getStatus(workOrder.getWorkOrderStatus()));
            list.add(work);
        }

        pageResult.setResult(list);
        pageResult.setPageCount(pageInfo.getPages());
        pageResult.setCount(pageInfo.getTotal());
        baseResponse.setResult(pageResult);
        return baseResponse;
    }

    @Override
    public BaseResponse<WorkOrderVO> queryWorkOrderDetail(WorkOrderVO workOrderVO) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
        WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(workOrderVO.getWorkOrderId());
        if (workOrder ==null){
            return baseResponse;
        }
        WorkOrderVO orderVO = new WorkOrderVO();
        BeanUtils.copyProperties(workOrder,orderVO);
        WorkNodeExample workNodeExample = new WorkNodeExample();
        WorkNodeExample.Criteria criteria = workNodeExample.createCriteria();
        criteria.andWorkOrderIdEqualTo(workOrderVO.getWorkOrderId());
        List<WorkNode> workNodes = nodeMapper.selectByExample(workNodeExample);
        List<WorkNodeVO> workNodeVOList = new ArrayList<>();
        for (int i = 0; i <workNodes.size() ; i++) {
            WorkNodeVO workNodeVO = new WorkNodeVO();
            BeanUtils.copyProperties(workNodes.get(i),workNodeVO);
            Integer userId = workNodes.get(i).getUserId();
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
            workNodeVO.setUserName(sysUser.getStaffName());

            WorkCarbonExample workCarbonExample = new WorkCarbonExample();
            WorkCarbonExample.Criteria workCarbonExampleCriteria = workCarbonExample.createCriteria();
            workCarbonExampleCriteria.andNodeIdEqualTo(workNodes.get(i).getNodeId());
            List<WorkCarbon> workCarbonList = workCarbonMapper.selectByExample(workCarbonExample);

            List<WorkCarbonVO> workCarbonVOS = new ArrayList<>();
            for (int j = 0; j < workCarbonList.size(); j++) {
                WorkCarbonVO carbonVO = new WorkCarbonVO();
                BeanUtils.copyProperties(workCarbonList.get(j),carbonVO);
                SysUser sysUser1 = sysUserMapper.selectByPrimaryKey(carbonVO.getUserId());
                carbonVO.setUserName(sysUser1.getStaffName());
                workCarbonVOS.add(carbonVO);
            }
            workNodeVO.setWorkCarbonVOList(workCarbonVOS);
            workNodeVOList.add(workNodeVO);
        }
        criteria.andNodeOrderEqualTo(Integer.parseInt(workOrder.getCurrentNode()));
        List<WorkNode> currentWorkNode = nodeMapper.selectByExample(workNodeExample);
        if (!CollectionUtils.isEmpty(currentWorkNode)){
            WorkNode workNode = currentWorkNode.get(0);
            orderVO.setWorkNode(workNode);
            orderVO.setWorkOrderStatusDesc(WorkOrderConstants.getStatus(orderVO.getWorkOrderStatus()));
            String organizer = orderVO.getOrganizer();
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(Integer.parseInt(organizer));
            orderVO.setUserName(sysUser.getStaffName());
        }
        orderVO.setWorkNodeList(JSONObject.toJSONString(workNodeVOList));
        baseResponse.setResult(orderVO);
        return baseResponse;
    }

    @Override
    public BaseResponse approveWorkOrder(WorkOrderVO workOrderVO) throws Exception {
        SessionUser sessionUser = SessionUtils.getSessionUser();
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);

        WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(workOrderVO.getWorkOrderId());
        WorkNode node = workOrderVO.getWorkNode();
        WorkNode workNode = nodeMapper.selectByPrimaryKey(node.getNodeId());

        Integer nodeCount = workOrder.getNodeCount();
        if (workNode.getNodeOrder()<nodeCount&&WorkOrderConstants.APPROVE_SUCCESS.equals(node.getNodeOperateResult())){
            workOrder.setCurrentNode(String.valueOf(Integer.parseInt(workOrderVO.getCurrentNode())+1));//更新当前节点为下一节点
        }

        workOrder.setOperateTime(new Date());




        //更新节点状态
        workNode.setNodeOperateStatus(NodeConstants.OPERATED);
        workNode.setNodeOperateResult(node.getNodeOperateResult());
        workNode.setNodeOperateDesc(node.getNodeOperateDesc());
        workNode.setNodeOperateTime(new Date());
        nodeMapper.updateByPrimaryKeySelective(workNode);


        //当前节点通过才会走下个节点
        if (workNode.getNodeOrder()<nodeCount&&WorkOrderConstants.APPROVE_SUCCESS.equals(node.getNodeOperateResult())){
            WorkNodeExample workNodeExample = new WorkNodeExample();
            WorkNodeExample.Criteria criteria = workNodeExample.createCriteria();
            criteria.andWorkOrderIdEqualTo(workOrderVO.getWorkOrderId());
            criteria.andNodeOrderEqualTo(workNode.getNodeOrder()+1);
            List<WorkNode> workNodes = nodeMapper.selectByExample(workNodeExample);
            if (!CollectionUtils.isEmpty(workNodes)){
                WorkNode workNode1 = workNodes.get(0);
                workNode1.setNodeOperateStatus(NodeConstants.TO_OPERATE);
                nodeMapper.updateByPrimaryKeySelective(workNode1);
            }
        }
        else if (workNode.getNodeOrder() == nodeCount&&WorkOrderConstants.APPROVE_SUCCESS.equals(node.getNodeOperateResult())){
            workOrder.setWorkOrderStatus(WorkOrderConstants.APPROVE_SUCCESS);
            List<SysDept> sysDepts = JSONObject.parseArray(workOrderVO.getSysDeptList(), SysDept.class);
            if (!StringUtils.isBlank(workOrder.getWorkInfo())&&!"null".equals(workOrder.getWorkInfo())){
                List<ElectronicDoc> electronicDocs = JSONObject.parseArray(workOrder.getWorkInfo(), ElectronicDoc.class);
                for (int i = 0; i <electronicDocs.size() ; i++) {
                    ElectronicDoc eDoc = electronicDocs.get(i);
                    ElectronicDoc electronicDoc = new ElectronicDoc();
                    BeanUtils.copyProperties(eDoc,electronicDoc);
                    electronicDoc.setStatus(String.valueOf(UserConstants.VALID_STATUS));
                    electronicDoc.setOperateId(Integer.parseInt(workOrder.getOrganizer()));
                    electronicDoc.setOperateTime(new Date());
                    electronicDocMapper.insertSelective(electronicDoc);
                    for (int j = 0; j <sysDepts.size() ; j++) {
                        DeptElectronicDoc deptElectronicDoc = new DeptElectronicDoc();
                        deptElectronicDoc.setWorkOrderId(workOrderVO.getWorkOrderId());
                        deptElectronicDoc.setDeptId(sysDepts.get(j).getDeptId());
                        deptElectronicDoc.setDocId(electronicDoc.getDocId());
                        deptElectronicDoc.setOperateId(Integer.parseInt(workOrder.getOrganizer()));
                        deptElectronicDoc.setOperateTime(new Date());
                        deptElectronicDoc.setStatus(UserConstants.VALID_STATUS);
                        deptElectronicDocMapper.insertSelective(deptElectronicDoc);
                    }
                }
            }
        }else {
            workOrder.setWorkOrderStatus(WorkOrderConstants.APPROVE_FIALE);
        }
        workOrderMapper.updateByPrimaryKeySelective(workOrder);
        return baseResponse;
    }

    @Override
    public BaseResponse approverCarbonCopy(WorkOrderVO workOrderVO) throws Exception {
        WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(workOrderVO.getWorkOrderId());
        if (null == workOrder){
            return new BaseResponse(BusinessConstants.BUSI_FAILURE,BusinessConstants.BUSI_FAILURE_CODE,"工单未找到");
        }

        //更新当前工单
        workOrder.setCurrentNode(String.valueOf(Integer.parseInt(workOrderVO.getCurrentNode())+1));//更新当前节点为下一节点
        Integer nodeCount = workOrder.getNodeCount();
        workOrder.setNodeCount(nodeCount+1);
        workOrderMapper.updateByPrimaryKeySelective(workOrder);

        //更新节点信息
        WorkNode node = workOrderVO.getWorkNode();
        WorkNode workNode = nodeMapper.selectByPrimaryKey(node.getNodeId());
        workNode.setNodeOperateStatus(NodeConstants.OPERATED);
        workNode.setNodeOperateResult(node.getNodeOperateResult());
        workNode.setNodeOperateDesc(node.getNodeOperateDesc());
        workNode.setNodeOperateTime(new Date());
        nodeMapper.updateByPrimaryKeySelective(workNode);

        //添加新的节点信息
        WorkNode wNode = new WorkNode();
        wNode.setWorkOrderId(workOrderVO.getWorkOrderId());
        wNode.setUserId(node.getUserId());
        wNode.setNodeOrder(nodeCount+1);
        wNode.setNodeOperateStatus(NodeConstants.TO_OPERATE);
        nodeMapper.insert(wNode);
        //添加抄送信息
        String workCarbonList = workOrderVO.getWorkCarbonList();
        if (!StringUtils.isEmpty(workCarbonList)){
            List<WorkCarbon> workCarbons = JSONObject.parseArray(workCarbonList, WorkCarbon.class);
            for (WorkCarbon workCarbon:workCarbons){
                workCarbon.setWorkOrderId(workOrderVO.getWorkOrderId());
                workCarbon.setNodeId(wNode.getNodeId());
                workCarbon.setCreateTime(new Date());
                workCarbonMapper.insertSelective(workCarbon);
            }
        }
        return new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE );
    }

    @Override
    public BaseResponse queryApproverCarbonCopy(WorkCarbonVO workCarbonVO) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);

        PageResult<WorkCarbonVO> pageResult = new PageResult<>();
        //查询抄送数据
        WorkCarbonExample workCarbonExample = new WorkCarbonExample();
        WorkCarbonExample.Criteria criteria = workCarbonExample.createCriteria();
        criteria.andUserIdEqualTo(workCarbonVO.getUserId());
        PageHelper.startPage(workCarbonVO.getPageNum(),workCarbonVO.getPageSize());
        List<WorkCarbon> workCarbonList = workCarbonMapper.selectByExample(workCarbonExample);
        PageInfo pageInfo = new PageInfo(workCarbonList);

        List<WorkCarbonVO> workCarbonVOS = new ArrayList<>();
        for (int i = 0; i < workCarbonList.size(); i++) {
            WorkCarbonVO carbonVO = new WorkCarbonVO();
            BeanUtils.copyProperties(workCarbonList.get(i),carbonVO);
            Integer workOrderId = workCarbonList.get(i).getWorkOrderId();
            WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(workOrderId);
            WorkOrderVO work = new WorkOrderVO();
            BeanUtils.copyProperties(workOrder, work);
            work.setWorkOrderStatusDesc(WorkOrderConstants.getStatus(workOrder.getWorkOrderStatus()));
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(Integer.parseInt(workOrder.getOrganizer()));
            work.setUserName(sysUser.getStaffName());
            carbonVO.setWorkOrderVO(work);
            workCarbonVOS.add(carbonVO);
        }
        pageResult.setCount(pageInfo.getTotal());
        pageResult.setResult(workCarbonVOS);
        baseResponse.setResult(pageResult);
        return baseResponse;
    }
}
