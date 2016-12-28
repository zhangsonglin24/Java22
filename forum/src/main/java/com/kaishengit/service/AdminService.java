package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminService {
    Logger logger = LoggerFactory.getLogger(AdminService.class);
    private AdminDao adminDao = new AdminDao();
    private ReplyDao replyDao = new ReplyDao();
    private TopicDao topicDao = new TopicDao();
    private NodeDao nodeDao = new NodeDao();

    public Admin login(String adminName, String password, String ip) {
        Admin admin = adminDao.findAdminByAdminName(adminName);
        if(admin != null && admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))){
            logger.info("管理员{}登录了管理系统IP为{}",adminName,ip);
            return admin;
        }else{
            throw new ServiceException("账号或密码错误");
        }
    }

    public void deleteTopicById(String id) {
        //删除帖子的回复
        replyDao.delReplyByTopicId(id);
        //更新Node下的帖子数量
           //1.由topicid --> nodeid
        Topic topic = topicDao.findTopicById(id);
        if(topic != null){

            //2.由nodeid --> node
            Node node = nodeDao.findNodeById(topic.getNodeid());
            //3.更新Node
            node.setTopicnum(node.getTopicnum() - 1);
            nodeDao.update(node);
            //删除帖子
            topicDao.delTopicById(id);
        }else{
            throw new ServiceException("该贴不存在或已被删除");
        }

    }
}
