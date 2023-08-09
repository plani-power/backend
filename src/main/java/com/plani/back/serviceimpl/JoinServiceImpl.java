package com.plani.back.serviceimpl;

import com.plani.back.mapper.JoinMapper;
import com.plani.back.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            if(!StringUtils.isEmpty(planGender) && !StringUtils.isEmpty(planBirthYear)){
                //참여조건 둘다

                //참여조건이 성별
                if(planGender.equals(userGender)){
                    result.put("Gender","Success");
                }
                else{
                    result.put("Gender","Fail");
                }

                //참여조건 연령
                if(Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear)){
                    result.put("Birth","Success");
                }
                else{
                    result.put("Birth","Fail");
                }
            }
            else if(planGender != null){
                //참여조건이 성별
                if(planGender == userGender){
                    result.put("Gender","Success");
                }
                else{
                    result.put("Gender","Fail");
                }
            }
            else if(planBirthYear != null){
                //참여조건이 연령
                if(Integer.parseInt(planBirthYear) < Integer.parseInt(userBirthYear)){
                    result.put("Birth","Success");
                }
                else{
                    result.put("Birth","Fail");
                }
            }
        }catch (Exception e){

        }

        return result;
    }
}
