package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/process")
public class ProcessController {

    @RequestMapping("/apply")
    public String processApply(){

        return "activiti/process/processList";
    }
}
