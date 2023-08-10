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

                boolean genderOk;
                boolean birthOk;

                genderOk = planGender.equals(userGender) ? true: false;
                birthOk = Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear) ? true: false;
//                //참여조건이 성별
//                if(planGender.equals(userGender)){
//                    genderOk = true;
//                    //result.put("Gender","Success");
//                }
//                else{
//                    genderOk = false;
//                    // result.put("Gender","Fail");
//                }
//
//                //참여조건 연령
//                if(Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear)){
//                    birthOk = true;
//                    // result.put("Birth","Success");
//                }
//                else{
//                    birthOk = false;
//                    // result.put("Birth","Fail");
//                }

                if(genderOk && birthOk){
                    result.put("result","Success");
                }
                else {
                    result.put("result","Fail");
                }
            }
            else if(!StringUtils.isEmpty(planGender) || !StringUtils.isEmpty(planBirthYear))
            {
                if(!StringUtils.isEmpty(planGender)){
                    //참여조건이 성별
                    if(planGender == userGender){
                        result.put("result","Success");
                    }
                    else{
                        result.put("result","Fail");
                    }
                }
                else if(!StringUtils.isEmpty(planBirthYear)){
                    //참여조건이 연령
                    if(Integer.parseInt(planBirthYear) > Integer.parseInt(userBirthYear)){
                        result.put("result","Success");
                    }
                    else{
                        result.put("result","Fail");
                    }
                }
            }
            else
            {
                result.put("result","Success");
            }
        }catch (Exception e){

        }

        return result;
    }
}
