package com.kaishengit.web.admin;

import com.kaishengit.entity.Node;
import com.kaishengit.service.TopicService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/node")
public class AdminNodeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* List<Node> nodeList = new TopicService().findAllNode();
        req.setAttribute("nodeList",nodeList);*/

        String p = req.getParameter("p");
        Integer pageNo = StringUtils.isNumeric(p)?Integer.valueOf(p):1;
        Page<Node> page = (Page<Node>) new TopicService().findAllNodes(pageNo);
        req.setAttribute("page",page);
        forward("admin/node",req,resp);
    }
}
