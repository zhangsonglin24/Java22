package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/users.json")
public class UserServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(1001,"Jack","北京");
        User user2 = new User(1003,"李斯","石家庄");
        User user3 = new User(1004,"Rose","UK");

        List<User> userLIst = Arrays.asList(user,user2,user3);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String json = new Gson().toJson(userLIst);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
