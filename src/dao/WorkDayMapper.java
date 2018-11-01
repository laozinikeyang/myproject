package dao;

import entity.WorkDay;

public interface WorkDayMapper {
    int deleteByPrimaryKey(String workId);

    int insert(WorkDay record);

    int insertSelective(WorkDay record);

    WorkDay selectByPrimaryKey(String workId);

    int updateByPrimaryKeySelective(WorkDay record);

    int updateByPrimaryKey(WorkDay record);
}