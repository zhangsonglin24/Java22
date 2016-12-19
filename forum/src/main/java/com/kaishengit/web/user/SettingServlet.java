package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
import com.qiniu.util.Auth;

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
        //获得七牛的token
        String bucketName = Config.get("qiniu.bucketName");
        Auth auth = Auth.create(Config.get("qiniu.ak"),Config.get("qiniu.sk"));
        String token = auth.uploadToken(bucketName);
        req.setAttribute("token",token);
        forward("user/setting",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("setEmail".equals(action)){
            updateSetEmail(req,resp);
        }else if("password".equals(action)){
            updatePassword(req,resp);
        }else if("avatar".equals(action)){
            updateAvatar(req,resp);
        }
    }

    private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileKey = req.getParameter("fileKey");
        User user = getCurrentUser(req);

        UserService userService = new UserService();
        userService.updateAvatar(user,fileKey);

        JsonResult result = new JsonResult();
        result.setState("success");
        renderJSON(result,resp);
    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");

        User user = getCurrentUser(req);
        UserService userService = new UserService();
        try {
            userService.updatePassword(user, oldpassword, newpassword);

            JsonResult result = new JsonResult();
            result.setState("success");
            renderJSON(result,resp);
        }catch (ServiceException ex){
            JsonResult result = new JsonResult(ex.getMessage());
            renderJSON(result,resp);

        }
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
