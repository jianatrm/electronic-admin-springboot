package com.electronic.dao.mapper.interfaces;

import com.electronic.dao.mapper.bo.WorkCarbon;
import com.electronic.dao.mapper.bo.WorkCarbonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkCarbonMapper {
    int countByExample(WorkCarbonExample example);

    int deleteByExample(WorkCarbonExample example);

    int deleteByPrimaryKey(Integer carbonId);

    int insert(WorkCarbon record);

    int insertSelective(WorkCarbon record);

    List<WorkCarbon> selectByExample(WorkCarbonExample example);

    WorkCarbon selectByPrimaryKey(Integer carbonId);

    int updateByExampleSelective(@Param("record") WorkCarbon record, @Param("example") WorkCarbonExample example);

    int updateByExample(@Param("record") WorkCarbon record, @Param("example") WorkCarbonExample example);

    int updateByPrimaryKeySelective(WorkCarbon record);

    int updateByPrimaryKey(WorkCarbon record);
}