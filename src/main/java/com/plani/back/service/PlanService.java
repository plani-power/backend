package com.plani.back.service;

import java.util.List;
import java.util.Map;

public interface PlanService {
    List<Object> planList();
    int createPlan(Map<String, Object> param);
}
