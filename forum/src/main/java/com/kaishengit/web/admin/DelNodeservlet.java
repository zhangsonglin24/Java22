package com.kaishengit.web.admin;

import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.NodeService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delNode")
public class DelNodeservlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        NodeService nodeService = new NodeService();
        JsonResult jsonResult = new JsonResult();
        try{
            nodeService.delNodeById(id);
            jsonResult.setState("success");
        }catch (ServiceException e){
            jsonResult.setMessage(e.getMessage());
        }
        renderJSON(jsonResult,resp);
    }
}
