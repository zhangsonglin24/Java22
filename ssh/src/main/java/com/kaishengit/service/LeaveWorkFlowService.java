package com.kaishengit.service;


import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;

import java.util.Map;

public interface LeaveWorkFlowService {

    void processStart(Leave leave,User user, String processDefKey, Map<String,Object> variables);
}
