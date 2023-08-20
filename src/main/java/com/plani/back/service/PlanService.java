package com.plani.back.service;

import java.util.List;
import java.util.Map;

public interface PlanService {
    List<Object> planList(String onoffType, boolean status, String order, int page, String keyword);
    int createPlan(Map<String, Object> param);
    int planLeader(Map<String, Object> param);
    Map<String, Object> planDetail(int planId);
    int updatePlan(Map<String, Object> param);
    int deletePlan(int planId);
    List<Object> planApplicant(int planId);
    List<Object> planMember(int planId);
}
