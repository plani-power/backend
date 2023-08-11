package com.plani.back.controller;
import com.plani.back.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PlanController {
    @Autowired
    PlanService planService;
    
    // 플랜 조회 컨트롤러
    @GetMapping(value="plans")
    public Map<String, Object> planList(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(value="", required=false) String test1,
        @RequestParam(value="", required=false) String test2) {
        Map<String, Object> result = new HashMap<>();
        List<Object> planList = planService.planList();

        try {
            result.put("planList", planList);
            result.put("resultCode", "200");
            result.put("message", "success!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }
    
    // 플랜 생성 컨트롤러
    @PostMapping(value="plans")
    public Map<String, Object> createPlan(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> param) {
        Map<String, Object> result = new HashMap<>();

        try {
            System.out.println(param);
            String isPublic = param.get("is_public").toString();
            String planPwd = String.valueOf(param.get("plan_pwd"));

            if("false".equals(isPublic) && ("".equals(planPwd) || "null".equals(planPwd))) {
                result.put("resultCode", "400");
                result.put("message", "비밀번호를 입력하세요.");
                return result;
            }
            planService.createPlan(param);

            // 추후에 로그인 토큰 등이 개발되면 수정, 현재는 user 정보가 없기 때문에 하드코딩으로 넘어가겠습니다.
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("user_id", param.get("created_user").toString());
            userInfo.put("plan_id", param.get("PLAN_ID"));
            userInfo.put("role_type", "ROLE001");

            planService.planLeader(userInfo);

                result.put("resultCode", "200");
                result.put("message", "success!");
        }catch (Exception e) {
            System.out.println(e);
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    @GetMapping(value="plans/{planId}")
    public Map<String, Object> planDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId) {
        Map<String, Object> result = new HashMap<>();

        try{
            Map<String, Object> planDetail = planService.planDetail(planId);
            result.put("planDetail", planDetail);
            result.put("resultCode", "200");
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    @PutMapping(value="plans/{planId}")
    public Map<String, Object> updatePlan(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId, @RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        param.put("plan_id", planId);

        planService.updatePlan(param);

        try {
            result.put("resultCode", "200");
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    @DeleteMapping(value="plans/{planId}")
    public Map<String, Object> deletePlan(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId) {
        Map<String, Object> result = new HashMap<>();

        planService.deletePlan(planId);

        try {
            result.put("resultCode", "200");
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    // 플랜 신청자 목록 조회
    @GetMapping(value="plan-applicants/{planId}")
    public Map<String, Object> planApplicantsList(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId) {
        Map<String, Object> result = new HashMap<>();

        List<Object> applicantList = planService.planApplicant(planId);

        try {
            result.put("resultCode", "200");
            result.put("planMember", applicantList);
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    // 플랜 멤버 목록 조회
    @GetMapping(value="plan-members/{planId}")
    public Map<String, Object> planMembersList(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId) {
        Map<String, Object> result = new HashMap<>();

        List<Object> memberList = planService.planMember(planId);

        try {
            result.put("resultCode", "200");
            result.put("planMember", memberList);
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }
    // 플랜 상세조회 > 적극왕 조회
    @GetMapping(value="plans/joinKing/{planId}")
    public Map<String, Object> joinKingList(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId) {
        Map<String, Object> result = new HashMap<>();

        List<Object> joinKingList = planService.joinKing(planId);

        try {
            result.put("resultCode", "200");
            result.put("joinKingList", joinKingList);
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    //플랜 참여 신청
    @PostMapping (value="plans/join")
    public Map<String,Object> planJoin(HttpServletRequest request, HttpServletResponse response,@RequestBody Map<String,Object> param)
    {
        Map<String,Object> result = new HashMap<>();

        try{

            for (var data :param.entrySet()) {
                if(StringUtils.isEmpty(data.getValue())){
                    result.put("resultCode",400);
                    result.put("message",data.getKey()+"가 null");
                    return result;
                }
            }

            Map planJoin = planService.planJoin(param);
            result.put("resultCode", planJoin);
           // result.put("message", "success!!");
        }
        catch (Exception e){
            result.put("resultCode", "400");
            result.put("message", "촤하하하하 실패!");
        }
        return result;
    }
}
