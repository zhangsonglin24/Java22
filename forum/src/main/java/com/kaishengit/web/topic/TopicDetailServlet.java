package com.kaishengit.web.topic;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TopicService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        TopicService service = new TopicService();
        try {
            Topic topic = service.findTopicById(topicid);
            //获取topicid对应帖子的回复列表
            List<Reply> replyList = service.findReplyListByTopicid(topicid);
            req.setAttribute("replyList",replyList);

            req.setAttribute("topic", topic);
            forward("topic/topicDetail", req, resp);
        }catch (ServiceException ex){
            resp.sendError(404);
        }

    }
}
