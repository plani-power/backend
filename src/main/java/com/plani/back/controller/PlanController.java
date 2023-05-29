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
    public Map<String, Object> planList(HttpServletRequest request, HttpServletResponse response) {
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
            planService.createPlan(param);

            String onOffType = param.get("onoff_type").toString();
            if(onOffType.equals("online")) {
                result.put("resultCode", "200");
                result.put("message", "success!");
                result.put("onOff", "online");
            } else if(onOffType.equals("offline")) {
                result.put("resultCode", "200");
                result.put("message", "success!");
                result.put("onOff", "offline");
            } else {
                result.put("resultCode", "200");
                result.put("message", "success!");
                result.put("onOff", "online offline both");
            }
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
}
