package com.plani.back.controller;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


public class ScheduleController {

    @GetMapping(value = "/schedules")
    public String schedules(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }
}
