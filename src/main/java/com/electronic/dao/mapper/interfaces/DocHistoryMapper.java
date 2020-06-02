package com.electronic.dao.mapper.interfaces;

import com.electronic.dao.mapper.bo.DocHistory;
import com.electronic.dao.mapper.bo.DocHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocHistoryMapper {
    int countByExample(DocHistoryExample example);

    int deleteByExample(DocHistoryExample example);

    int deleteByPrimaryKey(String historyId);

    int insert(DocHistory record);

    int insertSelective(DocHistory record);

    List<DocHistory> selectByExample(DocHistoryExample example);

    DocHistory selectByPrimaryKey(String historyId);

    int updateByExampleSelective(@Param("record") DocHistory record, @Param("example") DocHistoryExample example);

    int updateByExample(@Param("record") DocHistory record, @Param("example") DocHistoryExample example);

    int updateByPrimaryKeySelective(DocHistory record);

    int updateByPrimaryKey(DocHistory record);
}
