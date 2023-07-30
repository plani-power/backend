package com.plani.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class UserController {

    @PostMapping (value="users")
    public void createUser(HttpServletRequest request, HttpServletResponse response){

    }
}
