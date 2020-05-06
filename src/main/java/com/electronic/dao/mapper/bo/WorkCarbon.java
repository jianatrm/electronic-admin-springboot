package com.electronic.dao.mapper.bo;

import java.util.Date;

public class WorkCarbon {
    private Integer carbonId;

    private Integer workOrderId;

    private Integer userId;

    private String remark;

    private Date createTime;

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