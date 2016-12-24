package com.kaishengit.web.topic;

import com.kaishengit.entity.Fav;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
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
import java.util.List;

@WebServlet("/topicDetail")
public class TopicDetailServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topicid = req.getParameter("topicid");
        TopicService service = new TopicService();
        try {
            Topic topic = service.findTopicById(topicid);


            topic.setClicknum(topic.getClicknum() + 1);
            service.updateTopic(topic);

            //获取topicid对应帖子的回复列表
            List<Reply> replyList = service.findReplyListByTopicid(topicid);
            req.setAttribute("replyList",replyList);
            req.setAttribute("topic", topic);

            //判断用户是否收藏过该主题
            User user = getCurrentUser(req);
            if(user != null && StringUtils.isNumeric(topicid)){
                Fav fav = service.findFavByUseridAndTopicid(user,topicid);
                req.setAttribute("fav",fav);
            }

            forward("topic/topicDetail", req, resp);
        }catch (ServiceException ex){
            resp.sendError(404);
        }

    }
}
