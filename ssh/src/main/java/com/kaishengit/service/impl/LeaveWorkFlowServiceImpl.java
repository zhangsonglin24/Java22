package com.kaishengit.service.impl;


import com.kaishengit.mapper.LeaveMapper;
import com.kaishengit.pojo.Leave;
import com.kaishengit.pojo.User;
import com.kaishengit.service.LeaveWorkFlowService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class LeaveWorkFlowServiceImpl implements LeaveWorkFlowService {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    @Transactional
    public void processStart(Leave leave, User user, String processDefKey, Map<String, Object> variables) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //封装leave请假对象并保存
        leave.setUserId(user.getId().toString());
        leave.setApplyTime(format.format(new Date()));
        leaveMapper.save(leave);

        //保存流程定义中的activiti:initiator的applyUserId，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(user.getId().toString());

        //启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefKey,leave.getId().toString(),variables);
        leave.setProcessInstanceId(instance.getProcessInstanceId());
        leaveMapper.update(leave);
    }
}
