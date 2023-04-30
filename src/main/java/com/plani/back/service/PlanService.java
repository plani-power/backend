package com.plani.back.service;

import java.util.Map;

public interface PlanService {
    Map<String, Object> planList();
    int createPlan(Map<String, Object> param);
}
