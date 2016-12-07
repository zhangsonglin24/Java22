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

@WebServlet("/data.json")
public class JsonDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

       // String json = "{\"id\":1,\"name\":\"rose\"}";

        User user = new User(1,"tom","北京");
        User user2 = new User(2,"jack","上海");
        User user3 = new User(3,"rose","广州");

        List<User> userList = Arrays.asList(user,user2,user3);

        String json = new Gson().toJson(userList);


        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
