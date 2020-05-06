package com.electronic.base.VO;

import com.electronic.dao.mapper.bo.WorkOrder;

import java.util.Date;

public class WorkCarbonVO {
    private Integer carbonId;

    private Integer workOrderId;

    private Integer userId;

    private String remark;

    private Date createTime;

    private Integer pageNum = 1;

    private Integer pageSize =10;


    private WorkOrderVO workOrderVO;


    public WorkOrderVO getWorkOrderVO() {
        return workOrderVO;
    }

    public void setWorkOrderVO(WorkOrderVO workOrderVO) {
        this.workOrderVO = workOrderVO;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCarbonId() {
        return carbonId;
    }

    public void setCarbonId(Integer carbonId) {
        this.carbonId = carbonId;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}