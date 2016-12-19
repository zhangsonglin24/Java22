package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseServlet extends HttpServlet {

    public void forward(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp").forward(req,resp);
    }

    public void renderText(String str,HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(str);
        writer.flush();
        writer.close();
    }

    public void renderJson(Object obj,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(obj));
        writer.flush();
        writer.close();
    }

    public User getCurrentUser(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("curr_user");
        if(user == null){
            return null;
        }else {
            return user;
        }
    }
}
