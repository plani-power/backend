package com.plani.back.controller;
import com.plani.back.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
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
        @RequestParam(value="onoffType", required=false) String onoffType,
        @RequestParam(value="status", required=false) boolean status,
        @RequestParam(value="order", required=false) String order,
        @RequestParam(value="keyword", required=false) String keyword,
        @RequestParam int page) {
        Map<String, Object> result = new HashMap<>();
        List<Object> planList = planService.planList(onoffType, status, order, page, keyword);

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
            String isPublic = String.valueOf(param.get("is_public"));
            String planPwd = String.valueOf(param.get("plan_pwd"));

            if("0".equals(isPublic) && ("".equals(planPwd) || "null".equals(planPwd))) {
                result.put("resultCode", "400");
                result.put("message", "비밀번호를 입력하세요.");
                return result;
            }

            String isConstraints = String.valueOf(param.get("is_constraints"));
            String gender = String.valueOf(param.get("gender"));
            String birth = String.valueOf(param.get("birth_year"));

            if("1".equals(isConstraints) && ("".equals(gender) || "null".equals(gender) || "".equals(birth) || "null".equals(birth))) {
                result.put("resultCode", "400");
                result.put("message", "제한 조건이 설정되지 않았습니다.");
                return result;
            } else if ("0".equals(isConstraints) && !("".equals(gender) || "null".equals(gender) || "".equals(birth) || "null".equals(birth))) {
                result.put("resultCode", "400");
                result.put("message", "제한 조건이 true가 아닙니다.");
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
    
    // 플랜 상세보기
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
    
    // 플랜 수정
    @PutMapping(value="plans/{planId}")
    public Map<String, Object> updatePlan(HttpServletRequest request, HttpServletResponse response, @PathVariable int planId, @RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        param.put("plan_id", planId);

        try {
            String isPublic = String.valueOf(param.get("is_public"));
            String planPwd = String.valueOf(param.get("plan_pwd"));

            if("0".equals(isPublic) && ("".equals(planPwd) || "null".equals(planPwd))) {
                result.put("resultCode", "400");
                result.put("message", "비밀번호를 입력하세요.");
                return result;
            }

            String isConstraints = String.valueOf(param.get("is_constraints"));
            String gender = String.valueOf(param.get("gender"));
            String birth = String.valueOf(param.get("birth_year"));

            if("1".equals(isConstraints) && ("".equals(gender) || "null".equals(gender) || "".equals(birth) || "null".equals(birth))) {
                result.put("resultCode", "400");
                result.put("message", "제한 조건이 설정되지 않았습니다.");
                return result;
            } else if ("0".equals(isConstraints) && !("".equals(gender) || "null".equals(gender) || "".equals(birth) || "null".equals(birth))) {
                result.put("resultCode", "400");
                result.put("message", "제한 조건이 true가 아닙니다.");
                return result;
            }

            planService.updatePlan(param);

            result.put("resultCode", "200");
            result.put("message", "success!!");
        }catch (Exception e) {
            result.put("resultCode", "400");
            result.put("message", "Fail!!!");
        }

        return result;
    }

    // 플랜 삭제
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
}
