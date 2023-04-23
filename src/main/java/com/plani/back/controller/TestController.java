package com.plani.back.controller;
import com.plani.back.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("main")
    @ResponseBody
    public String testMain() {
        String result = "Hello World";
        return result;
    }

    @GetMapping("dbtest")
    @ResponseBody
    public String testDB() {
        String result = testService.testDB();
        return result;
    }
}
