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
import org.joda.time.DateTime;

import java.sql.Timestamp;
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
        //暂时设置最后回复时间为当前时间
        topic.setLastreplytime(new Timestamp(new DateTime().getMillis()));

        Integer topicId = topicDao.save(topic);
        topic.setId(topicId);

       //更新Node表中的topicnum
        Node node = nodeDao.findNodeById(nodeid);
        if (node != null){
        node.setTopicnum(node.getTopicnum() + 1);
        nodeDao.update(node);
        } else{
            throw new ServiceException("节点不存在");
        }

        return topic;

    }

    public Topic findTopicById(String topicid) {
        if (StringUtils.isNumeric(topicid)){
            Topic topic = topicDao.findTopicById(topicid);
            if(topic != null ){
                //通过topic对象的userid、nodeid 获取user和node对象,并set到topic对象中;
                User user = userDao.findById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);
                topic.setClicknum(topic.getClicknum() + 1);
                topicDao.update(topic);
                return topic;
            }else{
                throw new ServiceException("该帖不存在或已被删除");
            }
        }else{
            throw  new ServiceException("参数错误");
        }

    }

    public void addTopicReply(String content, String topicid, User user) {
        //添加新回复到t_reply表
        if(StringUtils.isNumeric(topicid)){
            Reply reply = new Reply();
            reply.setContent(content);
            reply.setUserid(user.getId());
            reply.setTopicid(Integer.valueOf(topicid));

            replyDao.addReply(reply);
        }else{
            throw new ServiceException("数据异常或错误");
        }


        //更新t_topic表中的replynum 和 lastreplytime字段
        Topic topic = topicDao.findTopicById(topicid);
        if (topic != null){
            topic.setReplynum(topic.getReplynum() + 1);
            topic.setLastreplytime(new Timestamp(DateTime.now().getMillis()));
            topicDao.update(topic);
        }else{
            throw new ServiceException("回复的主题不存在或被删除");
        }
    }

    public List<Reply> findReplyListByTopicid(String topicid) {
        return replyDao.findReplyListByTopicid(topicid);
    }

    public void updateTopicById(String title, String content, String nodeid, String topicid) {
        Topic topic = topicDao.findTopicById(topicid);
        if(topic.isEdit()){
            topic.setTitle(title);
            topic.setContent(content);
            topic.setNodeid(Integer.valueOf(nodeid));
            topicDao.update(topic);
        }else{
            throw new ServiceException("该帖子已经不可编辑");
        }
    }
}
