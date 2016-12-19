package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/setting")
public class SettingServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("user/setting",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("setEmail".equals(action)){
            updateSetEmail(req,resp);
        }else if("password".equals(action)){
            updatePassword(req,resp);
        }
    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String newPassword = req.getParameter("newpassword");
        String oldPassword = req.getParameter("oldpassword");

        User user = getCurrentUser(req);
        UserService userService = new UserService();
        try {
            userService.updatePassword(user, newPassword, oldPassword);
            JsonResult result = new JsonResult();
            result.setState("success");
            renderJson(result,resp);
        }catch (ServiceException ex){
            JsonResult result = new JsonResult(ex.getMessage());
            renderJson(result,resp);

        }
    }

    private void updateSetEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        User user = getCurrentUser(req);

        UserService userService = new UserService();
        userService.updateSetEmail(user,email);

        Map<String,Object> result = Maps.newHashMap();
        result.put("state","success");
        renderJson(result,resp);
    }
}
