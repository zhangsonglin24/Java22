package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.entity.User;
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

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) {
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");

        User user = getCurrentUser(req);
        UserService userService = new UserService();
        userService.updatePassword(user,oldpassword,newpassword);
    }

    private void updateSetEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        User user = getCurrentUser(req);

        UserService userService = new UserService();
        userService.updateEmail(user,email);

        Map<String,Object> result = Maps.newHashMap();
        result.put("state","success");
        renderJSON(result,resp);

    }
}
