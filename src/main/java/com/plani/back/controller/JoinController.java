package com.plani.back.controller;

import com.plani.back.service.JoinService;
import com.plani.back.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JoinController {

    @Autowired
    JoinService joinService;

    @GetMapping(value = "joinJudge")
    public Map<String,Object> joinJudge(HttpServletRequest request, HttpServletResponse response,@RequestParam("userid") String userid,
                                        @RequestParam("planid") int planid)
    {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = joinService.joinJudge(userid,planid);

        try{

        }
        catch (Exception e){
            result.put("resultCode", "400");
            result.put("message", "촤하하하하 실패!");
        }
        return result;
    }

}
