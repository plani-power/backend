package com.plani.back.service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    List<String> scheduleList();

    int createSchedules(Map<String, Object> param);

    int deleteSchedules(Map<String, Object> param);
}
