package com.plani.back.serviceimpl;

import com.plani.back.mapper.PlanMapper;
import com.plani.back.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanMapper planMapper;

    @Override
    public List<Object> planList(String onoffType, boolean status, String order, int page, String keyword) {
        return planMapper.planList(onoffType, status, order, page, keyword);
    }

    @Override
    public int createPlan(Map<String, Object> param) {
        return planMapper.createPlan(param);
    }
    @Override
    public int planLeader(Map<String, Object> param) {
        return planMapper.planLeader(param);
    }

    @Override
    public Map<String, Object> planDetail(int planId) {
        return planMapper.planDetail(planId);
    }

    @Override
    public int updatePlan(Map<String, Object> param){
        return planMapper.updatePlan(param);
    }

    @Override
    public int deletePlan(int planId) {
        return planMapper.deletePlan(planId);
    }

    @Override
    public List<Object> planApplicant (int planId) {
        return planMapper.planApplicant(planId);
    }

    @Override
    public List<Object> planMember (int planId) {
        return planMapper.planMember(planId);
    }
}
