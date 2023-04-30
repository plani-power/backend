package com.plani.back.serviceimpl;

import com.plani.back.mapper.PlanMapper;
import com.plani.back.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanMapper planMapper;

    @Override
    public Map<String, Object> planList() {
        return planMapper.planList();
    }

    @Override
    public int createPlan(Map<String, Object> param) {
        return planMapper.createPlan(param);
    }
}
