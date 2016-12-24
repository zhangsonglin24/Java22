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
@WebServlet("/topicFav")
public class TopicFavServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String topicid = req.getParameter("topicid");
        User user = getCurrentUser(req);

        TopicService service = new TopicService();
        JsonResult result = new JsonResult();

        if(StringUtils.isNotEmpty(action) && StringUtils.isNumeric(topicid)){
            if(action.equals("fav")){
                service.favTopic(user,topicid);
                result.setState("success");
            }else if(action.equals("unfav")){
                service.unfavTopic(user,topicid);
                result.setState("success");
            }

            Topic topic = service.findTopicById(topicid);
            result.setData(topic.getFavnum());

        }else{
            result.setMessage("参数异常");
        }
        renderJSON(result,resp);
    }
}
