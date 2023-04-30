package com.plani.back.controller;

import com.plani.back.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value = "/schedules")
    public Map<String, Object> schedules(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> result = new HashMap<>();
        List<String> schedulesList = scheduleService.scheduleList();

        try {
         result.put("schedulesList",schedulesList);
            result.put("resultCode", "200");
            result.put("message", "제법이군");
        }
        catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "촤하하하하 실패!");
        }

        return result;
    }
}
