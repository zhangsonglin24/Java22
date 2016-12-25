package com.kaishengit.web;

import com.kaishengit.entity.Node;
import com.kaishengit.service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicService service = new TopicService();
        List<Node> nodeList = service.findAllNode();

        req.setAttribute("nodeList",nodeList);
        forward("index",req,resp);
    }
}
