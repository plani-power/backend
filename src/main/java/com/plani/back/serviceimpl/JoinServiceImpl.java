package com.plani.back.serviceimpl;

import com.plani.back.mapper.JoinMapper;
import com.plani.back.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinMapper joinMapper;
    @Override
    public Map joinJudge(String userid, int planid)
    {
        Map<String,Object> userList = joinMapper.userList(userid);
        Map<String,Object> planList = joinMapper.planList(planid);
        Map<String,Object> result = new HashMap<>();

        String userGender = userList.get("user_gender").toString();
        String userBirthYear = userList.get("user_birth_year").toString();
        String planGender = planList.get("gender").toString();
        String planId = planList.get("plan_id").toString();
        String planBirthYear = planList.get("birth_year").toString();

        try{
            if(planGender != null && planBirthYear != null){
                //참여조건 둘다

                //참여조건이 성별
                if(planGender.equals(userGender)){
                    result.put("성별 성공","성별 성공");
                }
                else{
                    result.put("성별 실패","성별 실패");
                }

                //참여조건 연령
                if(Integer.parseInt(planBirthYear) < Integer.parseInt(userBirthYear)){
                    result.put("연령 성공","연령 성공");
                }
                else{
                    result.put("성별 실패","성별 실패");
                }
            }
            else if(planGender != null){
                //참여조건이 성별
                if(planGender == userGender){
                    result.put("성별 성공","성별 성공");
                }
                else{
                    result.put("성별 실패","성별 실패");
                }
            }
            else if(planBirthYear != null){
                //참여조건이 연령
                if(Integer.parseInt(planBirthYear) < Integer.parseInt(userBirthYear)){
                    result.put("연령 성공","연령 성공");
                }
                else{
                    result.put("성별 실패","성별 실패");
                }
            }
        }catch (Exception e){

        }

        return result;
    }
}
