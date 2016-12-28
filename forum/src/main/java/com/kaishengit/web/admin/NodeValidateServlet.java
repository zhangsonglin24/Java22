package com.kaishengit.web.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/nodeValidate")
public class NodeValidateServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nodeid = req.getParameter("nodeid");
        String nodename = req.getParameter("nodename");

        nodename = StringUtils.isoToUtf8(nodename);
        NodeService nodeService = new NodeService();
        Boolean result = nodeService.validateNodeName(nodeid,nodename);
        renderText("result",resp);
    }

}
