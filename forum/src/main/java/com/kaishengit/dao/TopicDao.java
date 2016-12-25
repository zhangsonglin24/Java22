package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class TopicDao {


    public Integer save(Topic topic) {
        String sql = "insert into t_topic (title,content,nodeid,userid,lastreplytime) values(?,?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getNodeid(),topic.getUserid(),topic.getLastreplytime());
    }

    public Topic findTopicById(String topicid) {
        String sql = "select * from t_topic where id = ?";
        return  DbHelp.query(sql,new BeanHandler<>(Topic.class),topicid);
    }

    public void update(Topic topic) {
        String sql ="update t_topic set title = ? ,content = ? ,clicknum = ?,favnum = ?,thanksnum = ?,replynum = ?,lastreplytime = ?, nodeid = ?,userid = ? where id = ?";
        DbHelp.update(sql,topic.getTitle(),topic.getContent(),topic.getClicknum(),topic.getFavnum(),topic.getThanksnum(),topic.getReplynum(),topic.getLastreplytime(),topic.getNodeid(),topic.getUserid(),topic.getId());
    }
}
