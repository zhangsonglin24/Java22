package com.kaishengit.controller;

import com.kaishengit.pojo.Process;
import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping("/apply")
    public String processApply(){

        return "activiti/process/processList";
    }

    @RequestMapping("/task/list")
    public String taskList(Model model){

        String userId = ShiroUtil.getCurrentUserId().toString();

        List<Task> taskList = new ArrayList<>();
        List<Process> processes = new ArrayList<>();

        List<Task> todoList = taskService.createTaskQuery()
                .taskAssignee(userId).orderByTaskCreateTime().asc().list();

        List<Task> unSignedTask = taskService.createTaskQuery()
                .taskCandidateUser(userId).orderByTaskCreateTime().asc().list();

        taskList.addAll(todoList);
        taskList.addAll(unSignedTask);

        for(Task task : taskList){
            String proInstanceId = task.getProcessInstanceId();
            HistoricProcessInstance hisInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(proInstanceId).singleResult();
            User actUser = identityService.createUserQuery().userId(hisInstance.getStartUserId()).singleResult();
            Process process = new Process();
            process.setUserName(actUser.getFirstName());
            process.setTask(task);
            process.setHistoricProcessInstance(hisInstance);
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(proInstanceId).singleResult();
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(instance.getProcessDefinitionId()).singleResult();

            process.setProcessDefinitionName(definition.getName());

            processes.add(process);
        }
        model.addAttribute("processes",processes);
        return "activiti/process/taskList";
    }
}
