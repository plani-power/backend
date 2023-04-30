package com.plani.back.mapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PlanMapper {
    List<Object> planList();
    int createPlan(Map<String, Object> param);
}
