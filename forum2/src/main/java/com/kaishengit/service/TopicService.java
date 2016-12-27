package com.kaishengit.service;

import com.kaishengit.dao.TopicDao;
import com.kaishengit.entity.Node;

import java.util.List;

public class TopicService {

    private TopicDao topicDao = new TopicDao();

    public List<Node> findAllNode() {
        topicDao.findAllNode();

        return null;
    }
}
