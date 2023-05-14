package com.plani.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ScheduleMapper {

    List<String> scheduleList ();

    int createSchedules(Map<String, Object> param);

    int deleteSchedules(Map<String, Object> param);
}
