package com.kaishengit.web.topic;

import com.kaishengit.entity.Node;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/newTopic")
public class NewTopicServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将所有节点给到newTopic.jsp
        TopicService topicService = new TopicService();
        List<Node> nodeList = topicService.findAllNode();

        req.setAttribute("nodeList",nodeList);
        forward("topic/newTopic",req,resp);
    }
}
