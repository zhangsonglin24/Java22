package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.StringUtils;

public class NodeService {

    private NodeDao nodeDao = new NodeDao();

    public String validateNodeName(String nodeid, String nodename) {
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
        if(node.getNodename().equals(nodename)){
            return "true";
        }else{
            Node nodeExist = nodeDao.findNodeByNodeName(nodename);
            if(nodeExist == null){
                return "true";
            }
        }
        return "false";
    }

    public void updateNode(String nodeId, String nodeName) {
        if(StringUtils.isNumeric(nodeId) && StringUtils.isNotEmpty(nodeName)){
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            node.setNodename(nodeName);
            nodeDao.update(node);
        }else{
            throw new ServiceException("参数异常");
        }
    }

    public void delNodeById(String id) {
        Node node = nodeDao.findNodeById(Integer.valueOf(id));
        if(node.getTopicnum() > 0){
            throw new ServiceException("该节点下有帖子，不能删除");
        }else{
            nodeDao.delNodeById(id);
        }
    }

    public String validateNewNodeName(String newNode) {
        Node nodeExist = nodeDao.findNodeByNodeName(newNode);
        if(nodeExist == null){
            return "true";
        }
        return "false";
    }

    public void addNewNode(String newNode) {
        nodeDao.addNewNode(newNode);
    }
}
