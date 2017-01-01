package com.kaishengit.service;


import com.google.common.collect.Maps;
import com.kaishengit.dao.*;
import com.kaishengit.entity.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class TopicService {

    private NodeDao nodeDao = new NodeDao();
    private TopicDao topicDao = new TopicDao();
    private UserDao userDao = new UserDao();
    private ReplyDao replyDao = new ReplyDao();
    private FavDao favDao = new FavDao();
    private NotifyDao notifyDao = new NotifyDao();
    private ThanksDao thanksDao = new ThanksDao();

    public List<Node> findAllNode() {
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
        if (node != null) {
            node.setTopicnum(node.getTopicnum() + 1);
            nodeDao.update(node);
        } else {
            throw new ServiceException("节点不存在");
        }

        return topic;

    }

    public Topic findTopicById(String topicid) {
        if (StringUtils.isNumeric(topicid)) {
            Topic topic = topicDao.findTopicById(topicid);
            if (topic != null) {
                //通过topic对象的userid、nodeid 获取user和node对象,并set到topic对象中;
                User user = userDao.findById(topic.getUserid());
                Node node = nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain") + user.getAvatar());
                topic.setUser(user);
                topic.setNode(node);

                return topic;
            } else {
                throw new ServiceException("该帖不存在或已被删除");
            }
        } else {
            throw new ServiceException("参数错误");
        }

    }

    public void addTopicReply(String content, String topicid, User user) {
        //添加新回复到t_reply表
        if (StringUtils.isNumeric(topicid)) {
            Reply reply = new Reply();
            reply.setContent(content);
            reply.setUserid(user.getId());
            reply.setTopicid(Integer.valueOf(topicid));

            replyDao.addReply(reply);
        } else {
            throw new ServiceException("数据异常或错误");
        }


        //更新t_topic表中的replynum 和 lastreplytime字段
        Topic topic = topicDao.findTopicById(topicid);
        if (topic != null) {
            topic.setReplynum(topic.getReplynum() + 1);
            topic.setLastreplytime(new Timestamp(DateTime.now().getMillis()));
            topicDao.update(topic);
        } else {
            throw new ServiceException("回复的主题不存在或被删除");
        }

        //新添加回复的通知
        if(!user.getId().equals(topic.getUserid())){
            Notify notify = new Notify();
            notify.setUserid(topic.getUserid());
            notify.setContent("您的主题 <a href=\"/topicDetail?topicid="+topic.getId()+"\">["+ topic.getTitle()+"]</a> 有了新的回复,请查看!");
            notify.setState(Notify.NOTIFY_UNREAD);
            notifyDao.save(notify);
        }

    }

    public List<Reply> findReplyListByTopicid(String topicid) {
        return replyDao.findReplyListByTopicid(topicid);
    }

    public void updateTopicById(String title, String content, String nodeid, String topicid) {
        Topic topic = topicDao.findTopicById(topicid);
        Integer lastNodeId = topic.getNodeid();
        if (topic.isEdit()) {
            topic.setTitle(title);
            topic.setContent(content);
            topic.setNodeid(Integer.valueOf(nodeid));
            topicDao.update(topic);
            updatNode(lastNodeId,nodeid);
        } else {
            throw new ServiceException("该帖子已经不可编辑");
        }
    }
    private void updatNode(Integer lastNodeId,String nodeid) {
        if (lastNodeId != Integer.valueOf(nodeid)) {
            //更新node表，使得原來的node的topicnum -1
            Node lastNode = nodeDao.findNodeById(lastNodeId);
            lastNode.setTopicnum(lastNode.getTopicnum() - 1);
            nodeDao.update(lastNode);
            //更新node表，使得新的node的topicnum + 1
            Node newNode = nodeDao.findNodeById(Integer.valueOf(nodeid));
            newNode.setTopicnum(newNode.getTopicnum() + 1);
            nodeDao.update(newNode);
        }
    }


    public Fav findFavByUseridAndTopicid(User user, String topicid) {
        return favDao.findFavByUseridAndTopicid(user.getId(),topicid);
    }

    public void favTopic(User user, String topicid) {
        Fav fav = new Fav();
        fav.setUserid(user.getId());
        fav.setTopicid(Integer.valueOf(topicid));
        favDao.addFav(fav);

        //topic表中收藏favnum  +1
        Topic topic = topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum() + 1);
        topicDao.update(topic);
    }

    public void unfavTopic(User user, String topicid) {
        favDao.deleteFav(user.getId(),topicid);

        //topic表中收藏favnum  -1
        Topic topic = topicDao.findTopicById(topicid);
        topic.setFavnum(topic.getFavnum() - 1);
        topicDao.update(topic);
    }

    public void updateTopic(Topic topic) {
        topicDao.update(topic);
    }

    public Page<Topic> findAllTopics(String nodeid, Integer pageNo) {
        HashMap<String,Object> map = Maps.newHashMap();

        //查询总topic数count
        int count = 0;
        if (StringUtils.isEmpty(nodeid)){
            count = topicDao.count();
        }else{
            count = topicDao.count(nodeid);
        }

        Page<Topic> topicPage = new Page<>(count,pageNo);
        map.put("nodeid",nodeid);
        map.put("start",topicPage.getStart());
        map.put("pageSize",topicPage.getPageSize());

        List<Topic> topicList = topicDao.findAll(map);
        topicPage.setItems(topicList);
        return topicPage;
    }

    public void thanksTopic(User user, String topicid) {
        Thanks thanks = new Thanks();
        thanks.setUserid(user.getId());
        thanks.setTopicid(Integer.valueOf(topicid));
        thanksDao.addThanks(thanks);

        //topic表中thanksnum + 1
        Topic topic = topicDao.findTopicById(topicid);
        topic.setThanksnum(topic.getThanksnum() + 1);
        topicDao.update(topic);
    }

    public void unThanksTopic(User user, String topicid) {
        thanksDao.deleteThanks(user.getId(),topicid);

        //topic表中thanksnum - 1
        Topic topic = topicDao.findTopicById(topicid);
        topic.setThanksnum(topic.getThanksnum() - 1);
        topicDao.update(topic);
    }

    public Thanks findThanksByUseridAndTopicid(User user, String topicid) {
        return thanksDao.findThanksByUseridAndTopicid(user.getId(),topicid);
    }

    public Node findNodeById(String nodeId) {
        if(StringUtils.isNumeric(nodeId)){
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            if(node != null){
                return node;
            }else{
                throw new ServiceException("此节点不存在");
            }
        }else{
            throw new ServiceException("参数异常");
        }
    }

    public void updateTopicNode(String topicid, String nodeid) {
        if(StringUtils.isNumeric(topicid)&& StringUtils.isNumeric(nodeid)){
            Topic topic = topicDao.findTopicById(topicid);
            //更新topic的nodeid
            topic.setNodeid(Integer.valueOf(nodeid));
            topicDao.update(topic);
            //更新node表中的topicnum
            updatNode(topic.getNodeid(),nodeid);
        }else{
            throw new ServiceException("参数异常");
        }
    }

    public Page<HomeCount> getTopicAndReplyNumByDayList(Integer pageNo) {
        int count = topicDao.countTopicByDay();
        Page<HomeCount> page = new Page<>(count,pageNo);

        List<HomeCount> countLit =  topicDao.getTopicAndReplyNumList(page.getStart(),page.getPageSize());
        page.setItems(countLit);
        return page;
    }


    public Page<Node> findAllNodes(Integer pageNo) {
        int count = findAllNode().size();
        Page<Node> page = new Page<>(count,pageNo);
        List<Node> nodeList = findAllNode(page.getStart(),page.getPageSize());
        page.setItems(nodeList);
        return page;
    }

    private List<Node> findAllNode(int start, int pageSize) {
        return nodeDao.findAllNodess(start,pageSize);
    }
}