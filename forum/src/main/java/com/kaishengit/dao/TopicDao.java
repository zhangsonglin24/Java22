package com.kaishengit.dao;

import com.kaishengit.entity.Topic;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class TopicDao {


    public Integer save(Topic topic) {
        String sql = "insert into t_topic (title,content,nodeid,userid) values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getNodeid(),topic.getUserid());
    }

    public Topic findTopicById(String topicId) {
        String sql = "select * from t_topic where id = ?";
        return  DbHelp.query(sql,new BeanHandler<>(Topic.class),topicId);
    }
}
