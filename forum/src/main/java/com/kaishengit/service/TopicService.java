package com.kaishengit.service;


import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.StringUtils;

import java.util.List;

public class TopicService {

    NodeDao nodeDao = new NodeDao();
    TopicDao topicDao = new TopicDao();
    UserDao userDao = new UserDao();
    ReplyDao replyDao = new ReplyDao();

    public List<Node> findAllNode(){
        return nodeDao.findAllNodes();
    }

    public Topic addNewTopic(String title, String content, Integer nodeid, Integer userId) {
        //封装topic对象
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNodeid(nodeid);
        topic.setUserid(userId);

        Integer topicId = topicDao.save(topic);

        topic.setId(topicId);
        return topic;

    }

    public Topic findTopicById(String topicId) {
        if (StringUtils.isNumeric(topicId)){
            Topic topic = topicDao.findTopicById(topicId);
            if(topic != null ){
                //通过topic对象的userid、nodeid 获取user和node对象,并set到topic对象中;
                User user = userDao.findById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);
                return topic;
            }else{
                throw new ServiceException("该帖不存在或已被删除");
            }
        }else{
            throw  new ServiceException("参数错误");
        }
    }

    public void addTopicReply(String content, String topicid, User user) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setUserid(user.getId());
        reply.setTopicid(Integer.valueOf(topicid));

        replyDao.addReply(reply);
    }
}
