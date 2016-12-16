package com.kaishengit.web;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseServlet extends HttpServlet {

    public void forward(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/"+path + ".jsp").forward(req,resp);
    }

    public void renderText(String str,HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.flush();
        writer.close();

    }
    public void renderJSON(Object obj,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(obj));
        writer.flush();
        writer.close();
    }

}
