package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
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

    TopicService service = new TopicService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有节点nodeList,传到前端页面newTopic.jsp
        List<Node> nodeList = service.findAllNode();
        req.setAttribute("nodeLise",nodeList);
        forward("topic/newTopic",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nodeid =req.getParameter("nodeid");
        User user = getCurrentUser(req);

        Topic topic = service.addNewTopic(title,content,Integer.valueOf(nodeid),user.getId());

        JsonResult result = new JsonResult(topic);
        renderJSON(result,resp);


    }
}
