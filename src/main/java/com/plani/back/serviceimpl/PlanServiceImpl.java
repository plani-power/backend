package com.plani.back.serviceimpl;

import com.plani.back.mapper.PlanMapper;
import com.plani.back.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanMapper planMapper;

    @Override
    public List<Object> planList() {
        return planMapper.planList();
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

    //플랜 상세보기 > 적극왕 조회
    @Override
    public List<Object> joinKing (int planId){return planMapper.joinKing(planId);}

    @Override
    public Map planJoin(Map<String, Object> param)
    {
        Map<String,Object> result = new HashMap<>();
        try{

            String userId = param.get("userId").toString();
            int planId = Integer.parseInt(param.get("planId").toString());

            //참여 신청을 했던 USER인지 확인
            Map<String,Object> JoinUserList = planMapper.planJoinUserList(userId,planId);

            //참여신청을 한 경우
            if(JoinUserList != null){
                result.put("result","이미 참여 신청한 플랜입니다.");
                return result;
            }
            else{
                //참여신청을 안한 경우 참여조건 확인
                Map<String,Object> userList = planMapper.joinUserList(userId);
                Map<String,Object> planList = planMapper.joinPlanList(planId);


                String userGender = userList.get("user_gender").toString();
                String userBirthYear = userList.get("user_birth_year").toString();
                String planGender = planList.get("gender").toString();
                String planBirthYear = planList.get("birth_year").toString();


                if(!StringUtils.isEmpty(planGender) && !StringUtils.isEmpty(planBirthYear)){
                    //참여조건이 둘 다인 경우

                    boolean genderOk;
                    boolean birthOk;

                    //성별, 연령 일치 확인
                    genderOk = planGender.equals(userGender) ? true: false;
                    birthOk = Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear) ? true: false;
//                //성별 일치 확인
//                if(planGender.equals(userGender)){
//                    genderOk = true;
//                    //result.put("Gender","Success");
//                }
//                else{
//                    genderOk = false;
//                    // result.put("Gender","Fail");
//                }
//
//                //연령 일치 확인
//                if(Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear)){
//                    birthOk = true;
//                    // result.put("Birth","Success");
//                }
//                else{
//                    birthOk = false;
//                    // result.put("Birth","Fail");
//                }
                    if(genderOk && birthOk){
                        result.put("result","200");
                    }
                    else {
                        result.put("result","400");
                        return result;
                    }
                }
                else if(!StringUtils.isEmpty(planGender) || !StringUtils.isEmpty(planBirthYear))
                {
                    if(!StringUtils.isEmpty(planGender)){
                        //참여조건이 성별만
                        if(planGender.equals(userGender)){
                            result.put("result","200");
                        }
                        else{
                            result.put("result","400");
                            return result;
                        }
                    }
                    else if(!StringUtils.isEmpty(planBirthYear)){
                        //참여조건이 연령만
                        if(Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear)){
                            result.put("result","200");
                        }
                        else{
                            result.put("result","400");
                            return result;
                        }
                    }
                }
                else
                {
                    //참여조건이 없는 경우
                    result.put("result","200");
                }

                if(result.get("result").equals("200")){
                    planMapper.createjoinUser(param);
                }
            }
        }catch (Exception e){
            result.put("error",e.toString());
        }
        return result;
    }
}
