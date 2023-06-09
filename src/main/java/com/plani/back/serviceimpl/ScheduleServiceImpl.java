package com.plani.back.serviceimpl;

import com.plani.back.mapper.ScheduleMapper;
import com.plani.back.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<String> scheduleList() {return scheduleMapper.scheduleList();}

    @Override
    public int createSchedules(Map<String,Object> param) {return scheduleMapper.createSchedules(param);}

    @Override
    public int deleteSchedules(Map<String, Object> param) {return scheduleMapper.deleteSchedules(param);}

}
