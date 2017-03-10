package com.kaishengit.controller;

import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;
import com.kaishengit.service.LeaveWorkFlowService;
import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/leave")
public class LeaveController {

    Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveWorkFlowService leaveWorkFlowService;

    @RequestMapping("/apply")
    public String leaveApply(){

        return "activiti/leave/leaveApply";
    }

    @PostMapping("/start")
    public String start(Leave leave, Model model){
        User user = ShiroUtil.getCurrentUser();
        String processDefKey = "leaveProcess";

        Map<String,Object> variables = new HashMap<>();
        // TODO: 2017/3/10 模拟查找到上级ID
        String upperId = "66";
        variables.put("deptLeaderUserId",upperId);
        try {
            leaveWorkFlowService.processStart(leave, user, processDefKey, variables);
            model.addAttribute("message","流程启动成功");
        }catch (ActivitiException e){
            logger.error("{}启动流程异常",processDefKey);
            model.addAttribute("message","流程启动失败");
        }
        return "activiti/leave/leaveApply";

    }
}
