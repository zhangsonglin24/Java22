package com.kaishengit.dao;

import com.kaishengit.entity.Thanks;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class ThanksDao {
    public void addThanks(Thanks thanks) {
        String sql = "insert into t_thanks (userid,topicid) values (?,?)";
        DbHelp.update(sql,thanks.getUserid(),thanks.getTopicid());
    }

    public void deleteThanks(Integer id, String topicid) {
        String sql = "delete from t_thanks where userid = ? and topicid = ?";
        DbHelp.update(sql,id,Integer.valueOf(topicid));
    }

    public Thanks findThanksByUseridAndTopicid(Integer id, String topicid) {
        String sql = "select * from t_thanks where userid = ? and topicid = ?";
        return DbHelp.query(sql,new BeanHandler<>(Thanks.class),id,topicid);
    }
}
