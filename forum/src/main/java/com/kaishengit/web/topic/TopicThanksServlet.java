package com.kaishengit.web.topic;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topicThanks")
public class TopicThanksServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        String action = req.getParameter("action");
        User user = getCurrentUser(req);

        TopicService topicService = new TopicService();
        JsonResult jsonResult = new JsonResult();

        if(StringUtils.isNotEmpty(action) && StringUtils.isNumeric(topicid)){
            if(action.equals("thanks")){
                topicService.thanksTopic(user,topicid);
                jsonResult.setState("success");
            }else if(action.equals("unthanks")){
                topicService.unThanksTopic(user,topicid);
                jsonResult.setState("success");
            }

            Topic topic = topicService.findTopicById(topicid);
            jsonResult.setData(topic.getThanksnum());

        }else{
            jsonResult.setMessage("参数异常");
        }

        renderJSON(jsonResult,resp);
    }
}
