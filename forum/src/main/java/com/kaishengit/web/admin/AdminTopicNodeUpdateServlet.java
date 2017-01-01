package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/topicNodeUpdate")
public class AdminTopicNodeUpdateServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("id");
        String nodeid = req.getParameter("nodeid");

        TopicService topicService = new TopicService();
        JsonResult result = new JsonResult();
        try {
            topicService.updateTopicNode(topicid, nodeid);
            result.setState("success");
        }catch (ServiceException e){
            result = new JsonResult("参数异常");

        }
        renderJSON(result,resp);
    }
}
