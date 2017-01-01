package com.kaishengit.dao;

import com.kaishengit.entity.Node;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class NodeDao {

    public List<Node> findAllNodes(){
        String sql = "select * from t_node";
        return DbHelp.query(sql,new BeanListHandler<>(Node.class));
    }

    public Node findNodeById(Integer nodeid) {
        String sql = "select * from t_node where id = ?";
        return DbHelp.query(sql,new BeanHandler<>(Node.class),nodeid);
    }

    public void update(Node node) {
        String sql = "update t_node set topicnum = ?,nodename = ? where id = ?";
        DbHelp.update(sql,node.getTopicnum(),node.getNodename(),node.getId());
    }

    public Node findNodeByNodeName(String nodename) {
        String sql = "select * from t_node where nodename = ?";
        return DbHelp.query(sql,new BeanHandler<>(Node.class),nodename);
    }

    public void delNodeById(String id) {
        String sql = "delete from t_node where id = ?";
        DbHelp.update(sql,id);
    }

    public List<Node> findAllNodess(int start, int pageSize) {
        String sql = "select * from t_node ORDER BY id ASC LIMIT ?,?";
        return DbHelp.query(sql,new BeanListHandler<>(Node.class),start,pageSize);
    }
}
