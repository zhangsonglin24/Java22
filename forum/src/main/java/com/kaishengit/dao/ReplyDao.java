package com.kaishengit.dao;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReplyDao {

    public void addReply(Reply reply) {
        String sql = "insert into t_reply (content,userid,topicid) values (?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());
    }

    public List<Reply> findReplyListByTopicid(String topicId) {
        String sql = "select tu.id,tu.avatar,tu.username,tr.* from t_reply tr ,t_user tu where tr.userid = tu.id and topicid = ?";
        return DbHelp.query(sql, new AbstractListHandler<Reply>() {
            @Override
            protected Reply handleRow(ResultSet rs) throws SQLException {
                Reply reply = new BasicRowProcessor().toBean(rs,Reply.class);
                User user = new User();
                user.setAvatar(Config.get("qiniu.domain") + rs.getString("avatar"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                reply.setUser(user);
                return reply;
            }
        },topicId);
    }
}
