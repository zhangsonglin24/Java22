package com.kaishengit.web.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/newNodeValidate")
public class NewNodeValidateServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newNode = req.getParameter("newNode");
        newNode = StringUtils.isoToUtf8(newNode);
        NodeService nodeService = new NodeService();
        String result = nodeService.validateNewNodeName(newNode);
        renderText(result,resp);
    }
}
