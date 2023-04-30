package com.plani.back.mapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;
@Mapper
public interface PlanMapper {
    Map<String, Object> planList();
    int createPlan(Map<String, Object> param);
}
