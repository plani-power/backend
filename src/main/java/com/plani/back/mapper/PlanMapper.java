package com.plani.back.mapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PlanMapper {
    List<Object> planList();
    int createPlan(Map<String, Object> param);
    int planLeader(Map<String, Object> param);
    Map<String, Object> planDetail(int planId);
    int updatePlan(Map<String, Object> param);
    int deletePlan(int planId);
    List<Object> planApplicant(int planId);
    List<Object> planMember(int planId);
}
