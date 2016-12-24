package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.List;

@WebServlet("/topicEdit")
public class TopicEditServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        TopicService service = new TopicService();
        Topic topic = service.findTopicById(topicid);

        List<Node> nodeList = service.findAllNode();
        req.setAttribute("nodeList",nodeList);
        req.setAttribute("topic",topic);
        forward("topic/topicEdit",req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid = req.getParameter("nodeid");
        String topicid = req.getParameter("topicid");

        JsonResult result = null;
        if(StringUtils.isNumeric(topicid)){
            TopicService service = new TopicService();
            try {
                service.updateTopicById(title, content, nodeid, topicid);
                result = new JsonResult();
                result.setState("success");
                result.setData(topicid);
            }catch (ServiceException e){
                result = new JsonResult(e.getMessage());
            }
        }else{
            result = new JsonResult("参数异常");
        }
        renderJSON(result,resp);

    }
}
