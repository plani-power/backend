package com.plani.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
@Mapper
public interface ScheduleMapper {

    Map<String,Object> scheduleList ();
}
