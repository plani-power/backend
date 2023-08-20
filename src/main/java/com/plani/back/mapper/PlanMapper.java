package com.plani.back.mapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface PlanMapper {
    List<Object> planList(String onoffType, boolean status, String order, int page, String keyword);
    int createPlan(Map<String, Object> param);
    int planLeader(Map<String, Object> param);
    Map<String, Object> planDetail(int planId);
    int updatePlan(Map<String, Object> param);
    int deletePlan(int planId);
    List<Object> planApplicant(int planId);
    List<Object> planMember(int planId);

    // 플랜 상세조회 > 적극왕 조회
    List<Object> joinKing(int planId);

    // 플랜 참여 신청
    Map<String,Object> joinUserList(String userid);
    Map<String,Object> joinPlanList(int planid);
    Map<String,Object> planJoinUserList(String userId,int planId);
    int createjoinUser(Map<String, Object> param);
}
