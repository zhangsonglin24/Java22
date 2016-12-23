package com.kaishengit.web.topic;

import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newReply")
public class NewReplyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");
        String topicid = req.getParameter("topicid");
        User user = getCurrentUser(req);

        TopicService service = new TopicService();
        try {
            service.addTopicReply(content, topicid, user);
        }catch (ServiceException e){
            e.printStackTrace();
        }
        resp.sendRedirect("/topicDetail?topicid="+topicid);
    }
}
